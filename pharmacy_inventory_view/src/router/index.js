import Vue from 'vue'
import VueRouter from 'vue-router'

import storeHouse from '../views/storeHouse.vue'
import BaseUnit from "../views/BaseUnit";
import BaseProviderList from "../views/base/BaseProviderList.vue"
import BankAccountList from "../views/base/BankAccountList.vue"
import CgsqManager from '../views/procurement/CGSQ/CGSQManager.vue';
import procurementOrder from '@/views/procurement/CGDD/procurementOrder'

import BaseMedicine from "../views/base/BaseMedicine.vue";
import SaleOrder from "../views/sale/SaleOrder.vue";
import PrintSaleOrder from "../views/sale/PrintSaleOrder.vue";
import SaleOutWarehouse from "../views/kc/CKGL/SaleOutWarehouse.vue";
import PrintSaleOutWarehouse from "../views/kc/CKGL/PrintSaleOutWarehouse.vue";
import StockDetail from "../views/kc/KCMX/StockDetail.vue";
import PrintStockDetail from "../views/kc/KCMX/PrintStockDetail.vue";
import PrintRefundOrder from "../views/refund/PrintRefundOrder.vue";
import RefundOrder from "../views/refund/RefundOrder.vue";
import RefundInWarehouse from "../views/kc/TKRK/RefundInWarehouse.vue";
import SaleOrderStatistics from "../views/saleStatistics/SaleOrderStatistics.vue";
import RefundOrderStatistics from "../views/saleStatistics/RefundOrderStatistics.vue";
import SaleStatistics from "../views/saleStatistics/SaleStatistics.vue";
import SaleOrderDetailStatistics from "../views/saleStatistics/SaleOrderDetailStatistics.vue";
import RefundOrderDetailStatistics from "../views/saleStatistics/RefundOrderDetailStatistics.vue";
import IndexHome from "../views/index/IndexHome.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/indexHome',
    name: 'indexHome',
    component: IndexHome
  },
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
  {
    path: '/cgsqManager',
    name: 'CgsqManager',
    component: CgsqManager
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
    
  },
  {
    path: '/printSaleOrder',
    name: 'printSaleOrder',
    component: PrintSaleOrder
  },
  {
    path: '/procurementOrder',
    name: 'procurementOrder',
    component: procurementOrder
  },
  {
    path: '/saleOutWarehouse',
    name: 'saleOutWarehouse',
    component: SaleOutWarehouse
  },
  {
    path: '/printSaleOutWarehouse',
    name: 'printSaleOutWarehouse',
    component: PrintSaleOutWarehouse
  },
  {
    path: '/stockDetail',
    name: 'stockDetail',
    component: StockDetail
  },
  {
    path: '/printStockDetail',
    name: 'printStockDetail',
    component: PrintStockDetail
  },
  {
    path: '/refundOrder',
    name: 'refundOrder',
    component: RefundOrder
  },
  {
    path: '/printRefundOrder',
    name: 'printRefundOrder',
    component: PrintRefundOrder
  },
  {
    path: '/refundInWarehouse',
    name: 'refundInWarehouse',
    component: RefundInWarehouse
  },
  {
    path: '/saleOrderStatistics',
    name: 'saleOrderStatistics',
    component: SaleOrderStatistics
  },
  {
    path: '/saleOrderDetailStatistics',
    name: 'saleOrderDetailStatistics',
    component: SaleOrderDetailStatistics
  },
  {
    path: '/refundOrderStatistics',
    name: 'refundOrderStatistics',
    component: RefundOrderStatistics
  },
  {
    path: '/refundOrderDetailStatistics',
    name: 'refundOrderDetailStatistics',
    component: RefundOrderDetailStatistics
  },
  {
    path: '/saleStatistics',
    name: 'saleStatistics',
    component: SaleStatistics
  },
]

const router = new VueRouter({
  routes
})

export default router
