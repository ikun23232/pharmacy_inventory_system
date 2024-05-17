<template>
  <div id="saleOutWarehouse">
    <h1>库存明细</h1>
    <div>
      <div style="padding-top: 15px; padding-left: 20px">
        <el-form :inline="true">
          <el-form-item label="创建时间">
            <el-col :span="11">
              <el-date-picker
                type="date"
                placeholder="请选择开始"
                v-model="object.orderDateBegin"
                style="width: 100%"
              ></el-date-picker>
            </el-col>
            <el-col class="line" :span="1">~</el-col>
            <el-col :span="11">
              <el-date-picker
                type="date"
                placeholder="请选择结束"
                v-model="object.orderDateEnd"
                style="width: 100%"
              ></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="仓库">
            <el-input
              placeholder="请选择"
              v-model="object.storehouseName"
            ></el-input>
          </el-form-item>
          <el-form-item label="医用商品名称">
            <el-input placeholder="请选择" v-model="object.name"></el-input>
          </el-form-item>
          <el-form-item label="医用商品类型">
            <el-input
              placeholder="请选择"
              v-model="object.categoryName"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="initStockDetailListByPage(1)"
              >查询</el-button
            >
            <el-button icon="el-icon-refresh-right">重置</el-button>
            <el-button
              type="text"
              icon="el-icon-upload2"
              style="margin-left: 18px"
              >导出</el-button
            >
            <el-button
              type="text"
              icon="el-icon-download"
              style="margin-left: 18px"
              >导入</el-button
            >
            <el-button
              type="text"
              icon="el-icon-printer"
              style="margin-left: 18px"
              @click="printOrder"
              >打印</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-table
      stripe="true"
      :data="list"
      border
      style="width: 100%; text-align: center"
    >
      <el-table-column fixed prop="index" label="#" width="60">
        <template #default="scope">
          {{ scope.$index + (pageInfo.pageNum - 1) * pageInfo.pageSize + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="医用商品名称" width="150">
      </el-table-column>
      <el-table-column prop="categoryName" label="医用商品类型" width="120">
      </el-table-column>
      <el-table-column prop="specification" label="型号规格" width="120">
      </el-table-column>
      <el-table-column prop="unitName" label="计量单位" width="120">
      </el-table-column>
      <el-table-column prop="salePrice" label="零售价" width="120">
      </el-table-column>
      <el-table-column prop="storehouseName" label="仓库" width="120">
      </el-table-column>
      <el-table-column prop="batchCode" label="批次号" width="120">
      </el-table-column>
      <el-table-column prop="stock" label="库存" width="120"> </el-table-column>
      <el-table-column prop="warning" label="库存预警值" width="120">
      </el-table-column>
      <el-table-column prop="isWarning" label="是否预警" width="120">
        <template slot-scope="scope">
          {{ scope.row.warning >= scope.row.stock ? "是" : "否" }}
        </template>
      </el-table-column>
      <el-table-column prop="createByName" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="120">
      </el-table-column>
      <el-table-column prop="updateByName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="120">
      </el-table-column>
      <!-- <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button @click="handleDelete(scope.row.id)" type="text">
            删除
          </el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <div class="block">
      <p>
        <el-pagination
          background
          layout="prev, pager, next"
          :total="pageInfo.total"
          page-size="5"
          @current-change="handleCurrentChange"
          style="float: right"
        ></el-pagination>
        <span style="color: gray; float: right; margin-top: 5px"
          >共{{ pageInfo.total }}条</span
        >
      </p>
    </div>
  </div>
</template>
  
  <script>
import { initStockDetailListByPage } from "../../../api/stockDetail.js";
import { Message } from "element-ui";

export default {
  name: "stockDetail",
  data() {
    return {
      object: {
        orderDateBegin: "",
        orderDateEnd: "",
        storehouseName: "",
        name: "",
        categoryName: "",
        currentPage: 1,
      },
      pageInfo: "",
      list: "",
    };
  },
  mounted() {
    this.initStockDetailListByPage(1);
  },
  methods: {
    async initStockDetailListByPage(currentPage) {
      this.object.currentPage = currentPage;
      let data = await initStockDetailListByPage(this.object);
      this.pageInfo = data.data;
      this.list = data.data.list;
      console.log("123", this.list);
    },
    handleCurrentChange(val) {
      this.object.currentPage = val;
      this.initStockDetailListByPage(this.object.currentPage);
    },
    printOrder() {
      const newPage = this.$router.resolve({ path: "/printStockDetail" });
      window.open(newPage.href, "_blank");
    },
    async handleDelete(code) {
      this.$messagebox
        .confirm("此操作将删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          try {
            let data = await deleteSaleOutWarehouseOrder(code);
            if (data.code === "200") {
              Message({ type: "success", message: "删除成功！" });
              this.initSaleOrderByPage(this.object.currentPage);
            } else {
              Message({ type: "error", message: "删除失败！" });
            }
          } catch (error) {
            Message({ type: "error", message: "删除失败！" });
          }
        })
        .catch(() => {
          Message({
            type: "success",
            message: "取消删除成功！",
          });
        });
    },
  },
};
</script>
  
  <style>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
  margin-left: 10px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>