<template>
  <div class="content">
    <div class="printArea">
      <el-main class="card-wrapper">
        <el-col :span="8" v-for="item in qrcodeData" :key="item.clientId">
          <el-card class="qrcode-box-card">
            <div class="qrcode-text">
              <span class="qrcode-card-text">{{ item.className }}</span>
              <span class="qrcode-card-text">{{ item.studentNumber }}</span>
              <span class="qrcode-card-text">{{ item.name }}</span>
            </div>
            <div>
              <qrcode-vue
                v-bind:value="getQRCodeString(item)"
                :size="size"
                level="M"
                class="qrcode"
              ></qrcode-vue>
            </div>
          </el-card>
        </el-col>
      </el-main>
    </div>
  </div>
</template>

<script>
import { getActivityClientList } from "../../api/activityClient.js";
import QrcodeVue from "qrcode.vue";

let Base64 = {
  encode(str) {
    // first we use encodeURIComponent to get percent-encoded UTF-8,
    // then we convert the percent encodings into raw bytes which
    // can be fed into btoa.
    return btoa(
      encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function toSolidBytes(
        match,
        p1
      ) {
        return String.fromCharCode("0x" + p1);
      })
    );
  }
};

export default {
  created() {
    console.log(this.$route.query.activityId);
    let id = this.$route.query.activityId;
    this.getList(id);
  },
  data() {
    return {
      qrcodeData: [],
      size: 130
    };
  },
  methods: {
    getList(activityId) {
      // pageSize = 0 代表获取全部数据
      getActivityClientList(activityId, 1, 0).then(res => {
        this.qrcodeData = res.data;
      });
    },
    getQRCodeString(item) {
      let s = Base64.encode(
        "activityId=" +
          item.visionActivityId +
          "&className=" +
          item.className +
          "&name=" +
          item.name +
          "&clientId=" +
          item.clientId +
          "&studentNumber=" +
          item.studentNumber
      );
      console.log(s);
      return s;
    }
  },
  components: {
    QrcodeVue
  }
};
</script>

<style scoped>
.printArea {
  position: absolute;
}

.qrcode-card-text {
  font-size: 15px;
  color: brown;
  align-self: auto;
  display: block;
}

.qrcode-box-card {
  width: 200px;
  margin-top: 8px;
  margin-left: 8px;
  margin-right: 8px;
  align-self: auto;
}

.qrcode {
  width: 100%;
  text-align: center;
  margin-top: 5px;
}

.qrcode-text {
  width: 100%;
  text-align: center;
}
</style>