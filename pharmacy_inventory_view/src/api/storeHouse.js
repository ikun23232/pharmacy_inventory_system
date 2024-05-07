import request from '../utils/request.js';

const baseUrl = '/base';

export function getStoreList(page,code) {
    return request.post(baseUrl + "/getStoreHouseList",{
        page:{
            pageNum:page.pageNum,
            pageSize:page.pageSize
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
