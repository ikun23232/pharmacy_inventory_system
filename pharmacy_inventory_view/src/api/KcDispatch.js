import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getKcDispathList(dispatchVO) {
    return request.post(baseUrl + "/getKcDispathList",dispatchVO);
}

export function addKcDispatch(KcDispatch) {
    return request.post(baseUrl + "/addKcDispatch",{
        KcDispatch:KcDispatch
    });
}

export function getKcDispatchById(id) {
    return request.get(baseUrl + "/getKcDispatchById",{
        params:{
            id:id
        }
    });
}

export function updateDispatchBy(KcDispatch) {
    return request.post(baseUrl + "/updateDispatchBy",{
        KcDispatch:KcDispatch
    });
}

export function auditingDispatch(KcDispatch) {
    return request.post(baseUrl + "/auditingDispatch",{
        KcDispatch:KcDispatch
    });
}

export function deleteDispatch(id) {
    return request.get(baseUrl + "/deleteDispatch",{
        params:{
            id:id
        }
    });
}

export function updateVoidStatus(id) {
    return request.get(baseUrl + "/updateVoidStatus",{
        params:{
            id:id
        }
    });
}

export function excelDispatch(dispatchVO) {
    return request.post(baseUrl + "/excelDispatch",{
        DispatchVO:dispatchVO
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '库存告警.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}