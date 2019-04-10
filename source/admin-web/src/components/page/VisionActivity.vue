<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i>活动
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="activityCriteria.nameCriteria" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="search">搜索</el-button>
        <el-button type="primary" icon="search" @click="create">创建</el-button>
      </div>
      <el-table
        :data="data"
        border
        class="table"
        ref="multipleTable"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="name" label="活动名称" width="200" align="center"></el-table-column>
        <el-table-column prop="beginDate" label="活动开始日期" width="150" align="center"></el-table-column>
        <el-table-column prop="endDate" label="活动结束日期" width="150" align="center"></el-table-column>
        <el-table-column prop="content" label="活动内容" width="400"></el-table-column>
        <el-table-column prop="address" label="活动地址" width="250"></el-table-column>
        <el-table-column prop="contactMan" label="联系人" width="80" align="center"></el-table-column>
        <el-table-column prop="contactPhoneNumber" label="联系电话" width="80" align="center"></el-table-column>
        <el-table-column prop="remark" label="备注" width="200"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
            >归档</el-button>
            <el-button
              type="text"
              icon="el-icon-upload"
              @click="handleUpload(scope.$index, scope.row)"
            >上传</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="1000"
        ></el-pagination>
      </div>
    </div>

    <!-- 上传弹出框 -->
    <el-dialog title="上传" :visible.sync="uploadVisible" width="50%">
      <el-input value="currentActivityId" type="hidden"></el-input>
      <el-upload class="upload-demo" drag v-bind:action="'/api/activity/' + this.currentItem.id + '/client/upload'"
      v-bind:headers="tokenHeader"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">只能上传xls、xlsx文件，且不超过2M</div>
      </el-upload>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="60%">
      <el-form ref="form" :model="form">
        <el-input type="hidden" v-model="form.id" style="width: 50%;"></el-input>
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-row>
          <el-form-item label="活动时间">
            <el-date-picker
              v-model="form.beginDate"
              type="date"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="开始日期"
            ></el-date-picker>
            <el-date-picker
              v-model="form.endDate"
              type="date"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="活动内容">
            <el-input type="textarea" v-model="form.content"></el-input>
          </el-form-item>
        </el-row>
        <el-form-item label="活动地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="form.contactMan"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="form.contactPhoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 创建弹出框 -->
    <el-dialog title="创建" :visible.sync="createVisible" width="60%">
      <el-form ref="form" :model="form">
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-row>
          <el-form-item label="活动时间">
            <el-date-picker
              v-model="form.beginDate"
              type="date"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="开始日期"
            ></el-date-picker>
            <el-date-picker
              v-model="form.endDate"
              type="date"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="活动内容">
            <el-input type="textarea" v-model="form.content"></el-input>
          </el-form-item>
        </el-row>
        <el-form-item label="活动地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="form.contactMan"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="form.contactPhoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="createVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCreate">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 归档提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">归档不可恢复，是否确定？</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getActivityList,
  createActivity,
  editActivity,
  deleteActivity
} from "../../api/activity.js";
import {
  getToken
} from "../../util/token.js";
export default {
  name: "activity",
  data() {
    return {
      url: "./vuetable.json",
      tableData: [],
      cur_page: 1,
      multipleSelection: [],
      select_cate: "",
      activityCriteria: {
        nameCriteria: '',
        beginDate: '',
        endDate: '',
        archived: false
      },
      del_list: [],
      is_search: false,
      editVisible: false,
      createVisible: false,
      delVisible: false,
      uploadVisible: false,
      form: {
        id: "",
        name: "",
        date: "",
        address: "",
        beginDate: "",
        endDate: "",
        content: "",
        contactMan: "",
        contactPhoneNumber: "",
        remark: ""
      },
      idx: -1,
      currentItem: {}
    };
  },
  created() {
    this.getData(this.activityCriteria);
  },
  computed: {
    data() {
      return this.tableData;
    },
    tokenHeader() {
      return { Authorization: 'Bearer ' + getToken() }
    }
  },
  methods: {
    // 分页导航
    handleCurrentChange(val) {
      this.cur_page = val;
      this.getData("");
    },
    getData(critiera) {
      getActivityList(critiera, this.cur_page, 20).then(res => {
        this.tableData = res.data;
      });
    },
    search() {
      this.is_search = true;
      this.getData(this.activityCriteria);
    },
    create() {
      this.form = {
        id: null,
        name: null,
        beginDate: null,
        endDate: null,
        address: null,
        content: null,
        contactMan: null,
        contactPhoneNumber: null,
        remark: null
      };
      this.createVisible = true;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    handleEdit(index, row) {
      this.idx = index;
      const item = this.tableData[index];
      this.form = {
        id: item.id,
        name: item.name,
        beginDate: item.beginDate,
        endDate: item.endDate,
        address: item.address,
        content: item.content,
        contactMan: item.contactMan,
        contactPhoneNumber: item.contactPhoneNumber,
        remark: item.remark
      };
      this.editVisible = true;
    },
    handleDelete(index, row) {
      this.idx = index;
      this.delVisible = true;
    },
    handleUpload(index, row) {
      this.idx = index;
      this.currentItem = this.tableData[this.idx];
      this.uploadVisible = true;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 保存编辑
    saveEdit() {
      editActivity(this.form);
      this.$set(this.tableData, this.idx, this.form);
      this.editVisible = false;
      this.$message.success(`修改第 ${this.idx + 1} 行成功`);
    },
    // 保存新增
    saveCreate() {
      createActivity(this.form);
      this.createVisible = false;
      this.$message.success(`创建成功`);
      this.search();
    },
    // 确定删除
    deleteRow() {
      deleteActivity(this.idx);
      this.delVisible = false;
      this.$message.success("归档成功");
      this.search();
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
  width: 300px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 12px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
</style>
