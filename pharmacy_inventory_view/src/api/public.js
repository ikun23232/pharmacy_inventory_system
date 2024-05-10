import request from '../utils/request.js';

const baseUrl = '/menus';

export function getPayType() {
    return request.post(baseUrl + "/getPayType");
}