// pages/screened/screened.js
const api = require('../../utils/httpApi');
console.log(api)
var httpApi = require('../../utils/httpApi.js')
Page({
  data: {
    id: '',
    leftEye:{},
    rightEye: {},
    year: '',
    month:'',
    day: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    let query = JSON.parse(options.query)
    this.getDetails(query)
  },
  getDetails(query){
    let url = 'check-record/find/' + query.activityId + '/' + query.clientId
      httpApi.fetchApi(url , {}, 'POST').then(res => {
        console.log(res.data)
      if(res.data.code==0) {
        let year,month,day
        if (res.data.data.leftEye) {
          year = res.data.data.leftEye.checkDate.substring(0, 4);
          month = res.data.data.leftEye.checkDate.substring(5, 7);
          day = res.data.data.leftEye.checkDate.substring(8, 10)
        }
        if(!res.data.data.leftEye&&res.data.data.rightEye) {
          year = res.data.data.rightEye.checkDate.substring(0, 4);
          month = res.data.data.rightEye.checkDate.substring(5, 7);
          day = res.data.data.rightEye.checkDate.substring(8, 10)
        }
        this.setData({
          leftEye: res.data.data.leftEye,
          rightEye: res.data.data.rightEye,
          year: year,
          month: month,
          day: day
        })
        console.log(this.data)
      }
      else {
        wx.showToast({
          title: res.data.message,
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})