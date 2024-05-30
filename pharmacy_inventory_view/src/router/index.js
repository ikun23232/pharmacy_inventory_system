import Vue from 'vue'
import VueRouter from 'vue-router'

import storeHouse from '../views/storeHouse.vue'
import CGRKManager from "@/views/procurement/CGRK/CGRKManager";
import BaseMedicine from "../views/base/BaseMedicine.vue";
import PrintSaleOrder from "../views/sale/PrintSaleOrder.vue";
import PrintSaleOutWarehouse from "../views/kc/CKGL/PrintSaleOutWarehouse.vue";
import PrintStockDetail from "../views/kc/KCMX/PrintStockDetail.vue";
import PrintRefundOrder from "../views/refund/PrintRefundOrder.vue";
import PrintCGRKOrder from "@/views/procurement/CGRK/PrintCGRKOrder";
import printDispatchOrder from './../views/kc/KCDD/printDispatchOrder.vue'

import axios from "@/utils/request";
import store from "@/store/index"
import Home from '../views/operate/Home.vue'
import Index from '../views/operate/Index.vue'
import IndexHome from "../views/index/IndexHome.vue";

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
		path: '/printcheck',
		name: 'Printcheck',
		component: () => import('../views/stocktake/PrintCheckList.vue')
	},
	{
		path: '/printcheckRK',
		name: 'PrintcheckRK',
		component: () => import('../views/stocktake/PrintCheckRK.vue')
	},
  {
    //仓库管理
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
    //采购入库
    path: '/cgrkManager',
    name: 'CGRKManager',
    component: CGRKManager
  },

  {
    //医用商品
    path: '/Medicine',
    name: 'BaseMedicine',
    component: BaseMedicine
  },

  {
    //销售订单打印
    path: '/printSaleOrder',
    name: 'printSaleOrder',
    component: PrintSaleOrder
  },

  {
    //销售出库打印
    path: '/printSaleOutWarehouse',
    name: 'printSaleOutWarehouse',
    component: PrintSaleOutWarehouse
  },

  {
    //库存明细打印
    path: '/printStockDetail',
    name: 'printStockDetail',
    component: PrintStockDetail
  },

  {
    //销售退货退款打印
    path: '/printRefundOrder',
    name: 'printRefundOrder',
    component: PrintRefundOrder
  },
  {
    //采购统计
    path:'/procurementTJ',
    name: 'procurementTJ',
    component: () => import('../views/procurement/ProcurementTJ.vue')
  },
  {
    //库存报损打印
    path:'/PrintKcReported',
    name: 'PrintKcReported',
    component: () => import('../views/warehouse/KCBC/PrintKcReported.vue')
  },

  {
    //付款订单
    path:'/procurementPay',
    name: 'procurementPay',
    component: () => import('../views/procurement/CGDD/procurementPay.vue')
  },
  {
    //采购应付打印
    path:'/printPurchasePay',
    name: 'printPurchasePay',
    component: () => import('../views/finance/CGYF/printPurchasePay.vue')

  },

  {
    //报损应收打印
    path:'/printBreakageReceivable',
    name: 'printBreakageReceivable',
    component: () => import('../views/finance/BSYS/printBreakageReceivable.vue')
  },

  {
    //销售应收打印
    path:'/printSalesReceivable',
    name: 'printSalesReceivable',
    component: () => import('../views/finance/XS/printSalesReceivable.vue')
  },

  {
    //销售退款打印
    path:'/printSalesRefund',
    name: 'printSalesRefund',
    component: () => import('../views/finance/XS/printSalesRefund.vue')
  },

  {
    //发票详情打印
    path:'/printInvoice',
    name: 'printInvoice',
    component: () => import('../views/finance/printInvoice.vue')
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
    path:'/printDDCKOrder',
    name: 'PrintDDCKOrder',
     component: () => import('@/views/kc/DDCK/PrintDDCKOrder')
  },
  {
    path:'/printDDRKOrder',
    name: 'printDDRKOrder',
     component: () => import('@/views/kc/DDRK/PrintDDRKOrder')
  },
  {
    path:'/crkmxManager',
    name: 'CRKMXManager',
    component: () => import('@/views/kc/CRKMX/CRKMXManager')
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
      //采购订单打印
    path: '/printCGDDOrder',
    name: 'printCGDDOrder',
    component: () => import('@/views/procurement/CGDD/PrintCGDDOrder.vue')
  },
  {
    //出入库明细
    path:'/printCGSQOrder',
    name: 'PrintCGSQOrder',
    component: () => import('@/views/procurement/CGSQ/PrintCGSQOrder')
  },


]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {

let hasRoute = store.state.menus.hasRoutes

let token = localStorage.getItem("token")

if (to.path == '/login') {
	next()

} else if (!token) {
	next({path: '/login'})


} else if(token && !hasRoute) {
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
	route.component = () => import('../views/' + menu.component +'.vue')

	return route
}

export default router
