import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initRefundInWarehouseListByPage(kcSalefromware) {
    return request.post(baseUrl + "/getRefundInWarehouseListByPage",kcSalefromware);
}

//导出销售退货入库
export function refundInWarehouseExcel(kcSalefromware) {
    return request.post(baseUrl + "/refundInWarehouseExcel", {
        kcSalefromware: kcSalefromware,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '销售退货入库.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}