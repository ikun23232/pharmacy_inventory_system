<template>
    <div id="refundOrder">
    <h1>销售退货退款</h1>
    <div>
      <div style="padding-top: 15px;padding-left: 20px;">
          <el-form :inline="true" >
              <el-form-item label="单据编号">
                  <el-input placeholder="单据编号" v-model="object.orderNo"></el-input>
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
                  <el-button type="primary" icon="el-icon-search" @click="initRefundOrderByPage(1)">查询</el-button>
                  <el-button icon="el-icon-refresh-right" @click="resetForm">重置</el-button>
                   <el-button type="text" icon="el-icon-plus" @click="handleAdd">添加</el-button>
              <el-button type="text" icon="el-icon-upload2" style="margin-left:18px" @click="handleExcel">导出</el-button>
              <!-- <el-button type="text" icon="el-icon-download" style="margin-left:18px">导入</el-button> -->
              </el-form-item>
          </el-form>
          </div>
  
    </div>
    <el-table :stripe=true :data="list" border style="width: 100%;text-align: center;">
      <el-table-column fixed prop="index" label="#" width="60">
          <template #default="scope">
          {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
          </template>
        </el-table-column>
      <el-table-column prop="orderNo" label="单据编号" width="150">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" @click.native="lookSaleOrderDetail(scope.row.orderNo)">{{ scope.row.orderNo }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="orderDate" label="单据日期" width="120">
      </el-table-column>
      <el-table-column prop="totalNumber" label="总数量" width="120">
      </el-table-column>
      <el-table-column prop="totalPrice" label="退款总金额" width="120">
      </el-table-column>
      <el-table-column prop="refundTypeName" label="退款原因" width="180">
      </el-table-column>
      <el-table-column prop="bankAccountName" label="银行账户" width="120">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120">
      </el-table-column>
      <el-table-column prop="editStatus" label="订单状态" width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.editStatus==0">编制中</span>
          <span v-if="scope.row.editStatus==1">编制完</span>
          <span v-if="scope.row.editStatus==2">已完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="isCheck" label="审批状态" width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.isCheck==0">未审核</span>
          <span v-if="scope.row.isCheck==1">已通过</span>
          <span v-if="scope.row.isCheck==2">不通过</span>
        </template>
      </el-table-column>
      <el-table-column prop="checkByName" label="审批人" width="120">
      </el-table-column>
      <el-table-column prop="cancelStatus" label="是否作废" width="120">
        <template slot-scope="scope">
          {{ scope.row.cancelStatus==0?"否":"是"}}
        </template>
      </el-table-column>
      <el-table-column prop="createByName" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="updateByName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updateDate" label="修改时间" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row.orderNo)" type="text" :disabled="scope.row.editStatus==2?true:false">
            编辑
          </el-button>
          <el-dropdown>
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleCheck(scope.row.orderNo)" :disabled="scope.row.isCheck!=0||scope.row.editStatus==0||scope.row.cancelStatus==1?true:false">审核</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.row.orderNo)">删除</el-dropdown-item>
              <el-dropdown-item @click.native="handleCancel(scope.row.orderNo)" :disabled="scope.row.cancelStatus==1?true:false">作废</el-dropdown-item>
              <el-dropdown-item @click.native="printRefundOrder(scope.row.orderNo)">打印</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <p>
        <el-pagination background layout="prev, pager, next" :total="pageInfo.total" page-size=5 @current-change="handleCurrentChange" style="float: right;"></el-pagination>
        <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
      </p>
  
      <!-- 添加退款订单 -->
      <el-dialog
        title="退款订单-添加"
        :visible.sync="addDialogFormVisible"
        width="1000px"
        v-if="addDialogFormVisible"
      >
        <AddRefundOrder @handleDialogFormVisible="changeDialogFormVisible"></AddRefundOrder>
      </el-dialog>
  
       <!-- 修改退款订单 -->
       <el-dialog
        title="退款订单-修改"
        :visible.sync="updateDialogFormVisible"
        width="1000px"
        v-if="updateDialogFormVisible"
      >
      <UpdateRefundOrder @handleDialogFormVisible="changeDialogFormVisible" :orderNo="orderNo"></UpdateRefundOrder>
    </el-dialog>
       <!-- 查看退款订单详情 -->
       <el-dialog
        title="退款订单-详情"
        :visible.sync="detailDialogFormVisible"
        width="1000px"
        v-if="detailDialogFormVisible"
      >
      <RefundOrderDetail @handleDialogFormVisible="changeDialogFormVisible" :orderNo="orderNo"></RefundOrderDetail>
      </el-dialog>

      <!-- 审核退款订单-->
      <el-dialog
        title="退款订单-审核"
        :visible.sync="checkDialogFormVisible"
        width="1000px"
        v-if="checkDialogFormVisible"
      >
      <CheckRefundOrder @handleDialogFormVisible="changeDialogFormVisible" :orderNo="orderNo"></CheckRefundOrder>
      </el-dialog>
    </div>
  </div>
  </template>
  
  <script>
  import {initRefundOrder,refundOrderExcel} from "../../api/refundOrder.js";
  import AddRefundOrder from "../refund/AddRefundOrder.vue";
  import UpdateRefundOrder from "../refund/UpdateRefundOrder.vue";
  import RefundOrderDetail from "../refund/RefundOrderDetail.vue";
  import CheckRefundOrder from "../refund/CheckRefundOrder.vue";
  import {deleteSaleOrder,cancelSaleOrder} from "../../api/saleOrder.js";
  import {getAllUser} from "../../api/sysUser.js";
  import { Message } from "element-ui";
  
  export default {
    name:"refundOrder",
    components: {
      AddRefundOrder,
      UpdateRefundOrder,
      RefundOrderDetail,
      CheckRefundOrder
    },
    data(){
      return{
        orderNo:"",
        userList:[],
        object:{
              orderNo:"",
              orderDateBegin:"",
              orderDateEnd:"",
              createBy:"",
              currentPage:1, 
          },
          // 时间
          time:{},
          pageInfo:[],
          list:[],
          addDialogFormVisible:false,
          updateDialogFormVisible:false,
          detailDialogFormVisible:false,
          checkDialogFormVisible:false,  
      }
    },
    mounted() {
      this.initRefundOrderByPage(1);
      this.initAllUser();
    },
    methods: {
      async initAllUser() {
        let data = await getAllUser();
        console.log("12345",data.data)
        this.userList=data.data;
        console.log("999",this.userList)
      },
      async initRefundOrderByPage(currentPage) {
        if (Array.isArray(this.time) && this.time.length > 0) {
        this.object.orderDateBegin = this.time[0];
        this.object.orderDateEnd = this.time[1];
      } else {
        this.object.orderDateBegin = null;
        this.object.orderDateEnd = null;
      }
          this.object.currentPage=currentPage;
          let data = await initRefundOrder(this.object);
          this.pageInfo=data.data;
          this.list = data.data.list;
      },
      handleCurrentChange(val) {
        this.object.currentPage = val;
        this.initSaleOrderByPage(this.object.currentPage);
      },
      printRefundOrder(orderNo){
        const newPage= this.$router.resolve({ 
          path: "/printRefundOrder",
          query:{ //要传的参数 可传多个
          orderNo:orderNo
        }})
        window.open(newPage.href,'_blank')
      },
      handleAdd(){
        this.addDialogFormVisible=true;
      },
      changeDialogFormVisible(val){
          this.addDialogFormVisible=val;
          this.updateDialogFormVisible=val;
          this.detailDialogFormVisible=val;
          this.checkDialogFormVisible=val;
          this.initRefundOrderByPage(this.object.currentPage);
      },
      lookSaleOrderDetail(orderNo){
        this.detailDialogFormVisible=true;
        this.orderNo=orderNo;
      },
      handleUpdate(orderNo){
        this.updateDialogFormVisible=true;
        this.orderNo=orderNo;
      },
      handleCheck(orderNo){
        this.checkDialogFormVisible=true;
        this.orderNo=orderNo;
      },
      //退货订单作废
     async handleCancel(orderNo){
      this.$messagebox
          .confirm("此操作将作废该销售订单, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {
            try {
              let data = await cancelSaleOrder(orderNo);
              if (data.code === "200") {
                Message({type: 'success',message: '作废成功！'})
                this.initRefundOrderByPage(this.object.currentPage);
              } else {
                Message({type: 'error',message: '作废失败！'})
              }
            } catch (error) {
              Message({type: 'error',message: '作废失败！'})
            }
          })
          .catch(() => {
            Message({
              type: 'success',
              message: '取消删除成功！'
            })
          });
      },
      async handleDelete(orderNo){
        this.$messagebox
          .confirm("此操作将删除该文件, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
          .then(async () => {
            try {
              let data = await deleteSaleOrder(orderNo);
              if (data.code === "200") {
                Message({type: 'success',message: '删除成功！'})
                this.initRefundOrderByPage(this.object.currentPage);
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
      //销售退货订单导出
      async handleExcel(){
        await refundOrderExcel();
      },
      //重置
      resetForm(){
      this.object.orderNo="";
      this.time="";
      this.object.createBy="";
    },
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