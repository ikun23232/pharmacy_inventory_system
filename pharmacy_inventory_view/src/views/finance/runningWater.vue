<script>
import {getCwAccountsList, getCwNumByYear, getCwNumByMonth, cwAccountsExcel} from '@/api/finance'
import * as echarts from "echarts";
export default {
  name: "runningWater",
  data() {
    return {
      cwAccountsPage: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      cwAccounts: {
        code: '',
        orderCode: '',
        categoryId:0,
        bandCount:''
      },
      time:{
        year:'',
        month:''
      },
      timeTo:{

      },
      CwNumByYear:[],
      monthListNum: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      monthListNum2: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      monthListCost: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      monthListCost2: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      CwNumByMonth:{},
      thisDate:{}
    }
  },
  mounted() {
    this.time.year='2024'
    this.getCwAccountsLists()
    this.getCwNumByYears()
    this.getCwNumByMonths()
  },
  methods: {
    getThisData() {
      if (this.time.month == ''){
        this.thisDate = this.time.year + '年'
      }else if(!this.time.month){
        this.thisDate =this.time.year + '年'
      } else {
        this.thisDate = this.time.year + '年' + this.time.month + '月'
      }
      if (!this.time.year){
        this.thisDate = '全年'
      }
    },
    getCwAccountsLists() {
      if (Array.isArray(this.timeTo) && this.timeTo.length > 0) {
        // time 是一个非空数组
        this.cwAccounts.beginTime = this.timeTo[0];
        this.cwAccounts.endTime = this.timeTo[1];
      } else {
        // time 是空数组、null 或 undefined
        // 这里可以添加适当的处理逻辑，例如重置 beginTime 和 endTime
        this.cwAccounts.beginTime = null;
        this.cwAccounts.endTime = null;
      }
      getCwAccountsList(this.cwAccounts, this.cwAccountsPage.pageNum, this.cwAccountsPage.pageSize).then(resp => {
        if (resp.code != 200){
          this.cwAccountsPage.list = []
          this.cwAccountsPage.total = 0
          this.cwAccountsPage.pageNum = 1
          return;
        }
        this.cwAccountsPage = resp.data
      })
    },
    clean() {
      this.cwAccounts.code = ''
      this.cwAccounts.orderCode = ''
      this.cwAccounts.categoryId=0
      this.getCwAccountsLists()
    },
    getCwNumByYears() {
      if (!this.time.year) {
        this.time.year='';
      }
      if (this.time.year == ''){
        this.CwNumByYear = []
        return;
      }
      getCwNumByYear(this.time.year).then(resp => {
        if (resp.code != 200){
          this.CwNumByYear = []
          return;
        }
        this.CwNumByYear = resp.data
        this.getThisData()
        this.setData()
        this.initEChart()
        this.initEChart2()

      })
    },
    setData(){
      this.monthListNum = this.monthListNum.map(() => 0);
      this.monthListNum2 = this.monthListNum2.map(() => 0);
      this.CwNumByYear.forEach(item => {
        this.monthListNum[item.month - 1] = item.totalInAmount;
        this.monthListNum2[item.month - 1] = item.totalOutAmount;
        this.monthListCost[item.month - 1] = item.inCount;
        this.monthListCost2[item.month - 1] = item.outCount;
      });

    },
    getCwNumByMonths() {
      if (!this.time.year) {
        this.time.year='';
      }
      if (!this.time.month) {
        this.time.month='';
      }
      getCwNumByMonth(this.time.year,this.time.month).then(resp => {

        if (resp.code != 200){
          this.CwNumByMonth = {}
          return;
        }
        this.CwNumByMonth = resp.data
        if (!this.CwNumByMonth.totalInAmount){
          this.CwNumByMonth.totalInAmount = 0;
        }
        if (!this.CwNumByMonth.totalOutAmount){
          this.CwNumByMonth.totalOutAmount = 0;
        }
        this.getThisData()
        this.initPieChart();
        this.initPieChart2();

      })
    },
    initPieChart() {
      var myPieChart = echarts.init(this.$refs.pieChart);
      this.pieChartOption = {
        title: {
          text: this.thisDate+'入账出账金额',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              {value: this.CwNumByMonth.totalInAmount, name: '入账金额'},
              {value: this.CwNumByMonth.totalOutAmount, name: '出账金额'},

            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myPieChart.setOption(this.pieChartOption);
    },
    initPieChart2() {
      var myPieChart2 = echarts.init(this.$refs.pieChart2);
      this.pieChartOption = {
        title: {
          text: this.thisDate+'入账出账单数',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              {value: this.CwNumByMonth.inCount, name: '入账数'},
              {value: this.CwNumByMonth.outCount, name: '出账数'},

            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myPieChart2.setOption(this.pieChartOption);
    },
    initEChart() {
      var myChart = echarts.init(this.$refs.echart);
      myChart.setOption({
        title: {
          text: this.time.year + '年采购出入账金额',
          left: 'center', // 将标题居中
          top: 'top',    // 根据需要调整标题的垂直位置
          textStyle: {
            fontWeight: 'normal'  // 根据需要调整字体样式
          }
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '入账金额',
            type: 'bar',
            data: this.monthListNum
          },
          {
            name: '出账金额',
            type: 'bar',
            data: this.monthListNum2
          }
        ]
      });
    },
    initEChart2() {
      var myChart = echarts.init(this.$refs.echart2);
      myChart.setOption({
        title: {
          text: this.time.year + '年采购出入账数',
          left: 'center', // 将标题居中
          top: 'top',    // 根据需要调整标题的垂直位置
          textStyle: {
            fontWeight: 'normal'  // 根据需要调整字体样式
          }
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '入账数',
            type: 'bar',
            data: this.monthListCost
          },
          {
            name: '出账数',
            type: 'bar',
            data: this.monthListCost2
          }
        ]
      });
    },
    async printExcel(){
      await cwAccountsExcel();
    }

  }
}
</script>

<template>
 <div>
   <h1>流水统计</h1>
   <div>

     <el-row :gutter="20">
       <el-col :span="8"
       ><div class="grid-content bg-purple">
         流水编号：
         <el-input
             v-model="cwAccounts.code"
             style="width: 200px"
             placeholder="请输入单据编号"
         ></el-input></div
       ></el-col>
       <el-col :span="8"
       ><div class="grid-content bg-purple">
         订单编号：
         <el-input
             v-model="cwAccounts.orderCode"
             style="width: 200px"
             placeholder="请输入原单据编号"
         ></el-input></div
       ></el-col>
       <el-col :span="8"
       ><div class="grid-content bg-purple">
         银行账户：
         <el-input
             v-model="cwAccounts.bandCount"
             style="width: 200px"
             placeholder="请输入银行账户"
         ></el-input></div
       ></el-col>
     </el-row>
     流水日期：
     <el-date-picker
         v-model="timeTo"
         type="daterange"
         align="right"
         unlink-panels
         range-separator="至"
         start-placeholder="开始日期"
         end-placeholder="结束日期"
     />&nbsp;&nbsp;&nbsp;&nbsp;
     <el-button type="primary" @click="getCwAccountsLists()">查询</el-button>
     <el-button type="primary" @click="clean()">清空</el-button>
     <el-button type="primary" @click="printExcel()">导出Excel</el-button>
     <br/><br/>
   </div>
   <div class="table">
     <el-table :data="cwAccountsPage.list" border style="width: 100%">
       <el-table-column prop="id" label="流水id" width="120"/>
       <el-table-column prop="code" label="流水编号" width="150" fixed/>
       <el-table-column prop="accountsCategoryName" label="流水类别" />
       <el-table-column prop="orderCode" label="订单编号"/>
       <el-table-column prop="bandCount" label="银行账户"/>
       <el-table-column prop="createName" label="创建人" />
       <el-table-column prop="createTime" label="创建时间"/>
       <el-table-column prop="modificationName" label="修改人" />
       <el-table-column prop="modificationTime" label="修改时间"/>
       <el-table-column prop="cost" label="流水总价" />

     </el-table>
   </div>
   <div>
     <br/>
     <el-pagination
         @current-change="getCwAccountsLists()"
         :current-page.sync="cwAccountsPage.pageNum"
         :page-size="cwAccountsPage.pageSize"
         layout="prev, pager, next, jumper"
         :total="cwAccountsPage.total"
     />
     <br/>
   </div>
   <div>
     <el-date-picker
         v-model="time.year"
         type="year"
         placeholder="选择年份"
         format="yyyy"
         value-format="yyyy"/>&nbsp;&nbsp;&nbsp;
     <el-date-picker
         v-model="time.month"
         type="month"
         placeholder="选择月份"
         format="M"
         value-format="M"/>&nbsp;&nbsp;&nbsp;&nbsp;
     <el-button type="primary" @click="getCwNumByMonths();getCwNumByYears()">查询</el-button>
     <br/>
     <br/>
   </div>
   <div style="display: flex;">
     <!-- 柱状图容器 -->
     <div ref="echart" style="flex: 1; width: 50%; height: 400px;"></div>
     <!-- 饼图容器 -->
     <div ref="pieChart" style="flex: 1; width: 50%; height: 400px;"></div>
   </div>
   <div style="display: flex;">
     <!-- 柱状图容器 -->
     <div ref="echart2" style="flex: 1; width: 50%; height: 400px;"></div>
     <!-- 饼图容器 -->
     <div ref="pieChart2" style="flex: 1; width: 50%; height: 400px;"></div>
   </div>

 </div>
</template>

<style scoped>

</style>