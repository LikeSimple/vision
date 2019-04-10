import request from '../util/request'

export const getClientList = (clientNameCriteria, idNumber, schoolNameCriteria, pageNum, pageSize) => {
    return request({
      url: '/api/client/list',
      method: 'post',
      params: {
        clientNameCriteria, idNumber, schoolNameCriteria, pageNum, pageSize
      }
    })
  }
