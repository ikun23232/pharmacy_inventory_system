import Vue from 'vue'
import VueRouter from 'vue-router'

import storeHouse from '../views/storeHouse.vue'
import BaseUnit from "../views/BaseUnit";
import BaseProviderList from "../views/base/BaseProviderList.vue"
import BankAccountList from "../views/base/BankAccountList.vue"
import CgsqManager from '../views/procurement/CGSQ/CGSQManager.vue';
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
    path: '/storeHouse',
    name: 'storeHouse',
    component: storeHouse
  }
  ,{
        path: '/baseProviderList',
        name: 'baseProviderList',
        component: BaseProviderList
    },
  {
    path: '/cgrkManager',
    name: 'CGRKManager',
    component: CGRKManager
  },
  {
    path: '/bankAccountList',
    name: 'bankAccountList',
    component: BankAccountList
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
    path: '/dispatch',
    name: 'dispatch',
    component: dispatch
  },
  {
    path:'/procurementTJ',
    name: 'procurementTJ',
    component: () => import('../views/procurement/ProcurementTJ.vue')
  },
  {
    path:'/KcReported',
    name: 'KcReported',
    component: () => import('../views/warehouse/KCBC/KcReported.vue')
  },{
        path: '/printCGRKOrder',
        name: 'PrintCGRKOrder',
        component: PrintCGRKOrder

    },
    {
        path:'/ddckManager',
        name: 'DDCKManager',
        component: () => import('@/views/kc/DDCK/DDCKManager')
    },{
        path:'/printDDCKOrder',
        name: 'PrintDDCKOrder',
        component: () => import('@/views/kc/DDCK/PrintDDCKOrder')
    },{
        path:'/crkmxManager',
        name: 'CRKMXManager',
        component: () => import('@/views/kc/CRKMX/CRKMXManager')
    },{
        path:'/printCGSQOrder',
        name: 'PrintCGSQOrder',
        component: () => import('@/views/procurement/CGSQ/PrintCGSQOrder')
    }
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
