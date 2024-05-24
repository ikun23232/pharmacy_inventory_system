import Vue from 'vue'
import VueRouter from 'vue-router'

import storeHouse from '../views/storeHouse.vue'
import BaseProviderList from "../views/base/BaseProviderList.vue"
import BankAccountList from "../views/base/BankAccountList.vue"
import procurementOrder from '@/views/procurement/CGDD/procurementOrder'
import CGRKManager from "@/views/procurement/CGRK/CGRKManager";
import dispatch from '../views/kc/KCDD/dispatch.vue'
import BaseMedicine from "../views/base/BaseMedicine.vue";
import SaleOrder from "../views/sale/SaleOrder.vue";
import PrintSaleOrder from "../views/sale/PrintSaleOrder.vue";
import SaleOutWarehouse from "../views/kc/CKGL/SaleOutWarehouse.vue";
import PrintSaleOutWarehouse from "../views/kc/CKGL/PrintSaleOutWarehouse.vue";
import StockDetail from "../views/kc/KCMX/StockDetail.vue";
import PrintStockDetail from "../views/kc/KCMX/PrintStockDetail.vue";
import PrintRefundOrder from "../views/refund/PrintRefundOrder.vue";
import RefundOrder from "../views/refund/RefundOrder.vue";
import PrintCGRKOrder from "@/views/procurement/CGRK/PrintCGRKOrder";
import printDispatchOrder from './../views/kc/KCDD/printDispatchOrder.vue'
import DDRKManager from './../views/kc/DDRK/DDRKManager.vue'
import PrintDDRKOrder from './../views/kc/DDRK/PrintDDRKOrder.vue'

