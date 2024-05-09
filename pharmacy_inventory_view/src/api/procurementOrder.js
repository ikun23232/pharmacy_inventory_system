import request from '../utils/request.js';

const baseUrl = '/procurement';

export function getProcList(procPage) {
    return request.post(baseUrl + "/getCgddOrder",procPage);
}
