import request from '../utils/request.js';

const baseUrl = '/user';

export function getLogin(user) {
    return request({
      url: baseUrl+'/login',
      method: 'post',
      data:user
    })
  }
  export function selectUser() {
    return request({
      url: baseUrl+'/selectUser',
      method: 'get',
    })
  }
  export function logout() {
    return request({
      url: baseUrl+'/logout',
      method: 'get',
    })
  }
  export function getUserlist(username,sex,isstate,currentNo) {
    return request({
      url: baseUrl+'/getUserListByPage',
      method: 'get',
      params: { username,sex,isstate,currentNo }
    })
  }

  export function addUser(user) {
    return request({
      url: baseUrl+'/saveUser',
      method: 'post',
      data:user
    })
  }
  export function updateUser(user) {
    return request({
      url: baseUrl+'/updateUser',
      method: 'post',
      data:user
    })
  }
  export function delUserById(userid) {
    return request({
      url: baseUrl+'/delUserById',
      method: 'get',
      params: { userid }
    })
  }



