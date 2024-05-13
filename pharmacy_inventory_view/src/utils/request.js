import axios from "axios";
import router from "@/router/index";
const instance = axios.create({
    baseURL: '/',
    // timeout: 5000
})

// 添加请求拦截器
instance.interceptors.request.use(async (request) => {
    // // 添加签名和时间戳
    // let resp=await axios.get('/system/getUUID')
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

		console.log("response ->" + response)

		let res = response.data

		if (res.code === "200") {
			return response.data
		} else {
			console.log(response+"111");
			console.log(res);
			Element.Message.error(!res.msg ? '系统异常' : res.msg)
			return Promise.reject(response.data.msg)
		}
	},
	error => {

		console.log(error+"1111")
		console.log(error.response)

		if (error.response.data) {
			error.massage = error.response.data.msg
		}

		if (error.response.status === 500) {
			router.push("/login")
		}

		Element.Message.error(error.response.data.massage, {duration: 3000})
		return Promise.reject(error)
	}
)
export default instance
