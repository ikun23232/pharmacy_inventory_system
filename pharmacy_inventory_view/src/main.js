import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Antd from 'ant-design-vue'
import "ant-design-vue/dist/antd.css"

import { Message, MessageBox } from 'element-ui'  //引入Message, MessageBox

Vue.prototype.$message = Message   //vue实例上挂载Message                
Vue.prototype.$messagebox = MessageBox   //vue实例上挂载MessageBox


Vue.use(ElementUI);
Vue.use(Antd)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
