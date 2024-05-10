import request from '../utils/request.js';

const baseUrl = '/procurement';

export function getProcList(procPage) {
    return request.post(baseUrl + "/getCgddOrder",procPage);
}

export function deleteById(id) {
    return request.get(baseUrl + "/deleteById", {
        params:{
            id:id
        }
    });
}

export function addCgddOrder(cgddOrder) {
    return request.post(baseUrl + "/addCgddOrder",{
        cgddOrder:cgddOrder
    });
}