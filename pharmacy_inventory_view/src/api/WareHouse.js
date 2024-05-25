import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getKcDispathList(dispatchVO) {
    return request.post(baseUrl + "/getKcDispathList",dispatchVO);
}