export const getClientRecordList = (name, idNumber, schoolName, className, activityName, pageNum, pageSize) => {
  return request({
    url: '/api/activity/' + activityId + '/record/list',
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