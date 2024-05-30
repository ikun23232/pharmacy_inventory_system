<template>
  <div>
    <div style="padding: 10px;">
      <div id="printView" style="padding: 20px; background-color: #f9f9f9; border: 1px solid #ddd;">
        <h2 style="text-align: center; margin-bottom: 20px; font-family: Arial, sans-serif;">销售应收</h2>
        <table class="invoice-info" border="0" cellpadding="0" cellspacing="0" style="width: 100%; margin-bottom: 20px; font-family: Arial, sans-serif;">
          <tr>
            <td style="padding: 5px; border-bottom: 1px solid #ddd;">应收单号: {{ cwXsys.code }}</td>
            <td style="padding: 5px; border-bottom: 1px solid #ddd;">原单号: {{ cwXsys.originalOrder }}</td>

          </tr>
          <tr>
            <td style="padding: 5px; border-bottom: 1px solid #ddd;">银行账号: {{ cwXsys.bandCount }}</td>
            <td style="padding: 5px; border-bottom: 1px solid #ddd;">单号生成时间: {{ cwXsys.createTime }}</td>
          </tr>
        </table>

        <div style="font-size: 14px; font-family: Arial, sans-serif; font-weight: bold; margin-bottom: 10px;">订单明细:</div>
        <table class="invoice-items" border="1" cellpadding="5" cellspacing="0" style="width: 100%; margin-bottom: 20px; font-family: Arial, sans-serif;">
          <thead>
          <tr>
            <th style="background-color: #f2f2f2;">商品名称</th>
            <th style="background-color: #f2f2f2;">数量</th>
            <th style="background-color: #f2f2f2;">单价</th>
            <th style="background-color: #f2f2f2;">小计</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item, index) in cwXsys.items" :key="index">
            <td>{{ item.name }}</td>
            <td>{{ item.quantity }}</td>
            <td>{{ item.salePrice }}</td>
            <td>{{ item.quantity * item.salePrice }}</td>
          </tr>
          </tbody>
        </table>

        <div style="font-size: 14px; font-family: Arial, sans-serif; font-weight: bold; margin-bottom: 10px;">应收金额: {{ cwXsys.cost }}</div>

        <div style="font-size: 12px; font-family: Arial, sans-serif; margin-bottom: 10px;">备注: {{ cwXsys.remark }}</div>
      </div>

      <div class="drawer-bottom-bar" style="text-align: center; margin-top: 20px;">
        <el-button @click="printInvoice" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCwXsysByCode } from '@/api/finance';
import { getSaleOrderByOrderNo } from '@/api/saleOrder';

export default {
  name: "printSalesReceivable",
  data() {
    return {
      cwXsys: {
        items: [] // 初始化为空数组
      }
    }
  },
  mounted() {
    this.getCwXsysByCodes(this.$route.query.code);
  },
  methods: {
    getCwXsysByCodes(code) {
      getCwXsysByCode(code).then(resp => {
        if (resp.code != 200) {
          return;
        }
        this.cwXsys = resp.data;
        getSaleOrderByOrderNo(this.cwXsys.originalOrder).then(resp => {
          if (resp.code != 200) {
            return;
          }
          // 使用 Vue.set 来设置 items 数组
          this.$set(this.cwXsys, 'items', resp.data.baseMedicineList);
          console.log(this.cwXsys.items);
        });
      });
    },
    printInvoice() {
      const printContents = document.getElementById('printView').innerHTML;
      const originalContents = document.body.innerHTML;
      document.body.innerHTML = printContents;
      window.print();
      document.body.innerHTML = originalContents;
    }
  }
}
</script>

<style scoped>
.invoice-info,
.invoice-items {
  width: 100%;
  border-collapse: collapse;
}

.invoice-info th,
.invoice-info td,
.invoice-items th,
.invoice-items td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.invoice-info th,
.invoice-items th {
  background-color: #f2f2f2;
}

.invoice-items tr:nth-child(even) {
  background-color: #f9f9f9;
}
</style>