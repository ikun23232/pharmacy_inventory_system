import request from '../utils/request.js';

const baseUrl = '/baseProvider';

const init = async (input, type, currentPageNo, pageSize) => {
    return request.post(baseUrl + "/getBaseProviderList", {
        name: input,
        type: type,
        currentPageNo: currentPageNo,
        pageSize: pageSize
    })
}
const getProviderType = async () => {
    return request.post("/baseProviderType/getBaseProviderTypeList")
}
export {
    init,
    getProviderType
}