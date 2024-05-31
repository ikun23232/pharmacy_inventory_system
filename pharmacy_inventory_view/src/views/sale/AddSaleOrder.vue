<template>
 <div>
  <div>
    <el-form
      :model="saleOrder"
      :rules="rules"
      ref="saleOrderForm"
      label-width="100px"
      class="demo-ruleForm"
    >
    <el-row :gutter="20">
      <el-col :span="8">
        <div>
            <el-form-item label="单据编号" prop="orderNo">
              <el-input type="text" disabled v-model="saleOrder.orderNo" size="small"></el-input>
            </el-form-item></div>
      </el-col>
        <el-col :span="8">
          <div>
            <el-form-item label="单据日期" prop="orderDate">
              <el-date-picker
                size="small"
                v-model="saleOrder.orderDate"
                disabled
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日">
              </el-date-picker>
            </el-form-item></div>
        </el-col>
        <el-col :span="8"><div>
            <el-form-item label="制单人" prop="createByName">
              <el-input type="text" disabled v-model="saleOrder.createByName" size="small"></el-input>
            </el-form-item></div></el-col>
        <el-col :span="8"
          ><div>
            <el-form-item label="银行账户" prop="bankAccountId">
              <el-select
                size="small"
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
        <el-divider style="margin: 0;"></el-divider>
        <el-row type="flex">
          <el-col :span="2" >
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDetails">添加</el-button>
          </el-col>
          <el-col :span="2">
            <el-button icon="el-icon-minus" size="mini" @click="handleDeleteDetails">删除</el-button>
          </el-col>
          <el-col :span="2">
            <el-button icon="el-icon-delete" size="mini" @click="handleDeleteAllDetails">清空</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
          <el-table
         :data="medicineDetailList"
         :row-class-name="rowClassName"
         @selection-change="handleSelectionChange"
         ref="tb"
         border
         style="width:960px"
       >
         <el-table-column type="selection" width="50" align="center" />
         <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>
         <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
          <template slot-scope="scope">
             <el-select @change="changeMedicine(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].medicineId" >
               <el-option
                 v-for="dict in baseMedicineList"
                 :key="dict.id"
                 :label="dict.name"
                 :value="dict.id"/>
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="批次号" align="center"  width="120" prop="batchCode">
          <template slot-scope="scope">
             <el-select @change="changeBatchCode(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].batchCode" >
               <el-option
                 v-for="dict in scope.row.batchCodeList"
                 :key="dict.batchCode"
                 :label="dict.batchCode"
                 :value="dict.batchCode"/>
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="规格型号" align="center" prop="specification" width="120">
           <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].specification" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="药品类型" align="center" prop="categoryId" width="120">
          <template slot-scope="scope">
            <el-input v-model="medicineDetailList[scope.row.xh-1].categoryName" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="计量单位" align="center" prop="unitId" width="120">
          <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].unitName" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="数量" align="center" prop="quantity" width="160">
           <template slot-scope="scope">
               <el-input-number size="small" :min="1" :step="1"  @change="changeQuantity(scope.row)" v-model="medicineDetailList[scope.row.xh-1].quantity"></el-input-number>
           </template>
         </el-table-column>
         <el-table-column label="单价" align="center" prop="salePrice" width="120">
           <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].salePrice" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="总价" align="center" prop="totalPrice" width="120">
           <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].totalPrice" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="当前库存" align="center" prop="stock" width="120">
           <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].stock" disabled></el-input>
           </template>
         </el-table-column>
         <el-table-column label="库存预警值" align="center" prop="warning" width="120">
           <template slot-scope="scope">
            <el-input  v-model="medicineDetailList[scope.row.xh-1].warning" disabled></el-input>
           </template>
         </el-table-column>
       </el-table>
        </el-col>
        </el-row>
        <div style="text-align: left;">
          <span style=" margin-bottom:25px;">合计: {{sumPrice}} 元</span> 
        </div>   
       <el-divider></el-divider>
      <div style="text-align: left;">
       <span>备注: </span><el-input size="small" v-model="saleOrder.remark" placeholder="请输入备注" style="width: 860px;"></el-input>
     </div>

    <el-row type="flex" justify="end" style="margin-top: 20px;">
      <el-col :span="2" >
        <el-button type="primary" size="mini" @click="saveForm('saleOrderForm')">保存</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" size="mini" @click="submitForm('saleOrderForm')" :disabled="isPay?true:false">提交</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" size="mini" @click="payOrder" :disabled="!isPay?true:false">支付</el-button>
      </el-col>
      <el-col :span="2">
        <el-button size="mini" @click="cancelForm" :disabled="isPay?true:false">取消</el-button>
      </el-col>
    </el-row>
  </el-form>
  </div>
  <!-- <el-dialog
    title="销售订单支付"
    :visible.sync="dialogVisible"
    width="30%"
    append-to-body
    :before-close="handleClose">
    <div>
      <img :src="imgUrl" alt="">
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogVisible = false">关闭交易</el-button>
    </span>
  </el-dialog> -->
 </div>
</template>

