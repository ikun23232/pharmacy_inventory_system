import request from '../utils/request.js';

const baseUrl = '/procurement';

export function getProcList(procPage) {
    return request.post(baseUrl + "/getCgddOrder",procPage);
}

export function deleteById(id) {
    return request.get(baseUrl + "/deleteById", {
        params:{
            id:id
        }
    });
}


export function getCgPayCom(year,month) {
    return request.get(baseUrl + "/getCgPayCom", {
        params:{
            year:year,
            month:month
        }
    });
}

export function getCgPayNum(year) {
    return request.get(baseUrl + "/getCgPayNum", {
        params:{
            year:year
        }
    });
}

export function getCgPayNumList(year,pageNum,pageSize) {
    return request.get(baseUrl + "/getCgPayNumList", {
        params:{
            year:year,
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}