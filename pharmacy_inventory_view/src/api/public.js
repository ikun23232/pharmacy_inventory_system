import request from '../utils/request.js';

const baseUrl = '/menus';

export function getPayType() {
    return request.post(baseUrl + "/getPayType");
}
export function setExcel(list, name) {
    return request.post(baseUrl + "/setExcel", {
        data: list,
        name: name
    }, {
        responseType: 'blob'
    }).then(resp =>{
        
    });
}