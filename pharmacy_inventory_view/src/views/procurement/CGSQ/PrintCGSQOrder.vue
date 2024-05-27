<template>
  <div>
    <div v-loading="loading" style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">采购申请订单</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">单据编号:{{ saleOrder.code }}</td>
            <td style="padding:5px;" align="left">单据日期:{{ saleOrder.createtime }}</td>
            <td style="padding:5px;" align="left">制单人:{{ saleOrder.demanderUserName }}</td>
            <td style="padding:5px;" align="left">单据主题: {{saleOrder.subject}}</td>
          </tr>
          <tr style="margin:0;padding:0;">
            <td style="padding:5px;" align="left">源单号: {{ saleOrder.sourceCode }}</td>
            <td style="padding:5px;" align="left">采购类型: {{ saleOrder.cgtype }}</td>
            <td style="padding:5px;" align="left">参考数量：{{saleOrder.count }}件</td>
            <td style="padding:5px;" align="left">合计金额: {{ saleOrder.referenceamount
              }}元</td>
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
            <th>建议供应商</th>
          </tr>
          <tr v-for="(item, index) in medicineDetailList" :key="index" align="center">
            <td>{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.specification}}</td>
            <td>{{ item.categoryName}}</td>
            <td>{{ item.unitName }}</td>
            <td>{{ item.quantity }}</td>
            <td>{{ item.totalPrice }}</td>
            <td>{{ item.providerName }}</td>
          </tr>
          <tr align="center">
            <td colspan="5" style="font-weight: bolder;">合计</td>
            <td>{{saleOrder.count}}</td>
            <td>{{saleOrder.referenceamount
              }}</td>
          </tr>
          <tr align="center">
            <td style="font-weight: bolder;">备注</td>
            <td colspan="6">{{saleOrder.remark}}</td>
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

import {getCgrkOrderById} from "@/api/CgrkOrder";
import {getCgsqOrderById} from "@/api/CgsdOrder";

export default {
  name:"printView",
  data() {
    return{
      saleOrder:"",
      medicineDetailList:"",
      commonStyle: { width: '100%' },
      // name:"123",
      //   specification:"123",
      //   categoryName:"123",
      //   unitName:"123",
      //   quantity:"123",
      //   totalPrice:"123",
    }
  },
  mounted() {
    this.getSaleOrderByOrderNo(this.$route.query.orderNo);
  },
  methods:{
    async getSaleOrderByOrderNo(orderNo) {
      let data = await getCgsqOrderById(orderNo);
      console.log(data)
      this.saleOrder=data.data;
      this.medicineDetailList = this.saleOrder.medicineList;
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