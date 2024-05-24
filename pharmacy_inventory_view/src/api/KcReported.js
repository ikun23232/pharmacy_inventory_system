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

export function addKcReportedAll(theData) {
    return request.post(baseUrl + "/addKcReportedAll",{theData:theData});
}

export function getKcMedicine(kcMedicine,pageNum,pageSize) {
    return request.post(baseUrl + "/getKcMedicine",kcMedicine,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getKcMedicineByReportedCode(storehouseId,reportedCode) {
    return request.get(baseUrl + "/getKcMedicineByReportedCode",{
        params:{
            storehouseId:storehouseId,
            reportedCode:reportedCode
        }
    })

}

export function updateReportedByCode(theData) {
    return request.post(baseUrl + "/updateReportedByCode",{theData:theData});
}

export function delKcReportedAndDetailByCode(code) {
    return request.get(baseUrl + "/delKcReportedAndDetailByCode",{
        params:{
            code:code
        }
    });
}

export function getKcReportedfromware(kcReportedfromware,pageNum,pageSize) {
    return request.post(baseUrl + "/getKcReportedfromware",kcReportedfromware,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getKcReportedListById(id) {
    return request.get(baseUrl + "/getKcReportedListById",{
        params:{
            id:id
        }
    });

}