<template>
    <div id="refundOrderDetailStatistics">
     <h1>销售退货订单明细统计</h1>
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
               <el-form-item label="月">
                 <el-date-picker
                 v-model="object.orderMonth"
                 type="month"
                 format="M"
                 value-format="M"
                 placeholder="选择月">
                 </el-date-picker>
               </el-form-item>
               <el-form-item label="医用商品类型">
                <el-select v-model="object.categoryId" placeholder="请选择">
                    <!-- <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option> -->
                </el-select>
               </el-form-item>
               <el-form-item label="医用商品名称">
                <el-select v-model="object.name" placeholder="请选择">
                    <!-- <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option> -->
                </el-select>
               </el-form-item>
               <el-form-item>
                   <el-button type="primary" icon="el-icon-search" @click="initRefundOrderDetailStatisticsList(1)">查询</el-button>
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
        <el-table-column prop="name" label="医用商品名称" >
       </el-table-column>
       <el-table-column prop="categoryName" label="医用商品类型" >
       </el-table-column>
       <el-table-column prop="unitName" label="计量单位" >
       </el-table-column>
       <el-table-column prop="specification" label="型号规格" >
       </el-table-column>
       <el-table-column prop="totalQuantity" label="销售退款数量" >
       </el-table-column>
       <el-table-column prop="totalAmount" label="销售退款金额" >
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
 import {getRefundOrderDetailStatisticsList,refundOrderDetailStatisticsExcel} from "@/api/saleStatistics.js";
 
 export default {
     name:"refundOrderDetailStatistics",
     data(){
         return{
             object:{
                 orderYear:new Date().getFullYear().toString(),
                 orderMonth:(new Date().getMonth()+1).toString(),
                 categoryId:"",
                 name:"",
                 currentPage:"",
             },
             pageInfo:[],
             list:[],
         }
     },
     mounted() {
       this.initRefundOrderDetailStatisticsList(1);
     },
     methods:{
         async initRefundOrderDetailStatisticsList(currentPage) {
             this.object.currentPage=currentPage;
             let data = await getRefundOrderDetailStatisticsList(this.object);
             console.log("123",data)
             this.pageInfo=data.data;
             this.list = data.data.list;
         },
         handleCurrentChange(val) {
             this.object.currentPage = val;
             this.initRefundOrderDetailStatisticsList(this.object.currentPage);
         },
         async handleExcel(){
          await refundOrderDetailStatisticsExcel(this.object);
        }
     }
 }
 </script>
 
 <style>
 
 </style>