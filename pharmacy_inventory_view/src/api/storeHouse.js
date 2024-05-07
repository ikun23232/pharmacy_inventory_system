import request from '../utils/request.js';

const baseUrl = '/base/storehouse';

const getStoreList = (page,code) => {
    return request.post(baseUrl + "/getUserAddress",{
        page:{
            pageNum:page.pageNum,
            pageSize:page.pageSize
        },
        code:code
    });
}

export {
    getStoreList
}