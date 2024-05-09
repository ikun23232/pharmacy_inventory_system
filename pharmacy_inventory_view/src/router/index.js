import Vue from 'vue'
import VueRouter from 'vue-router'
import storeHouse from '../views/storeHouse.vue'
import BaseUnit from "../views/BaseUnit";
import procurementOrder from '@/views/procurement/procurementOrder'

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
  },
  {
    path: '/procurementOrder',
    name: 'procurementOrder',
    component: procurementOrder
  }
]

const router = new VueRouter({
  routes
})

export default router
