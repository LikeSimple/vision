import request from '../util/request'

export const getProfile = () => {
    return request({
      url: '/api/user/profile',
      method: 'get'
    })
  }

export const getUserList = (form) => {
  return request({
    url: '/api/user/list',
    method: 'post',
    data: form
  })
}
// 新增用户ß
export const createUser = (form) => {
  return request({
    url: '/api/useradd',
    method: 'post',
    data: form
  })
}
// 修改
export const updateUser = (userId,data) => {
  return request({
    url: '/api/user/update/'+userId,
    method: 'post',
    data: data
  })
}
// 删除
export const deleteUser = (userId) => {
  return request({
    url: '/api/user/delete/'+userId,
    method: 'post',
  })
}
// 查找用户角色列表
export const findUserRoleList = (userId) => {
  return request({
    url: '/api/user/find-role/'+userId,
    method: 'post',
  })
}
// 重置密码
export const resetPassword = (userId) => {
    return request({
      url: '/api/user/reset-password/'+userId,
      method: 'post',
    })
  }
