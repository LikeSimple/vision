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
        <el-select v-model="select_cate" placeholder="请选择活动" class="handle-select mr10">
          <el-option
            v-for="item in activityList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button type="primary" icon="el-icon-lx-qrcode" @click="printQRCode">二维码</el-button>
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
        <el-table-column prop="schoolName" label="学校" width="160" align="center"></el-table-column>
        <el-table-column prop="className" label="班级" width="120" align="center"></el-table-column>
        <el-table-column prop="studentNumber" label="学号" width="120" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" width="100" align="center"></el-table-column>
        <el-table-column
          prop="gender"
          label="性别"
          width="50"
          :formatter="genderFormatter"
          align="center"
        ></el-table-column>
        <el-table-column prop="age" label="年龄" width="60" align="center"></el-table-column>
        <el-table-column prop="visionAcuity" label="视力" width="60" align="center"></el-table-column>
        <el-table-column prop="visionAcuityLeft" label="左眼" width="60" align="center"></el-table-column>
        <el-table-column prop="visionAcuityRight" label="右眼" width="60" align="center"></el-table-column>
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

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="50px">
        <el-form-item label="活动日期">
          <el-date-picker
            type="activityDate"
            placeholder="选择日期"
            v-model="form.date"
            value-format="yyyy-MM-dd"
            style="width: 100%;"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="活动地址">
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
import { getActivityClientList } from "../../api/activityClient.js";
import { getActivityList } from "../../api/activity.js";
export default {
  name: "basetable",
  data() {
    return {
      url: "./vuetable.json",
      tableData: [],
      cur_page: 1,
      multipleSelection: [],
      select_cate: "",
      select_word: "",
      del_list: [],
      is_search: false,
      editVisible: false,
      delVisible: false,
      activityList: [],
      form: {
        name: "",
        date: "",
        address: ""
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
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.cur_page = val;
      this.getData();
    },
    getData() {
      if (this.select_cate == null || this.select_cate == "") {
        this.$message.warning("没有选择活动！");
        return false;
      }
      getActivityClientList(this.select_cate, this.cur_page, 20).then(res => {
        this.tableData = res.data;
      });
    },
    search() {
      this.getData();
      this.is_search = true;
    },
    genderFormatter(row, column) {
      return 1 == row.gender ? "男" : "女";
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
      };
      this.editVisible = true;
    },
    handleDelete(index, row) {
      this.idx = index;
      this.delVisible = true;
    },
    delAll() {
      const length = this.multipleSelection.length;
      let str = "";
      this.del_list = this.del_list.concat(this.multipleSelection);
      for (let i = 0; i < length; i++) {
        str += this.multipleSelection[i].name + " ";
      }
      this.$message.error("删除了" + str);
      this.multipleSelection = [];
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 保存编辑
    saveEdit() {
      this.$set(this.tableData, this.idx, this.form);
      this.editVisible = false;
      this.$message.success(`修改第 ${this.idx + 1} 行成功`);
    },
    // 确定删除
    deleteRow() {
      this.tableData.splice(this.idx, 1);
      this.$message.success("删除成功");
      this.delVisible = false;
    },
    getActivityListMethod() {
      getActivityList(null, 1, 0).then(res => {
        this.activityList = res.data;
      });
    },
    printQRCode() {
      if (this.select_cate == null || this.select_cate == "") {
        this.$message.warning("没有选择活动！");
        return false;
      }
      // resolve href
      const { href } = this.$router.resolve({
        name: "qrcode",
        query: { activityId: this.select_cate }
      });
      console.log(href);
      window.open(href, "_blank");
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
  color: coral;
}
</style>
