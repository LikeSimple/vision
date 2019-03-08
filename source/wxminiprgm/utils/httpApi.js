// 这是域名
const app = getApp()
export const API_URI = 'https://www.yshjh.com/vision'
let SESSIONID = wx.getStorageSync('sessionid')
function fetchApi(type, params, method) {
  return new Promise((resolve, reject) => {
    wx.request({
      url: `${API_URI}/${type}`,
      data: params,
      method: method || 'GET',
      header: {
        'content-type': 'application/json',
        'wechatSessionId': SESSIONID
      },
      success: res=> {
        if (res.data.code ==0) {
          resolve(res)
        }
        else {
          this.intercept(res)
        }
      },
      fail: fail=>{
        wx.showToast({
          title: '请求失败，请候再试！',
        })
        reject(res)
      }
    })
  })
}

function intercept(res) {
  if (res.data.code === '-2') {
    wx.removeStorageSync('sessionid')
    wx.removeStorageSync('userinfo')
    app.checkLogin()
  }
  return
}
module.exports = {
  fetchApi: fetchApi
}
