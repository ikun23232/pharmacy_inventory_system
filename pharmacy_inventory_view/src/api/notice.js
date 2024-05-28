import request from '../utils/request.js';

const baseUrl = '/user';
export function checkNotice(noticetitle,id) {
    return request({
      url: baseUrl+'/sysNotice/existNotice',
      method: 'get',
      params: { noticetitle,id}
    })
  }