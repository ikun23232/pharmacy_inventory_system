<template>
<div>
  <div v-loading="loading" style="padding: 10px;">
    <div id="printView" style="padding-left: 90px; padding-right: 20px;">
      <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">库存调度单</h2>
      <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
        <tr>
          <td style="padding:5px;" align="left">单据编号:{{ cgdd.code }}</td>
          <td style="padding:5px;" align="left">单据日期:{{ cgdd.createTime }}</td>
          <td style="padding:5px;" align="left">制单人:{{ cgdd.documenterName }}</td>
          <td style="padding:5px;" align="left">主题: {{cgdd.subject}}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">制单时间: {{cgdd.createTime}}</td>
          <td style="padding:5px;" align="left">采购金额:{{cgdd.referenceAmount}}</td>
          <td style="padding:5px;" align="left">采购数量{{cgdd.count }}</td>        
          <td style="padding:5px;" align="left">供应商: {{ cgdd.providerName }}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">备注: {{ cgdd.remark }}</td>
          <td style="padding:5px;" align="left">审批状态: {{ cgdd.approvalStatus == 1 ? "未通过":"通过" }}</td>
          <td style="padding:5px;" align="left">审批人: {{ cgdd.approverName}}</td>
          <td style="padding:5px;" align="left">单据状态: {{cgdd.OrderStatueType}}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">联系人: {{ cgdd.contactperson }}</td>
          <td style="padding:5px;" align="left">联系电话: {{ cgdd.phone}}</td>
          <td style="padding:5px;" align="left">传真: {{ cgdd.fax}}</td>
          <td style="padding:5px;" align="left">邮件: {{cgdd.email}}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">交货日期: {{ cgdd.deliveryDate }}</td>
          <td style="padding:5px;" align="left">交货人: {{ cgdd.deliveryName }}</td>
          <td v-if="cgdd.payType == 0" style="padding:5px;" align="left">结算方式: 货到付款</td>
          <td v-if="cgdd.payType != 0" style="padding:5px;" align="left">结算方式: {{ cgdd.payType == 1 ? "全款后发货":"直接付款" }}</td>
          <td style="padding:5px;" align="left">结算日期: {{cgdd.payTime}}</td>
        </tr>
        <tr style="margin:0;padding:0;">
          <td style="padding:5px;" align="left">支付状态: {{ cgdd.isPay == 0 ?"未支付":"已支付" }}</td>
          <td style="padding:5px;" align="left">采购类型: {{ cgdd.orderTypeName }}</td>
        </tr>
      </table>
 
      <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
      <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
        <tr>
          <th width="55px">序号</th>
          <th>医用商品名称</th>
          <th>型号规格</th>
          <th>药品类型</th>
          <th>计量单位</th>
          <th>数量</th>
          <th>金额（元）</th>
        </tr>
        <tr v-for="(item, index) in medicineDetailList" :key="index" align="center">
          <td>{{ index + 1 }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.specification}}</td>
          <td>{{ item.categoryName}}</td>
          <td>{{ item.unitName }}</td>
          <td>{{ item.quantity }}</td>
          <td>{{ item.salePrice }}</td>
        </tr> 
        <tr align="center">
          <td colspan="5" style="font-weight: bolder;">合计</td>
          <td>{{cgdd.count}}</td>
          <td>{{cgdd.referenceAmount}}</td>
        </tr>
        <tr align="center">
          <td style="font-weight: bolder;">备注</td>
          <td colspan="6">{{cgdd.remark}}</td>
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
  import {getCgddByCode} from "@/api/procurementOrder";
  import { getMedicineListByCode } from '@/api/baseMedicine';
  export default {
      name:"printCGDDOrder",
      data() {
       return{
        cgdd:{},
        medicineDetailList:[],
        commonStyle: { width: '100%' },
       }
      },
      async mounted() {
        console.log("123",this.$route.query.code)
        let data = await getCgddByCode(this.$route.query.code)
        console.log(data)
        this.cgdd = data.data
        this.getSaleOrderByOrderNo();
      },
      methods:{
        async getSaleOrderByOrderNo() {
          let data = await getMedicineListByCode(this.cgdd.code);
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