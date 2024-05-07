<template>
  <div id="storeHouse">
    <h1>计量单位</h1>
    <div>
      <el-input v-model="code" style="width: 300px" placeholder=""></el-input>
      <el-button type="primary" @click="getList(1, 5)">查询</el-button>
      <el-button type="primary" @click="jump('addUser')">添加</el-button>
    </div>
    <el-table :data="list.list" border style="width: 100%">

      <el-table-column prop="id" label="单位id" width="120">
      </el-table-column>
      <el-table-column prop="name" label="计量名称" width="150">
      </el-table-column>
      <el-table-column prop="sign" label="符号" width="120">
      </el-table-column>
      <el-table-column prop="createby" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间" width="300">
      </el-table-column>
      <el-table-column prop="updateby" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updatetime" label="修改时间" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="primary" size="small"
          >编辑
          </el-button
          >
          <el-button @click="handleDelete(scope.row)" type="danger" size="small"
          >删除
          </el-button
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
import {delUnit, initUnit} from "@/api/BaseUnit";

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
    this.getList(this.page);
  },
  methods: {
    async getList(page) {

      let data = await initUnit(page.pageNum, page.pageSize);
      console.log(data);
      this.list = data.data;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let data = await delUnit(row.id);
      console.log(data)
      alert(data.code)
      if (data.code == 200) {
        this.$message({
          message: '删除成功!',
          type: 'success',
          center: 'true',
        })
        this.getList(this.page);
      }else {
        this.$message({
          message: '删除失败!',
          type: 'success',
          center: 'true',
        })
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
          userName: row.userName,
        },
      });
    },
  },
};
</script>

<style>
</style>