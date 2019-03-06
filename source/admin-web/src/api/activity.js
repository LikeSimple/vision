import request from '../util/request'

export const getActivityList = (nameCriteria, pageNum, pageSize) => {
    return request({
      url: '/api/activity/list',
      method: 'post',
      params: {
        nameCriteria, pageNum, pageSize
      }
    })
  }

export const createActivity = (form) => {
  return request({
    url: '/api/activity/new',
    method: 'post',
    params: form
    // transformRequest: [function(){
    //   return JSON.stringify(data);
    // }]
  })
}

export const editActivity = (form) => {

  return request({
    url: '/api/activity/' + id + '/modify',
    method: 'post',
    params: form
  })
}

export const deleteActivity = (id) => {
  return request({
    url: '/api/activity/' + id + '/archive',
    method: 'post',
  })
}