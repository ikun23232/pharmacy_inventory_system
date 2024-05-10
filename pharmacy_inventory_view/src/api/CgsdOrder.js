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