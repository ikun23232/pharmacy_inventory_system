<template>
  <el-container>
    <el-aside :width="isCollapseValue ? '64px' : '230px'">
      <SideMenu @collapse-change="handleCollapseChange"></SideMenu>
    </el-aside>
    <el-container>
      <el-header>
        <i style="float: left">医药商品进销存管理系统</i>

        <el-button style="position: absolute; top: 9px; right: 235px">
          <span>{{ dateFormat(newDate) }}</span>
        </el-button>
        <div class="header-avatar" style="position: relative">
          <el-avatar
            size="medium"
            :src="require('@/assets/favicon.png')"
          ></el-avatar>
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ userInfo.username }}
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link :to="{ name: 'UserCenter' }">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-link href="https://kdocs.cn/l/cgFcNROVB65l" target="_blank"
            >日报</el-link
          >
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
    SideMenu,
    Tabs,
  },
  data() {
    return {
      newDate: new Date(),
      isCollapseValue: false,
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
  mounted() {
    let that = this;
    this.timer = setInterval(function () {
      that.newDate = new Date().toLocaleString();
    });
  },
  // 销毁时清除计时器
  beforeDestroy: function () {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
  methods: {
    dateFormat() {
      var date = new Date();
      var year = date.getFullYear();
      var month =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      var hours =
        date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      var minutes =
        date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      var seconds =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      let week = date.getDay(); // 星期
      let weekArr = [
        "星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ];
      // 拼接 时间格式处理
      return (
        year +
        "年" +
        month +
        "月" +
        day +
        "日 " +
        hours +
        ":" +
        minutes +
        ":" +
        seconds +
        " " +
        weekArr[week]
      );
    },
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
      let res = await logout();
      if (res.data.code === "200") {
        localStorage.clear();
        sessionStorage.clear();
        this.$store.commit("resetState");
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
  content: "";
  bottom: 0;
  left: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(to right, #17b3a3, #ffffff, #17b3a3);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>