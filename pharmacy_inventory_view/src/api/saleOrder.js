import request from '../utils/request.js';

const baseUrl = '/sale';

//销售订单分页查询
export function initSaleOrder(xsOrder) {
    return request.post(baseUrl + "/getSaleOrderListByPage",xsOrder);
}

//查询已完成的销售订单
// export function initSaleOrder(xsOrder) {
//     return request.post(baseUrl + "/getSaleOrderListByPage",xsOrder);
// }

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
//导出销售订单
export function saleOrderExcel(xsOrder) {
    return request.post(baseUrl + "/saleOrderExcel", {
        xsOrder: xsOrder,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售订单.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}