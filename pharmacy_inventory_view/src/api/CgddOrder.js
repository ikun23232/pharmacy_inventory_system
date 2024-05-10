import request from '../utils/request.js';

const baseUrl = '/procurement';

export function initCgSqOrderList(CgddOrder) {
    return request.post(baseUrl + "/addCgddOrder", CgddOrder);
}

export function deleteById(id) {
    return request.get(baseUrl + "/deleteById", {
        params:{
            id:id
        }
    });
}