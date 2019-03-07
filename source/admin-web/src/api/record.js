import request from '../util/request'

export const getRecordList = (name, idNumber, schoolName, className, activityName, pageNum, pageSize) => {
  return request({
    url: '/api/record/list',
    method: 'post',
    params: {
      name,
      idNumber,
      schoolName,
      className,
      activityName,
      pageNum,
      pageSize
    }
  })
}