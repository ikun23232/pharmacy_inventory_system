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
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-table
      stripe="true"
      :data="list"
      border
      @selection-change="chandleDetailSelectionChange"
      style="width: 100%; text-align: center"
    >
      <el-table-column type="selection" width="30" align="center" ></el-table-column>
      <el-table-column fixed prop="index" label="序号" width="60">
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
      <el-row type="flex" justify="center">
          <el-col :span="6"
            ><div class="grid-content bg-purple">
              <el-button type="primary" @click="submitForm()"
                >立即添加</el-button
              >
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button @click="cancelKcmx()">取 消</el-button>
            </div>
          </el-col>
        </el-row>
  </div>
</template>
  
  <script>
import { initStockDetailListByPage } from "@/api/stockDetail.js";

export default {
  name: "stockDetail",
  props:{
    wareHouseId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      object: {
        orderDateBegin: "",
        orderDateEnd: "",
        storeHouseId: this.wareHouseId,
        name: "",
        categoryName: "",
        currentPage: 1,
      },
      pageInfo: "",
      list: "",
      selectList:[]
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
    cancelKcmx() {
      this.$emit("cancelKcmx");
    },
    submitForm(){
      if(this.selectList.length == 0){
        this.$message({
          message: '请选择调度的药品！',
          type: 'error',
        })
      }
      this.$emit("handleKcmxSuccess",this.selectList)
    },
    chandleDetailSelectionChange(val){
      this.selectList = val
    }
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