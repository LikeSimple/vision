import request from '../util/request'

export const getActivityList = (nameCriteria, pageNum) => {
    return request({
      url: '/api/activity/list',
      method: 'post',
      params: {
        nameCriteria, pageNum
      }
    })
  }

export const createActivity = (name, address, beginDate, endDate, content) => {
  const data = {"name":name, "address":address, "beginDate":beginDate, "endDate":endDate, "content":content};
  return request({
    url: '/api/activity/new',
    method: 'post',
    headers: {"Content-Type": "application/json"},
    transformRequest: [function(){
      return JSON.stringify(data);
    }]
  })
}

export const editActivity = (id, name, address, beginDate, endDate) => {
  const data = {"name":name, "address":address, "beginDate":beginDate, "endDate":endDate};
  return request({
    url: '/api/activity/' + id + '/modify',
    method: 'post',
    headers: {"Content-Type": "application/json"},
    transformRequest: [function(){
      return JSON.stringify(data);
    }]
  })
}

export const deleteActivity = (id) => {
  return request({
    url: '/api/activity/' + id + '/archive',
    method: 'post',
  })
}