const tokenKey = 'vision-token'

export const saveToken = (token) => {
    return localStorage.setItem(tokenKey, token)
  }
export const getToken = () => {
    return localStorage.getItem(tokenKey)
  }
export const removeToken = () => {
    return localStorage.removeItem(tokenKey)
  }
