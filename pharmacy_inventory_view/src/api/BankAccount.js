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
const getBankAccountBybandCount = (updatebelongBank,updatename,updatebandCount) => {
    return request.get(baseUrl + "/bankAccount/getBankAccountBybandCount", {
        params: {
            belongBank: updatebelongBank,
            name:updatename,
            bandCount:updatebandCount
        }
    })
}
const checkaddBankAccount = (belongBank,name,bandCount) => {
    return request.get(baseUrl + "/bankAccount/checkaddBankAccount", {
        params: {
            belongBank:belongBank,
            name:name,
            bandCount: bandCount
        }
    })
}
const checkupdateBankAccount = (belongBank,name,bandCount,id) => {
    return request.get(baseUrl + "/bankAccount/checkupdateBankAccount", {
        params: {
            belongBank:belongBank,
            name:name,
            bandCount: bandCount,
            id:id
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
    checkaddBankAccount,
    checkupdateBankAccount,
    getAllBankCountList
}