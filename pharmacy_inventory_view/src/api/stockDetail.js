import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initStockDetailListByPage(baseMedicine) {
    return request.post(baseUrl + "/getStockDetailListByPage",baseMedicine);
}

export function initStockDetailList() {
    return request.post(baseUrl + "/getStockDetailList");
}

export function deleteStockDetail(medicineId) {
    return request.get(baseUrl + "/deleteStockDetail",{
        params:{
            medicineId:medicineId
        }
    });
}

//导出库存明细
export function stockDetailExcel(baseMedicine) {
    return request.post(baseUrl + "/stockDetailExcel", {
        baseMedicine: baseMedicine,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '库存明细.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}

export function getKcMedicineByWareHouseId(baseMedicine) {
    return request.post(baseUrl + "/getKcMedicineByWareHouseId",baseMedicine);
}
