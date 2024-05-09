<template>
    <div class="order">
      <h1 class="title">销售订单</h1>
      <div class="data">
         <div class="field"><span>客户：</span></div><div class="value">测试测是是的</div>
      </div>
      <div class="data">
          <div class="field">单据日期：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">单据编号：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">联系人：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">主题：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">付款方式：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">交货日期：</div><div class="value">收藏收藏是</div>
      </div>
      <div class="data">
          <div class="field">结算方式：</div><div class="value">收藏收藏是</div>
      </div>
      <div class="data">
          <div class="field">交货方式：</div><div class="value">收藏收藏是</div>
      </div>
      <div class="data">
          <div class="field">结算日期：</div><div class="value">收藏收藏是</div>
      </div>
      <div class="data">
          <div class="field">运输方式：</div><div class="value">收藏收藏是</div>
      </div>
      <div class="data">
          <div class="field">开票方式：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">交货地点：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">发票类型：</div><div class="value">收藏收藏是</div>
      </div>
        <table>
      <tr>
        <td>序号</td>
        <td>物料</td>
        <td>规格型号</td>
        <td>单位</td>
        <td>数量</td>
        <td>税率(%)</td>
        <td>含税单价</td>
        <td>折扣率</td>
        <td>税额</td>
        <td>含税金额</td>
      </tr>
      <tr v-for="item in list" :key="item.id">
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
        <td>{{item.value}}</td>
      </tr>
      <tr>
        <td colspan="4">合计 </td>
        <td>1</td>
        <td>1</td>
        <td>1</td>
        <td>1</td>
        <td>1</td>
        <td>1</td>
      </tr>
      <tr>
        <td>备注</td>
        <td colspan="9"></td>
      </tr>
    </table>
      <div class="data">
          <div class="field">业务部门：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">业务员：</div><div class="value">收藏收藏是</div>
      </div>
        <div class="data">
          <div class="field">制单：</div><div class="value">收藏收藏是</div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
      name:"salesOrder",
      data() {
        return {
      
        }
      },
  }
  </script>
  
  <style>
    .order{
      width: 1000px;
      height: 450px;
      margin: 0px auto;
    }
    .title{
      margin-left:400px;
    }
    .data{
      width: 300px;
      height: 20;
      float: left;
      margin-bottom: 10px;
      margin-left: 20px;
    }
    .field{
      width: 80px;
      text-align:justify; 
      text-justify:inter-ideograph;
      float: left;
      text-align: center;
    }
    .value{
      float: left;
    }
    table{
      float: left;
      width: 1000px;
      border-collapse:collapse;
      text-align:center;
      margin-bottom: 10px;
    }
    table td{
        border: 1px solid black;
        height: 30px;
    }
  </style>