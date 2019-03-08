var httpApi = require('../../utils/httpApi.js')
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    list: [],
    height: '',
  },
  getCheckList(){
    let url = 'check-record/list'
    httpApi.fetchApi(url, {}, 'POST').then(res => {
      console.log(res.data)
      if (res.data.code ==0 ){
        this.setData({
          list: res.data.data.list || []
        })
      }
    })
  },
  onLoad: function (options) {
    wx.getSystemInfo({
      success: (res) => {
        this.setData({
          height: res.windowHeight
        })
      }
    })
    this.getCheckList()
  },
  goDetails(e){
    console.log(e)
    let query = {
      activityId: e.currentTarget.dataset.item.activityId,
      clientId: e.currentTarget.dataset.item.clientId
    }
    wx.navigateTo({
      url: '/pages/screened/screened?query=' + JSON.stringify(query),
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