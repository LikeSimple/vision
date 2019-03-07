import request from '../util/request'

export const getActivityClientRecordList = (activityId, pageNum, pageSize) => {
  return request({
    url: '/api/activity/' + activityId + '/record/list',
    method: 'post',
    params: {
      pageNum, pageSize
    }
  })
}

