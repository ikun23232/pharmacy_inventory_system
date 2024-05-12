import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Antd from 'ant-design-vue'
import "ant-design-vue/dist/antd.css"
import global from './globalFun'
import axios from './utils/request'
import { Message } from "element-ui";


Vue.prototype.$axios = axios //
Vue.use(ElementUI);
Vue.use(Antd)
Vue.use(Message);
Vue.config.productionTip = false
Vue.prototype.$message = Message;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
