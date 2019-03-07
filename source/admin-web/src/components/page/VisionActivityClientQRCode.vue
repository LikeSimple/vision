<template>
  <div class="card-wrap">
    <div v-for="item in client" :key="item.id">
      <el-card class="box-card">
        <div>
          <div class="card-text">{{ item.schoolName }}</div>
          <div class="card-text">{{ item.className }}</div>
          <div class="card-text">{{ item.studentNumber }}</div>
          <div class="card-text">{{ item.qrcodeString }}</div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { USER_LOGIN } from "../../store/mutation-types.js";

export default {
  props: {
    client: Object
  },
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-size: 100%;
}

.card-text {
  font-size: 14px;
}
</style>