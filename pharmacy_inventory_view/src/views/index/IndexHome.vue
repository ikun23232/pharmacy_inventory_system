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
            <div v-for="o in 4" :key="o">
                <el-tag size="small" style="margin-right: 10px;margin-bottom: 5px;">公告</el-tag>{{'列表内容 ' + o }}
            </div>
        </div>
       
    </el-card>
    <el-card style="width: 28%;height:210px;float:left;margin: 10px">
        <div slot="header" class="clearfix">
            <span>待办事项</span>
            <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-refresh"></i></el-button>
        </div>
        <div class="content">
            <div v-for="o in 4" :key="o" class="text item">
                <el-tag size="small" type="danger" style="margin-right: 10px;margin-bottom: 5px;">审核</el-tag>{{'列表内容 ' + o }}
            </div>
        </div>
    </el-card>


    </div>
</template>

<script>
// import {initSysNotice} from "@/api/notice.js";
export default {
    name:"index",
    data(){
        return{
         noticeList:"",
        }
    },
    mounted() {
        // this.initSysNotice();
     },
    methods:{
        // async initSysNotice() {
        //     let data = await initSysNotice();
        //     this.noticeList = data.data;
        // },
    }

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
</style>