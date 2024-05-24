import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getKcDetailsList(code) {
    return request.get(baseUrl + "/getKcDetailsList",{
        params:{
            dispatchCode:code
        }
    });
}