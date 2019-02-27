import axios from "axios";
import { getToken } from "./token";
import { Message, MessageBox } from "element-ui";
import store from "../store";
import { USER_WEB_LOGOUT } from "../store/mutation-types";
var instance = axios.create({
  baseURL: process.env.baseURL,
  timeout: 5 * 1000
});

instance.interceptors.request.use(
  config => {
    const token = getToken();
    if (token) {
      config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  error => {
    console.log(error);
    Promise.reject(error);
  }
);

instance.interceptors.response.use(
  response => {
    return response.data;
  },
  error => {
    console.log(error);

    if (error.response) {
      if (error.response.status === 403) {
        MessageBox.confirm("你已被登出，或者重新登录", "确定登出", {
          confirmButtonText: "重新登录",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          store.dispatch(USER_WEB_LOGOUT).then(() => {
            location.reload(); // 为了重新实例化vue-router对象 避免bug
          });
        });
      } else {
        Message({
          message: error.message,
          type: "error",
          duration: 5 * 1000
        });
      }
    }

    return Promise.reject(error);
  }
);

export default instance;
