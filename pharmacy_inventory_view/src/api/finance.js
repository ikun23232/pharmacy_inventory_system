import request from '../utils/request.js';

const baseUrl = '/finance';

export function getCwCgyfList(cwCgyf,pageNum,pageSize) {
    return request.post(baseUrl + "/getCwCgyfList",cwCgyf,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getCgddByCode(code) {
    return request.get(baseUrl + "/getCgddByCode", {
        params: {
            code:code
        }
    });
}

export function getCwbsysList(cwBsys,pageNum,pageSize) {
    return request.post(baseUrl + "/getCwbsysList",cwBsys,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getXstkList(cwXstk,pageNum,pageSize) {
    return request.post(baseUrl + "/getXstkList",cwXstk,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getXsysList(cwXsys,pageNum,pageSize){
    return request.post(baseUrl + "/getXsysList",cwXsys,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getCwInvoice(cwInvoice,pageNum,pageSize){
    return request.post(baseUrl + "/getCwInvoice",cwInvoice,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getCategoryString(){
    return request.get(baseUrl + "/getCategoryString");
}

export function getCwAccountsList(cwAccounts,pageNum,pageSize){
    return request.post(baseUrl + "/getCwAccountsList",cwAccounts,{
        params:{
            pageNum:pageNum,
            pageSize:pageSize
        }
    });
}

export function getCwNumByYear(year){
    return request.get(baseUrl + "/getCwNumByYear",{
        params:{
            year:year
        }
    });
}

export function getCwNumByMonth(year,month){
    return request.get(baseUrl + "/getCwNumByMonth",{
        params:{
            year:year,
            month:month
        }
    });
}

export function getProviderList(){
    return request.get(baseUrl + "/getProviderList");
}