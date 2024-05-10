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
//修改回显
export function getBaseMedicineById(id) {
    return request.get(baseUrl + "/getBaseMedicineById", {
        params: {
            id: id,
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

export function getMedicineListByCode(code) {
    return request.get(baseUrl + "/getMedicineListByCode", {
        params: {
            code: code,
        }

    });
}
export function getAllBaseMedicine(code) {
    return request.get(baseUrl + "/getAllBaseMedicine")
}