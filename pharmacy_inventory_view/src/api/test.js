import request from '../utils/request.js';

const baseUrl = '/address';

//例如
const getUserAddress = (page,currentUser) => {
    return request.post(baseUrl + "/getUserAddress",{
        page:page,
        currentUser:currentUser
    });
}
const UpdateAddress = (address,currentUser) => {
    return request.post(baseUrl + "/UpdateAddress",{
        address:address
    });
}
const setDefault = (address,currentUser) => {
    return request.post(baseUrl + "/setDefault",{
        address:address,
        currentUser:currentUser
    });
}
const delAddress = (address) => {
    return request.post(baseUrl + "/delAddress",{
        address:address
    });
}
const addAddress = (address,currentUser) => {
    return request.post(baseUrl + "/addAddress",{
        address:address,
        currentUser:currentUser
    });
}
const getById = (address,currentUser) => {
    return request.post(baseUrl + "/getById",{
        address:address,
        currentUser:currentUser
    });
}
const  getLoginAddressDef = (currentUser) =>{
    return request.post(baseUrl + "/getLoginAddressDef",{currentUser:currentUser})
}
export {
    getUserAddress,
    UpdateAddress,
    delAddress,
    addAddress,
    setDefault,
    getById,
    getLoginAddressDef
}