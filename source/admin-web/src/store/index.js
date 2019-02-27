import Vue from "vue";
import Vuex from "vuex";
import { USER_LOGIN, USER_GET_PROFILE, USER_WEB_LOGOUT } from './mutation-types'
import { userLogin, getUserProfile } from '../api/login'
import { saveToken, removeToken } from '../util/token'
Vue.use(Vuex);

class NonUser {
  constructor() {
    this.id = '';
    this.realName = '';
    this.avatarImage = '';
    this.gender = '';
    this.authorities = [];
    this.token = '';
  }
}

export default new Vuex.Store({
  state: {
    user: new NonUser()
  },
  mutations: {
    [USER_LOGIN] (state, token) {
      state.user.token = token
    },
    [USER_GET_PROFILE](state, profile) {
      state.user.id = profile.id
      state.user.realName = profile.realName
      state.user.gender = profile.gender
      state.user.avatarImage = profile.avatarImage
      state.user.authorities = profile.authorities
    },
    [USER_WEB_LOGOUT](state) {
      state.user = new NonUser()
    }
  },
  actions: {
    [USER_LOGIN] ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        userLogin(userInfo.username, userInfo.password).then(
          response => {
            commit(USER_LOGIN, response.data.token)
            saveToken(response.data.token)
            resolve()
          }
        )
      }).catch(error => {
        reject(error)
      })
    },
    [USER_GET_PROFILE] ({ commit }) {
      return new Promise((resolve, reject) => {
        getUserProfile().then(
          response => {
            commit(USER_GET_PROFILE, response.data)
          }
        )
      })
    },
    [USER_WEB_LOGOUT] ({ commit }) {
      return new Promise(resolve => {
        commit(USER_WEB_LOGOUT)
        removeToken()
        resolve()
      })
    }
  }
});
