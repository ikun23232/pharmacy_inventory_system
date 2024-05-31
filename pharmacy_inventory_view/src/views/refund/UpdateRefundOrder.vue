<template>
  <div>
   <div slot="footer" class="dialog-footer">
     <el-form
       :model="saleOrder"
       :rules="rules"
       ref="saleOrderForm"
       label-width="100px"
       class="demo-ruleForm"
     >
     <el-row :gutter="20">
       <el-col :span="8">
         <div class="grid-content bg-purple">
             <el-form-item label="单据编号" prop="orderNo">
               <el-input type="text" disabled v-model="saleOrder.orderNo"></el-input>
             </el-form-item></div>
       </el-col>
         <el-col :span="8">
           <div class="grid-content bg-purple">
             <el-form-item label="单据日期" prop="orderDate">
               <el-date-picker
                 v-model="saleOrder.orderDate"
                 disabled
                 type="date"
                 placeholder="选择日期"
                 format="yyyy 年 MM 月 dd 日">
               </el-date-picker>
             </el-form-item></div>
         </el-col>
         <el-col :span="8"><div class="grid-content bg-purple">
             <el-form-item label="制单人" prop="createByName">
               <el-input type="text" disabled v-model="saleOrder.createByName"></el-input>
             </el-form-item></div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple">
      <el-form-item label="退款原因" prop="refundTypeId">
          <el-select
              v-model="saleOrder.refundTypeId"
              placeholder="请选择"
              clearable
              filterable
              >
              <el-option v-for="item in refundTypeList"
                  :key="item.index"
                  :label="item.type"
                  :value="item.id">
              </el-option>
              </el-select>
      </el-form-item></div></el-col>   

         <el-col :span="8"
           ><div class="grid-content bg-purple">
             <el-form-item label="银行账户" prop="bankAccountId">
               <el-select
                 v-model="saleOrder.bankAccountId"
                 placeholder="请选择"
                 clearable
                 filterable
               >
               <el-option v-for="item in bankAccountList"
                   :key="item.index"
                   :label="item.bandCount"
                   :value="item.id">
               </el-option>
               </el-select>
             </el-form-item>
           </div></el-col>
         </el-row>
           <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="销售订单" name="first">
              <div style="float: left;margin-bottom: 10px;">
                  <el-button  type="primary" icon="el-icon-plus" size="mini" @click="handleAddSaleOrder">销售订单</el-button>
                  <!-- <el-button  icon="el-icon-minus" size="mini" @click="handleDeleteDetails">移除</el-button> -->
                  <el-button  icon="el-icon-delete" size="mini" @click="handleDeleteAllDetails">清空</el-button>
              </div>
              <el-table
              :data="saleOrderList"
              @selection-change="handleSelectionChange"
              ref="tb"
              border
              style="width:960px"
              >
              <el-table-column type="selection" width="50" align="center" />
              <el-table-column label="序号" fixed align="center" type="index" width="80">
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
                <el-table-column prop="createByName" label="创建人" width="120">
                </el-table-column>
                <el-table-column prop="updateByName" label="修改人" width="120">
                </el-table-column>
                <el-table-column prop="updateDate" label="修改时间" width="120">
                </el-table-column>
              </el-table>
              <div style="margin: 10px 0;float: left;">
            <el-button type="primary" size="mini" @click="handleAddSaleOrderDetail">添加<i class="el-icon-arrow-right"></i></el-button>
            </div>
            <el-table
              :data="medicineDetailList"
              @selection-change="handleSelectionDetail"
              ref="tb"
              border
              style="width:960px"
            >
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column label="序号" fixed align="center" type="index" width="80"></el-table-column>
            <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].name" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="批次号" align="center"  width="120" prop="batchCode">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].batchCode" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="规格型号" align="center" prop="specification" width="120">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].specification" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="药品类型" align="center" prop="categoryId" width="120">
            <template slot-scope="scope">
              <el-input v-model="medicineDetailList[scope.$index].categoryName" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="计量单位" align="center" prop="unitId" width="120">
            <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].unitName" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="数量" align="center" prop="quantity" width="160">
              <template slot-scope="scope">
                  <el-input-number size="small"  v-model="medicineDetailList[scope.$index].quantity" disabled></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="单价" align="center" prop="salePrice" width="120">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].salePrice" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="总价" align="center" prop="totalPrice" width="120">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].totalPrice" readonly></el-input>
              </template>
            </el-table-column>
            <el-table-column label="当前库存" align="center" prop="stock" width="120">
              <template slot-scope="scope">
              <el-input  v-model="medicineDetailList[scope.$index].stock" readonly></el-input>
              </template>
            </el-table-column>
           </el-table>
            </el-tab-pane>
            <el-tab-pane label="明细" name="second">
              <div style="float: left;margin-bottom: 10px;">
                  <el-button  icon="el-icon-minus" size="mini" @click="handleDeleteDetails2">移除</el-button>
                  <el-button  icon="el-icon-delete" size="mini" @click="handleDeleteAllDetails2">清空</el-button>
              </div>
              <el-table
              v-loading="loading"
              :data="saleOrder.medicineDetailList"
              :row-class-name="rowClassName"
              @selection-change="handleSelectionDetail2"
              ref="tb"
              border
              style="width:960px"
            >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>
          <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
            <template slot-scope="scope">
              <el-input v-model="saleOrder.medicineDetailList[scope.$index].name" readonly></el-input>
              </template>
          </el-table-column>
          <el-table-column label="批次号" align="center"  width="120" prop="batchCode">
            <template slot-scope="scope">
              <el-input  v-model="saleOrder.medicineDetailList[scope.$index].batchCode" readonly></el-input>
              </template>
          </el-table-column>
          <el-table-column label="规格型号" align="center" prop="specification" width="120">
            <template slot-scope="scope">
             <el-input  v-model="saleOrder.medicineDetailList[scope.row.xh-1].specification" readonly></el-input>
            </template>
          </el-table-column>
          <el-table-column label="药品类型" align="center" prop="categoryId" width="120">
           <template slot-scope="scope">
             <el-input v-model="saleOrder.medicineDetailList[scope.row.xh-1].categoryName" readonly></el-input>
            </template>
          </el-table-column>
          <el-table-column label="计量单位" align="center" prop="unitId" width="120">
           <template slot-scope="scope">
             <el-input  v-model="saleOrder.medicineDetailList[scope.row.xh-1].unitName" readonly></el-input>
            </template>
          </el-table-column>
          <el-table-column label="数量" align="center" prop="quantity" width="160">
            <template slot-scope="scope">
                <el-input-number size="small" v-model="saleOrder.medicineDetailList[scope.row.xh-1].quantity" disabled></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="单价" align="center" prop="salePrice" width="120">
            <template slot-scope="scope">
             <el-input  v-model="saleOrder.medicineDetailList[scope.row.xh-1].salePrice" readonly></el-input>
            </template>
          </el-table-column>
          <el-table-column label="总价" align="center" prop="totalPrice" width="120">
            <template slot-scope="scope">
             <el-input  v-model="saleOrder.medicineDetailList[scope.row.xh-1].totalPrice" readonly></el-input>
            </template>
          </el-table-column>
          <el-table-column label="当前库存" align="center" prop="stock" width="120">
            <template slot-scope="scope">
             <el-input  v-model="saleOrder.medicineDetailList[scope.row.xh-1].stock" readonly></el-input>
            </template>
          </el-table-column>
          </el-table>
          </el-tab-pane>
          </el-tabs>
         <div style="text-align: left;margin-top: 10px;margin-left: 10px;">
          <span>合计: {{saleOrder.totalPrice}}元</span> 
         </div>  
        <el-divider></el-divider>
        <el-row>
          <el-col :span="24"><div>
            <el-form-item label-width="80px" size="small">
              <label slot="label" v-html="'备&#8195;&#8195;注:'"></label>
              <el-input
              type="textarea"
              autosize
              placeholder="请输入内容"
              v-model="saleOrder.remark">
            </el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="核批意见:" label-width="80px" size="small">
              <el-input
              disabled
              type="textarea"
              autosize
              placeholder="请输入内容"
              v-model="saleOrder.opinion">
            </el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="核批结果:" label-width="80px" size="small" prop="isCheck">
              <el-select disabled v-model="saleOrder.isCheck"  placeholder="请选择" style="width:100%">
                <el-option label="未审批" :value="0"></el-option>
                <el-option label="同意" :value=1></el-option>
                <el-option label="拒绝" :value=2></el-option>
              </el-select>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-divider></el-divider>
     <el-row type="flex" justify="end" style="margin-top: 20px;">
       <el-col :span="2" >
         <el-button type="primary" size="mini" @click="saveForm('saleOrderForm')">保存</el-button>
       </el-col>
       <el-col :span="2">
         <el-button type="primary" size="mini" @click="submitForm('saleOrderForm')" :disabled="activeName=='first'?true:false">提交</el-button>
       </el-col>
       <el-col :span="2">
         <el-button size="mini" @click="cancelForm">取消</el-button>
       </el-col>
     </el-row>
   </el-form>
   </div>
      <!-- 添加销售订单 -->
      <el-dialog
        title="销售订单"
        :visible.sync="chooseDialogFormVisible"
        width="900px"
        v-if="chooseDialogFormVisible"
        append-to-body
        >
        <ChooseSaleOrder @handleDialogFormVisible="changeDialogFormVisible" @chooseSaleOrder="chooseSaleOrderList"></ChooseSaleOrder>
        </el-dialog>
  </div>
 </template>
 
 <script>
 import { Message } from "element-ui";
 import {getSaleOrderByOrderNo,recoverSaleOrderByOrderNo} from "../../api/saleOrder.js";
 import {getAllBankCountList} from "../../api/BankAccount.js";
 import {getAllBaseMedicine} from "../../api/baseMedicine.js";
 import {getAllRefundTypeList, updateRefundOrder,saveUpdateRefundOrder} from "../../api/refundOrder.js";
 import ChooseSaleOrder from "../refund/ChooseSaleOrder.vue";
 
 export default {
   name: "updateRefundOrder",
   props:{
      orderNo:"",
   },
   components: {
      ChooseSaleOrder,
    },
   data() {
     return {
       medicineDetailList:[],
       bankAccountList:[],
       baseMedicineList:[],
       refundTypeList:[], 
       activeName:"second",
       saleOrderList:[],
       saleOrder:{
          orderNo:"",
          orderDate:new Date(),
          createByName:"张三",
          bankAccountId:"",
          refundTypeId:"",
          remark:'',
          checkedDetail: [],
          checkedDetail2:[],
          checkedOrder:[],
          medicineDetailList:[],
          totalPrice:"",
          totalNumber:""
       },  
       chooseDialogFormVisible:false,
       temp:[],
       rules:{
         orderNo:[
             { required: true, message: "请输入订单编号", trigger: "blur" },
         ],
         orderDate:[
             { required: true, message: "请输入单据日期", trigger: "blur" },
         ],
         createName:[
             { required: true, message: "请选择创建人", trigger: "blur" },
         ],
         bankAccountId:[
             { required: true, message: "请选择银行账户", trigger: "blur" },
         ],
       }
     };
   },
   async mounted() {
     this.getAllBankCountList();
     this.getAllRefundTypeList();
     this.getAllBaseMedicine();
     this.getSaleOrderByOrderNo();
   },
   methods: {
    async getAllBankCountList() {
       let data = await getAllBankCountList();
       this.bankAccountList=data.data;
     },
     async getAllRefundTypeList() {
       let data = await getAllRefundTypeList();
       this.refundTypeList=data.data;
     },
    //查询本地仓库所有药品
    async getAllBaseMedicine() {
      let data = await getAllBaseMedicine();
      this.baseMedicineList=data.data;
    },
    //回显
    async getSaleOrderByOrderNo() {
      let data = await getSaleOrderByOrderNo(this.orderNo);
      this.saleOrder=data.data;
      this.saleOrder.medicineDetailList = this.saleOrder.baseMedicineList;
    },
    rowClassName({ row, rowIndex }) {
      row.xh = rowIndex + 1;
    },
    // tabs标签页切换事件
    handleClick(tab, event) {
      if (tab.index == 1) {
        this.activeName="first";
      } else if (tab.index == 2) {
        this.activeName="second";   
      }
    },
    //添加销售订单弹框
    handleAddSaleOrder(){
      this.chooseDialogFormVisible=true;
    },
    //显示要退款的销售订单
    async chooseSaleOrderList(val){
      for(var i=0; i<val.length; i++){
        this.saleOrderList.push(val[i]);
      }
      this.medicineDetailList=[];
      //查询订单详情
      for(var i=0;i<this.saleOrderList.length;i++){
        const orderNo=this.saleOrderList[i].orderNo;
        const data=await getSaleOrderByOrderNo(orderNo)
        const baseMedicineList=data.data.baseMedicineList;
        for(var j=0;j<baseMedicineList.length;j++){
          this.medicineDetailList.push(data.data.baseMedicineList[j]);
        }
      }
    },
        //关闭添加销售订单弹框
        changeDialogFormVisible(val){
          this.chooseDialogFormVisible=val;
        },
        //获取选中的订单
        handleSelectionChange(val) {
         this.saleOrder.checkedOrder = val;
        },
        //移除
        // async handleDeleteDetails() {
        //   if (this.saleOrder.checkedOrder.length == 0) {
        //     this.$alert("请先选择要删除的数据", "提示", {
        //       confirmButtonText: "确定",
        //     });
        //   } else {
        //     this.saleOrderList.splice(this.saleOrder.checkedOrder[0].index - 1, this.saleOrder.checkedOrder.length);
        //     const data=await recoverSaleOrderByOrderNo(this.saleOrder.checkedOrder[0].orderNo);
        //     this.medicineDetailList.splice(this.saleOrder.checkedOrder[0].index - 1, length);
            
        //   }
        // }, 
        //移除明细
        handleDeleteDetails2() {
          if (this.saleOrder.checkedDetail2.length == 0) {
            this.$alert("请先选择要删除的数据", "提示", {
              confirmButtonText: "确定",
            });
          } else {
            this.saleOrder.medicineDetailList.splice(this.saleOrder.checkedDetail2[0].index - 1, 1);
          }
          this.saleOrder.totalPrice=this.sumPrice;
        },
        //清空
        async handleDeleteAllDetails() {
         for(var i=0;i<this.saleOrderList.length;i++){
          const orderNo=this.saleOrderList[i].orderNo;
          const data=await recoverSaleOrderByOrderNo(orderNo)
        }
         this.saleOrderList = [];
         this.medicineDetailList=[];
        
       },
        //清空明细
        handleDeleteAllDetails2() {
         this.saleOrder.totalPrice=this.sumPrice;
         this.saleOrder.medicineDetailList=[];
       },
       //处理要添加的订单详情
       handleSelectionDetail(val){
        this.saleOrder.checkedDetail = val;
       },
        //处理明细
       handleSelectionDetail2(val){
        this.saleOrder.checkedDetail2 = val;
       },
      //添加要退款的订单详情
       handleAddSaleOrderDetail(){
        if (this.saleOrder.checkedDetail.length== 0) {
          Message({
            message: "请添加退款详情!",
            type: "error",
            center: "true",
          });
          return;
        }

        for(var i=0;i<this.saleOrder.checkedDetail.length;i++){
          // this.medicineDetailList[this.saleOrder.checkedDetail[0].index - 1].splice(flag,true);
          this.saleOrder.medicineDetailList.push(this.saleOrder.checkedDetail[i]);
        }
        this.saleOrder.totalPrice=this.sumPrice;
        this.activeName="second";
       },
     
     //提交修改
      submitForm(formName){
       this.$refs[formName].validate((valid) => {
         if (valid) {
           if(this.saleOrder.medicineDetailList==0){
            Message({
              message: "请输入订单详情!",
              type: "error",
              center: "true",
            });
            return;
           } 
           this.saleOrder.totalPrice=this.sumPrice
           this.saleOrder.totalNumber=this.totalNumber
          //  this.saleOrder.checkedDetail=this.saleOrder.medicineDetailList
           updateRefundOrder(this.saleOrder).then((resp) => {
              console.log(resp);
              if (resp.code == "200") {
                Message({
                  message: "修改成功!",
                  type: "success",
                  center: "true",
                });
                this.$emit("handleDialogFormVisible",false);
              }         
            });     
         } else {
           console.log("error submit!!");
           return false;
         }
       });
     },
     //保存修改
     saveForm(formName){
       this.$refs[formName].validate((valid) => {
         if (valid) {
          this.saleOrder.totalPrice=this.sumPrice
           this.saleOrder.totalNumber=this.totalNumber
          //  this.saleOrder.checkedDetail=this.saleOrder.medicineDetailList
          saveUpdateRefundOrder(this.saleOrder).then((resp) => {
               console.log(resp);
               if (resp.code == "200") {
                 Message({
                   message: "保存成功!",
                   type: "success",
                   center: "true",
                 });
                 this.$emit("handleDialogFormVisible",false);
               }         
             });
         } else {
           console.log("error submit!!");
           return false;
         }
       });
     },
     cancelForm(){
       this.$emit("handleDialogFormVisible",false);
     },
 
   },
   computed: {
     sumPrice() {
       // 使用 reduce 方法计算总价
       if (this.saleOrder.medicineDetailList==undefined){
         return 0
       }
       return this.saleOrder.medicineDetailList.reduce((total, item) => total + item.totalPrice, 0);
     },
     totalNumber() {
       // 使用 reduce 方法计算总数量
       if (this.saleOrder.medicineDetailList==undefined){
         return 0
       }
       return this.saleOrder.medicineDetailList.reduce((total, item) => total + item.quantity, 0);
     }
   }
 };
 </script>
 
 <style>
 .anniu {
   float: left;
 }
 .el-row {
     margin-bottom: 10px;
   }
 </style>