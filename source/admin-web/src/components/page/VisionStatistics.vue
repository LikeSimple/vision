<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i>角色</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" class="handle-add" @click="addRole">新增</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable">
                <el-table-column prop="name" align="center" label="姓名" width="120">
                </el-table-column>
                <el-table-column prop="description" align="center" label="描述">
                </el-table-column>
                <el-table-column prop="createdTime" align="center" label="日期" width="100">
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="total">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="roleVisible" width="50%">
            <el-input v-model="roleName" placeholder="角色名称" class="handle-input handle-role-name mr10"></el-input>
            <el-input type="textarea" v-model="desc" class="handle-input handle-role-name mr10" placeholder="描述"></el-input>
             <el-checkbox-group v-model="checkedSys">
               <el-checkbox v-for="sys in sysList" :label="sys.name" :key="sys.name">{{sys.name}}</el-checkbox>
            </el-checkbox-group>
            <span slot="footer" class="dialog-footer">
                <el-button @click="roleVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import { 
        getRoleList,
        getSysList,
        createSysRole,
        updateRole,
        deleteRole
    } from "../../api/sysRole.js";
    export default {
        name: 'basetable',
        data() {
            return {
                url: './vuetable.json',
                tableData: [],
                roleName:'',
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                roleVisible: false,
                delVisible: false,
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1,
                roleId:'',
                desc:'',
                sysList:[],
                checkedSys:[],
                total:0,
                params:{
                    roleName: '',
                    pageNum: 1,
                    pageSize: 20
                }
            }
        },
        created() {
            this.getList(this.params)
            // this.getData();
             getSysList({}).then(res=>{
                 console.log(res)
                this.sysList = res
            })
        },
        methods: {
            addRole(){
               this.roleVisible = true
            },
            getList(params){
                getRoleList(params).then(res=>{
                    this.tableData = res.data.list || []
                    this.total = res.data.total
               })
            },
            // 分页导航
            handleCurrentChange(val) {
                this.params.pageNum = val;
                this.getList(this.params);
            },
            // 获取 easy-mock 的模拟数据
            getData() {
                // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
                if (process.env.NODE_ENV === 'development') {
                    this.url = '/ms/table/list';
                };
                this.$axios.post(this.url, {
                    page: this.cur_page
                }).then((res) => {
                    this.tableData = res.data.list;
                })
            },
            search() {
                this.is_search = true;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.roleName = row.name
                this.desc = row.description
                this.idx = index;
                this.roleId = row.id
                this.roleVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.roleId = row.id
                this.delVisible = true;               
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存编辑
            saveEdit() {
                let params = {
                    roleName:this.roleName,
                    description:this.desc,
                    systemAuthorityList:this.checkedSys
                }
                if (this.roleId) {
                  params.id = this.roleId
                  updateRole(this.roleId,params).then(res=>{
                    if (res.errorCode===0){
                       this.getList(this.params)
                       this.$message.success(`修改第 ${this.idx+1} 行成功`);
                    }
                    else {
                       this.$message.warning('编辑失败');
                   }
                   this.roleVisible = false;
                })
               } 
               else {
                   createSysRole(params).then(res=>{
                    if (res.errorCode===0){
                       this.getList(this.params)
                       this.$message.success(`新增成功`);
                    }
                    else {
                       this.$message.warning('新增失败');
                   }
                   this.roleVisible = false;
                })
               }   
            },
            // 确定删除
            deleteRow(){
                this.tableData.splice(this.idx, 1);
                deleteRole(this.roleId).then(res=>{
                   if (res.errorCode === 0){
                    this.$message.success('删除成功');
                   }
                   else {
                    this.$message.warning('删除失败');
                   }
                   this.delVisible = false;
                }) 
            }
        }
    }

</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
    .mr10{
        margin-right: 10px;
    }
</style>
