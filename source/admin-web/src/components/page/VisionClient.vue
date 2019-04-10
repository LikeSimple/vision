<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i>用户
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="params.name" class="handle-input mr10" placeholder="关键字搜索"></el-input>
        <!-- 身份证号：
        <el-input v-model="idNumber" class="handle-input mr10"></el-input>学校：
        <el-input v-model="schoolNameCriteria" class="handle-input mr10"></el-input> -->
        <el-button type="primary" icon="search"  @click="search">搜索</el-button>
        <el-button type="primary" class="handle-add" @click="editVisible = true">新增</el-button>
      </div>
      <el-table
        :data="tableData"
        border
        class="table"
        ref="multipleTable"
        @selection-change="handleSelectionChange">
        <el-table-column prop="name" label="姓名" align="center"></el-table-column>
        <el-table-column  label="性别" align="center">
           <template slot-scope="scope">
              <span>{{scope.row.gender ? '男':'女'}}</span>
            </template>
        </el-table-column>
        <el-table-column label="角色" align="center">
           <template slot-scope="scope">
                <span>{{scope.row.profileName}}</span>
                <el-button type="text" icon="el-icon-edit" @click="editRole(scope.row)">编辑</el-button>
            </template>
        </el-table-column>
        <el-table-column label="操作" width="220" align="center">
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-setting" @click="handleResetPsw(scope.row)">重置密码</el-button>
                <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="total"
        ></el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="100px" class="user-form"> 
        <div v-if="!userId">
           <el-form-item label="登录名">
               <el-input v-model="loginName"></el-input>
           </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="password"></el-input>
            </el-form-item>
        </div>
        <el-form-item label="姓名">
           <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
         <el-radio v-model="form.gender" label="0" >女</el-radio>
         <el-radio v-model="form.gender" label="1" >男</el-radio>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>
     <el-dialog title="重置密码" :visible.sync="passwordVisible" width="30%">
          <el-form ref="form" :model="formPassword" label-width="100px"> 
            <!-- <el-form-item label="旧密码">
              <el-input v-model="formPassword.password"></el-input>
            </el-form-item> -->
            <el-form-item label="新密码">
              <el-input v-model="formPassword.repassword"></el-input>
            </el-form-item>
         </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="passwordVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveEditPassword">确 定</el-button>
        </span>
      </el-dialog>
    <el-dialog title="编辑角色" :visible.sync="roleVisible" width="50%" class="role-row">
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
  getUserList,
  getProfile,
  createUser,
  updateUser,
  deleteUser,
  findUserRoleList,
  resetPassword
} from "../../api/user.js";
export default {
  name: "basetable",
  data() {
    return {
      url: "./vuetable.json",
      cur_page: 1,
      multipleSelection: [],
      select_cate: "",
      select_word: "",
      clientNameCriteria: "",
      idNumber: "",
      schoolNameCriteria: "",
      del_list: [],
      is_search: false,
      editVisible: false,
      roleVisible:false,
      passwordVisible:false,
      sysList:[],
      checkedSys:[],
      delVisible: false,
      form: {
        name: '',
        gender: '1',
        avatar: ''
      },
      loginName:'',
      password:'',
      idx: -1,
      tableData:[],
      total:0,
      userId:'',
      params:{
        name: '',
        pageNum: 1,
        pageSize: 20
      },
      formPassword:{
        password:'',
        repassword:''
      }
    };
  },
  created() {
    this.getUserData(this.params)
  },
  methods: {
    getUserData(params){
        getUserList(params).then(res=>{
          this.tableData = res.data.list || []
          this.total = res.data.total
       })
    },
    editRole(row){
      this.roleVisible = true
      findUserRoleList(row.userId).then(res=>{
        this.sysList = res.data
      })
    },
    // 分页导航
    handleCurrentChange(val) {
      this.params.pageNum = val;
      this.getUserData(this.params)
    },
    search() {
      this.getUserData(this.params)
      this.is_search = true;
    },
    handleResetPsw(row){
      // 重置密码
      this.userId = row.userId
      this.passwordVisible = true
    },
    saveEditPassword(){

    },
    handleEdit(row) {
      this.userId = row.userId
      this.form = {
        name: row.name,
        gender: row.gender.toString(),
        avatar: row.avatar,
      }
      console.log(this.form)
      this.editVisible = true;
    },
    handleDelete(index, row) {
      this.idx = index;
      this.userId = row.userId
      this.delVisible = true;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 保存编辑
    saveEdit() {
          let params = this.form
      if (this.userId) {
          params.sysUserId = this.userId
          updateUser(this.userId,params).then(res=>{
            if (res.errorCode===0){
                his.getUserData(this.params)
                this.$message.success(`修改第 ${this.idx+1} 行成功`);
            }
            else {
                this.$message.warning('编辑失败');
            }
            this.editVisible = false;
        })
        } 
        else {
            createUser(params).then(res=>{
            if (res.errorCode===0){
                this.getUserData(this.params)
                this.$message.success(`新增成功`);
            }
            else {
                this.$message.warning('新增失败');
            }
            this.editVisible = false;
        })
        }   
    },
    // 确定删除
    deleteRow() {
      this.tableData.splice(this.idx, 1);
      deleteUser(this.userId).then(res=>{
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
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 200px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}

.table-expand {
  font-size: 0;
}
.table-expand label {
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 30%;
  color:coral;
}
</style>
