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


export function kcReportedExcel(kcReportedVO) {
    return request.post(baseUrl + "/kcReportedExcel", {
        kcReportedVO: kcReportedVO,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '库存报损.xlsx';
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function kcReportedfromwareExcel(){
    return request.get(baseUrl + "/kcReportedfromwareExcel",{
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '报损出库.xlsx';
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function getKcReportedByCode(code) {
    return request.get(baseUrl + "/getKcReportedByCode",{
        params:{
            code:code
        }
    });
}