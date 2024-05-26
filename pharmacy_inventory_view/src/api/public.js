
import request from '../utils/request.js';

const baseUrl = '/user';

export function getPayType() {
    return request.post(baseUrl + "/menus/getPayType");
}
export function setExcel(list, name) {
    return request.post(baseUrl + "/setExcel", {
        data: list,
        name: name
    }, {
        responseType: 'blob'
    }).then(response => {
        // 处理响应数据
        const blob = new Blob([response.data]);
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = name + '.xlsx';
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
    }).catch(error => {
        console.error('Error:', error);
    });;
}
