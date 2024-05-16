import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initStockDetailListByPage(baseMedicine) {
    return request.post(baseUrl + "/getStockDetailListByPage",baseMedicine);
}

export function initStockDetailList() {
    return request.post(baseUrl + "/getStockDetailList");
}

export function deleteStockDetail(medicineId) {
    return request.get(baseUrl + "/deleteStockDetail",{
        params:{
            medicineId:medicineId
        }
    });
}