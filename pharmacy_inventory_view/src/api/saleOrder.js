import request from '../utils/request.js';

const baseUrl = '/sale';

//销售订单分页查询
export function initSaleOrder(xsOrder) {
    return request.post(baseUrl + "/getSaleOrderListByPage",xsOrder);
}

export function addSaleOrder(xsOrder) {
    return request.get(baseUrl + "/addSaleOrder", {
        params: {
            xsOrder: xsOrder,
        }

    });
}
//修改回显
export function getSaleOrderById(id) {
    return request.get(baseUrl + "/getSaleOrderById", {
        params: {
            id: id,
        }

    });
}
export function updateSaleOrder(xsOrder) {
    return request.get(baseUrl + "/updateSaleOrder", {
        params: {
            xsOrder: xsOrder,
        }

    });
}

export function deleteSaleOrder(id) {
    return request.get(baseUrl + "/deleteSaleOrder", {
        params: {
            id: id,
        }

    });
}
