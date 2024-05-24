import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initKcDistowareList(vo) {
    return request.post(baseUrl + "/getKcDistowareList", vo);
}



export function delKcDistoware(id) {
    return request.get(baseUrl + "/delKcDistoware",{
        params:{
            id:id
        }
    });
}

export function ddckExcel(kcDisfromware) {
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
export function getKcDistowareOrder(id) {
    return request.get(baseUrl + "/getKcDistowareOrder",{
        params:{
            id:id
        }
    });
}

