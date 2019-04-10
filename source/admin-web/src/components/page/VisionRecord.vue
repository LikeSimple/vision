<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i>权限</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                姓名：<el-input v-model="query_name"  class="handle-input"></el-input>
                身份证号：<el-input v-model="query_id_number"  class="handle-input"></el-input>
                学校：<el-input v-model="query_school_name"  class="handle-input"></el-input>
                班级：<el-input v-model="query_class_name"  class="handle-input"></el-input>
                活动：<el-input v-model="query_activity_name"  class="handle-input"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="data" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column prop="schoolName" label="学校" width="180" align="center"></el-table-column>
                <el-table-column prop="className" label="班级" width="120" align="center"></el-table-column>
                <el-table-column prop="name" label="姓名" width="120" align="center"></el-table-column>
                <el-table-column prop="idNumber" label="身份证号" width="200" align="center"></el-table-column>
                <el-table-column prop="eyeType" label="左右眼" :formatter="eyeTypeFormatter" width="100" align="center"></el-table-column>
                <el-table-column prop="pupil" label="瞳孔大小" width="50" align="center"></el-table-column>
                <el-table-column prop="pd" label="瞳距" width="80" align="center"></el-table-column>
                <el-table-column prop="activityName" label="活动" width="80" align="center"></el-table-column>
                <el-table-column prop="checkDate" label="检测日期" width="200" align="center"></el-table-column>
                
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <el-form-item label="日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
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
    import { getRecordList } from "../../api/record.js";
    export default {
        name: 'basetable',
        data() {
            return {
                url: './vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
                query_name: "", 
                query_id_number: "", 
                query_school_name: "", 
                query_class_name: "", 
                query_activity_name: "",
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1
            }
        },
        created() {
            this.getData();
        },
        computed: {
            data() {
                return this.tableData;
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData() {
                getRecordList(this.query_name, this.query_id_number, this.query_school_name, 
                    this.query_class_name, this.query_activity_name, this.cur_page, 20).then((res) => {
                    this.tableData = res.data;
                })
            },
            search() {
                this.getData();
                this.is_search = true;
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                this.form = {
                    name: item.name,
                    date: item.date,
                    address: item.address
                }
                this.editVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存编辑
            saveEdit() {
                this.$set(this.tableData, this.idx, this.form);
                this.editVisible = false;
                this.$message.success(`修改第 ${this.idx+1} 行成功`);
            },
            // 确定删除
            deleteRow(){
                this.tableData.splice(this.idx, 1);
                this.$message.success('删除成功');
                this.delVisible = false;
            },
            eyeTypeFormatter(row, column) {
                var value = "未知";
                if ("OD" == row.eysType){
                    value = "左眼";
                } else if ("OD" == row.eysType){
                    value = "右眼";
                }
                return value;
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
