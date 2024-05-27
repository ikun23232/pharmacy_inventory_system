<template>
  <div>
    <div  style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">库存报损</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">报损单号:{{ KcReported.code }}</td>
            <td style="padding:5px;" align="left">报损仓库:{{ KcReported.storehouseName}}</td>
            <td style="padding:5px;" align="left">报损类型:{{ KcReported.reportedTypeName }}</td>
            <td style="padding:5px;" align="left">报损时间:{{ KcReported.createTime }}</td>
          </tr>
          <tr style="margin:0;padding:0;">
            <td style="padding:5px;" align="left">报损人:{{ KcReported.createName }}</td>
            <td style="padding:5px;" align="left">审批人:{{ KcReported.approverName}}</td>
            <td style="padding:5px;" align="left">报损审批时间:{{ KcReported.modificationTime }}</td>
            <td style="padding:5px;" align="left">审批结果(1未通过2通过):{{KcReported.approvalStatus}}</td>
          </tr>
        </table>

        <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
        <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <th width="55px">序号</th>
            <th>医用商品名称</th>
            <th>药品批次号</th>
            <th>供应商</th>
            <th>药品单价(元)</th>
            <th>数量</th>
            <th>合计</th>

          </tr>
          <tr v-for="(item, index) in KcReported.medicineList" :key="index" align="center">
            <td>{{ index + 1 }}</td>
            <td>{{ item.medicineName }}</td>
            <td>{{ item.batchCode}}</td>
            <td>{{ item.providerName}}</td>
            <td>{{ item.money }}</td>
            <td>{{ item.reportedNum }}</td>
            <th>{{ item.money * item.reportedNum}}</th>

          </tr>
          <tr align="center">
            <td colspan="6" style="font-weight: bolder;">合计</td>
            <td>{{allPrice}}</td>
          </tr>
          <tr align="center">
            <td style="font-weight: bolder;">备注</td>
            <td colspan="6">无</td>
          </tr>
        </table>
      </div>

      <div class="drawer-bottom-bar">
        <el-button v-print="'#printView'" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {getKcReportedByCode} from '@/api/KcReported';

export default {
  name:"PrintKcReported",
  data() {
    return{
      KcReported:{},
      allPrice:0,
      commonStyle: { width: '100%' },
    }
  },
  mounted() {
    this.getKcReportedByCodes(this.$route.query.code);
  },
  methods:{
    getKcReportedByCodes(code){
      getKcReportedByCode(code).then(resp=>{
        if (resp.code!=200){
          return
        }
        this.KcReported=resp.data
        console.log(this.KcReported)
        this.allPriceAdd()
      })
    },
    allPriceAdd(){
      for (let i=0;i<this.KcReported.medicineList.length;i++){
        this.allPrice+=this.KcReported.medicineList[i].money*this.KcReported.medicineList[i].reportedNum
      }
    }
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