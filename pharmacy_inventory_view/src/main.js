import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Antd from 'ant-design-vue'
import "ant-design-vue/dist/antd.css"
import VueRouter from 'vue-router'
import { Message, MessageBox } from 'element-ui'  //引入Message, MessageBox
import axios from './utils/request'
import global from './globalFun'
Vue.prototype.$message = Message                //vue实例上挂载Message
Vue.prototype.$messagebox = MessageBox             //vue实例上挂载MessageBox
Vue.prototype.$axios = axios 
import Print from 'vue-print-nb'



Vue.use(VueRouter)
Vue.use(ElementUI);
Vue.use(Antd)
// Vue.use(Message);
Vue.use(Print)
Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
