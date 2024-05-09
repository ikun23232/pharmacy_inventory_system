<template>
  <div id="storeHouse">
    <h1>计量单位</h1>
    <div>
      <el-input v-model="code" style="width: 300px" placeholder=""></el-input>
      <el-button type="primary" @click="getList(1, 5)">查询</el-button>
      <el-button type="primary" @click="addOrder">添加</el-button>
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column prop="id" label="单位id" width="120"> </el-table-column>
      <el-table-column prop="name" label="计量名称" width="150">
      </el-table-column>
      <el-table-column prop="sign" label="符号" width="120"> </el-table-column>
      <el-table-column prop="createUserName" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间" width="300">
      </el-table-column>
      <el-table-column prop="updateUserName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updatetime" label="修改时间" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            @click="updateOrder(scope.row.id)"
            type="primary"
            size="small"
            >编辑
          </el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small"
            >删除
          </el-button>
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
    <!-- 修改订单状态 -->
    <el-dialog
      title="修改计量单位"
      :visible.sync="updatedialogVisible"
      width="30%"
      v-if="updatedialogVisible"
    >
      <updateOrderStatus
        :serialNumber="serialNumber"
        @closeUpdateDiago="closeUpdateDiago"
      ></updateOrderStatus>
    </el-dialog>
    <el-dialog
      title="添加计量单位"
      :visible.sync="adddialogVisible"
      width="30%"
      v-if="adddialogVisible"
    >
      <AddUnit
        @addSuccess="addSuccess"
      ></AddUnit>
    </el-dialog>
  </div>
</template>

<script>
import { delUnit, initUnit } from "@/api/BaseUnit";
import { Message } from "element-ui";
import updateOrderStatus from "./UpdateOrderStatus.vue";
import AddUnit from "./AddUnit.vue";

export default {
  name: "storeHouse",
  components: {
    updateOrderStatus,
    AddUnit
  },
  data() {
    return {
      code: "",
      page: {
        pageNum: 1,
        pageSize: 5,
      },
      serialNumber: "",
      list: {},
      //添加弹框开关
      dialogVisible: false,
      updatedialogVisible: false,
      adddialogVisible:false
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
      let resp = await delUnit(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "删除成功",
        });
        this.getList(this.page);
      }
    },
    updateOrder(serialNumber) {
      this.serialNumber = serialNumber;
      this.updatedialogVisible = true;
    },
    addOrder() {
      this.adddialogVisible = true;
    },
  
    closeUpdateDiago(){
      this.updatedialogVisible = false;
      this.getList(this.page);
    },
    addSuccess(){
      this.adddialogVisible = false;
      this.getList(this.page);
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