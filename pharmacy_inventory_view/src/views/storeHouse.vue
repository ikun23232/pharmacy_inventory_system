<template>
  <div id="storeHouse">
    <div>
      仓库编号:
      <el-input v-model="code" style="width: 300px" placeholder=""></el-input>
      <el-button type="primary" @click="getList(1, list.pageSize)"
        >查询</el-button
      >
      <el-button type="primary" @click="adddialogVisible = true"
        >添加仓库</el-button
      >
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column fixed label="序号" type="index" width="50">
      </el-table-column>
      <el-table-column prop="code" label="仓库编号" width="120">
      </el-table-column>
      <el-table-column prop="name" label="仓库名称" width="150">
      </el-table-column>
      <el-table-column prop="capacity" label="仓库面积" width="120">
        <template slot-scope="scope">
          <div>
            {{scope.row.capacity}}/平方米
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="仓库地址" width="120">
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
          <el-button
            @click="updateStoreHouse(scope.row)"
            type="primary"
            size="small"
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
      title="添加仓库"
      :visible.sync="adddialogVisible"
      width="30%"
      v-if="adddialogVisible"
    >
      <addStoreHouse
        @handleAddSuccess="handleAddSuccess"
        @cancel="cancel"
      ></addStoreHouse>
    </el-dialog>
    <el-dialog
      title="修改仓库"
      :visible.sync="updatedialogVisible"
      width="30%"
      v-if="updatedialogVisible"
    >
      <updateStoreHouse
        :id="this.id"
        @handleUpdateSuccess="handleUpdateSuccess"
        @cancelUpdate="cancelUpdate"
      ></updateStoreHouse>
    </el-dialog>
  </div>
</template>

<script>
import { getStoreList, deteleStoreHouse } from "@/api/storeHouse";
import { Message } from "element-ui";
import addStoreHouse from "@/components/addStoreHouse";
import updateStoreHouse from "@/components/updateStoreHouse";
export default {
  name: "storeHouse",
  components: {
    addStoreHouse,
    updateStoreHouse,
  },
  data() {
    return {
      code: "",
      pageNum: 1,
      pageSize: 5,
      list: {},
      id: "",
      //添加弹框开关
      adddialogVisible: false,
      updatedialogVisible: false,
    };
  },
  mounted() {
    this.getList(1, 5);
  },
  methods: {
    async getList(pageNum, pageSize) {
      let data = await getStoreList(pageNum, pageSize, this.code);
      console.log(data);
      this.list = data.data;
    },
    handleCurrentChange(val) {
      this.getList(val, this.list.pageSize, this.code);
    },
    async handleDelete(row) {
      if (confirm("你确定要删除吗？")) {
        let data = await deteleStoreHouse(row.id);
        if (data.code == 200) {
          Message({
            message: "删除成功!",
            type: "success",
            center: "true",
          });
          this.getList(1, this.list.pageSize, this.code);
        }
      }
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
    },
    updateStoreHouse(row) {
      this.id = row.id;
      this.updatedialogVisible = true;
    },
    handleAddSuccess() {
      this.adddialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getList(1, 5);
    },
    cancel() {
      this.adddialogVisible = false; // 关闭 el-dialog
    },
    handleUpdateSuccess() {
      this.updatedialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getList(1, 5);
    },
    cancelUpdate() {
      this.updatedialogVisible = false; // 关闭 el-dialog
    },
  },
};
</script>

<style>
</style>