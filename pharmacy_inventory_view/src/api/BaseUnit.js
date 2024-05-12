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




