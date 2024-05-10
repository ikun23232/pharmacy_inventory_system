import request from '../utils/request.js';

const baseUrl = '/procurement';

export function initCgSqOrderList(vo) {
    return request.post(baseUrl + "/getCgSqOrderList", vo);
}

export function delCgsqOrderById(id) {
    return request.get(baseUrl + "/delCgsqOrderById",{
        params:{
            id:id
        }
    });
}

export function voidCgsqOrderById(id) {
    return request.get(baseUrl + "/voidCgsqOrderById",{
        params:{
            id:id
        }
    });
}