import request from '../utils/request.js';

const baseUrl = '/baseProvider';

const init = (input, type, currentPageNo, pageSize) => {
    return request.get(baseUrl + "/getBaseProviderList", {
        params: {
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

const addBaseProvider = (formdata) => {
    return request.post(baseUrl + "/addBaseProvider", formdata)
}
const updateBaseProvider = (formdata) => {
    return request.post(baseUrl + "/updateBaseProvider", formdata)
}
const delBaseProvider = (id) => {
    return request.get(baseUrl + "/delBaseProvider", {
        params: {
            id: id
        }
    })
}
const getBaseProviderById = (id) => {
    return request.get(baseUrl + "/getBaseProviderById", {
        params: {
            id: id
        }
    })
}
export {
    init,
    getProviderType,
    addBaseProvider,
    getBaseProviderById,
    updateBaseProvider,
    delBaseProvider
}