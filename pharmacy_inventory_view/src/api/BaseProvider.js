import request from '../utils/request.js';

const baseUrl = '/base';

const init = (input, type, currentPageNo, pageSize) => {
    return request.get(baseUrl + "/baseProvider/getBaseProviderList", {
        params: {
            name: input,
            type: type,
            currentPageNo: currentPageNo,
            pageSize: pageSize
        }
    })
}
const getProviderType = async () => {
    return request.post(baseUrl + "/baseProviderType/getBaseProviderTypeList")
}

const addBaseProvider = (formdata) => {
    return request.post(baseUrl + "/baseProvider/addBaseProvider", formdata)
}
const updateBaseProvider = (formdata) => {
    return request.post(baseUrl + "/baseProvider/updateBaseProvider", formdata)
}
const delBaseProvider = (id) => {
    return request.get(baseUrl + "/baseProvider/delBaseProvider", {
        params: {
            id: id
        }
    })
}
const getBaseProviderById = (id) => {
    return request.get(baseUrl + "/baseProvider/getBaseProviderById", {
        params: {
            id: id
        }
    })
}
const checkaddBaseProvider = (name) => {
    return request.get(baseUrl + "/baseProvider/checkaddBaseProvider", {
        params:{
            name:name   
        }
    })
}
const checkupdateBaseProvider = (name,id) => {
    return request.get(baseUrl + "/baseProvider/checkupdateBaseProvider", {
        params:{
            name:name,
            id:id
        }
    })
}
export {
    init,
    getProviderType,
    addBaseProvider,
    getBaseProviderById,
    updateBaseProvider,
    delBaseProvider,
    checkaddBaseProvider,
    checkupdateBaseProvider
}