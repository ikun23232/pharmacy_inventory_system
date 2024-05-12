<template>
  <div id="userList">
    <div>
      账户名:
      <el-input
        v-model="username"
        style="width: 300px"
        placeholder="请输入账户名"
      ></el-input>
      <el-select v-model="sex" placeholder="请选择性别" style="width: 300px">
        <el-option label="全部" value=3></el-option>
        <el-option label="男" value=1></el-option>
        <el-option label="女" value=0></el-option>
      </el-select>

      <el-select
        v-model="isstate"
        placeholder="请选择状态"
        style="width: 300px"
      >
        <el-option label="全部" value=3></el-option>
        <el-option label="已启用" value=0></el-option>
        <el-option label="未启用" value=1></el-option>
      </el-select>
      <el-button type="primary" @click="getList(1)">查询</el-button>
      <el-button type="primary" @click="adddialogVisible = true"
        >添加用户</el-button
      >
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column fixed label="序号" type="index" width="50">
      </el-table-column>
      <el-table-column prop="username" label="账户名" width="120">
      </el-table-column>
      <el-table-column prop="nickname" label="用户名" width="120">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="150">
        <template slot-scope="scope">
          <span v-if="scope.row.sex === 1">男</span>
          <span v-else-if="scope.row.sex === 0">女</span>
        </template>
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120"> </el-table-column>
      <el-table-column prop="createdate" label="创建时间" width="120">
      </el-table-column>
      <el-table-column prop="createby" label="创建人" width="300">
      </el-table-column>
      <el-table-column prop="isstate" label="状态" width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.isstate === 1">未启用</span>
          <span v-else-if="scope.row.isstate === 0">启用</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="primary" size="small"
            >编辑</el-button
          >
          <el-button @click="handleDelete(scope.row)" type="danger" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="list.pageNum"
        :page-size="list.pageSize"
        layout="prev, pager, next, jumper"
        :total="list.total"
      >
      </el-pagination>
    </div>
    <el-dialog
      title="添加用户"
      :visible.sync="adddialogVisible"
      width="30%"
      v-if="adddialogVisible"
    >
      <addUser
        @updateSuccess="handleAddSuccess"
        @cancel="cancel"
      ></addUser>
    </el-dialog>
  </div>
</template>

<script>
import { getUserlist, delUserById } from "@/api/test.js";
import { Message } from "element-ui";
import addUser from "@/views/user/addUser.vue";
export default {
  name: "userList",
  components: {
    addUser,
  },
  data() {
    return {
      username: "",
      sex: null,
      isstate: null,
      page: {
        currentNo: 1,
        pageSize: 5,
      },
      list: {},
      //添加弹框开关
      adddialogVisible: false,
    };
  },
  mounted() {
    this.getList(1);
  },
  methods: {
    async getList(currentNo) {
      let data = await getUserlist(
        this.username,
        this.sex,
        this.isstate,
        currentNo
      );
      console.log(data.data.data);
      this.list = data.data.data;
    },
    handleCurrentChange(val) {
      console.log(val);
      this.page.currentNo = val;
      this.getList(val);
    },
    async handleDelete(row) {
      if (confirm("你确定要删除吗？")) {
        let data = await delUserById(row.userid);
        if (data.code == 200) {
          Message({
            message: "删除成功!",
            type: "success",
            center: "true",
          });
          this.getList(1);
        }
      }
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
    },
    handleEdit(row) {
      this.$router.push({
        name: "updateUser",
        params: {
          userid: row.userid,
        },
      });
    },
    handleAddSuccess() {
      this.adddialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getList(1);
    },
    cancel() {
      this.adddialogVisible = false; // 关闭 el-dialog
    },
  },
};
</script>

<style>
</style>