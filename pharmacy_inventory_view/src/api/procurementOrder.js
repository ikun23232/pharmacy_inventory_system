import request from '../utils/request.js';

const baseUrl = '/procurement';

export function getProcList(procPage) {
    return request.post(baseUrl + "/getCgddOrder", procPage);
}

export function deleteById(id) {
    return request.get(baseUrl + "/deleteById", {
        params: {
            id: id
        }
    });
}


export function getCgPayCom(year,month) {
    return request.get(baseUrl + "/getCgPayCom", {
        params:{
            year:year,
            month:month
        }
    });
}

export function getCgPayNum(year) {
    return request.get(baseUrl + "/getCgPayNum", {
        params:{
            year:year
        }
    });
}

export function getCgPayNumList(year,pageNum,pageSize) {
    return request.get(baseUrl + "/getCgPayNumList", {
        params:{
            year:year,
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function addCgddOrder(cgddOrder) {
    return request.post(baseUrl + "/addCgddOrder", {
        cgddOrder: cgddOrder
    });
}

export function setVoidState(id, voidState) {
    return request.get(baseUrl + "/setVoidState", {
        params: {
            id: id,
            voidState: voidState
        }
    });
}

export function getCgddByCode(code) {
    return request.get(baseUrl + "/getCgddByCode", {
        params: {
           code:code
        }
    });
}

export function updateCgddById(cgddOrder) {
    return request.post(baseUrl + "/updateCgddById", {
        cgddOrder: cgddOrder
    });
}

export function auditingOrder(cgddOrder) {
    return request.post(baseUrl + "/auditingOrder", {
        cgddOrder: cgddOrder
    });
}

export function cgddExcel(cgddOrder) {
    return request.post(baseUrl + "/cgddExcel", {
        cgddOrder: cgddOrder,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '采购订单.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function addcgyf(cwCgyf) {
    return request.post(baseUrl + "/addcgyf", cwCgyf );
}

export function getCheckHintList() {
    return request.get(baseUrl + "/getCheckHintList");
}

export function getCgStatistics() {
    return request.get(baseUrl + "/getCgStatistics");
}