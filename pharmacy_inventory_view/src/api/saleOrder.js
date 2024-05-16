import request from '../utils/request.js';

const baseUrl = '/sale';

//销售订单分页查询
export function initSaleOrder(xsOrder) {
    return request.post(baseUrl + "/getSaleOrderListByPage",xsOrder);
}

//添加销售订单
export function addSaleOrder(xsOrder) {
    return request.post(baseUrl + "/addSaleOrder",{xsOrder:xsOrder});
}
//保存销售订单
export function saveSaleOrder(xsOrder) {
    return request.post(baseUrl + "/saveSaleOrder",{xsOrder:xsOrder});
}
//根据订单号查询订单详情
export function getSaleOrderByOrderNo(orderNo) {
    return request.get(baseUrl + "/getSaleOrderByOrderNo", {
        params: {
            orderNo: orderNo,
        }
    });
}
export function updateSaleOrder(xsOrder) {
    return request.post(baseUrl + "/updateSaleOrder",{xsOrder:xsOrder});
}

export function saveUpdateSaleOrder(xsOrder) {
    return request.post(baseUrl + "/saveUpdateSaleOrder",{xsOrder:xsOrder});
}
//销售订单作废
export function cancelSaleOrder(orderNo) {
    return request.get(baseUrl + "/cancelSaleOrder", {
        params: {
            orderNo: orderNo,
        }
    });
}
//销售订单删除
export function deleteSaleOrder(orderNo) {
    return request.get(baseUrl + "/deleteSaleOrder", {
        params: {
            orderNo: orderNo,
        }
    });
}
