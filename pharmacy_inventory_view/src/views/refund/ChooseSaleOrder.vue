<template>
    <div id="ChooseSaleOrder">
     <el-row>
      <el-col>
        <div>
          <div style="padding-left: 20px;">
            <el-form :inline="true" ref="saleOrderForm">
              <el-form-item label="单据编号">
                  <el-input placeholder="单据编号" v-model="object.orderNo" size="small"></el-input>
              </el-form-item>
              <el-form-item label="单据日期">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="请选择开始" v-model="object.orderDateBegin" style="width: 100%;" size="small"></el-date-picker>
                </el-col>
                <el-col class="line" :span="1">~</el-col>
                  <el-col :span="11">
                  <el-date-picker type="date" placeholder="请选择结束" v-model="object.orderDateEnd" style="width: 100%;" size="small"></el-date-picker>
                </el-col>
              </el-form-item>
              <el-form-item label="创建人">
                <el-select v-model="object.createBy" size="small">
                  <el-option
                    v-for="dict in userList"
                    :key="dict.id"
                    :label="dict.username"
                    :value="dict.userid"/>
                 </el-select>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" icon="el-icon-search" @click="initSaleOrderByPage(1)" size="small">查询</el-button>
                  <el-button icon="el-icon-refresh-right" @click="resetForm('saleOrderForm')" size="small">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
          <el-table stripe="true" :data="list" border style="width:860px;text-align: center;" @selection-change="handleSelectionChange" :row-class-name="rowClassName">
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column fixed prop="index" label="#" width="60">
                <template #default="scope">
                {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
                </template>
              </el-table-column>
            <el-table-column prop="orderNo" label="单据编号" width="150">
            </el-table-column>
            <el-table-column prop="orderDate" label="单据日期" width="120">
            </el-table-column>
            <el-table-column prop="totalNumber" label="总数量" width="120">
            </el-table-column>
            <el-table-column prop="totalPrice" label="总金额" width="120">
            </el-table-column>
            <el-table-column prop="bankAccountName" label="银行账户" width="120">
            </el-table-column>
            <el-table-column prop="remark" label="备注" width="120">
            </el-table-column>
            <el-table-column prop="editStatus" label="订单状态" width="120">
              <template slot-scope="scope">
                {{ scope.row.editStatus==0?"编制中":"已完成"}}
              </template>
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
          </el-table>
          <p>
            <el-pagination background layout="prev, pager, next" :total="pageInfo.total" page-size=5 @current-change="handleCurrentChange" style="float: right;"></el-pagination>
            <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
          </p> 
      </el-col>
     </el-row>
    <el-divider></el-divider>
    <el-row type="flex" justify="end">
      <el-col :span="2" >
        <el-button type="primary" size="mini" @click="addSaleOrder">确定</el-button>
      </el-col>
      <el-col :span="2">
        <el-button size="mini" @click="closeForm">关闭</el-button>
      </el-col>
    </el-row>
  </div>
  </template>
  
  <script>
  import {initSaleOrder,updateSaleOrderByOrderNo} from "../../api/saleOrder.js";
  import {getAllUser} from "../../api/sysUser.js";
  import { Message } from "element-ui";
  
  export default {
    name:"ChooseSaleOrder",
    data(){
      return{
        orderNo:"",
        userList:[],
        object:{
              orderNo:"",
              orderDateBegin:"",
              orderDateEnd:"",
              createByName:"",
              currentPage:1, 
              editStatus:1,
              isCheck:1
          },
          pageInfo:"",
          list:"",
          saleOrderList:[],

      }
    },
    mounted() {
      this.initSaleOrderByPage(1);
      this.initAllUser();
    },
    methods: {
      async initAllUser() {
        let data = await getAllUser();
        console.log("12345",data.data)
        this.userList=data.data;
      },
      async initSaleOrderByPage(currentPage) {
          this.object.currentPage=currentPage;
          let data = await initSaleOrder(this.object);
          this.pageInfo=data.data;
          this.list = data.data.list;
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //关闭添加销售订单
      closeForm(){
         this.$emit("handleDialogFormVisible",false);
       },
      handleCurrentChange(val) {
        this.object.currentPage = val;
        this.initSaleOrderByPage(this.object.currentPage);
      },
      //拿到选中的表单
      handleSelectionChange(val) {
         this.saleOrderList= val;
         console.log("saleOrderList",this.saleOrderList)
       },
       rowClassName({ row, rowIndex }) {
         row.xh = rowIndex + 1;
       },
       //确认要添加的销售订单
       async addSaleOrder(){
         //给选中的销售订单添加退货标识
         for(var i=0;i<this.saleOrderList.length;i++){
          const orderNo=this.saleOrderList[i].orderNo;
          const data=await updateSaleOrderByOrderNo(orderNo)
          console.log("888",data.data)
        }
        this.$emit("chooseSaleOrder",this.saleOrderList);
        this.closeForm()
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