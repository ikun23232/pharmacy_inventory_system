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
             <el-divider></el-divider>
           <el-row>
             <el-col>
             <el-table
             v-loading="loading"
            :data="medicineDetailList"
            :row-class-name="rowClassName"
            @selection-change="handleSelectionChange"
            ref="tb"
            border
            style="width:960px"
          >
            <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>
            <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
            </el-table-column>
            <el-table-column label="批次号" align="center"  width="120" prop="batchCode">
            </el-table-column>
            <el-table-column label="规格型号" align="center" prop="specification" width="120">
              <template slot-scope="scope">
               <el-input  v-model="medicineDetailList[scope.row.xh-1].specification"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="药品类型" align="center" prop="categoryId" width="120">
             <template slot-scope="scope">
               <el-input v-model="medicineDetailList[scope.row.xh-1].categoryName"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="计量单位" align="center" prop="unitId" width="120">
             <template slot-scope="scope">
               <el-input  v-model="medicineDetailList[scope.row.xh-1].unitName"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="数量" align="center" prop="quantity" width="160">
              <template slot-scope="scope">
                  <el-input-number size="small" :min="1" :step="1"  @change="changeQuantity(scope.row)" v-model="medicineDetailList[scope.row.xh-1].quantity"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="单价" align="center" prop="salePrice" width="120">
              <template slot-scope="scope">
               <el-input  v-model="medicineDetailList[scope.row.xh-1].salePrice"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="总价" align="center" prop="totalPrice" width="120">
              <template slot-scope="scope">
               <el-input  v-model="medicineDetailList[scope.row.xh-1].totalPrice"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="当前库存" align="center" prop="stock" width="120">
              <template slot-scope="scope">
               <el-input  v-model="medicineDetailList[scope.row.xh-1].stock"></el-input>
              </template>
            </el-table-column>
          </el-table>
           </el-col>
           </el-row>
           <el-row type="flex" justify="start" style="margin-top: 10px;">
          <el-col :span="2"><span>合计: {{saleOrder.totalPrice}} 元</span></el-col>
          </el-row>
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
                type="textarea"
                autosize
                placeholder="请输入内容"
                v-model="saleOrder.opinion">
              </el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="核批结果:" label-width="80px" size="small">
                <el-select v-model="saleOrder.isCheck"  placeholder="请选择" style="width:100%">
                  <el-option label="同意" value="1"></el-option>
                  <el-option label="拒绝" value="2"></el-option>
                </el-select>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-divider></el-divider>
       <el-row type="flex" justify="end" style="margin-top: 20px;">
        <el-col :span="2" >
           <el-button type="primary" size="mini" @click="printForm">打印</el-button>
         </el-col>
         <el-col :span="2" >
           <el-button type="primary" size="mini" @click="checkedRefundOrder">审核</el-button>
         </el-col>
         <el-col :span="2">
           <el-button size="mini" @click="cancelForm">取消</el-button>
         </el-col>
       </el-row>
     </el-form>
     </div>
    </div>
   </template>
   
   <script>
   import {getSaleOrderByOrderNo} from "../../api/saleOrder.js";
   import {getAllRefundTypeList,checkedRefundOrder} from "../../api/refundOrder.js";
   import {getAllBankCountList} from "../../api/BankAccount.js";
   import { Message } from "element-ui";

   export default {
     name: "CheckRefundOrder",
     props:{
        orderNo:"",
     },
     data() {
       return {
         medicineDetailList:[],
         saleOrder:[],
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
       this.getSaleOrderByOrderNo();
       this.getAllBankCountList();
       this.getAllRefundTypeList();
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
        async getSaleOrderByOrderNo() {
          let data = await getSaleOrderByOrderNo(this.orderNo);
          this.saleOrder=data.data;
          console.log("saleOrder",this.saleOrder)
          this.medicineDetailList = this.saleOrder.baseMedicineList;
        },
        rowClassName({ row, rowIndex }) {
         row.xh = rowIndex + 1;
       },
       cancelForm(){
         this.$emit("handleDialogFormVisible",false);
       },
       printForm(){
        const newPage= this.$router.resolve({ 
        path: "/printRefundOrder",
        query:{ //要传的参数 可传多个
        orderNo:this.orderNo
        }})
        window.open(newPage.href,'_blank')
       },
       async checkedRefundOrder(){
        console.log("1234",this.saleOrder.isCheck)
          if(this.saleOrder.isCheck!=null){
            let data = await checkedRefundOrder(this.saleOrder);
            if(data.code=="200"){
                Message({
                type: 'success',
                message: '审核成功！'
                })
                this.$emit("handleDialogFormVisible",false);
            }else{
                Message({type: 'error',message: '审核失败！'})
            }
          }else{
            Message({type: 'error',message: '请先选择审核结果'})
          }
        
       }
     },
     
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