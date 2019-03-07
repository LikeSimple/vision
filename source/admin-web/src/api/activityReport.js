import request from '../util/request'

export const getActivityRecordList = (activityId, pageNum, pageSize) => {
  return request({
    url: '/api/activity/' + activityId + '/client/list',
    method: 'post',
    params: {
      pageNum, pageSize
    }
  })
}