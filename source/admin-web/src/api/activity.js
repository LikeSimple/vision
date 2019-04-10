import request from '../util/request'

export const getActivityList = (activityCriteria, pageNum, pageSize) => {
    let criteria = activityCriteria
    if (!activityCriteria) {
      criteria  = {
        nameCriteria: '',
        beginDate: null,
        endDate: null,
        archived: false
      }
    } 
    return request({
      url: '/api/activity/list',
      method: 'post',
      params: {
        pageNum, pageSize
      },
      data: criteria
      
    })
  }

export const createActivity = (form) => {
  return request({
    url: '/api/activity/new',
    method: 'post',
    data: form
  })
}

export const editActivity = (form) => {

  return request({
    url: '/api/activity/' + form.id + '/modify',
    method: 'post',
    data: form
  })
}

export const deleteActivity = (id) => {
  return request({
    url: '/api/activity/' + id + '/archive',
    method: 'post',
  })
}