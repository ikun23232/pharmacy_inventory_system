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
    <el-card shadow="always" :body-style="{ padding: '0px' }" style="width: 98%;height: 400px;margin:0 auto">
     <div id="moneyChartLine" style="width:100%;height: 360px"></div>
    </el-card>

    </div>
</template>

<script>
import {getCheckHintList} from '@/api/procurementOrder';
import VueSeamlessScroll from 'vue-seamless-scroll';
// 引入饼状图组件
require('echarts/lib/chart/pie')
// 引入提示框和title组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
export default {
    name:"index",
    data(){
        return{
            object:{
                orderYear:new Date().getFullYear().toString(),
                orderMonth:(new Date().getMonth()+1).toString(),
            },
            orderMedicineList:[],
            saleOrderMoneyList:[],
            saleOrderNumberList:[],
            CheckHintList:[],
        }
    },
    mounted() {
      this.getCheckHintList();
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
    },
  components:{
    VueSeamlessScroll,
  },
  directives: {
    'seamless-scroll': VueSeamlessScroll
  },




}
</script>

<style>
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