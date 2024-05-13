import request from '../utils/request.js';

const baseUrl = '/base';


export function initCat() {
    return request.get(baseUrl + "/catlist");
}
export function delCat(id) {
    return request.get(baseUrl + "/delCatById", {
        params: {
            id: id
        }
    });
}
export function addCat(baseMedicineCategory) {
    return request({
      url: baseUrl+'/addCat',
      method: 'post',
      data:baseMedicineCategory
    })
  }
export function updateCat(baseMedicineCategory) {
    return request({
      url: baseUrl+'/updateCat',
      method: 'post',
      data:baseMedicineCategory
    })
  }
export function checkCat(name) {
    return request({
      url: baseUrl+'/checkCat',
      method: 'get',
      params: {
        name: name
    }
    })
  }
  export function checkCatById(id) {
    return request({
      url: baseUrl+'/checkCatById',
      method: 'get',
      params: {
        id: id
    }
    })
  }
  