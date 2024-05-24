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

