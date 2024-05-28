<template>
    <div>
      <div v-loading="loading" style="padding: 10px;">
        <div id="printView" style="padding-left: 90px; padding-right: 20px;">
          <h2 style="text-align: center;margin: 30px 0 10px 0;font-family: SimSun;">盘点入库打印订单</h2>
  
          <div style="font-size: 12px;font-family: SimSun;font-weight: bolder;margin: 0 0 10px 5px;float: left;">订单明细:</div>
          <table class="yk-table" border="1pm" cellspacing="0" align="center" width="100%" style="font-size: 12px;font-family: SimSun; table-layout:fixed;word-wrap:break-word;word-break:break-all">
            <tr>
              <th width="55px">序号</th>
              <th>入库编号</th>
              <th>药品名称</th>
              <th>源编号</th>
              <th>仓库名称</th>
              <th>数量</th>
              <th>创建日期</th>
            </tr>
            <tr v-for="(item, index) in saleOrder" :key="index" align="center">
              <td>{{ index + 1 }}</td>
              <td>{{ item.inventoryCode }}</td>
              <td>{{ item.materialCategoryName}}</td>
              <td>{{ item.code}}</td>
              <td>{{ item.storehouseName }}</td>
              <td>{{ item.rkQuantity}}</td>
              <td>{{ item.createDate }}</td>
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
      this.getSaleOrderByOrderNo(this.$route.query.id);
    },
    methods:{
       getSaleOrderByOrderNo(orderNo) {
         this.$axios
        .get("/warehouse/check/getAllCheckById",{params:{id:orderNo}})
        .then((resp) => {
            console.log(resp.data)
            this.saleOrder=resp.data
        });
        

      },
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