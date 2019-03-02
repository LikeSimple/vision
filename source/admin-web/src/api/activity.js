import request from '../util/request'

export const getActivityList = () => {
    return request({
      url: '/api/activity/list',
      method: 'post'
    })
  }
