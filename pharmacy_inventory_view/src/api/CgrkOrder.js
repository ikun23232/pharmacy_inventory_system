import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initCgrkOrderList(vo) {
    return request.post(baseUrl + "/getCgrkOrderList", vo);
}
export function delCgrqOrderById(id) {
    return request.get(baseUrl + "/delCgrkOrderById",{
        params:{
            id:id
        }
    });
}

export function voidCgrkOrderById(id) {
    return request.get(baseUrl + "/voidCgrkOrderById",{
        params:{
            id:id
        }
    });
}

export function addCgrkOrder(cgsqOrder) {
    return request.post(baseUrl + "/addCgrkOrder",{
        cgrkOrder:cgsqOrder
    });
}

export function updateCgrkOrder(cgsqOrder) {
    return request.post(baseUrl + "/updateCgrkOrder",{
        cgrkOrder:cgsqOrder
    });
}
export function getCgrkOrderById(id) {
    return request.get(baseUrl + "/getCgrkOrderById",{
        params:{
            id:id
        }
    });
}
export function approveCgrkOrder(id,approveRemark,approveMent) {
    return request.get(baseUrl + "/approveCgrkOrder",{
        params:{
            id:id,
            approveRemark:approveRemark,
            approveMent:approveMent


        }
    });
}