<script>
import { Message } from "element-ui";
import {addSaleOrder,saveSaleOrder,createOrder} from "../../api/saleOrder.js";
import {getAllBankCountList} from "../../api/BankAccount.js";
import { getCurrentTime } from "../../api/util.js";
import {getAllBaseMedicine,getBaseMedicineById,getAllBatchCodeByMedicineId} from "../../api/baseMedicine.js";

export default {
  name: "addSaleOrder",
  data() {
    return {
      medicineDetailList:[],
      bankAccountList:[],
      baseMedicineList:[],
      saleOrder:{
        orderNo:"",
        orderDate:new Date(),
        createByName:"张三",
        bankAccountId:"",
        remark:'',
        medicineDetailList:[],
        totalPrice:"",
        totalNumber:""
      },   
      checkedDetail:[],
      isPay:false,
      // dialogVisible:false,
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
    //拿到所有的银行账户
    async getAllBankCountList() {
      let data = await getAllBankCountList();
      this.bankAccountList=data.data;
    },
    //获取所有的基础药品
    async getAllBaseMedicine() {
      let data = await getAllBaseMedicine();
      this.baseMedicineList=data.data;
    },
    rowClassName({ row, rowIndex }) {
      row.xh = rowIndex + 1;
    },
    //当药品信息发生变化，获取当前药品的所有批次号
    async changeMedicine(obj){
     obj.batchCodeList=[]
     let data=await getAllBatchCodeByMedicineId(obj.medicineId);
     obj.batchCodeList=data.data;
    },
    //当批次号发生变化
    async changeBatchCode(obj){
      let data=await getBaseMedicineById(obj.medicineId,obj.batchCode);
      obj.unitName=data.data.unitName
      obj.specification=data.data.specification
      obj.categoryName=data.data.categoryName
      obj.stock=data.data.stock
      obj.quantity=data.data.quantity
      obj.salePrice=data.data.salePrice
      obj.totalPrice=data.data.salePrice
      obj.warning=data.data.warning
    },
    //数量发生变化
    async changeQuantity(obj){
      obj.totalPrice=parseFloat((obj.salePrice*obj.quantity).toFixed(2))
    },
    //点击添加
    async handleAddDetails() {
      if (this.medicineDetailList == undefined) {
        this.medicineDetailList = new Array();
      }
      let obj = {
        batchCodeList:[],
        medicineId:'',
        batchCode:"",
        unitId:"",
        specification:'',
        categoryId:"",
        salePrice:'',
        quantity:"",
        stock:"",
        totalPrice:'',
        warning:"",
      };
      this.medicineDetailList.push(obj);
    },
    //删除选中的订单明细
    handleDeleteDetails() {
      if (this.checkedDetail.length == 0) {
        this.$alert("请先选择要删除的数据", "提示", {
          confirmButtonText: "确定",
        });
      } else {
        this.medicineDetailList.splice(this.checkedDetail[0].xh - 1, 1);
      }
    },
    //清空
    handleDeleteAllDetails() {
      this.medicineDetailList = undefined;
    },
    //选中
    handleSelectionChange(val) {
      this.checkedDetail = val;
      console.log("checkedDetail",this.checkedDetail)
    },
    //提交要添加的销售订单
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.medicineDetailList.length==0){
            Message({
              message: "请输入订单详情",
              type: "error",
              center: "true",
            });
            return;
          }else{
            for (const obj of this.medicineDetailList) {
              if (obj.medicineId==''||obj.medicineId==undefined||obj.batchCode==''||obj.batchCode==undefined){
                Message({
                  message: "请输入商品明细",
                  type: "error",
                  center: "true",
                });
                return;
              }
              if (obj.stock<obj.quantity){
                Message({
                  message: "库存不足，无法购买",
                  type: "error",
                  center: "true",
                });
                return;
              }
            }
          }
          this.saleOrder.medicineDetailList=this.medicineDetailList
          this.saleOrder.totalPrice=this.sumPrice
          this.saleOrder.totalNumber=this.totalNumber
          addSaleOrder(this.saleOrder).then((resp) => {
            this.isPay=true;
            console.log("999",this.isPay)
            if (resp.code == "200") {
              Message({
                message: "添加成功!",
                type: "success",
                center: "true",
              });   
            }         
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    payOrder(){
      window.location.href = "sale/createOrder?orderNo="+this.saleOrder.orderNo+"&totalPrice="+this.saleOrder.totalPrice;
    },
    saveForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          for (const obj of this.medicineDetailList) {
            if (obj.stock<obj.quantity){
              Message({
                message: "库存不足，无法购买",
                type: "error",
                center: "true",
              });
              return;
            }
          }
          this.saleOrder.medicineDetailList=this.medicineDetailList
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
      if (this.medicineDetailList==undefined){
        return 0
      }
      return this.medicineDetailList.reduce((total, item) => total + item.totalPrice, 0);
    },
    totalNumber() {
      // 使用 reduce 方法计算总数量
      if (this.medicineDetailList==undefined){
        return 0
      }
      return this.medicineDetailList.reduce((total, item) => total + item.quantity, 0);
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