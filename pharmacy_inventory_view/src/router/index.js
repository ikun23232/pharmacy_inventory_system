import Vue from 'vue'
import VueRouter from 'vue-router'
import BaseUnit from "@/views/BaseUnit";
import BaseMedicine from "../views/base/BaseMedicine.vue";
import SaleOrder from "../views/sale/SaleOrder.vue";


Vue.use(VueRouter)

const routes = [
{
    path: '/baseUnit',
    name: 'BaseUnit',
    component: BaseUnit
  },
  {
    path: '/baseMedicine',
    name: 'BaseMedicine',
    component: BaseMedicine
  },
  {
    path: '/saleOrder',
    name: 'SaleOrder',
    component: SaleOrder
  },
]

const router = new VueRouter({
  routes
})

export default router
