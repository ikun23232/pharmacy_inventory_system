<template>
  <div ref="chartContainer" style="width: 50%; height: 400px;"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'EChartsExample',
  data() {
    return {
      myChart: null
    };
  },
  mounted() {
    this.initChart();
  },
  methods: {
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
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '去年',
              data: [820, 932, 901, 934, 1290, 1330, 1320, 1480, 1510, 1540, 1570, 1600],
              type: 'line',
              smooth: true
            },
            {
              name: '今年',
              data: [1000, 1200, 1100, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100],
              type: 'line',
              smooth: true
            }
          ]
        };
        this.myChart.setOption(option);
      });
    }
  }
};
</script>

<style scoped>
/* 在这里添加图表的样式 */
</style>