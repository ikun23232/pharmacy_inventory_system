import axios from "axios";

const instance = axios.create({
    baseURL: '/api',
    // timeout: 5000
})

// 添加请求拦截器
instance.interceptors.request.use(async (request) => {
    // // 添加签名和时间戳
    // let resp=await axios.get('/api/getUUID')
    // console.log(resp.data)
    // request.headers['signature'] = resp.data;
    // request.headers['timestamp'] = Date.now();
        if(store.state.token){
        config.headers.token=store.state.token
      }
    return request;
}, (error) => {
    // 处理错误
    return Promise.reject(error);
});

// 添加响应拦截器
request.interceptors.response.use(
	response => {

		console.log("response ->" + response)

		let res = response.data

		if (res.code === 200) {
			return response
		} else {
			Element.Message.error(!res.msg ? '系统异常' : res.msg)
			return Promise.reject(response.data.msg)
		}
	},
	error => {

		console.log(error)

		if (error.response.data) {
			error.massage = error.response.data.msg
		}

		if (error.response.status === 401) {
			router.push("/login")
		}

		Element.Message.error(error.massage, {duration: 3000})
		return Promise.reject(error)
	}
)
export default instance
