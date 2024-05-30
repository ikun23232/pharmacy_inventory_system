<template>
    <div id="saleOrderStatistics">
     <h1>销售订单统计</h1>
        <div style="padding-top: 15px;padding-left: 20px;">
            <el-form :inline="true" >
                <el-form-item label="年">
                    <el-date-picker
                    v-model="object.orderYear"
                    type="year"
                    format="yyyy"
                    value-format="yyyy"
                    placeholder="选择年">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="initSaleOrderStatisticsList(1)">查询</el-button>
                    <el-button icon="el-icon-refresh-right" >重置</el-button>
                <!-- <el-button type="text" icon="el-icon-upload2" style="margin-left:18px">导出</el-button> -->
                </el-form-item>
            </el-form>
        </div>
        <el-card shadow="always" :body-style="{ padding: '0px' }" style="width: 1200px;height: 400px;margin:0 auto">
        <div id="moneyChartLine" style="width:100%;height: 360px"></div>
        </el-card>
        <el-card shadow="always" :body-style="{ padding: '0px' }" style="width: 1200px;height: 400px;margin:20px auto">
        <div id="numberChartLine" style="width:100%;height: 360px"></div>
        </el-card>
        <!-- <el-card shadow="always" :body-style="{ padding: '0px' }" style="width: 500px;height: 350px;padding:15px 10px;float: left;margin-right: 10px;">
            <div id="chartPie" style="width:100%;height: 350px"></div>
        </el-card>
        <el-card shadow="always" :body-style="{ padding: '0px' }" style="width: 500px;height: 350px;padding:15px 10px;float: left;margin-right: 10px;">
           
        </el-card> -->
   </div>
 </template>
 
 <script>
 import {getAllSaleOrderStatisticsList} from "@/api/saleStatistics.js";
 import {getAllRefundOrderStatisticsList} from "@/api/saleStatistics.js";
 
