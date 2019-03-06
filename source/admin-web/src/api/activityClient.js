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


  export const getActivityClientRecordList = (name, idNumber, schoolName, className, activityName, pageNum) => {
    return request({
      url: '/api/record/list',
      method: 'post',
      params: {
        name, idNumber, schoolName, className, activityName, pageNum
      }
    })
  }