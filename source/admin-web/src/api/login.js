import request from '@/utils/request'

export function loginByUsername(username, password) {
  return request({
    url: '/login',
    method: 'post',
    params: { username, password }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function getUserInfo() {
  return request({
    url: '/api/admin/info',
    method: 'get'
  })
}

