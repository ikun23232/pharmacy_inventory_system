import request from '../utils/request.js';

const baseUrl = '/procurement';

export function getProcList(procPage) {
    return request.post(baseUrl + "/getCgddOrder", procPage);
}

export function deleteById(id) {
    return request.get(baseUrl + "/deleteById", {
        params: {
            id: id
        }
    });
}

export function addCgddOrder(cgddOrder) {
    return request.post(baseUrl + "/addCgddOrder", {
        cgddOrder: cgddOrder
    });
}

export function setVoidState(id, voidState) {
    return request.get(baseUrl + "/setVoidState", {
        params: {
            id: id,
            voidState: voidState
        }
    });
}

export function getCgddByCode(code) {
    return request.get(baseUrl + "/getCgddByCode", {
        params: {
           code:code
        }
    });
}

export function updateCgddById(cgddOrder) {
    return request.post(baseUrl + "/updateCgddById", {
        cgddOrder: cgddOrder
    });
}

export function auditingOrder(cgddOrder) {
    return request.post(baseUrl + "/auditingOrder", {
        cgddOrder: cgddOrder
    });
}