import axios from "@/utils/request";
import store from "@/store/index"
import Home from '../views/operate/Home.vue'
import Index from '../views/operate/Index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/index',
        name: 'Index',
        meta: {
          title: "首页"
        },
        component: Index
      },

    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/operate/Login.vue')
  },
  {
    //仓库管理
    path: '/storeHouse',
    name: 'storeHouse',
    component: storeHouse
  },
  {
    //供应商管理
    path: '/baseProviderList',
    name: 'baseProviderList',
    component: BaseProviderList
  },
  {
    //采购入库
    path: '/cgrkManager',
    name: 'CGRKManager',
    component: CGRKManager
  },
  {
    //银行账户
    path: '/bankAccountList',
    name: 'bankAccountList',
    component: BankAccountList
  },
  {
    //医用商品
    path: '/baseMedicine',
    name: 'BaseMedicine',
    component: BaseMedicine
  },
  {
    //销售订单
    path: '/saleOrder',
    name: 'SaleOrder',
    component: SaleOrder,
  },
  {
    //销售订单打印
    path: '/printSaleOrder',
    name: 'printSaleOrder',
    component: PrintSaleOrder
  },
  {
    //采购订单
    path: '/procurementOrder',
    name: 'procurementOrder',
    component: procurementOrder
  },
  {
    //销售出库
    path: '/saleOutWarehouse',
    name: 'saleOutWarehouse',
    component: SaleOutWarehouse
  },
  {
    //销售出库打印
    path: '/printSaleOutWarehouse',
    name: 'printSaleOutWarehouse',
    component: PrintSaleOutWarehouse
  },
  {
    //库存明细
    path: '/stockDetail',
    name: 'stockDetail',
    component: StockDetail
  },
  {
    //库存明细打印
    path: '/printStockDetail',
    name: 'printStockDetail',
    component: PrintStockDetail
  },
  {
    //销售退货退款
    path: '/refundOrder',
    name: 'refundOrder',
    component: RefundOrder
  },
  {
    //销售退货退款打印
    path: '/printRefundOrder',
    name: 'printRefundOrder',
    component: PrintRefundOrder
  },
  {
    //库存调度
    path: '/dispatch',
    name: 'dispatch',
    component: dispatch
  },
  {
    //采购统计
    path:'/procurementTJ',
    name: 'procurementTJ',
    component: () => import('../views/procurement/ProcurementTJ.vue')
  },
  {
    //库存报损
    path:'/KcReported',
    name: 'KcReported',
    component: () => import('../views/warehouse/KCBC/KcReported.vue')
  },
  {
    //报损出库
    path:'/KcReportedfromware',
    name: 'KcReportedfromware',
    component: () => import('../views/warehouse/BSCK/KcReportedfromware.vue')
  },
  {
    //付款订单
    path:'/procurementPay',
    name: 'procurementPay',
    component: () => import('../views/procurement/CGDD/procurementPay.vue')
  },
  {
    //采购应付
    path:'/purchasePay',
    name: 'purchasePay',
    component: () => import('../views/finance/CGYF/purchasePay.vue')
  },
  {
    //报损应收
    path:'/breakageReceivable',
    name: 'breakageReceivable',
    component: () => import('../views/finance/BSYS/breakageReceivable.vue')
  },
  {
    //销售应收
    path:'/salesReceivable',
    name: 'salesReceivable',
    component: () => import('../views/finance/XS/salesReceivable.vue')
  },
  {
    //销售退款
    path:'/salesRefund',
    name: 'salesRefund',
    component: () => import('../views/finance/XS/salesRefund.vue')
  },
  {
    //发票详情
    path:'/invoice',
    name: 'invoice',
    component: () => import('../views/finance/invoice.vue')
  },
  {
    //流水统计
    path:'/runningWater',
    name: 'runningWater',
    component: () => import('../views/finance/runningWater.vue')
  },
  {
    //首页
    path:'/Index2',
    name: 'Index2',
    component: () => import('../views/index/Index.vue')
  },
  {
    //库存调度打印
    path: '/printDispatchOrder',
    name: 'printDispatchOrder',
    component: printDispatchOrder
  },
  {
    //采购入库订单打印
    path: '/printCGRKOrder',
    name: 'PrintCGRKOrder',
    component: PrintCGRKOrder
  },
  {
    //调度入库订单
    path: '/DDRKManager',
    name: 'DDRKManager',
    component: DDRKManager
  },
  {
    //调度出库单打印
    path: '/PrintDDRKOrder',
    name: 'PrintDDRKOrder',
    component: PrintDDRKOrder
  },
  {
    //库存告警半成品(史)
    path: '/KCGJManager',
    name: 'KCGJManager',
    component: () => import('@/views/kc/KCGJ/KCGJManager')
  },
  {
    //调度入库订单
    path: '/ddckManager',
    name: 'DDCKManager',
    component: () => import('@/views/kc/DDCK/DDCKManager')
  },
  {
    //调度出库单打印
    path: '/printDDCKOrder',
    name: 'PrintDDCKOrder',
    component: () => import('@/views/kc/DDCK/PrintDDCKOrder')
  },
  {
    //出入库明细
    path: '/crkmxManager',
    name: 'CRKMXManager',
    component: () => import('@/views/kc/CRKMX/CRKMXManager')
  },
  {
    //出入库明细
    path:'/printCGSQOrder',
    name: 'PrintCGSQOrder',
    component: () => import('@/views/procurement/CGSQ/PrintCGSQOrder')
  },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {

  let hasRoute = store.state.menus.hasRoutes

  let token = localStorage.getItem("token")

  if (to.path == '/login') {
    next()

  } else if (!token) {
    next({ path: '/login' })


  } else if (token && !hasRoute) {
    axios.get("/user/menu/nav", {
      headers: {
        Authorization: localStorage.getItem("token")
      }
    }).then(res => {

      console.log(res.data.nav)

      // 拿到menuList
      store.commit("setMenuList", res.data.nav)

      // 拿到用户权限
      store.commit("setPermList", res.data.authoritys)

      console.log(store.state.menus.menuList)

      // 动态绑定路由
      let newRoutes = router.options.routes

      res.data.nav.forEach(menu => {
        if (menu.children && menu.children.length > 0) {
          menu.children.forEach(e => {

            if (e.children && e.children.length > 0) {
              e.children.forEach(child => {
                // 转成路由
                let route = menuToRoute(child)

                // 吧路由添加到路由管理中
                if (route) {
                  newRoutes[0].children.push(route)
                }
              })
            } else {
              // 转成路由
              let route = menuToRoute(e)

              // 吧路由添加到路由管理中
              if (route) {
                newRoutes[0].children.push(route)
              }
            }

          })
        }

      })
      router.addRoutes(newRoutes)

      hasRoute = true
      store.commit("changeRouteStatus", hasRoute)
    })
  }
  next()
})


// 导航转成路由
const menuToRoute = (menu) => {

  if (!menu.component) {
    return null
  }

  let route = {
    name: menu.perms,
    path: menu.path,
    meta: {
      title: menu.title
    }
  }
  route.component = () => import('../views/' + menu.component + '.vue')

  return route
}

export default router
