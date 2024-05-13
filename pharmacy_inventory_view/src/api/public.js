import request from '../utils/request.js';

const baseUrl = '/user';

export function getPayType() {
    return request.post(baseUrl + "/menus/getPayType");
}