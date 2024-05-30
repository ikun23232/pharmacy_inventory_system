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

//导出销售订单统计
export function saleOrderStatisticsExcel(orderStatistics) {
    return request.post(baseUrl + "/saleOrderStatisticsExcel", {
        orderStatistics: orderStatistics,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售订单统计.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}
//导出销售退货订单统计
export function refundOrderStatisticsExcel(orderStatistics) {
    return request.post(baseUrl + "/refundOrderStatisticsExcel", {
        orderStatistics: orderStatistics,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售退货订单统计.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}


export function getSalesStatistics() {
    return request.get(baseUrl + "/getSalesStatistics");
}

export function getSalesStatisticsTK() {
    return request.get(baseUrl + "/getSalesStatisticsTK");
}

//导出销售订单明细统计
export function saleOrderDetailStatisticsExcel(orderStatistics) {
    return request.post(baseUrl + "/saleOrderDetailStatisticsExcel", {
        orderStatistics: orderStatistics,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售订单明细统计.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}
//导出销售退货订单明细统计
export function refundOrderDetailStatisticsExcel(orderStatistics) {
    return request.post(baseUrl + "/refundOrderDetailStatisticsExcel", {
        orderStatistics: orderStatistics,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售退货订单明细统计.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}