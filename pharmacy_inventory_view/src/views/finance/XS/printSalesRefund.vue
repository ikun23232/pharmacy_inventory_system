<template>
  <div class="print-sales-refund">
    <div class="print-view" style="padding: 20px;">
      <h2 class="title">销售退款</h2>
      <table class="info-table">
        <tr>
          <td class="label">退款单号:</td>
          <td>{{ cwXstk.code }}</td>
          <td class="label">原单号:</td>
          <td>{{ cwXstk.originalOrder }}</td>
        </tr>

        <tr>
          <td class="label">银行账号:</td>
          <td>{{ cwXstk.bandCount }}</td>
          <td class="label">单号生成时间:</td>
          <td>{{ formatDate(cwXstk.createTime) }}</td>
        </tr>

      </table>

      <div class="section-title">订单明细:</div>
      <table class="order-details-table">
        <thead>
        <tr>
          <th>商品名称</th>
          <th>数量</th>
          <th>单价</th>
          <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in cwXstk.items" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.quantity }}</td>
          <td>{{ formatCurrency(item.price) }}</td>
          <td>{{ formatCurrency(item.quantity * item.price) }}</td>
        </tr>
        </tbody>
      </table>

      <div class="total-section">
        <div class="total-label">退款金额:</div>
        <div class="total-value">{{ formatCurrency(cwXstk.cost) }}</div>
      </div>

      <div class="drawer-bottom-bar">
        <el-button @click="print" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCwXstkByCode } from '@/api/finance'

export default {
  name: "printSalesRefund",
  data() {
    return {
      cwXstk: {},
    }
  },
  mounted() {
    this.getCwXstkByCodes(this.$route.query.code)
  },
  methods: {
    getCwXstkByCodes(code) {
      getCwXstkByCode(code).then(resp => {
        if (resp.code != 200) {
          return
        }
        this.cwXstk = resp.data
      })
    },
    formatDate(dateString) {
      const date = new Date(dateString)
      return date.toLocaleDateString()
    },
    formatCurrency(amount) {
      return amount.toFixed(2)
    },
    print() {
      window.print()
    }
  }
}
</script>

<style scoped>
.print-sales-refund {
  padding: 10px;
}

.print-view {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.title {
  text-align: center;
  margin-bottom: 20px;
  font-family: 'SimSun', sans-serif;
}

.info-table {
  width: 100%;
  font-size: 14px;
  font-family: 'SimSun', sans-serif;
  margin-bottom: 20px;
}

.info-table td {
  padding: 10px;
  vertical-align: top;
}

.info-table .label {
  font-weight: bold;
}

.section-title {
  font-size: 14px;
  font-family: 'SimSun', sans-serif;
  font-weight: bold;
  margin-bottom: 10px;
}

.order-details-table {
  width: 100%;
  font-size: 12px;
  font-family: 'SimSun', sans-serif;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.order-details-table th,
.order-details-table td {
  padding: 5px;
  border: 1px solid #ddd;
  text-align: left;
}

.order-details-table th {
  background-color: #f5f5f5;
}

.total-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  font-family: 'SimSun', sans-serif;
  margin-bottom: 20px;
}

.total-label {
  font-weight: bold;
}

.total-value {
  font-weight: bold;
}

.drawer-bottom-bar {
  text-align: right;
}
</style>