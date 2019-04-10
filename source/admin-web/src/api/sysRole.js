import request from '../util/request'
// 角色列表
export const getRoleList = (data) => {
    return request({
      url: '/api/sys-role/list',
      method: 'post',
      data 
    })
  }
//   权限列表
  export const getSysList = (data) => {
    return request({
      url: '/api/sys-role/get-authority-list',
      method: 'post',
      data 
    })
  }
// 新增角色
export const createSysRole = (form) => {
  return request({
    url: '/api/sys-role/add',
    method: 'post',
    data: form
  })
}
// 修改
export const updateRole = (sysRoleId,data) => {
  return request({
    url: 'api/sys-role/update/'+sysRoleId,
    method: 'post',
    data: data
  })
}
// 删除
export const deleteRole = (sysRoleId) => {
  return request({
    url: '/api/sys-role/delete/'+sysRoleId,
    method: 'post',
  })
}