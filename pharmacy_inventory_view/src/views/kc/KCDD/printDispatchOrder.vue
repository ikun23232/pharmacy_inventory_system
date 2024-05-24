<template>
<div>
  <div v-loading="loading" style="padding: 10px;">
    <div id="printView" style="padding-left: 90px; padding-right: 20px;">
      <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">库存调度单</h2>
      <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
        <tr>
          <td style="padding:5px;" align="left">单据编号:{{ dispatch.code }}</td>
          <td style="padding:5px;" align="left">单据日期:{{ dispatch.createDate }}</td>
          <td style="padding:5px;" align="left">制单人:{{ dispatch.createName }}</td>
          <td style="padding:5px;" align="left">调度主题: {{dispatch.subject}}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">调度时间: {{dispatch.dispatchTime}}</td>
          <td style="padding:5px;" align="left">调度总价格:{{dispatch.price}}</td>
          <td style="padding:5px;" align="left">调度总数量：{{dispatch.totalCount }}</td>        
          <td style="padding:5px;" align="left">调度仓库: {{ dispatch.beforeWareName }}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">备注: {{ dispatch.remark }}</td>
          <td style="padding:5px;" align="left">审批状态: {{ dispatch.approvalStatus == 1 ? "未通过":"通过" }}</td>
          <td style="padding:5px;" align="left">审批人: {{ dispatch.approverName}}</td>
          <td style="padding:5px;" align="left">单据状态: {{dispatch.orderStatusResult}}</td>
        </tr>
      </table>
 
      <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
      <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
        <tr>
          <th width="55px">序号</th>
          <th>目标仓库</th>
          <th>批次号</th>
          <th>药品名称</th>
          <th>药品规格</th>
          <th>药品类型</th>
          <th>计量单位</th>
          <th>药品进价</th>
          <th>调度数量</th>
          <th>药品总价</th>
        </tr>
        <tr v-for="(item, index) in medicineDetailList" :key="index" align="center">
          <td>{{ index + 1 }}</td>
          <td>{{ item.aimStoreHouseName }}</td>
          <td>{{ item.batchCode }}</td>
          <td>{{ item.medicineName }}</td>
          <td>{{ item.specification}}</td>
          <td>{{ item.categoryName}}</td>
          <td>{{ item.unitName }}</td>
          <td>{{ item.purchasePrice}}</td>
          <td>{{ item.stock }}</td>
          <td>{{ item.stock * item.purchasePrice}}</td>
        </tr>   
        <tr align="center">
          <td colspan="8" style="font-weight: bolder;">合计</td>
          <td>{{dispatch.totalCount}}</td>
          <td>{{dispatch.price}}</td>
        </tr>
        <tr align="center">
          <td style="font-weight: bolder;">备注</td>
          <td colspan="9">{{dispatch.remark}}</td>
        </tr>
      </table>
    </div>
 
    <div class="drawer-bottom-bar">
      <el-button v-print="'#printView'" type="primary" size="small">打印</el-button>
      <!-- <el-button @click="$parent.$parent.closePrintDialog()" size="small">取消</el-button> -->
    </div>
  </div>
</div>
  </template>
  
  <script>
  import {getKcDispatchById} from "@/api/KcDispatch";
  import { getKcDetailsList } from '@/api/kcDisparchDetails';
  export default {
      name:"printDispatchOrder",
      data() {
       return{
        dispatch:{},
        medicineDetailList:[],
        commonStyle: { width: '100%' },
       }
      },
      async mounted() {
        console.log("123",this.$route.query.id)
        let data = await getKcDispatchById(this.$route.query.id)
        console.log(data)
        this.dispatch = data.data
        this.getSaleOrderByOrderNo();
      },
      methods:{
        async getSaleOrderByOrderNo() {
          let data = await getKcDetailsList(this.dispatch.code);
          console.log("data",data)
          this.medicineDetailList = data.data;
        },
      }
  }
  </script>
  
  <style media="print">
  @page {
    size: auto;
    margin: 3mm;
  }
  html {
    background-color: #ffffff;
    margin: 0px;
  }
  body {
    border: solid 1px #ffffff;
  }
  </style>
  <style lang="scss" scoped>
  @media print {
    #printView {
      display: block;
      width: 100%;
      overflow: hidden;
    }
  }
  .drawer-bottom-bar{
    float: right;
    margin-top: 20px;
    margin-right: 20px;
  }
  </style>