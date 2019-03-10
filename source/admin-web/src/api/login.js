import request from '../util/request'

export const userLogin = (username, password) => {
    let fd = new FormData()
    fd.append('username', username)
    fd.append('password', password)
    return request({
      url: '/api/user/login',
      method: 'post',
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data: fd
    })
  }

export const getUserProfile = () => {
  return request({
    url: '/api/user/profile',
    method: 'get'
  })
}