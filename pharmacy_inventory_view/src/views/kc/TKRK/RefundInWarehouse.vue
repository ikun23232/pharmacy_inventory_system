<template>
    <div id="refundInWarehouse">
    <h1>销售退货入库</h1>
    <div>
      <div style="padding-top: 15px;padding-left: 20px;">
          <el-form :inline="true" >
              <el-form-item label="单据编号">
                  <el-input placeholder="单据编号" v-model="object.code"></el-input>
              </el-form-item>
              <el-form-item label="单据日期">
                <el-col :span="11">
                  <el-date-picker
                    v-model="time"
                    type="daterange"
                    align="right"
                    unlink-panels
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    :picker-options="pickerOptions"
                />                </el-col>
              </el-form-item>
              <el-form-item label="创建人">
                <el-select v-model="object.createBy" >
                  <el-option
                    v-for="dict in userList"
                    :key="dict.id"
                    :label="dict.username"
                    :value="dict.userid"/>
                 </el-select>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" icon="el-icon-search" @click="initRefundInWarehouseListByPage(1)">查询</el-button>
                  <el-button icon="el-icon-refresh-right" @click="resetForm">重置</el-button>
              <el-button type="text" icon="el-icon-download" style="margin-left:18px" @click="handleExcel">导出</el-button>
              <!-- <el-button type="text" icon="el-icon-download" style="margin-left:18px">导入</el-button> -->
              </el-form-item>
          </el-form>
          </div>
  
    </div>
    <el-table stripe="true" :data="list" border style="width: 100%;text-align: center;">
      <el-table-column fixed prop="index" label="#" width="60">
          <template #default="scope">
          {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
          </template>
        </el-table-column>
      <el-table-column prop="code" label="单据编号" width="150">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" @click.native="lookSaleOrderDetail(scope.row.xsOrder.orderNo)">{{ scope.row.code }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="xsOrder.orderDate" label="单据日期" width="120">
      </el-table-column>
      <el-table-column prop="xsOrder.orderNo" label="源单号" width="120">
      </el-table-column>
      <el-table-column prop="xsOrder.refundTypeName" label="退款原因" width="180">
      </el-table-column>
      <el-table-column prop="xsOrder.totalNumber" label="出库总数量" width="120">
      </el-table-column>
      <el-table-column prop="xsOrder.totalPrice" label="出库总金额" width="120">
      </el-table-column>
      <el-table-column prop="xsOrder.remark" label="备注" width="120">
      </el-table-column>
      <el-table-column prop="xsOrder.checkByName" label="审批人" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button @click="printOrder(scope.row.xsOrder.orderNo)" type="text">
            打印
          </el-button>
          <el-button @click="handleDelete(scope.row.code)" type="text">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <p>
        <el-pagination background layout="prev, pager, next" :total="pageInfo.total" page-size=5 @current-change="handleCurrentChange" style="float: right;"></el-pagination>
        <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
      </p>
    </div>
     <!-- 查看销售订单详情 -->
     <el-dialog
        title="退款订单-详情"
        :visible.sync="detailDialogFormVisible"
        width="1000px"
        v-if="detailDialogFormVisible"
      >
      <RefundOrderDetail @handleDialogFormVisible="changeDialogFormVisible" :orderNo="orderNo"></RefundOrderDetail>
      </el-dialog>
  </div>
  </template>
  
  <script>
  import RefundOrderDetail from "@/views/refund/RefundOrderDetail.vue";
  import {deleteSaleOutWarehouseOrder} from  "../../../api/saleOutWarehouse.js";
  import {initRefundInWarehouseListByPage,refundInWarehouseExcel} from  "../../../api/refundInWarehouse.js";
  import {getAllUser} from "@/api/sysUser.js";
  import { Message } from "element-ui";
  
  export default {
    name:"refundInWarehouse",
    components: {
      RefundOrderDetail
    },
    data(){
      return{
        orderNo:"",
        userList:[],
        object:{
              code:"",
              orderDateBegin:"",
              orderDateEnd:"",
              createBy:"",
              currentPage:1, 
          },
          // 时间
          time:{},
          pageInfo:"",
          list:"",
          detailDialogFormVisible:false,
      }
    },
    mounted() {
      this.initRefundInWarehouseListByPage(1);
      this.initAllUser();
    },
    methods: {
      async initAllUser() {
        let data = await getAllUser();
        console.log("12345",data.data)
        this.userList=data.data;
    },
      async initRefundInWarehouseListByPage(currentPage) {
        if (Array.isArray(this.time) && this.time.length > 0) {
          this.object.orderDateBegin = this.time[0];
          this.object.orderDateEnd = this.time[1];
        } else {
          this.object.orderDateBegin = null;
          this.object.orderDateEnd = null;
        }
          this.object.currentPage=currentPage;
          let data = await initRefundInWarehouseListByPage(this.object);
          this.pageInfo=data.data;
          this.list = data.data.list;
          console.log("123",this.list)
      },
      handleCurrentChange(val) {
        this.object.currentPage = val;
        this.initRefundInWarehouseListByPage(this.object.currentPage);
      },
      printOrder(orderNo){
        const newPage= this.$router.resolve({ 
          path: "/printRefundOrder",
          query:{ //要传的参数 可传多个
          orderNo:orderNo
        }})
        window.open(newPage.href,'_blank')
      },
      lookSaleOrderDetail(orderNo){
        this.detailDialogFormVisible=true;
        this.orderNo=orderNo;
      },
      changeDialogFormVisible(val){
        this.detailDialogFormVisible=val;
        this.initSaleOrderByPage(this.object.currentPage);
      },
      async handleDelete(code){
        this.$messagebox
          .confirm("此操作将删除该文件, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {
            try {
              let data = await deleteSaleOutWarehouseOrder(code);
              console.log("111",data)
              if (data.code == "200") {
                Message({type: 'success',message: '删除成功！'})
                this.initRefundInWarehouseListByPage(this.object.currentPage);
              } else {
                Message({type: 'error',message: '删除失败！'})
              }
            } catch (error) {
              Message({type: 'error',message: '删除失败！'})
            }
          })
          .catch(() => {
            Message({
              type: 'success',
              message: '取消删除成功！'
            })
          });
      },
      resetForm(){
        this.object.code="";
        this.time="";
        this.object.createBy="";
      },
      async handleExcel(){
        await refundInWarehouseExcel(this.object);
      }
    }
  }
  </script>
  
  <style>
    .el-dropdown-link {
      cursor: pointer;
      color: #409EFF;
      margin-left: 10px;
    }
    .el-icon-arrow-down {
      font-size: 12px;
    }
  </style>