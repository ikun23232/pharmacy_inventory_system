import request from '../utils/request.js';

const baseUrl = '/baseProvider';

const init = (input, type, currentPageNo, pageSize) => {
    return request.get(baseUrl + "/getBaseProviderList", {
        params:{
            name: input,
            type: type,
            currentPageNo: currentPageNo,
            pageSize: pageSize
        }
    })
}
const getProviderType = async () => {
    return request.post("/baseProviderType/getBaseProviderTypeList")
}
export {
    init,
    getProviderType
}