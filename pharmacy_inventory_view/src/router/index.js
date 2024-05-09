import Vue from 'vue'
import VueRouter from 'vue-router'
import BaseUnit from "@/views/BaseUnit";
import BaseMedicine from "../views/base/BaseMedicine.vue";
import SaleOrder from "../views/sale/SaleOrder.vue";
import PrintSaleOrder from "../views/sale/PrintSaleOrder.vue";


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
    component: SaleOrder,
    children: [{
      path: 'printSaleOrder', 
      name: 'printSaleOrder',
      component: PrintSaleOrder
    },]
  },
]

const router = new VueRouter({
  routes
})

export default router
