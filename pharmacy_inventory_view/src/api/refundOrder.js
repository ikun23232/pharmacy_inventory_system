import request from '../utils/request.js';

const baseUrl = '/sale';

//退款订单分页查询
export function initRefundOrder(xsOrder) {
    return request.post(baseUrl + "/getRefundOrderListByPage",xsOrder);
}

//添加退货订单
export function addRefundOrder(xsOrder) {
    return request.post(baseUrl + "/addRefundOrder",{xsOrder:xsOrder});
}
//获取退货原因
export function getAllRefundTypeList() {
    return request.post(baseUrl + "/getAllRefundTypeList");
}

//保存退货订单
export function saveRefundOrder(xsOrder) {
    return request.post(baseUrl + "/saveRefundOrder",{xsOrder:xsOrder});
}
//审核
export function checkedRefundOrder(xsOrder) {
    return request.post(baseUrl + "/checkedRefundOrder",{
        xsOrder:xsOrder
    });
}

// export function updateRefundOrder(xsOrder) {
//     return request.post(baseUrl + "/updateRefundOrder",{xsOrder:xsOrder});
// }

// export function saveUpdateRefundOrder(xsOrder) {
//     return request.post(baseUrl + "/saveUpdateRefundOrder",{xsOrder:xsOrder});
// }


//退货订单作废
// export function cancelRefundOrder(orderNo) {
//     return request.get(baseUrl + "/cancelRefundOrder", {
//         params: {
//             orderNo: orderNo,
//         }
//     });
// }
//退货订单删除
// export function deleteRefundOrder(orderNo) {
//     return request.get(baseUrl + "/deleteRefundOrder", {
//         params: {
//             orderNo: orderNo,
//         }
//     });
// }
