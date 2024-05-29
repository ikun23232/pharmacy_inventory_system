import request from '../utils/request.js';

const baseUrl = '/user';

export function getAllUser() {
    return request.post(baseUrl + "/getAllUser");
}
