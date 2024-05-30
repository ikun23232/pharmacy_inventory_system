import request from '../utils/request.js';

const baseUrl = '/base';


export function initUnit(currentPageNo, pageSize,name) {
    return request.get(baseUrl + "/getUnitListByPage", {
        params: {
            currentPageNo: currentPageNo,
            pageSize: pageSize,
            name:name
        }
    });
}
export function delUnit(id) {
    return request.get(baseUrl + "/delUnitById", {
        params: {
            id: id
        }
    });
}
export function getUnitBy(id) {
    return request.get(baseUrl + "/getUnitById", {
        params: {
            id: id
        }
    });
}
export function addUnit(reOrder) {
    return request.post(baseUrl + "/addUnit", reOrder);
}
export function updateUnit(reOrder) {
    return request.post(baseUrl + "/updateUnit", reOrder);
}

export function CheckUnit(name,id) {
    return request.get(baseUrl + "/CheckUnit", {
        params: {
            unit: name,
            id:id
        }
    });
}

export function getAllBaseUnit() {
    return request.get(baseUrl + "/getAllBaseUnit")
}




