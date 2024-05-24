import request from '../utils/request.js';

const baseUrl = '/procurement';

export function initCgSqOrderList(vo) {
    return request.post(baseUrl + "/getCgSqOrderList", vo);
}

export function delCgsqOrderById(id) {
    return request.get(baseUrl + "/delCgsqOrderById",{
        params:{
            id:id
        }
    });
}

export function voidCgsqOrderById(id) {
    return request.get(baseUrl + "/voidCgsqOrderById",{
        params:{
            id:id
        }
    });
}

export function addCgddOrder(cgsqOrder) {
    return request.post(baseUrl + "/addCgsqOrder",{
        cgsqOrder:cgsqOrder
    });
}

export function updateCgsqOrder(cgsqOrder) {
    return request.post(baseUrl + "/updateCgsqOrder",{
        cgsqOrder:cgsqOrder
    });
}
export function getCgsqOrderById(id) {
    return request.get(baseUrl + "/getCgsqOrderById",{
        params:{
            id:id
        }
    });
}
export function approveCgsqOrder(id,approveRemark,approveMent) {
    return request.get(baseUrl + "/approveCgsqOrder",{
        params:{
            id:id,
            approveRemark:approveRemark,
            approveMent:approveMent
        }
    });
}
export function getCgsqOrderByCode(code) {
    return request.get(baseUrl + "/getCgsqOrderByCode",{
        params:{
            code:code
        }
    });
}

export function cgsqExcel(cgrkOrder) {
    return request.post(baseUrl + "/cgsqExcel", {
        cgrkOrder: cgrkOrder,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '采购申请订单.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

