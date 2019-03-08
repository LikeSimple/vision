// pages/studentList/studentList.js
var httpApi = require('../../utils/httpApi.js')
Page({
  data: {
    students:[],
    isData:true
  },
  goCheckRecord(){
    wx.navigateTo({
      url: '/pages/checkinfo/checkinfo'
    })
  },
  relevancy(){
    wx.navigateTo({
      url: '/pages/claim/claim'
    })
  },
  getStudentList(){
    let url = 'vision-client/list'
    httpApi.fetchApi(url, {},'POST').then(res=>{
      if (res.data.code == 0) {
        let list = res.data.data
        if (list.length == 0) {
          this.setData({
            isData: false
          })
        } else {
          this.setData({
            students: res.data.data,
            isData: true
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    this.getStudentList()
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