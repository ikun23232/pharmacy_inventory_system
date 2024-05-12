<template>
  <el-container>
    <el-aside :width="isCollapseValue ? '64px' : '230px'">
      
  <SideMenu @collapse-change="handleCollapseChange"></SideMenu>
</el-aside>
    <el-container>
      <el-header>
        <i style="float:left">医药商品进销存管理系统</i>

        <div class="header-avatar">
         
          <el-avatar
            size="medium"
            :src="require('@/assets/favicon.png')"
          ></el-avatar>

          <el-dropdown>
            <span class="el-dropdown-link">
              {{ userInfo.username}}
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link :to="{ name: 'UserCenter' }">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
         
        </div>
      </el-header>
      <el-main>
        <Tabs></Tabs>
        <div style="margin: 0 15px">
          <router-view />
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import SideMenu from "@/views/operate/SideMenu";
import Tabs from "@/views/operate/Tabs";

import { selectUser, logout } from "@/api/test";

export default {
  name: "Home",
  components: {
  	SideMenu, Tabs
  },
  data() {
    return {
     
      isCollapseValue:false,
      sideMenuWidth: "230px",
      userInfo: {
        userid: "",
        username: "",
      },
    };
  },
  created() {
    this.getUserInfo();
  },

  methods: {
    handleCollapseChange(isCollapse) {
      console.log(isCollapse);
      this.isCollapseValue = isCollapse;
    },
    async getUserInfo() {
      let res = await selectUser();
      if (res.data.code === "200") {
        console.log(res);

        this.userInfo = res.data.data;
      }
    },
    async logout() {
      let res =await logout();
      if (res.data.code === "200") {
        localStorage.clear();
        sessionStorage.clear();
        this.$store.commit("resetState")
        this.$router.push("/login");
      }
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 28px;
  margin-bottom: 20px;
}

.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}

.header-avatar {
  float: right;
  width: 210px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-header {
  position: relative;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  line-height: 200px;
}

.el-main {
  color: #333;
  padding: 0;
}

a {
  text-decoration: none;
}

.el-submenu {
    margin-right: 64px;
}
.el-header::after {
  position: absolute;
  content: '';
  bottom: 0;
  left: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(to right, #17b3a3, #ffffff, #17b3a3);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>