import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initSaleOutWarehouseListByPage(kcSalefromware) {
    return request.post(baseUrl + "/getSaleOutWarehouseListByPage",kcSalefromware);
}

export function deleteSaleOutWarehouseOrder(code) {
    return request.get(baseUrl + "/deleteSaleOutWarehouseOrder",{
        params:{
            code:code
        }
    });
}

//导出销售出库
export function saleOutWarehouseExcel(kcSalefromware) {
    return request.post(baseUrl + "/saleOutWarehouseExcel", {
        kcSalefromware: kcSalefromware,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售出库.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}