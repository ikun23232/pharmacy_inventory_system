import request from '../utils/request.js';

const baseUrl = '/base';


export function initUnit(currentPageNo, pageSize) {
    return request.get(baseUrl + "/getUnitListByPage", {
        params: {
            currentPageNo: currentPageNo,
            pageSize: pageSize
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
    alert(reOrder.id)
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





