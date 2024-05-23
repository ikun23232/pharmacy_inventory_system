import request from '../utils/request.js';

const baseUrl = '/sale';

//汇总统计（分页
export function getSaleOrderStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getSaleOrderStatisticsList",orderStatistics);
}

export function getRefundOrderStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getRefundOrderStatisticsList",orderStatistics);
}
//视图统计（不分页
export function getAllSaleOrderStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getAllSaleOrderStatisticsList",orderStatistics);
}

export function getAllRefundOrderStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getAllRefundOrderStatisticsList",orderStatistics);
}
//明细统计(分页)
export function getSaleOrderDetailStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getSaleOrderDetailStatisticsList",orderStatistics);
}

export function getRefundOrderDetailStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getRefundOrderDetailStatisticsList",orderStatistics);
}

export function getALLSaleOrderDetailStatisticsList(orderStatistics) {
    return request.post(baseUrl + "/getALLSaleOrderDetailStatisticsList",orderStatistics);
}

