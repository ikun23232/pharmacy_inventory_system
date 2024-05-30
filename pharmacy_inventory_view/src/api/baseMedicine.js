import request from '../utils/request.js';

const baseUrl = '/base';

export function initMedicine(baseMedicine) {
    return request.post(baseUrl + "/getBaseMedicineListByPage",baseMedicine);
}

export function addBaseMedicine(baseMedicine) {
    return request.get(baseUrl + "/addBaseMedicine", {
        params: {
            baseMedicine: baseMedicine,
        }

    });
}

export function getBaseMedicineById(id,batchCode) {
    return request.get(baseUrl + "/getBaseMedicineById", {
        params: {
            id: id,
            batchCode:batchCode
        }

    });
}
export function updateBaseMedicine(baseMedicine) {
    return request.post(baseUrl + "/updateBaseMedicineById", baseMedicine);
}

export function deleteBaseMedicine(id) {
    return request.get(baseUrl + "/deleteBaseMedicineById", {
        params: {
            id: id,
        }

    });
}

export function getBaseMedicineListByProviderId(id) {
    return request.get(baseUrl + "/getBaseMedicineListByProviderId", {
        params: {
            providerId: id,
        }
    });
}
export function getMedicineListByCode(code) {
    return request.get(baseUrl + "/getMedicineListByCode", {
        params: {
            code: code,
        }

    });
}
export function getAllBaseMedicine() {
    return request.get(baseUrl + "/getAllBaseMedicine")
}

export function getAllBatchCodeByMedicineId(medicineId) {
    return request.get(baseUrl + "/getAllBatchCodeByMedicineId",{
        params: {
            medicineId: medicineId,
        }
    })
}
export function getMedicineListByCodeComblie(code) {
    return request.get(baseUrl + "/getMedicineListByCodeComblie", {
        params: {
            code: code,
        }

    });
}

//导出库存明细
export function baseMedicineExcel(baseMedicine) {
    return request.post(baseUrl + "/baseMedicineExcel", {
        baseMedicine: baseMedicine,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '医用商品.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}