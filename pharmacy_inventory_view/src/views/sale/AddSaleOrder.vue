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
            <el-form-item label="制单人" prop="createBy">
              <el-select
                v-model="saleOrder.createBy"
                placeholder="请选择"
                clearable
                filterable
              >
              <el-option v-for="item in userList"
                  :key="item.index"
                  :label="item.userName"
                  :value="item.userId">
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
        <el-row type="flex">
          <el-col :span="2" >
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDetails">添加</el-button>
          </el-col>
          <el-col :span="2">
            <el-button type="success" icon="el-icon-delete" size="mini" @click="handleDeleteDetails">删除</el-button>
          </el-col>
          <el-col :span="2">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAllDetails">清空</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
          <el-table
         v-loading="loading"
         :data="medicineDetailList"
         :row-class-name="rowClassName"
         @selection-change="handleDetailSelectionChange"
         ref="tb"
         border
         style="width:900px"
       >
         <el-table-column type="selection" width="50" align="center" />
         <el-table-column label="序号" fixed align="center" prop="xh" width="80"></el-table-column>

         <el-table-column label="医用商品名称" fixed align="center"  width="150" prop="medicineId">
          <template slot-scope="scope">
             <el-select clearable filterable @change="changeMedicine(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].medicineId" >
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
             <el-select clearable filterable @change="changeBatchCode(scope.row)"  v-model="medicineDetailList[scope.row.xh-1].batchCode" >
               <el-option
                 v-for="dict in scope.row.batchCodeList"
                 :key="dict.batchcode"
                 :label="dict.batchcode"
                 :value="dict.batchcode"/>
             </el-select>
           </template>
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
        <el-button type="primary" size="mini" @click="submitForm('saleOrderForm')">提交</el-button>
      </el-col>
      <el-col :span="2">
        <el-button size="mini" @click="cancelForm('saleOrderForm')">取消</el-button>
      </el-col>
    </el-row>
  </el-form>
  </div>
 </div>
</template>

<script>
import { Message } from "element-ui";
import {addSaleOrder} from "../../api/saleOrder.js";
import {getAllBankCountList} from "../../api/BankAccount.js";
import { getCurrentTime } from "../../api/util.js";
import {getAllBaseMedicine,getBaseMedicineById,getAllBatchCodeByMedicineId} from "../../api/baseMedicine.js";

export default {
  name: "addSaleOrder",
  data() {
    return {
      medicineDetailList:[],
      bankAccountList:[],
      userList:[],
      baseMedicineList:[],
      saleOrder:{
        orderNo:"",
        orderDate:new Date(),
        createName:"张三",
        bankAccountId:"",
        remark:'',
      },
      checkedDetail: [],
      dialogFormVisible:false,
      rules:{
        orderNo:[
            { required: true, message: "请输入订单编号", trigger: "blur" },
        ],
        orderDate:[
            { required: true, message: "请输入单据日期", trigger: "blur" },
        ],
        createBy:[
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
    this.getUserList();
    this.saleOrder.orderNo = await getCurrentTime("XXDD");
    this.getAllBaseMedicine();
  },
  methods: {
    async getAllBankCountList() {
      let data = await getAllBankCountList();
      this.bankAccountList=data.data;
    },
    async getUserList() {
      // let data = await getUserList();
      // this.userList=data.data;
    },
    async getAllBaseMedicine() {
      let data = await getAllBaseMedicine();
      this.baseMedicineList=data.data;
    },
    getMedicineList() {},
    rowClassName({ row, rowIndex }) {
      row.xh = rowIndex + 1;
    },
    async changeMedicine(obj){
     obj.batchCodeList=[]
     console.log("111",obj.medicineId)  
     let data=await getAllBatchCodeByMedicineId(obj.medicineId);
     obj.batchCodeList=data.data;
     console.log("123",data.data)
    },
    async changeBatchCode(obj){
      console.log("1234",obj)
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
        medicineList:"",
        batchCodeList:[],
        medicineId:'',
        batchCode:"",
        unitId:"",
        unitName:'',
        specification:'',
        categoryId:"",
        categoryName:"",
        salePrice:'',
        stock:"",
        totalPrice:'',
      };
      this.medicineDetailList.push(obj);
    },
    handleDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.tb.clearSelection();
        this.$refs.tb.toggleRowSelection(selection.pop());
      } else {
        this.checkedDetail = selection;
      }
    },
    handleDeleteDetails() {
      if (this.checkedDetail.length == 0) {
        this.$alert("请先选择要删除的数据", "提示", {
          confirmButtonText: "确定",
        });
      } else {
        this.medicineDetailList.splice(this.checkedDetail[0].xh - 1, 1);
      }
    },
    handleDeleteAllDetails() {
      this.medicineDetailList = undefined;
    },
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(checkedDetail!=null){
            // addSaleOrder().then((resp) => {
            //   console.log(resp);
            //   if (resp.code == "200") {
            //     Message({
            //       message: "添加成功!",
            //       type: "success",
            //       center: "true",
            //     });
            //     this.$emit("handleDialogFormVisible",false);
            //   }         
            // });
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
          if(checkedDetail!=null){
            this.$emit("handleDialogFormVisible",false);
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
    cancelForm(formName){
      this.$emit("handleDialogFormVisible",false);
      this.$refs[formName].resetFields();
      this.$refs[formName].clearValidate();
    },

  },
  computed: {
    sumPrice() {
      // 使用 reduce 方法计算总价
      if (this.medicineDetailList==undefined){
        return 0
      }
      return this.medicineDetailList.reduce((total, item) => total + item.totalPrice, 0);
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