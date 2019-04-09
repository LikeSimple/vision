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
        <el-select v-model="form.activityId" placeholder="请选择活动" class="handle-select mr10">
          <el-option
            v-for="item in activityList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
        <el-input type="textarea" rows="5" v-model="form.desc"></el-input>
        <el-button type="primary" icon="search" @click="search">搜索</el-button>
        <el-button type="primary" icon="upload" @click="handleUpload">导入</el-button>
        <el-button type="primary" icon="download" @click="handleDownload">下载</el-button>
      </div>
      <el-table
        :data="data"
        border
        class="table"
        ref="multipleTable"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand">
              <el-form-item label="左屈光度">
                <span>{{ props.row.dioptersLeft }}</span>
              </el-form-item>
              <el-form-item label="右屈光度">
                <span>{{ props.row.dioptersRight }}</span>
              </el-form-item>
              <el-form-item label="左散光度">
                <span>{{ props.row.astigmatismLeft }}</span>
              </el-form-item>
              <el-form-item label="右散光度">
                <span>{{ props.row.astigmatismRight }}</span>
              </el-form-item>
              <el-form-item label="左联合光度">
                <span>{{ props.row.jointLuminosityLeft }}</span>
              </el-form-item>
              <el-form-item label="右联合光度">
                <span>{{ props.row.jointLuminosityRight }}</span>
              </el-form-item>
              <el-form-item label="左轴">
                <span>{{ props.row.axisLeft }}</span>
              </el-form-item>
              <el-form-item label="右轴">
                <span>{{ props.row.axisRight }}</span>
              </el-form-item>
              <el-form-item label="轴距">
                <span>{{ props.row.pupilDistance }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="schoolName" label="学校" width="180" align="center"></el-table-column>
        <el-table-column prop="className" label="班级" width="120" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" align="center"></el-table-column>
        <el-table-column prop="idNumber" label="身份证号" width="200" align="center"></el-table-column>
        <el-table-column
          prop="eyeType"
          label="位置"
          :formatter="eyeTypeFormatter"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column prop="pupil" label="瞳孔大小" width="50" align="center"></el-table-column>
        <el-table-column prop="pd" label="瞳距" width="80" align="center"></el-table-column>
        <el-table-column prop="activityName" label="活动" width="80" align="center"></el-table-column>
        <el-table-column prop="checkDate" label="检测日期" width="200" align="center"></el-table-column>
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
      <el-input value="select_cate" type="hidden"></el-input>
      <el-upload class="upload-record" drag v-bind:action="'/api/activity/' + this.select_cate + '/record/upload'"
      v-bind:headers="tokenHeader"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">只能上传csv文件，且不超过2M</div>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
import { getActivityList } from "../../api/activity.js";
import { getActivityClientRecordList } from "../../api/activityClientRecord.js";
import { getToken } from '../../util/token.js'
export default {
  name: "basetable",
  data() {
    return {
      url: "./vuetable.json",
      tableData: [],
      cur_page: 1,
      multipleSelection: [],
      uploadVisible: false,
      del_list: [],
      is_search: false,
      editVisible: false,
      delVisible: false,
      activityList: [],
      select_cate: "",
      form: {
        activityId: "",
        province: "",
        city: "",
        school: "",
        age: ""
      },
      idx: -1
    };
  },
  created() {
    this.getActivityListMethod();
  },
  computed: {
    data() {
      return this.tableData;
    },
    tokenHeader() {
      return { Authorization: 'Bearer ' + getToken() }
    },
  },
  methods: {
    handleCurrentChange(val) {
      this.cur_page = val;
      this.getData();
    },
    getData() {
      getActivityClientRecordList(this.form.activityId, this.cur_page, 20).then(
        res => {
          this.tableData = res.data;
        }
      );
    },
    search() {
      if (this.form.activityId == null || this.form.activityId == "") {
        alert("请选择活动");
        return false;
      }
      this.getData();
      this.is_search = true;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    handleUpload() {
      if (null == this.form.activityId || '' == this.form.activityId) {
        this.$message.warning("没有选择活动！");
        return false;
      }
      this.uploadVisible = true;
    },
    eyeTypeFormatter(row, column) {
      var value = "未知";
      console.log(row);
      console.log(row.getEyeType);
      if ("OD" == row.eysType) {
        value = "左眼";
      } else if ("OD" == row.eysType) {
        value = "右眼";
      }
      return value;
    },
    getActivityListMethod() {
      getActivityList(null, 1, 0).then(res => {
        this.activityList = res.data;
      });
    },

  }
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 160px;
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
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
</style>
