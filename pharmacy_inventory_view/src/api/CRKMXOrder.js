import request from '../utils/request.js';

const baseUrl = '/warehouse';


export function initCRKMXDetailList(vo) {
    return request.post(baseUrl + "/getKcOutIntoDetailList", vo);
}


export function getKcTypeList() {
    return request.post(baseUrl + "/getKcTypeList");
}

export function delKcOutinTodetail(id) {
    return request.get(baseUrl + "/delKcOutinTodetail",{
        params:{
            id:id
        }
    });
}

export function CRKMXExcel(vo) {
    return request.post(baseUrl + "/crkmxExcel", {
        vo: vo,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '出入库明细.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}
