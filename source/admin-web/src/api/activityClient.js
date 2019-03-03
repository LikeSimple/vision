import request from '../util/request'

export const getActivityClientList = (activityId, pageNum) => {
    return request({
      url: '/api/activity/' + activityId + '/client/list',
      method: 'post',
      params: {
        pageNum
      }
    })
  }