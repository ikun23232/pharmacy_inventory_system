import request from '../utils/request.js';

const baseUrl = '/warehouse';

export function getStoreList(dispatchVO) {
    return request.post(baseUrl + "/getKcDispathList",);
}