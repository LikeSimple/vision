<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i>权限</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="formData.roleName" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable">
                <el-table-column prop="name" label="姓名" width="150">
                </el-table-column>
                <el-table-column prop="authority" label="权限" width="120">
                </el-table-column>
                <el-table-column prop="createdTime" label="创建时间">
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="total">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import { getSysList } from "../../api/sysRole.js";
    export default {
        name: 'basetable',
        data() {
            return {
                url: './vuetable.json',
                tableData: [],
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                is_search: false,
                formData: {
                   roleName: '',
                   pageNum: 1,
                   pageSize: 20
                },
                total: 0
            }
        },
        created() {
           this.getList(this.formData)
        },
        methods: {
            getList(params){
              getSysList(params).then(res=>{
                this.tableData = res
                this.total = this.tableData.length
              })
            },
            // 分页导航
            handleCurrentChange(val) {
                this.formData.pageNum = val;
                this.getList(this.formData)
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
                this.getList(this.formData)
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