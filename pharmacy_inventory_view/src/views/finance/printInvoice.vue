<script>
import {getCwInvoiceByCode} from '@/api/finance';
export default {
  name: "printInvoice",
  data() {
    return {
      cwInvoice:{}
    }
  },
  mounted() {
    let code = this.$route.query.code;
    getCwInvoiceByCode(code).then(resp => {
      if (resp.code != 200) {
        return;
      }
      this.cwInvoice = resp.data;
      console.log("11111");
      console.log(this.cwInvoice);
    });
  }
}
</script>

<template>
  <div>
    <div  style="padding: 10px;">
      <div id="printView" style="padding-left: 90px; padding-right: 20px;">
        <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">发票</h2>
        <table class="table" border="0pm" cellspacing="0" align="left" width="100%" style="font-size: 12px;font-family: SimSun;margin-bottom: 10px; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <td style="padding:5px;" align="left">发票单号:{{ cwInvoice.code }}</td>
            <td style="padding:5px;" align="left">发票类型:{{ cwInvoice.categoryName}}</td>
            <td style="padding:5px;" align="left">修改人:{{ cwInvoice.modificationName }}</td>
          </tr>
          <tr style="margin:0;padding:0;">
            <td style="padding:5px;" align="left">订单单号:{{ cwInvoice.orderNumber }}</td>
            <td style="padding:5px;" align="left">创建人:{{ cwInvoice.createName }}</td>
            <td style="padding:5px;" align="left">发票生成时间:{{ cwInvoice.createTime}}</td>

          </tr>
        </table>

        <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
        <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
          <tr>
            <th>发票金额</th>
            <th colspan="4">{{cwInvoice.cost }}</th>
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