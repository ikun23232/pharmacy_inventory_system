<script>
import { getCwXsysByCode } from '@/api/finance'
export default {
  name: "printSalesReceivable",
  data() {
    return {
      cwXsys: {},
      commonStyle: { width: '100%' }
    }
  },
  mounted() {
    this.getCwXsysByCodes(this.$route.query.code)
  },
  methods: {
    getCwXsysByCodes(code) {
      getCwXsysByCode(code).then(resp => {
        if (resp.code != 200) {
          return
        }
        this.cwXsys = resp.data
        console.log("11111")
        console.log(this.cwXsys)
      })
    }
  }
}
</script>

<template>
  <div>
    <div  style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">销售应收</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">应收单号:{{ cwXsys.code }}</td>
            <td style="padding:5px;" align="left">原单号:{{ cwXsys.originalOrder }}</td>
          </tr>
          <tr style="margin:0;padding:0;">

            <td style="padding:5px;" align="left">银行账号:{{ cwXsys.bandCount}}</td>
            <td style="padding:5px;" align="left">单号生成时间:{{ cwXsys.createTime}}</td>

          </tr>
        </table>

        <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
        <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <th>应收金额</th>
            <th colspan="4">{{cwXsys.cost }}</th>


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