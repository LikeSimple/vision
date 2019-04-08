import request from '../util/request'

export const getActivityList = (data) => {
    return request({
      url: '/api/activity/list',
      method: 'post',
      data:data
    })
  }
