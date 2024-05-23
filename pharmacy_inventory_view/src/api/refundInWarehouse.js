import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initRefundInWarehouseListByPage(kcSalefromware) {
    return request.post(baseUrl + "/getRefundInWarehouseListByPage",kcSalefromware);
}
