<template>
  <div>
    <div v-loading="loading" style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">调度入库单</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">单据编号:{{ saleOrder.code }}</td>
            <td style="padding:5px;" align="left">单据日期:{{ saleOrder.dispatchTime }}</td>
            <td style="padding:5px;" align="left">制单人:{{ saleOrder.documenterName }}</td>
            <td style="padding:5px;" align="left">单据主题: {{saleOrder.subject}}</td>
          </tr>
          <tr style="margin:0;padding:0;">
            <td style="padding:5px;" align="left">源单号: {{ saleOrder.dispatchCode }}</td>
            <td style="padding:5px;" align="left">备注: {{ saleOrder.remark }}</td>
            <td style="padding:5px;" align="left">源仓库：{{saleOrder.beforeWarehouseName }}</td>
            <td style="padding:5px;" align="left">合计金额: {{ saleOrder.price}}元</td>
          </tr>

        </table>

        <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
        <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <th>批次号</th>
            <th>医用商品名称</th>
            <th>型号规格</th>
            <th>计量单位</th>
            <th>目的仓库</th>
            <th>数量</th>
            <th>金额（元）</th>
          </tr>
          <tr align="center">
            <td>{{ saleOrder.batchCode }}</td>
            <td>{{ saleOrder.medicineName }}</td>
            <td>{{ saleOrder.specification}}</td>
            <td>{{ saleOrder.unitName }}</td>
            <td>{{ saleOrder.aimStoreHouseName }}</td>
            <td>{{ saleOrder.quantity }}</td>
            <td>{{ saleOrder.dispatchPrice }}</td>
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
export default {
  name:"printView",
  data() {
    return{
      saleOrder:"",
      medicineDetailList:"",
      commonStyle: { width: '100%' },
    }
  },
  mounted() {
    this.saleOrder = this.$route.query;
  },
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