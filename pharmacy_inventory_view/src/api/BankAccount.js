import request from '../utils/request.js';

const baseUrl = '/base';

const init = (bandCount,name,belongBank,currentPageNo, pageSize) => {
    return request.get(baseUrl + "/bankAccount/getBankAccount", {
        params: {
            belongBank:belongBank,
            bandCount: bandCount,
            name: name,
            currentPageNo: currentPageNo,
            pageSize: pageSize
        }
    })
}
const addBankAccount = (formdata) => {
    return request.post(baseUrl + "/bankAccount/addBankAccount", formdata)
}
const updateBankAccount = (formdata) => {
    return request.post(baseUrl + "/bankAccount/updateBankAccount", formdata)
}
const delBankAccount = (bandCount) => {
    return request.get(baseUrl + "/bankAccount/delBankAccountBybandCount", {
        params: {
            bandCount: bandCount
        }
    })
}
const getBankAccountBybandCount = (bandCount) => {
    return request.get(baseUrl + "/bankAccount/getBankAccountBybandCount", {
        params: {
            bandCount: bandCount
        }
    })
}

const getAllBankCountList = () => {
    return request.get(baseUrl + "/bankAccount/getAllBankCountList")
}

export {
    init,
    addBankAccount,
    updateBankAccount,
    delBankAccount,
    getBankAccountBybandCount,
    getAllBankCountList
}