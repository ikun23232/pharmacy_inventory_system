<template>
  <div>
    <el-card style="width: 30%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>概况</span>
        <el-button style="float: right; padding: 3px 0" type="text" size="big">
          详情
        </el-button>
      </div>
      <div style="font-size: 18px; color: gray">
        <div style="float: left; margin-left: 40px">
          <p>本月支出(元)</p>
          <p>{{ this.CWStatistics.totalExpense}}</p>
        </div>
        <div>
          <p>本月收出(元)</p>
          <p>{{ this.CWStatistics.totalIncome}}</p>
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>库存告警</span>
        <el-button
          v-if="warningCount == 0"
          style="float: right; padding: 3px 0"
          type="text">
          <i class="el-icon-refresh"></i>暂无</el-button>
        <el-button
          v-if="warningCount > 0"
          style="float: right; padding: 3px 0"
          type="text"
          @click="toKCGJManager">
          更多({{ warningCount }})</el-button>
      </div>
      <div class="content" >
        <div v-for="o in alarmList" :key="o.id" class="text item">
          <el-tag size="small" style="margin-right: 10px; margin-bottom: 5px">{{o.warningType == 1 ? "本地仓库告警":"储存总数报警"}}</el-tag>{{ o.medicineName }}
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>公告</span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <div class="content">
        <div v-for="item in tableData" class="text item" :key="item.id">
          <el-tag size="small" type="danger" style="margin-right: 10px; margin-bottom: 5px">公告</el-tag>
          <router-link :to="{ path: '/Newslst', query: { code: item.noticetitle } }">{{ item.noticetitle }}</router-link>
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>待办事项</span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <div class="content" style="height: 20px">
        <div class="scroll-container" >
          <div v-for="item in CheckHintList" class="text item" :key="item.id">
            <el-tag size="small" type="danger" style="margin-right: 10px; margin-bottom: 5px">审批</el-tag>
            <router-link :to="{ path: item.path, query: { code: item.code } }">{{ item.documenterName }}的{{ item.tableName }}审批</router-link>
          </div>
        </div>
      </div>
    </el-card><br/>
    <el-card style="width: 20%; height: 250px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>销售订单</span>
        <el-button style="float: right; padding: 3px 0" type="text" >

        </el-button>
      </div>
      <div class="content">
        <div>销售</div>
        <div class="statistics">
          <div class="statistic-item">
            <div class="statistic-label">今日笔数</div>
            <div class="statistic-value">{{ SalesStatistics.daySalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">今日金额</div>
            <div class="statistic-value">{{ SalesStatistics.daySalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月笔数:{{ SalesStatistics.monthsSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月金额:{{ SalesStatistics.monthSalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周笔数:{{ SalesStatistics.weekSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周金额:{{ SalesStatistics.weekSalesPrice }}</div>
          </div>
        </div>

      </div>
    </el-card>
    <el-card style="width: 20%; height: 250px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>销售退款</span>
        <el-button style="float: right; padding: 3px 0" type="text" >

        </el-button>
      </div>
      <div class="content">
        <div>销售</div>
        <div class="statistics">
          <div class="statistic-item">
            <div class="statistic-label">今日笔数</div>
            <div class="statistic-value">{{ SalesStatisticsTK.daySalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">今日金额</div>
            <div class="statistic-value">{{ SalesStatisticsTK.daySalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月笔数:{{ SalesStatisticsTK.monthsSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月金额:{{ SalesStatisticsTK.monthSalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周笔数:{{ SalesStatisticsTK.weekSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周金额:{{ SalesStatisticsTK.weekSalesPrice }}</div>
          </div>
        </div>

      </div>
    </el-card>
    <el-card style="width: 51%; height: 250px; float: left; margin: 10px">
      <span>今年支出收入</span>
      <div ref="chartContainer" style="width: 100%; height: 240px;"></div>
    </el-card>

    <el-card style="width: 20%; height: 250px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>采购订单</span>
        <el-button style="float: right; padding: 3px 0" type="text" >

        </el-button>
      </div>
      <div class="content">
        <div>采购</div>
        <div class="statistics">
          <div class="statistic-item">
            <div class="statistic-label">今日笔数</div>
            <div class="statistic-value">{{ CgStatistics.daySalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">今日金额</div>
            <div class="statistic-value">{{ CgStatistics.daySalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月笔数:{{ CgStatistics.monthsSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月金额:{{ CgStatistics.monthSalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周笔数:{{ CgStatistics.weekSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周金额:{{ CgStatistics.weekSalesPrice }}</div>
          </div>
        </div>

      </div>
    </el-card>

    <el-card style="width: 30%; height: 250px; float: left; margin: 10px">
      <div ref="pieChart2" style=" width: 110%; height: 300%;"></div>
    </el-card>

    <el-card style="width: 41%; height: 250px; float: left; margin: 10px">
      <div ref="echart" style="flex: 1; width: 100%; height: 250%;"></div>
    </el-card>



  </div>
</template>

<script>
import { getAlarmCount, initKcAlarmList,checkWarning} from "@/api/KcWarning";
import {getCheckHintList, getCgStatistics, getCgPayNum} from "@/api/procurementOrder";
import {getSalesStatistics,getSalesStatisticsTK} from "@/api/saleStatistics";
import {getCwNumByMonth, getCWStatistics, getCWStatisticsPicture} from "@/api/finance";
import * as echarts from "echarts";
export default {
  name: "index",
  data() {
    return {
      orderMedicineList: [],
      saleOrderMoneyList: [],
      saleOrderNumberList: [],
      CheckHintList: [],
      warningCount: "",
      alarmList: [],
      vo: {
        currentPageNo: 1,
        pageSize: 4,
        code: "",
        medicineId: "",
        warningType: "",
        startTime: "",
        endTime: "",
      },
      SalesStatistics:{},
      SalesStatisticsTK:{},
      CgStatistics:{},
      searchForm: {
        noticeTitle:'',
        startTime:null,
        endTime:null,
        current: 1,
      },
      tableData:[],
      CWStatistics:{},
      myChart: null,
      CWStatisticsPicture:[],
      income:[0,0,0,0,0,0,0,0,0,0,0,0,],
      expenditure:[0,0,0,0,0,0,0,0,0,0,0,0,],
      monthList:['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      CwNumByMonth:{},
      time:{
        year:'',
        month:''
      },
      monthListNum2: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],

    };
  },
  async mounted() {
    this.getCheckHintList();
    await checkWarning();
    getAlarmCount().then((resp) => {
      this.warningCount = resp.data;
    });
    initKcAlarmList(this.vo).then((resp) => {

      this.alarmList = resp.data.list;
    });

    this.getCgStatisticses()

    this.getSalesStatisticses()
    this.getSalesStatisticsesTK()
    this.getNoticeList()
    this.getCWStatisticses()
    this.getCWStatisticsPictures()
    this.time.year='2024'
    this.getCwNumByMonths()
    this.getCgPayNumInfo();


  },
  methods: {
    getCheckHintList() {
      getCheckHintList().then((resp) => {
        if (resp.code == 200) {
          this.CheckHintList = resp.data;

        }
      });
    },

    toKCGJManager(){
      this.$router.push({
        path:"/KCGJManager"
      })
    },
    //销售订单
    getSalesStatisticses(){
      getSalesStatistics().then(resp=>{
        if(resp.code==200){
          this.SalesStatistics=resp.data;
        }
      })
    },
    getSalesStatisticsesTK(){
      getSalesStatisticsTK().then(resp=>{
        if(resp.code==200){
          this.SalesStatisticsTK=resp.data;
        }
      })
    },
    getCgStatisticses(){
      getCgStatistics().then(resp=>{
        if(resp.code==200){
          this.CgStatistics=resp.data;
        }
      })
    },
    getNoticeList() {
      this.searchForm.startTime = ''
      this.searchForm.endTime = ''
      if (this.value2 != null && this.value2.length > 0) {
        this.searchForm.startTime = this.value2[0];
        this.searchForm.endTime = this.value2[1];
      }
      this.$axios
          .post("/user/sysNotice/getNoticeList", this.searchForm)
          .then((res) => {
            this.tableData = res.data.list;
          });
    },
    getCWStatisticses(){
      getCWStatistics().then(resp=>{
        if(resp.code==200){
          this.CWStatistics=resp.data;
        }
      })
    },
    getCWStatisticsPictures(){
      getCWStatisticsPicture().then(resp=>{
        if(resp.code==200){
          this.CWStatisticsPicture=resp.data;
          this.setDataToChart()
          this.initChart();
        }
      })
    },

    // setDataToChart(){
    //   this.monthList=['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    //   this.income=[0,0,0,0,0,0,0,0,0,0,0,0,]
    //   this.expenditure=[0,0,0,0,0,0,0,0,0,0,0,0,]
    //   this.CWStatisticsPicture.forEach(item=>{
    //     this.income[item.month-1]=item.totalIncome
    //     this.expenditure[item.month-1]=item.totalExpense
    //
    //   })
    // },
    setDataToChart() {
      this.monthList = [];
      this.income = [];
      this.expenditure = [];
      // 遍历CWStatisticsPicture数据，根据月份填充monthList、income和expenditure数组
      this.CWStatisticsPicture.forEach(item => {
        // 检查月份是否已经存在于monthList中
        const monthIndex = this.monthList.findIndex(month => month === `${item.month}月`);
        if (monthIndex === -1) {
          // 如果月份不存在，则添加到monthList中
          this.monthList.push(`${item.month}月`);
          // 同时在income和expenditure中添加对应的数据
          this.income.push(item.totalIncome);
          this.expenditure.push(item.totalExpense);
        } else {
          // 如果月份已经存在，则更新income和expenditure中的数据
          this.income[monthIndex] = item.totalIncome;
          this.expenditure[monthIndex] = item.totalExpense;
        }
      });
      // 确保monthList中的月份按照顺序排列
      this.monthList.sort((a, b) => parseInt(a) - parseInt(b));
    },
    initChart() {
      this.$nextTick(() => {
        const chartDom = this.$refs.chartContainer;
        this.myChart = echarts.init(chartDom);
        const option = {
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: this.monthList,
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '收入',
              data: this.income,
              type: 'line',
              smooth: true
            },
            {
              name: '支出',
              data: this.expenditure,
              type: 'line',
              smooth: true
            }
          ]
        };
        this.myChart.setOption(option);
      });
    },
    initPieChart2() {
      var myPieChart = echarts.init(this.$refs.pieChart2);
      this.pieChartOption = {
        title: {
          text: '本月入账出账单数',
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
      myPieChart.setOption(this.pieChartOption);
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
          this.CwNumByMonth = []
          return;
        }
        this.CwNumByMonth = resp.data
        // this.initPieChart();
        this.initPieChart2();
        console.log(this.CwNumByMonth)
      })
    },
    initEChart() {
      var myChart = echarts.init(this.$refs.echart);
      myChart.setOption({
        title: {
          text: '今年采购成交数'
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
    getCgPayNumInfo() {
      const year = new Date().getFullYear().toString();
      if (year) {
        getCgPayNum(year).then(resp => {
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


  },
  components: {
  },
  directives: {
  },
};
</script>

<style>
.clearfix {
  font-size: 20px;
  text-align: left;
}
.content {
  font-size: 14px;
  text-align: left;
}
.el-icon-refresh {
  font-size: 20px;
}

.scroll-container {
  height: 116px; /* 设置一个固定的高度 */
  overflow-y: auto; /* 启用垂直滚动 */
}
.statistics {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.statistic-item {
  flex: 1 0 50%;
  text-align: center;
  margin-bottom: 10px;
}

.statistic-label {
  font-size: 14px;
  color: #606266;
}

.statistic-value {
  font-size: 20px;
  color: #303133;
}

.statistic-value.large {
  font-size: 24px;
}

.statistic-value.black {
  font-weight: bold;
}
</style>