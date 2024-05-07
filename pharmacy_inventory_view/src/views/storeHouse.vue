<template>
    <div id="storeHouse">
        <div>
      <el-input
        v-model="code"
        style="width: 300px"
        placeholder=""
      ></el-input>
      <el-button type="primary" @click="getUserList(1, 5)">查询</el-button>
      <el-button type="primary" @click="jump('addUser')">添加用户</el-button>
    </div>
    <el-table :data="page.list" border style="width: 100%">
      <el-table-column fixed prop="createDate" label="日期"> </el-table-column>
      <el-table-column prop="filePath" label="图片">
        <template slot-scope="scope">
          <img :src="showImg(scope.row.filePath)" />
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="用户姓名"> </el-table-column>
      <el-table-column prop="address" label="用户地址"> </el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="idCard" label="身份证"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">
          <div v-if="scope.row.sex ==0">
            男
          </div>
            <div v-if="scope.row.sex ==1">
            女
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)"
            >修改用户</el-button
          >
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)"
            >删除用户</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        layout="prev, pager, next, jumper"
        :total="page.total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>

export default {
  name: "storeHouse",
  data() {
    return {
      page: {},
      userName: "",
    };
  },
  mounted() {
    this.getUserList(1, 5);
  },
  methods: {
    getUserList(pageNum, pageSize) {
      axios
        .get("getUserList", {
          params: {
            userName: this.userName,
            pageNum: pageNum,
            pageSize: pageSize,
          },
        })
        .then((result) => {
          console.log(result.data);
          this.page = result.data.data;
        });
    },
    handleCurrentChange(val) {
      this.getOrderList(val, 5);
    },
    handleDelete(row) {
      if (confirm("你确定要删除吗？")) {
        axios
          .get("deleteUser", {
            params: {
              userId: row.userId,
            },
          })
          .then((result) => {
            if (result.data.code == 200) {
              this.$message("删除成功！");
              this.getUserList(1, 5);
            }
          });
      }
    },
    showImg(filePath) {
      return "showImage?filePath=" + filePath;
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
    },
    handleEdit(row){
         this.$router.push({
          name: "updateUser",
          params:{
            userName:row.userName
          }
      });
    }
  },
};
</script>

<style>

</style>