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
      showList: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      pieChartOption: {},// 饼图的配置
      pay: {
        success: 0,
        fail: 0
      }
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
    initEChart() {
      var myChart = echarts.init(this.$refs.echart);
      myChart.setOption({
        title: {
          text: '                                                      ' + this.year + '年采购成交数'
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
    initPieChart() {
      var myPieChart = echarts.init(this.$refs.pieChart);
      this.pieChartOption = {
        title: {
          text: '当年当月采购成功成交数',
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
          }
          if (resp.data[i].paymentStatus == '未支付') {
            this.pay.fail = resp.data[i].orderCount;
          }
        }
        this.initPieChart();
      });
    },
    getCgPayNumInfo() {
      if (this.year) {
        getCgPayNum(this.year).then(resp => {
          console.log(resp.data);
          this.monthListNum = resp.data;
          this.getCgPayNumInfo2();
        });
      }
    },
    getCgPayNumInfo2() {
      this.monthListNum2 = this.monthListNum2.map(() => 0); // 重置数组为0
      this.monthListNum.forEach(item => {
        this.monthListNum2[item.month - 1] = item.paidCount;
      });
      this.initEChart(); // 更新柱状图
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