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

export function getStorehouseList(){
    return request.get(baseUrl + "/getStorehouseList");
}

export function cwbsysExcel() {
    return request.get(baseUrl + "/cwbsysExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '报损应收.xlsx';
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function cwCgyfExcel() {
    return request.get(baseUrl + "/cwCgyfExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '采购费用.xlsx';
        link.click();
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function cwXstkExcel() {
    return request.get(baseUrl + "/cwXstkExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '销售退款.xlsx';
        link.click();
   }).catch(error =>{
       console.log("error:",error)
    })
}

export function cwXsysExcel() {
    return request.get(baseUrl + "/cwXsysExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '销售应收.xlsx';
        link.click();
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function cwInvoiceExcel() {
    return request.get(baseUrl + "/cwInvoiceExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '发票.xlsx';
        link.click();
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function cwAccountsExcel() {
    return request.get(baseUrl + "/cwAccountsExcel", {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = '财务总账.xlsx';
        link.click();
   }).catch(error =>{
       console.log("error:",error)
    })
}

export function getCwbsysByCode(code) {
    return request.get(baseUrl + "/getCwbsysByCode", {
        params: {
            code:code
        }
    });
}

export function getCwCgyfByCode(code) {
    return request.get(baseUrl + "/getCwCgyfByCode", {
        params: {
            code:code
        }
    });
}

export function getCwXstkByCode(code) {
    return request.get(baseUrl + "/getCwXstkByCode", {
        params: {
            code:code
        }
    });
}

export function getCwXsysByCode(code) {
    return request.get(baseUrl + "/getCwXsysByCode", {
        params: {
            code:code
        }
    });
}

export function getCwInvoiceByCode(code) {
    return request.get(baseUrl + "/getCwInvoiceByCode", {
        params: {
            code:code
        }
    });
}

export function updateCwCgyf(cwCgyf) {
    return request.post(baseUrl + "/updateCwCgyf",cwCgyf);
}

export function getCWStatistics() {
    return request.get(baseUrl + "/getCWStatistics");
}

export function getCWStatisticsPicture() {
    return request.get(baseUrl + "/getCWStatisticsPicture");
}