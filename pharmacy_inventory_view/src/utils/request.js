import axios from "axios";

const instance = axios.create({
    baseURL: '/base',
    // timeout: 5000
})

// 添加请求拦截器
instance.interceptors.request.use(async (request) => {
    // // 添加签名和时间戳
    // let resp=await axios.get('/system/getUUID')
    // console.log(resp.data)
    // request.headers['signature'] = resp.data;
    // request.headers['timestamp'] = Date.now();
    return request;
}, (error) => {
    // 处理错误
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(request => request.data, error => {
    if (error.response.status === 401) {
        // 未授权错误，跳转到login.html界面
        window.location.href = '/Login.html';
    }
    if (error.response.status === 403) {
        // 未授权错误，跳转到login.html界面
        window.location.href = '/index.html';
    }
    return Promise.reject(error);})

export default instance
