<template>
  <div>
    <h1>采购统计</h1>
    <el-date-picker
        v-model="year"
        type="year"
        placeholder="选择年份"
        format="yyyy"
        value-format="yyyy">
    </el-date-picker>&nbsp;&nbsp;&nbsp;&nbsp;


    <el-date-picker
        v-model="month"
        type="month"
        placeholder="选择月份"
        format="M"
        value-format="M"/>&nbsp;&nbsp;&nbsp;&nbsp;


    <el-button type="primary" @click="getCgPayComInfo();getCgPayNumInfo();getList()">查询</el-button>
    <br/><br/><br/>
    <div class="table-container">
      <el-table :data="showList.list" border>
        <el-table-column label="序号" width="100">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="year" label="年">
        </el-table-column>
        <el-table-column prop="month" label="月">
        </el-table-column>
        <el-table-column prop="allPayCount" label="全部采购数量">
        </el-table-column>
        <el-table-column prop="paidCount" label="已支付数量">
        </el-table-column>
        <el-table-column prop="unpaidCount" label="未支付数量">
        </el-table-column>
        <el-table-column prop="totalPaidAmount" label="已支付金额">
        </el-table-column>
        <el-table-column prop="totalUnpaidAmount" label="未支付金额">
        </el-table-column>
      </el-table>

    </div><br/>
    <el-pagination
        @current-change="getList"
        :current-page.sync="showList.pageNum"
        :page-size="showList.pageSize"
        background
        layout="prev, pager, next"
        :total="showList.total">
    </el-pagination>
    <br/><br/><br/>
    <div style="display: flex;">
      <!-- 柱状图容器 -->
      <div ref="echart" style="flex: 1; width: 50%; height: 400px;"></div>
      <!-- 饼图容器 -->
      <div ref="pieChart" style="flex: 1; width: 50%; height: 400px;"></div>
    </div><br/>
    <div style="display: flex;">
      <!-- 柱状图容器 -->
      <div ref="echart2" style="flex: 1; width: 50%; height: 400px;"></div>
      <!-- 饼图容器 -->
      <div ref="pieChart2" style="flex: 1; width: 50%; height: 400px;"></div>
    </div>


  </div>
</template>

<script>
import * as echarts from 'echarts';
import {getCgPayCom, getCgPayNum, getCgPayNumList} from '@/api/procurementOrder';

export default {
  name: 'EChartComponent',
  data() {
    return {
      year: new Date().getFullYear().toString(),
      month: '',
      monthListNum: [],
      monthListNum2: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      monthListNums: [],
      monthListNums2: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      showList: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      pieChartOption: {},// 饼图的配置
      pieChartOption2: {},
      pay: {
        success: 0,
        fail: 0
      },
      pay2: {
        success: 0,
        fail: 0
      },
      thisDate:""
    };
  },
  mounted() {
    this.getCgPayComInfo();
    this.getCgPayNumInfo();
    this.initEChart();
    this.initPieChart();
    this.getList();
  },
  methods: {
    getThisData() {
      if (this.month == ''){
        this.thisDate = this.year + '年'
      }else if(!this.month){
        this.thisDate = this.year + '年'
      } else {
        this.thisDate = this.year + '年' + this.month + '月'
      }
      if (!this.year){
        this.thisDate = '全年'
      }

    },
    initEChart() {
      var myChart = echarts.init(this.$refs.echart);
      myChart.setOption({
        title: {
          text: this.year + '年采购成交数',
          left: 'center'
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
            name: '销量',
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
          text: this.year + '年采购成金额',
          left: 'center'
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
            name: '销量',
            type: 'bar',
            data: this.monthListNums2
          }
        ]
      });
    },
    initPieChart() {
      var myPieChart = echarts.init(this.$refs.pieChart);
      this.pieChartOption = {
        title: {
          text: this.thisDate+'采购成功成交数',
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
              {value: this.pay.success, name: '已支付'},
              {value: this.pay.fail, name: '未支付'},

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
      this.pieChartOption2 = {
        title: {
          text: this.thisDate+'采购成功成金额',
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
              {value: this.pay2.success, name: '已支付'},
              {value: this.pay2.fail, name: '未支付'},

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
      myPieChart2.setOption(this.pieChartOption2);
    },
    getCgPayComInfo() {
      // 设置年份和月份的默认值为空字符串
      let year = '';
      let month = '';
      // 检查this.year是否存在且不为''，如果存在则赋值给year
      if (this.year) {
        year = this.year;
      }
      // 检查this.month是否存在且不为''，如果存在则赋值给month
      if (this.month) {
        month = this.month;
      }
      this.pay.success = 0
      this.pay.fail = 0
      // 调用getCgPayCom函数并传入年份和月份
      getCgPayCom(year, month).then(resp => {

        for (let i = 0; i < resp.data.length; i++) {
          if (resp.data[i].paymentStatus == '已支付') {
            this.pay.success = resp.data[i].orderCount;
            this.pay2.success = resp.data[i].totalAmount;
          }
          if (resp.data[i].paymentStatus == '未支付') {
            this.pay.fail = resp.data[i].orderCount;
            this.pay2.fail = resp.data[i].totalAmount;
          }
        }
        this.getThisData();
        this.initPieChart();
        this.initPieChart2();
      });
    },
    getCgPayNumInfo() {
      if (this.year) {
        getCgPayNum(this.year).then(resp => {

          this.monthListNum = resp.data;
          this.monthListNums = resp.data;
          this.getCgPayNumInfo2();
        });
      }
    },
    getCgPayNumInfo2() {
      this.monthListNum2 = this.monthListNum2.map(() => 0); // 重置数组为0
      this.monthListNums2 = this.monthListNums2.map(() => 0);
      this.monthListNum.forEach(item => {
        this.monthListNum2[item.month - 1] = item.paidCount;
      });
      this.monthListNums.forEach(item => {
        this.monthListNums2[item.month - 1] = item.totalPaidAmount;
      });
      console.log("2222222222222222222222222222222222");
      console.log(this.monthListNums);
      console.log(this.monthListNums2);
      this.initEChart(); // 更新柱状图
      this.initEChart2();

    },
    getList() {
      let year = '';
      if (this.year) {
        year = this.year;
      }
      getCgPayNumList(year, this.showList.pageNum, this.showList.pageSize).then(resp => {
        console.log(resp.data);
        this.showList = resp.data;

      });
    }
  }
};
</script>

<style scoped>
.table-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>