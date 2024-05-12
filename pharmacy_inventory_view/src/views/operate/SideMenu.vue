<template>
  <!-- 侧边栏 -->
  <!-- <el-aside :width="isCollapse ? '64px' : '230px'" style="height:100%">-->
	<div style="height:100%">
	<div class="toggle-button" @click="toggleCollapse()">|||</div> 
	<el-menu
	  :default-active="this.$store.state.menus.editableTabsValue"
	  class="el-menu-vertical-demo"
	  background-color="#333744"
	  text-color="#fff"
	  active-text-color="#409BFF"
	  :unique-opened="true"
	  :collapse="isCollapse"
	  :collapse-transition="false"
	>
	  <router-link to="/index">
		<el-menu-item index="Index" @click="selectMenu({ name: 'Index', title: '首页' })" style="padding-right:72px;">
		  <template slot="title" >
			<i class="el-icon-s-home"></i>
			<span style="margin-left:2px;">首页</span>
		  </template>
		</el-menu-item>
	  </router-link>
  
	  <template v-for="menu in menuList">
		<el-submenu :index="menu.name" :key="menu.id">
		  <template slot="title">
			<i :class="iconsObj[menu.id]"></i>
			<span class="level-1">{{ menu.title }}</span>
		  </template>
  
		  <template v-for="item in menu.children">
			<template v-if="item.children && item.children.length">
			  <el-submenu :index="item.name" :key="item.id">
				<template slot="title">
				  <i class="el-icon-s-home"></i>
				  <span class="level-2">{{ item.title }}</span>
				</template>
				<template v-for="child in item.children">
				  <router-link :to="child.path" :key="child.id">
					<el-menu-item :index="child.name" @click="selectMenu(child)">
					  <template slot="title">
						<span class="level-3">{{ child.title }}</span>
					  </template>
					</el-menu-item>
				  </router-link>
				</template>
			  </el-submenu>
			</template>
			<template v-else>
			  <router-link :to="item.path" :key="item.id">
				<el-menu-item :index="item.name" @click="selectMenu(item)">
				  <template slot="title">
					<span class="level-4">{{ item.title }}</span>
				  </template>
				</el-menu-item>
			  </router-link>
			</template>
		  </template>
		</el-submenu>
	  </template>
	</el-menu>
</div>
<!-- </el-aside > -->
  <!-- 侧边栏 -->

  </template>
  
  <script>
  export default {
	name: "SideMenu",
	data() {
	  return {
		isCollapse: false,
		iconsObj: {
      	'1':'el-icon-user-solid',//用户管理
      	'2':'el-icon-s-check',//权限管理
      	'3':'el-icon-s-goods',//商品管理
      	'4':'el-icon-s-order',//订单管理
      	'5':'el-icon-s-data',//数据管理
    }

	  };
	},
	computed: {
	  menuList() {
		return this.$store.state.menus.menuList;
	  },
	},
	methods: {
		toggleCollapse(){
  		this.isCollapse = !this.isCollapse;
		  this.$emit('collapse-change', this.isCollapse);
		},
	  selectMenu(item) {
		this.$store.commit("addTab", item);
	  },
	},
  };
  </script>
  
  <style scoped>
.el-menu-vertical-demo {
  height: 100%;
}

.level-1 {
  margin-left: 0; /* Adjust margin for level 1 */
}

.level-2 {
  margin-left: 6px; /* Adjust margin for level 2 */
}

.level-3 {
  margin-left: 50px; /* Adjust margin for level 3 */
}
.el-menu-item * {
    vertical-align: middle;
    margin-left: 60px;
}

.el-menu-item *[data-v-3a49f73b][data-v-3a49f73b] {
    vertical-align: middle;
    margin-left: 51px;
}
.el-aside[data-v-4f834ac4] {
    background-color: #d3dce6;
    color: #333;
    line-height: 200px;
}
.el-aside[data-v-4f834ac4] {
	width: 300px;
}
.toggle-button{
  background-color: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>