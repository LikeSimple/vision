const app = getApp()
var httpApi = require('../../utils/httpApi.js')
Page({
  data: {
    welcome: '欢迎使用视力检查系统',
    userInfo: {},
    hasUserInfo: false,
    certify:false
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (wx.getStorageSync('sessionid') && wx.getStorageSync('userinfo')) {
      this.setData({
        userInfo: JSON.parse(wx.getStorageSync('userinfo')),
        hasUserInfo: true,
      })
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      this.setData({
        hasUserInfo: false
      })
    }
  },
  saveUser(userInfo) {
    let url = 'wx-miniprgm/save-user'
    httpApi.fetchApi(url, userInfo, 'POST').then(res => {
      console.log(res)
    })
  },
  onGotUserInfo: function (e) {
    if (e.detail.userInfo) {
      app.globalData.userInfo = e.detail.userInfo
      wx.setStorageSync('userinfo', JSON.stringify(e.detail.userInfo))
      this.setData({
        userInfo: e.detail.userInfo,
        hasUserInfo: true
      })
      this.saveUser(e.detail.userInfo)
    } else {
      //用户按了拒绝按钮
      wx.showModal({
        title: '警告',
        content: '您点击了拒绝授权，将无法进入小程序，请授权之后再进入!',
        showCancel: false,
        confirmText: '返回授权',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击了“返回授权”');
          }
        }
      });
    }
  },
  registerUserinfo:function(){
    // 未启用
    // 用户注册
    console.log("用户注册");
    // console.log(this.userInfo);
    // 保存基本资料 /wx-miniprgm/save-user
  },
  claim:function(e){
    // TODO
    // 跳转用户档案认证页面
    console.log("跳转档案认领页面")
    wx.navigateTo({
      url: '/pages/claim/claim',
    })
  },

  directWelcome: function (e) {
    console.log("跳转welcome页面")
    wx.navigateTo({
      url: '../welcome/welcome',
      success: function () {
        console.log("success");
      },
      fail: function () {
        console.log("fail");
      }
    })
  }
})