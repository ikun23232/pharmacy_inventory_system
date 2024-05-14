import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getKcReportedList(kcReported,pageNum,pageSize) {
    return request.post(baseUrl + "/getKcReportedList",kcReported,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}
export function getCodeByCode(code) {
    return request.post(baseUrl + "/getCodeByCode",{
        code:code
    });
}

export function getStorehouseList() {
    return request.get(baseUrl + "/getStorehouseList");
}

export function getReportedType() {
    return request.get(baseUrl + "/getReportedType");
}

export function addKcReported(kcReported) {
    return request.post(baseUrl + "/addKcReported",kcReported);
}

export function getKcMedicine(kcMedicine,pageNum,pageSize) {
    return request.post(baseUrl + "/getKcMedicine",kcMedicine,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}