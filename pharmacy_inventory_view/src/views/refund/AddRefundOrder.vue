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
        <el-form-item label="退款原因" prop="refundTypeName">
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
            <el-tab-pane label="销售订单" name="first"></el-tab-pane>
            <el-tab-pane label="明细" name="second"></el-tab-pane>
           </el-tabs>
           <div style="float: left;margin-bottom: 10px;">
            <el-button  type="primary" icon="el-icon-plus" size="mini" @click="handleAddSaleOrder">销售订单</el-button>
            <el-button  icon="el-icon-minus" size="mini" @click="handleDeleteDetails">移除</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAllDetails">清空</el-button>
           </div>
       
           <el-row>
             <el-col>
             <el-table
             v-loading="loading"
            :data="medicineDetailList"
            :row-class-name="rowClassName"
            @selection-change="handleSelectionChange"
            ref="tb"
            border
            style="width:900px"
          >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>
          <!-- <el-table-column fixed prop="index" label="#" width="60">
                <template #default="scope">
                {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
                </template>
            </el-table-column> -->
            <el-table-column prop="orderNo" label="单据编号" width="150">
            <template slot-scope="scope">
                <el-link type="primary" :underline="false" @click.native="lookSaleOrderDetail(scope.row.orderNo)">{{ scope.row.orderNo }}</el-link>
            </template>
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
           </el-col>
           </el-row>
           <div style="margin: 10px 0;float: left;">
            <el-button type="primary" size="mini" @click="handleAddSaleOrderDetail">添加<i class="el-icon-arrow-right"></i></el-button>
           </div>
           <el-table
             v-loading="loading"
            :data="medicineDetailList"
            :row-class-name="rowClassName"
            @selection-change="handleSelectionChange"
            ref="tb"
            border
            style="width:900px"
          >
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>
            <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
             <!-- <template slot-scope="scope">
                <el-select clearable filterable @change="changeMedicine(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].medicineId" >
                  <el-option
                    v-for="dict in baseMedicineList"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"/>
                </el-select>
              </template> -->
            </el-table-column>
            <el-table-column label="批次号" align="center"  width="120" prop="batchCode">
             <!-- <template slot-scope="scope">
                <el-select clearable filterable @change="changeBatchCode(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].batchCode" >
                  <el-option
                    v-for="dict in scope.row.batchCodeList"
                    :key="dict.batchcode"
                    :label="dict.batchcode"
                    :value="dict.batchcode"/>
                </el-select>
              </template> -->
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

           <!-- <div style="">
             <span>合计: {{sumPrice}} 元</span> 
           </div>    -->
          <!-- <el-divider></el-divider> -->
       <!-- <el-row type="flex" justify="end" style="margin-top: 20px;">
         <el-col :span="2" >
           <el-button type="primary" size="mini" @click="saveForm('saleOrderForm')">保存</el-button>
         </el-col>
         <el-col :span="2">
           <el-button type="primary" size="mini" @click="submitForm('saleOrderForm')">提交</el-button>
         </el-col>
         <el-col :span="2">
           <el-button size="mini" @click="cancelForm">取消</el-button>
         </el-col>
       </el-row> -->
     </el-form>
     </div>
         <!-- 添加销售订单 -->
        <el-dialog
        title="销售订单-添加"
        :visible.sync="addDialogFormVisible"
        width="1000px"
        v-if="addDialogFormVisible"
        >
        <ChooseSaleOrder @handleDialogFormVisible="changeDialogFormVisible"></ChooseSaleOrder>
        </el-dialog>

    </div>
   </template>
   
   <script>
   import { Message } from "element-ui";
   import {addSaleOrder,saveSaleOrder,initSaleOrder} from "../../api/saleOrder.js";
   import {getAllBankCountList} from "../../api/BankAccount.js";
   import { getCurrentTime } from "../../api/util.js";
   import {getAllBaseMedicine,getBaseMedicineById,getAllBatchCodeByMedicineId} from "../../api/baseMedicine.js";
   import ChooseSaleOrder from "../refund/ChooseSaleOrder.vue";

   export default {
     name: "addSaleOrder",
     components: {
        ChooseSaleOrder,
     },
     data() {
       return {
         medicineDetailList:[],
         refundTypeList:[],
         baseMedicineList:[],
         saleOrder:{
           orderNo:"",
           orderDate:new Date(),
           createByName:"张三",
           bankAccountId:"",
           remark:'',
           checkedDetail: [],
           totalPrice:"",
           totalNumber:""
         },   
         addDialogFormVisible:false,
         rules:{
           orderNo:[
               { required: true, message: "请输入订单编号", trigger: "blur" },
           ],
           orderDate:[
               { required: true, message: "请输入单据日期", trigger: "blur" },
           ],
           createByName:[
               { required: true, message: "请选择创建人", trigger: "blur" },
           ],
           bankAccountId:[
               { required: true, message: "请选择银行账户", trigger: "blur" },
           ],
         }
       };
     },
     async mounted() {
       this.saleOrder.orderNo = await getCurrentTime("XXDD");
       this.getAllBankCountList();
       this.getAllBaseMedicine();
     },
     methods: {
       async getAllBankCountList() {
         let data = await getAllBankCountList();
         this.bankAccountList=data.data;
       },
       async getAllBaseMedicine() {
         let data = await getAllBaseMedicine();
         this.baseMedicineList=data.data;
       },
       rowClassName({ row, rowIndex }) {
         row.xh = rowIndex + 1;
       },
        // tabs标签页切换事件
        handleClick(tab, event) {
            console.log(tab, event);
            if (tab.index == 1) {
                //网络请求1      
            } else if (tab.index == 2) {
                //网络请求2    
            } else {
            //网络请求3
            }
        },
        handleAddSaleOrder(){
            this.addDialogFormVisible=true;
        },
       async changeMedicine(obj){
        obj.batchCodeList=[]
        let data=await getAllBatchCodeByMedicineId(obj.medicineId);
        obj.batchCodeList=data.data;
       },
       async changeBatchCode(obj){
         let data=await getBaseMedicineById(obj.medicineId,obj.batchCode);
         obj.unitName=data.data.unitName
         obj.specification=data.data.specification
         obj.categoryName=data.data.categoryName
         obj.stock=data.data.stock
         obj.quantity=data.data.quantity
         obj.salePrice=data.data.salePrice
         obj.totalPrice=data.data.salePrice
       },
       async changeQuantity(obj){
         obj.totalPrice=parseFloat((obj.salePrice*obj.quantity).toFixed(2))
       },
       async handleAddDetails() {
         if (this.medicineDetailList == undefined) {
           this.medicineDetailList = new Array();
         }
         let obj = {
           batchCodeList:[],
           medicineId:'',
           batchCode:"",
           unitId:"",
           unitName:'',
           specification:'',
           categoryId:"",
           categoryName:"",
           salePrice:'',
           quantity:"",
           stock:"",
           totalPrice:'',
         };
         this.medicineDetailList.push(obj);
       },
       handleDeleteDetails() {
         if (this.saleOrder.checkedDetail.length == 0) {
           this.$alert("请先选择要删除的数据", "提示", {
             confirmButtonText: "确定",
           });
         } else {
           this.medicineDetailList.splice(this.saleOrder.checkedDetail[0].xh - 1, 1);
         }
       },
       handleDeleteAllDetails() {
         this.medicineDetailList = undefined;
       },
       handleSelectionChange(val) {
         this.saleOrder.checkedDetail = val;
         console.log("checkedDetail",this.saleOrder.checkedDetail)
       },
       submitForm(formName){
         this.$refs[formName].validate((valid) => {
           if (valid) {
             console.log("length",this.medicineDetailList.length,this.saleOrder.checkedDetail.length)
             if(this.medicineDetailList.length > 0&&this.saleOrder.checkedDetail!=null){
               this.saleOrder.totalPrice=this.sumPrice
               this.saleOrder.totalNumber=this.totalNumber
               addSaleOrder(this.saleOrder).then((resp) => {
                 if (resp.code == "200") {
                   Message({
                     message: "添加成功!",
                     type: "success",
                     center: "true",
                   });
                   this.$emit("handleDialogFormVisible",false);
                 }         
               });
             }else{
               Message({
                   message: "请输入订单详情!",
                   type: "error",
                   center: "true",
                 });
             }         
           } else {
             console.log("error submit!!");
             return false;
           }
         });
       },
       saveForm(formName){
         this.$refs[formName].validate((valid) => {
           if (valid) {
             this.saleOrder.totalPrice=this.sumPrice
             this.saleOrder.totalNumber=this.totalNumber
             saveSaleOrder(this.saleOrder).then((resp) => {
                 if (resp.code == "200") {
                   Message({
                     message: "保存成功!",
                     type: "success",
                     center: "true",
                   });
                   this.$emit("handleDialogFormVisible",false);
                 }         
               });
             this.$emit("handleDialogFormVisible",false);
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
         if (this.saleOrder.checkedDetail==undefined){
           return 0
         }
         return this.saleOrder.checkedDetail.reduce((total, item) => total + item.totalPrice, 0);
       },
       totalNumber() {
         // 使用 reduce 方法计算总数量
         if (this.saleOrder.checkedDetail==undefined){
           return 0
         }
         return this.saleOrder.checkedDetail.reduce((total, item) => total + item.quantity, 0);
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