<template>
  <div id="medicineCategory">
    <h1>药品分类</h1>
    <div>
      <el-button type="primary" @click="addOrder">添加</el-button>
    </div>
    <div style="display: flex; justify-content: center">
      <el-table
        :data="list.data"
        border
        style="width: 100%"
        row-key="id"
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        align="center"
      >
        <el-table-column prop="name" label="商品分类名字" width="120">
        </el-table-column>

        <el-table-column prop="createUser" label="创建人" width="120">
        </el-table-column>
        <el-table-column prop="createdate" label="创建时间" width="300">
        </el-table-column>
        <el-table-column prop="updateby" label="修改人" width="120">
        </el-table-column>
        <el-table-column prop="updatedate" label="修改时间" width="300">
        </el-table-column>
        <el-table-column label="操作" width="330">
          <template slot-scope="scope">
            <el-button
              @click="updateOrder(scope.row.id)"
              type="primary"
              size="small"
              >编辑
            </el-button>
            <el-button
              @click="handleDelete(scope.row)"
              type="danger"
              size="small"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 修改订单状态 -->
    <el-dialog
      title="修改分类数据"
      :visible.sync="updatedialogVisible"
      width="30%"
      v-if="updatedialogVisible"
    >
      <updateCat
        :id="id"
        @closeUpdateDiago="closeUpdateDiago"
      ></updateCat>
    </el-dialog>
    <el-dialog
      title="添加分类数据"
      :visible.sync="adddialogVisible"
      width="30%"
      v-if="adddialogVisible"
    >
      <AddCat @addSuccess="addSuccess"></AddCat>
    </el-dialog>
  </div>
</template>

<script>
import { delCat, initCat } from "@/api/BaseCategory.js";
import { Message } from "element-ui";
import updateCat from "./updatecategory.vue";
import AddCat from "./addcategory.vue";

export default {
  name: "medicineCategory",
  components: {
    updateCat,
    AddCat,
  },
  data() {
    return {
      id: "",
      list: {},
      //添加弹框开关
      dialogVisible: false,
      updatedialogVisible: false,
      adddialogVisible: false,
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    async getList() {
      let data = await initCat();
      console.log(data);
      this.list = data;
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let resp = await delCat(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "删除成功",
        });
        this.getList();
      }else{
        Message({
          type: "error",
          message: "删除失败，里面仍然有数据",
        });
      }
    },
    updateOrder(id) {
      this.id = id;
      this.updatedialogVisible = true;
    },
    addOrder() {
      this.adddialogVisible = true;
    },

    closeUpdateDiago() {
      this.updatedialogVisible = false;
      this.getList();
    },
    addSuccess() {
      this.adddialogVisible = false;
      this.getList();
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
    },
    handleEdit(row) {
      this.$router.push({
        name: "updateCat",
        params: {
          id: row.id,
        },
      });
    },
  },
};
</script>

<style>
</style>