import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import storeHouse from '../views/storeHouse.vue'
import BaseUnit from "../views/BaseUnit";

Vue.use(VueRouter)

const routes = [
  {
    path: '/storeHouse',
    name: 'storeHouse',
    component: storeHouse
  },
  {
    path: '/baseUnit',
    name: 'BaseUnit',
    component: BaseUnit
  }
]

const router = new VueRouter({
  routes
})

export default router
