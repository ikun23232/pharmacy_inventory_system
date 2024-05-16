import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initSaleOutWarehouseListByPage(kcSalefromware) {
    return request.post(baseUrl + "/getSaleOutWarehouseListByPage",kcSalefromware);
}

export function deleteSaleOutWarehouseOrder(code) {
    return request.get(baseUrl + "/deleteSaleOutWarehouseOrder",{
        params:{
            code:code
        }
    });
}