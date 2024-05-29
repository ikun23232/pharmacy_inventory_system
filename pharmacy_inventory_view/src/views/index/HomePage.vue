<script>
import {getCheckHintList} from "@/api/procurementOrder";
import VueSeamlessScroll from 'vue-seamless-scroll';
import * as echarts from "echarts";

export default {
  name: "HomePage",
  data() {
    return {
      CheckHintList:[],
    }
  },
  mounted() {
    this.getCheckHintList();
    this.initEChart();
  },
  methods:{
    getCheckHintList(){
      getCheckHintList().then(resp=>{
        if (resp.code==200){
          this.CheckHintList=resp.data;
          console.log(this.CheckHintList);
        }
      })
    },
    initEChart(){
      var myChart = echarts.init(this.$refs.echart);
      myChart.setOption({
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320, 1450, 1480, 1510, 1540, 1570, 1600],
            type: 'line',
            smooth: true
          }
        ]
      })
    },
  },
  components:{
    VueSeamlessScroll,
  },
  directives: {
    'seamless-scroll': VueSeamlessScroll
  },

}
</script>

<template>
  <div>
    <el-card style="width: 38%;height:210px;float:left;margin: 10px">
      <div slot="header" class="clearfix">
        <span>概况</span>
        <el-button style="float: right; padding: 3px 0" type="text" size="big"><i class="el-icon-refresh"></i></el-button>
      </div>
      <div style="font-size: 18px;color: gray;">
        <div style="float: left;margin-left: 40px;"><p>本月支出(元)</p><p>999.00</p></div>
        <div><p>本月收出(元)</p><p>20,000.00</p></div>
      </div>
    </el-card>
    <el-card style="width: 28%;height:210px;float:left;margin: 10px">
      <div slot="header" class="clearfix">
        <span>公告</span>
        <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-refresh"></i></el-button>
      </div>
      <div class="content">
        <div v-for="o in 4" :key="o" class="text item">
          <el-tag size="small" style="margin-right: 10px;margin-bottom: 5px;">公告</el-tag>{{'列表内容 ' + o }}
        </div>
      </div>

    </el-card>
    <el-card style="width: 28%;height:210px;float:left;margin: 10px">
      <div slot="header" class="clearfix">
        <span>待办事项</span>
        <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-refresh"></i></el-button>
      </div>
      <div class="content" style="height: 20px">
        <div class="scroll-container" v-seamless-scroll>
          <div v-for="item in CheckHintList" class="text item" :key="item.id">
            <el-tag size="small" type="danger" style="margin-right: 10px;margin-bottom: 5px;">审批</el-tag>
            <router-link :to="{ path: item.path , query: {code: item.code}}">{{item.documenterName}}的{{item.tableName}}审批</router-link>
          </div>
        </div>
      </div>
    </el-card>

    <div ref="echart" style="flex: 1; width: 100%; height: 400px;"></div>

  </div>
</template>

<style scoped>
.clearfix{
  font-size: 20px;
  text-align: left;
}
.content{
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
</style>