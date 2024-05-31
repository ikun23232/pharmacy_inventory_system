import axios from "axios";
import router from "@/router/index";
import Element from "element-ui"
const instance = axios.create({
    baseURL: '/',
    // timeout: 5000
})

// 添加请求拦截器
instance.interceptors.request.use(async (request) => {
    // // 添加签名和时间戳
    // let resp=await axios.get('/user/sysCommon/getUUID')
    // console.log(resp.data)
    // request.headers['signature'] = resp.data;
    // request.headers['timestamp'] = Date.now();
    //     if(store.state.token){
    //     config.headers.token=store.state.token
    //   }
	request.headers['Authorization'] = localStorage.getItem("token")
    return request;
}, (error) => {
    // 处理错误
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(
	response => {
		console.log("sadasfaffffff");
		console.log(response);
		let res = response.data
		console.log(res+"1111")
		console.log(res)
		if (res.code === "200") {
			return response.data
		} else if (res.code === "202") {
			return response.data
		}else if(response.status===200){
			return response.data
		} else {
			console.log(response+"1111111111111111111111111111111111111111111");
			console.log(res);
			Element.Message.error(!res.message ? '系统异常' : res.message)
			return Promise.reject(response)
		}
	},
	error => {
		console.log(error);
		if (error.response.data) {
			error.message = error.response.data.message;
			if (error.response.data.disableMessage) {
				error.disableMessage = error.response.data.disableMessage;
			}
		}

		if (error.response.status === 500) {
			// router.push("/login");
		}

		Element.Message.error(error.response.data.message, {duration: 3000});
		if (error.disableMessage) {
			console.log(error.disableMessage); // 获取账号禁止信息
		}
		return Promise.reject(error);
	}
)
export default instance
