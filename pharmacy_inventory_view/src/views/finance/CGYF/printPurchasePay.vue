<template>
  <div class="print-purchase-pay">
    <div class="print-view" style="padding: 20px;">
      <h2 class="title">采购应付</h2>
      <table class="info-table">
        <tr>
          <td class="label">付款单号:</td>
          <td>{{ cwCgyf.code }}</td>
        </tr>
        <tr>
          <td class="label">供应商:</td>
          <td>{{ cwCgyf.providerName }}</td>
        </tr>
        <tr>
          <td class="label">是否支付:</td>
          <td>{{ cwCgyf.isPay === 1 ? '待付款' : '已付款' }}</td>
        </tr>
        <tr>
          <td class="label">原单号:</td>
          <td>{{ cwCgyf.cgddCode}}</td>
        </tr>
        <tr>
          <td class="label">单号生成时间:</td>
          <td>{{ formatDate(cwCgyf.createTime) }}</td>
        </tr>
        <tr>
          <td class="label">支付时间:</td>
          <td>{{ formatDate(cwCgyf.paymentTime) }}</td>
        </tr>
      </table>

      <div class="section-title">应付明细:</div>
      <table class="order-details-table">
        <thead>
        <tr>
          <th>药品名称</th>
          <th>数量</th>
          <th>单价</th>
          <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in cwCgyf.items" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.quantity }}</td>
          <td>{{ formatCurrency(item.purchasePrice) }}</td>
          <td>{{ formatCurrency(item.quantity * item.purchasePrice)}}</td>
        </tr>
        </tbody>
      </table>

      <div class="total-section">
        <div class="total-label">应收金额:</div>
        <div class="total-value">{{ formatCurrency(cwCgyf.cost) }}</div>
      </div>

      <div class="drawer-bottom-bar">
        <el-button @click="print" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCwCgyfByCode } from '@/api/finance';
import {getMedicineListByCode} from '@/api/baseMedicine';
import Vue from "vue";
export default {
  name: "printPurchasePay",
  data() {
    return {
      cwCgyf: {
        items: [],
        cost: 0 ,// 确保 cost 被初始化为一个数字

      },
      theData:[]
    }
  },
  mounted() {
    this.getCwCgyfByCodes(this.$route.query.code)
  },
  methods: {
    getCwCgyfByCodes(code) {
      getCwCgyfByCode(code).then(resp => {
        if (resp.code != 200) {
          return
        }
        this.cwCgyf = resp.data
        console.log(resp.data)
        getMedicineListByCode(this.cwCgyf.cgddCode).then(resp => {
          // 假设resp.data是你要添加到items数组中的数据
          Vue.set(this.cwCgyf, 'items', resp.data);
          console.log("11111");
          console.log(this.cwCgyf.items);
        })


      })
    },
    formatDate(dateString) {
      if (dateString) {
        const date = new Date(dateString)
        return date.toLocaleDateString()
      }
      return ''
    },
    formatCurrency(amount) {
      if (typeof amount === 'number') {
        return amount.toFixed(2);
      } else {
        console.error('formatCurrency 方法需要一个数字类型的参数');
        return '';
      }
    },
    print() {
      window.print()
    }
  }
}
</script>

<style scoped>
.print-purchase-pay {
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