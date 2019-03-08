//app.js
App({
onLaunch: function() {
  // 展示本地存储能力
  var logs = wx.getStorageSync('logs') || []
  logs.unshift(Date.now())
  wx.setStorageSync('logs', logs)
  // 登录
 this.checkLogin()
},
  checkLogin() {
    if (!wx.getStorageSync('sessionid')) {
      wx.login({
        success(res) {
          // 发起网络请求
          wx.request({
            url: 'https://www.yshjh.com/vision/wx-miniprgm/by-code/' + res.code,
            method: 'post',
            success: res => {
              wx.setStorageSync('sessionid', res.data.data)
              // 判断登陆用户是否已登陆或者注册
            },
            fail: function (res) {
              console.log('submit fail');
            },
            complete: function (res) {
              console.log('submit complete');
            }
          })
        }
      })
    }
  },
globalData: {
  userInfo: null, // 用户信息
  certify: false, // 用户是否注册
  openid: null,
  session_key: null
}
})