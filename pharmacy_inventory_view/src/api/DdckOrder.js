import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initKcDisfromwareList(vo) {
    return request.post(baseUrl + "/getKcDisfromwareList", vo);
}



export function delKcDisfromware(id) {
    return request.get(baseUrl + "/delKcDisfromware",{
        params:{
            id:id
        }
    });
}

export function DdckExcel(kcDisfromware) {
    return request.post(baseUrl + "/ddckExcel", {
        kcDisfromware: kcDisfromware,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '调度出库单.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}
export function getKcDisfromwareOrder(id) {
    return request.get(baseUrl + "/getKcDisfromwareOrder",{
        params:{
            id:id
        }
    });
}

