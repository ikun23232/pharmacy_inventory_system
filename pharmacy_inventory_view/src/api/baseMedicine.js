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