import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getKcDispathList(dispatchVO) {
    return request.post(baseUrl + "/getKcDispathList",dispatchVO);
}

export function addKcDispatch(KcDispatch) {
    return request.post(baseUrl + "/addKcDispatch",{
        KcDispatch:KcDispatch
    });
}

export function getKcDispatchById(id) {
    return request.get(baseUrl + "/getKcDispatchById",{
        params:{
            id:id
        }
    });
}

export function updateDispatchBy(KcDispatch) {
    return request.post(baseUrl + "/updateDispatchBy",{
        KcDispatch:KcDispatch
    });
}

export function auditingDispatch(KcDispatch) {
    return request.post(baseUrl + "/auditingDispatch",{
        KcDispatch:KcDispatch
    });
}

export function deleteDispatch(id) {
    return request.get(baseUrl + "/deleteDispatch",{
        params:{
            id:id
        }
    });
}

export function updateVoidStatus(id) {
    return request.get(baseUrl + "/updateVoidStatus",{
        params:{
            id:id
        }
    });
}