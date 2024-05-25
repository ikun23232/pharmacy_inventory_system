<template>
  <div>
    <div v-loading="loading" style="padding: 10px">
      <div id="printView" style="padding-left: 90px; padding-right: 20px">
        <h2
          style="text-align: center; margin: 30px 0 10px 0; font-family: SimSun"
        >
          库存明细单
        </h2>
        <!-- <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div> -->
        <table
          class="yk-table"
          border="1pm"
          cellspacing="0"
          align="center"
          width="100%"
          style="
            font-size: 12px;
            font-family: SimSun;
            table-layout: fixed;
            word-wrap: break-word;
            word-break: break-all;
          "
        >
          <tr>
            <th width="55px">序号</th>
            <th>医用商品名称</th>
            <th>型号规格</th>
            <th>药品类型</th>
            <th>计量单位</th>
            <th>零售价</th>
            <th>仓库</th>
            <th>批次号</th>
            <th>库存</th>
            <th>库存预警值</th>
            <th>是否预警</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>修改人</th>
            <th>修改时间</th>
          </tr>
          <tr
            v-for="(item, index) in medicineDetailList"
            :key="index"
            align="center"
          >
            <td>{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.specification }}</td>
            <td>{{ item.categoryName }}</td>
            <td>{{ item.unitName }}</td>
            <td>{{ item.salePrice }}</td>
            <td>{{ item.storehouseName }}</td>
            <td>{{ item.batchCode }}</td>
            <td>{{ item.stock }}</td>
            <td>{{ item.warning }}</td>
            <td>{{ item.isWarning }}</td>
            <td>{{ item.createByName }}</td>
            <td>{{ item.createTime }}</td>
            <td>{{ item.updateByName }}</td>
            <td>{{ item.updateTime }}</td>
          </tr>
        </table>
      </div>

      <div class="drawer-bottom-bar">
        <el-button v-print="'#printView'" type="primary" size="small"
          >打印</el-button
        >
        <!-- <el-button @click="$parent.$parent.closePrintDialog()" size="small">取消</el-button> -->
      </div>
    </div>
  </div>
</template>
      
<script>
import { initStockDetailList } from "../../../api/stockDetail.js";
export default {
  name: "printView",
  data() {
    return {
      medicineDetailList: "",
      commonStyle: { width: "100%" },
    };
  },
  mounted() {
    this.initStockDetailList();
  },
  methods: {
    async initStockDetailList() {
      let data = await initStockDetailList();
      this.medicineDetailList = data.data;
    },
  },
};
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
.drawer-bottom-bar {
  float: right;
  margin-top: 20px;
  margin-right: 20px;
}
</style>