<script>
import {getCwCgyfByCode} from '@/api/finance';
export default {
  name: "printPurchasePay",
  data() {
    return {
      cwCgyf:{}
    }
  },
  mounted() {
    this.getCwCgyfByCodes(this.$route.query.code)
  },
  methods:{
    getCwCgyfByCodes(code){
      getCwCgyfByCode(code).then(resp=>{
        if (resp.code!=200){
          return
        }
        this.cwCgyf=resp.data
        console.log("11111")
        console.log(this.cwCgyf)
      })
    }
  },
}
</script>

<template>
  <div>
    <div  style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">报损应收</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">付款单号:{{ cwCgyf.code }}</td>
            <td style="padding:5px;" align="left">供应商:{{ cwCgyf.providerName}}</td>
            <td style="padding:5px;" align="left">是否支付(1待付款2已付款):{{ cwCgyf.isPay}}</td>
          </tr>
          <tr style="margin:0;padding:0;">
            <td style="padding:5px;" align="left">原单号:{{ cwCgyf.originalOrder }}</td>
            <td style="padding:5px;" align="left">单号生成时间:{{ cwCgyf.createTime}}</td>
            <td style="padding:5px;" align="left">支付时间:{{ cwCgyf.paymentTime}}</td>

          </tr>
        </table>

        <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
        <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <th>应收金额</th>
            <th colspan="4">{{cwCgyf.cost }}</th>


          </tr>
          <tr  align="center">
            <td>备注</td>
            <td colspan="4"></td>


          </tr>

        </table>
      </div>

      <div class="drawer-bottom-bar">
        <el-button v-print="'#printView'" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>