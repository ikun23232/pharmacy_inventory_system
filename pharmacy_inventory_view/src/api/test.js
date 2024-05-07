import request from '../utils/request.js';

const baseUrl = '/user';

export function getLogin(user) {
    return request({
      url: '/baseUrl/login',
      method: 'post',
      user
    })
  }

export function createArticle(data) {
  return request({
    url: '/vue-element-admin/article/create',
    method: 'post',
    data,
    params: { user }
  })
}
