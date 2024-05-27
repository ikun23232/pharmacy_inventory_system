import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function initKcAlarmList(vo) {
    return request.post(baseUrl + "/getKcAlarmList", vo);
}

export function deleteAlarmList(id) {
    return request.get(baseUrl + "/deleteAlarmList",{
        params:{
            id:id
        }
    });
}

export function updateRemind(id) {
    return request.get(baseUrl + "/updateRemind",{
        params:{
            id:id
        }
    });
}

export function excelKcAlarm(kcAlarmVO) {
    return request.post(baseUrl + "/excelKcAlarm", {
        KcAlarmVO: kcAlarmVO,
    }, {
        responseType: 'blob'
    }).then(resp => {
        let blob = new Blob([resp], { type: 'application/xlsx' });
        let url = window.URL.createObjectURL(blob);
        const link = document.createElement('a'); //创建a标签
        link.href = url;
        link.download = '库存告警.xlsx'; //重命名文件
        link.click();
        URL.revokeObjectURL(url);
    }).catch(error =>{
        console.log("error:",error)
    })
}
export function checkWarning() {
    return request.get(baseUrl + "/checkWarning");
}

export function getAlarmCount() {
    return request.get(baseUrl + "/getAlarmCount");
}


