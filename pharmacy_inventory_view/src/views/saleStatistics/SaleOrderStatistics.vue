<template>
   <div id="saleOrderStatistics">
    <h1>销售订单统计</h1>
    <div>
      <div style="padding-top: 15px;padding-left: 20px;">
          <el-form :inline="true" >
              <el-form-item label="年">
                <el-date-picker
                v-model="object.orderYear"
                type="year"
                format="yyyy"
                value-format="yyyy"
                placeholder="选择年">
                </el-date-picker>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" icon="el-icon-search" @click="initSaleOrderStatisticsList(1)">查询</el-button>
                  <el-button icon="el-icon-refresh-right" >重置</el-button>
              <el-button type="text" icon="el-icon-upload2" style="margin-left:18px" @click="handleExcel">导出</el-button>
              </el-form-item>
          </el-form>
          </div>
        </div>
    <el-table :stripe=true :data="list" border fit style="width:100%;text-align: center;">
      <el-table-column fixed prop="index" label="#" width="60">
          <template #default="scope">
          {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
          </template>
       </el-table-column>
       <el-table-column prop="orderYear" label="年" >
      </el-table-column>
      <el-table-column prop="orderMonth" label="月" >
      </el-table-column>
      <el-table-column prop="orderCount" label="订单笔数" >
      </el-table-column>
      <el-table-column prop="totalAmount" label="销售金额" >
      </el-table-column>
    </el-table>
    <div class="block">
      <p>
        <el-pagination background layout="prev, pager, next" :total="pageInfo.total" :page-size=5 @current-change="handleCurrentChange" style="float: right;"></el-pagination>
        <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
      </p>
    </div>
  </div>
</template>

<script>
import {getSaleOrderStatisticsList,saleOrderStatisticsExcel} from "@/api/saleStatistics.js";

export default {
    name:"saleOrderStatistics",
    data(){
        return{
            object:{
                orderYear:new Date().getFullYear().toString(),
                currentPage:"",
            },
            pageInfo:[],
            list:[],
        }
    },
    mounted() {
      this.initSaleOrderStatisticsList(1);
    },
    methods:{
        async initSaleOrderStatisticsList(currentPage) {
            this.object.currentPage=currentPage;
            let data = await getSaleOrderStatisticsList(this.object);
            console.log("123",data)
            this.pageInfo=data.data;
            this.list = data.data.list;
        },
        handleCurrentChange(val) {
            this.object.currentPage = val;
            this.initSaleOrderStatisticsList(this.object.currentPage);
        },
        async handleExcel(){
          await saleOrderStatisticsExcel();
        }
    }
}
</script>

<style>

</style>