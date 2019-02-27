import request from '../util/request'

export const userLogin = (username, password) => {
    return request({
      url: '/api/user/login',
      method: 'post',
      params: {
        username, password
      }
    })
  }

export const getUserProfile = () => {
  return request({
    url: '/api/user/profile',
    method: 'get'
  })
}