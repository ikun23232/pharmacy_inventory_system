<template>
  <div id="storeHouse">
    <div>
      <el-input v-model="code" style="width: 300px" placeholder=""></el-input>
      <el-button type="primary" @click="getList(1, 5)">查询</el-button>
      <el-button type="primary" @click="jump('addUser')">添加仓库</el-button>
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column fixed label="序号" type="index" width="50">
      </el-table-column>
      <el-table-column prop="code" label="仓库编号" width="120">
      </el-table-column>
      <el-table-column prop="name" label="仓库名称" width="150">
      </el-table-column>
      <el-table-column prop="capacity" label="存库量" width="120">
      </el-table-column>
      <el-table-column prop="address" label="仓库地址" width="120">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="300">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="120">
      </el-table-column>
      <el-table-column prop="createUser" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="120">
      </el-table-column>
      <el-table-column prop="updateUser" label="修改人" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
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
  </div>
</template>

<script>
import { getStoreList } from "@/api/storeHouse";
export default {
  name: "storeHouse",
  data() {
    return {
      code: "",
      page: {
        pageNum: 1,
        pageSize: 5,
      },
      list: {},
    };
  },
  mounted() {
    this.getList(this.page, this.code);
  },
  methods: {
    async getList(page, code) {
      let data = await getStoreList(page, code);
      console.log(data);
      this.list = data.data;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page, this.code);
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
    handleEdit(row) {
      this.$router.push({
        name: "updateUser",
        params: {
          userName: row.userName,
        },
      });
    },
  },
};
</script>

<style>
</style>