import * as echarts from 'echarts';
let echarts1 = require('echarts/lib/echarts')
let echarts2 = require('echarts/lib/echarts')
// 引入饼状图组件
require('echarts/lib/chart/pie')
// 引入提示框和title组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
 export default {
     name:"saleStatistics",
     data(){
         return{
             object:{
                 orderYear:new Date().getFullYear().toString(),
             },
             saleList:[],
             refundList:[],
             saleOrderMoneyList:[0,0,0,0,0,0,0,0,0,0,0,0],
             saleOrderNumberList:[0,0,0,0,0,0,0,0,0,0,0,0],
             refundOrderMoneyList:[0,0,0,0,0,0,0,0,0,0,0,0],
             refundOrderNumberList:[0,0,0,0,0,0,0,0,0,0,0,0],
         }
     },
     mounted() {
       this.initSaleOrderStatisticsList();
       this.initRefundOrderStatisticsList();
     },
     methods:{
         async initSaleOrderStatisticsList() {
             let data = await getAllSaleOrderStatisticsList(this.object);
             this.saleList = data.data;
             this.saleList.forEach(item => {
                this.saleOrderMoneyList[item.orderMonth - 1] = item.totalAmount;
                this.saleOrderNumberList[item.orderMonth - 1] = item.orderCount;
            });
         },
         async initRefundOrderStatisticsList() {
             let data = await getAllRefundOrderStatisticsList(this.object);
             this.refundList = data.data;
             this.refundList.forEach(item => {
                this.refundOrderMoneyList[item.orderMonth - 1] = item.totalAmount;
                this.refundOrderNumberList[item.orderMonth - 1] = item.orderCount;
            });
            this.initOrderMoneyData();
            this.initOrderNumberData();
         },
        //销售、退款金额对比
        initOrderMoneyData() {
            // 基于准备好的dom，初始化echarts实例
            var myChart1 = echarts1.init(document.getElementById('moneyChartLine'));
            // 绘制图表
            myChart1.setOption({
            grid: {
                x: 50,//直角坐标系内绘图网格与左侧距离
                y: 60,//直角坐标系内绘图网格与顶部距离
                x2: 50,//直角坐标系内绘图网格与右侧距离
                y2: 60 //直角坐标系内绘图网格与底部距离
                },
                title: {
                    text: '月销售情况-金额',//主标题
                    x: 'center',//x轴方向对齐方式
                    top: "10",//图例组件离容器下侧的距离 
                },
                tooltip: {},
                legend: {
                    orient: 'horizontal',//图例列表的布局朝向
                    bottom: "0",//图例组件离容器下侧的距离 
                    itemGap:15,//图例每项之间的间隔        
                    width: '400',// 这个定义图例的总宽度 
                    data: ['销售总金额',"退款总金额"]
                },
                xAxis: {
                    name:"月份",
                    data:[1,2,3,4,5,6,7,8,9,10,11,12]
                    },
                yAxis:{
                name:"金额",
                },
                series: [
                {
                    name: '销售总金额',
                    type: 'bar',
                    data: this.saleOrderMoneyList, 
                    barGap: '0%', // 设置柱形间的间距为-100%，使其完全重合
                    barWidth: 30, // 设置柱形宽度
                    yAxisIndex:0,
                    itemStyle: {
                    normal: {
                        label: {
                        show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                            color: 'gray',
                            fontSize: 12,
                        }
                        }
                    }
                },
                },
                {
                name: '退款总金额',
                type: 'bar',
                data: this.refundOrderMoneyList, 
                barGap: '0%', // 设置柱形间的间距为-100%，使其完全重合
                barWidth: 30, // 设置柱形宽度
                yAxisIndex:0,
                itemStyle: {
                    normal: {
                    label: {
                        show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                        color: 'gray',
                        fontSize: 12,
                        }
                    }
                    }
                },
            },
            ],
            });
        },
          //销售、退款数量对比
          initOrderNumberData() {
            // 基于准备好的dom，初始化echarts实例
            var myChart2 = echarts2.init(document.getElementById('numberChartLine'));
            // 绘制图表
            myChart2.setOption({
            grid: {
                x: 50,//直角坐标系内绘图网格与左侧距离
                y: 50,//直角坐标系内绘图网格与顶部距离
                x2: 50,//直角坐标系内绘图网格与右侧距离
                y2: 60 //直角坐标系内绘图网格与底部距离
                },
                title: {
                    text: '月销售情况-数量',//主标题
                    x: 'center',//x轴方向对齐方式
                    top: "10",//图例组件离容器下侧的距离 
                },
                tooltip: {},
                legend: {
                    orient: 'horizontal',//图例列表的布局朝向
                    bottom: "0",//图例组件离容器下侧的距离 
                    itemGap:15,//图例每项之间的间隔        
                    width: '400',// 这个定义图例的总宽度 
                    data: ['销售总数量',"退款总数量"]
                },
                xAxis: {
                    name:"月份",
                    data:[1,2,3,4,5,6,7,8,9,10,11,12]
                    },
                yAxis:{
                name:"金额",
                },
                series: [
                {
                    name: '销售总数量',
                    type: 'bar',
                    data: this.saleOrderNumberList, 
                    barGap: '0%', // 设置柱形间的间距为-100%，使其完全重合
                    barWidth: 30, // 设置柱形宽度
                    yAxisIndex:0,
                    itemStyle: {
                    normal: {
                        label: {
                        show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                            color: 'gray',
                            fontSize: 12,
                        }
                        }
                    }
                },
                },
                {
                name: '退款总数量',
                type: 'bar',
                data: this.refundOrderNumberList, 
                barGap: '0%', // 设置柱形间的间距为-100%，使其完全重合
                barWidth: 30, // 设置柱形宽度
                yAxisIndex:0,
                itemStyle: {
                    normal: {
                    label: {
                        show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                        color: 'gray',
                        fontSize: 12,
                        }
                    }
                    }
                },
            },
            ],
            });
        },
     }
 }
 </script>
 
 <style>
 
 </style>