import request from '../util/request'

export const getActivityClientList = (activityId, pageNum, pageSize) => {
  return request({
    url: '/api/activity/' + activityId + '/client/list',
    method: 'post',
    headers: {'Content-Type': 'application/json'},
    params: {
      pageNum, pageSize
    }
  })
}
