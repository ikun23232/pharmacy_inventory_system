import Vue from 'vue'
import VueRouter from 'vue-router'
import storeHouse from '../views/storeHouse.vue'
import BaseUnit from "../views/BaseUnit";
import BaseProviderList from "../views/base/BaseProviderList.vue"
import BankAccountList from  "../views/base/BankAccountList.vue"
import CgsqManager from '../views/procurement/CGSQ/CGSQManager.vue';
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
    path: '/baseProviderList',
    name: 'baseProviderList',
    component: BaseProviderList
  },
  {
    path: '/bankAccountList',
    name: 'bankAccountList',
    component: BankAccountList
  },
  ,{
    path: '/cgsqManager',
    name: 'CgsqManager',
    component: CgsqManager
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
