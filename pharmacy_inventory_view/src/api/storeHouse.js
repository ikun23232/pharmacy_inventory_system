import request from '../utils/request.js';

const baseUrl = '/base';

export function getStoreList(pageNum,pageSize,code) {
    return request.post(baseUrl + "/getStoreHouseList",{
        page:{
            pageNum:pageNum,
            pageSize:pageSize
        },
        code:code
    });
}

export function deteleStoreHouse(id) {
    return request.get(baseUrl + "/deleteStorehouse",{
        params:{
            id:id
        }
    });
}

export function addStoreHouse(BaseStorehouse) {
    return request.post(baseUrl + "/addStoreHouse", BaseStorehouse);
}

export function checkName(name) {
    return request.get(baseUrl + "/checkName", {
        params:{
            name:name
        }
    });
}


export function getStoreHouseById(id) {
    return request.get(baseUrl + "/getStoreHouseById", {
        params:{
            id:id
        }
    });
}

export function updateStoreHouse(BaseStorehouse) {
    return request.post(baseUrl + "/updateStoreHouse",BaseStorehouse);
}