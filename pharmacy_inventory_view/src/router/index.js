import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BaseUnit from "@/views/BaseUnit";
import BaseProvider from '../views/base/BaseProviderList.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'BaseUnit',
    component: BaseUnit
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },{
    path: '/baseUnit',
    name: 'BaseUnit',
    component: BaseUnit
  },
  {
    path: '/baseProvider',
    name: 'baseProvider',
    component: BaseProvider
  }
]

const router = new VueRouter({
  routes
})

export default router
