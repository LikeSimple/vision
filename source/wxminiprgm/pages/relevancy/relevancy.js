// pages/relevancy/relevancy.js
var httpApi = require('../../utils/httpApi.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    idNumber: ''
  },
  relevancyStu() {
    let url = '/vision-client/relation'
    let params ={
      name:this.data.name,
      idNumber: this.data.idNumber
    }
    httpApi.fetchApi(url, params, 'POST').then(res => {
      console.log(res.data)
      if (res.data.code ==0){
        wx.switchTab({
          url: '/pages/studentList/studentList'
        })
      }
      else {
        wx.showToast({
          title: res.data.message,
          icon: 'none',
          duration: 2000
        })
      }
    })
  },
  changeName(e){
   this.setData({
     name : e.detail.value
   })
  },
  changeIdCard(e){
    this.setData({
      idNumber: e.detail.value
    })
  },
  save(){
    this.relevancyStu()
  },
  cancel(){
    this.setData({
      name: '',
      idNumber: ''
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