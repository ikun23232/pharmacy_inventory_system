<template>
  <div class="print-breakage-receivable">
    <div class="print-view" style="padding: 20px;">
      <h2 class="title">报损应收</h2>
      <table class="info-table">
        <tr>
          <td class="label">报损单号:</td>
          <td>{{ cwBsys.code }}</td>
        </tr>
        <tr>
          <td class="label">报损仓库:</td>
          <td>{{ cwBsys.storehouseName }}</td>
        </tr>
        <tr>
          <td class="label">报损类型:</td>
          <td>{{ cwBsys.reportedTypeName }}</td>
        </tr>
        <tr>
          <td class="label">原单号:</td>
          <td>{{ cwBsys.originalOrder }}</td>
        </tr>
        <tr>
          <td class="label">审批人:</td>
          <td>{{ cwBsys.createByName }}</td>
        </tr>
        <tr>
          <td class="label">单号生成时间:</td>
          <td>{{ formatDate(cwBsys.createTime) }}</td>
        </tr>
      </table>

      <div class="section-title">报损明细:</div>
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
        <tr v-for="item in cwBsys.items" :key="item.id">
          <td>{{ item.drugName }}</td>
          <td>{{ item.quantity }}</td>
          <td>{{ formatCurrency(item.price) }}</td>
          <td>{{ formatCurrency(item.quantity * item.price) }}</td>
        </tr>
        </tbody>
      </table>

      <div class="total-section">
        <div class="total-label">应收金额:</div>
        <div class="total-value">{{ formatCurrency(cwBsys.cost) }}</div>
      </div>

      <div class="drawer-bottom-bar">
        <el-button @click="print" type="primary" size="small">打印</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCwbsysByCode } from '@/api/finance';

export default {
  name: "printBreakageReceivable",
  data() {
    return {
      cwBsys: {
        items: [],
        cost: 0 // 确保 cost 被初始化为一个数字
      }
    }
  },
  mounted() {
    this.getCwbsysByCodes(this.$route.query.code)
  },
  methods: {
    getCwbsysByCodes(code) {
      getCwbsysByCode(code).then(resp => {
        if (resp.code != 200) {
          return
        }
        this.cwBsys = resp.data
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
.print-breakage-receivable {
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