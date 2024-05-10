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
    <el-row :gutter="10">
      <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="orderNo">
              <el-input type="text"  placeholder="请输入" v-model="saleOrder.orderNo"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="7">
          <div class="grid-content bg-purple">
            <el-form-item label="单据日期" prop="orderDate">
              <el-date-picker
                v-model="saleOrder.orderDate"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日">
              </el-date-picker>
            </el-form-item></div>
        </el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="制单人" prop="createBy">
              <el-input type="text" v-model="saleOrder.createBy"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="银行账户" prop="type">
              <el-select
                v-model="saleOrder.bankAccountId"
                placeholder="请选择银行账户"
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
         @selection-change="handleDetailSelectionChange"
         ref="tb"
         border
       >
         <el-table-column type="selection" width="55" align="center"/>
         <el-table-column label="序号" align="center" prop="xh" width="80">
          <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
         </el-table-column>

         <el-table-column label="医用商品名称" prop="name" align="center" width="120" >
          <template>
             <el-select
               clearable
             >
               <!-- <el-option
                 v-for="dict in zdtsOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="dict.dictValue"
               /> -->
             </el-select>
           </template>
         </el-table-column>

         <el-table-column label="规格型号" prop="name" align="center" width="120">
         </el-table-column>
         <el-table-column label="药品类型" align="center" width="120">
         </el-table-column>
         <el-table-column label="计量单位" align="center" width="120">
         </el-table-column>
         <el-table-column label="数量" align="center" width="120">
          
         </el-table-column>
         <el-table-column label="单价" align="center"  width="120">
          
         </el-table-column>
         <el-table-column label="药品库存" align="center" width="120">

         </el-table-column>
       </el-table>
        </el-col>
        </el-row>
       <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
      <div style="text-align: left;">
       <div style=" margin-bottom:25px;">合计7878元</div> 
       <span>备注:</span><el-input class=""  placeholder="请输入备注" style="width: 1000px;"></el-input>
     </div>


  </el-form>
  </div>
 </div>
</template>

<script>
import { Message } from "element-ui";
import {initSaleOrder} from "../../api/saleOrder.js";
import {getAllBankCountList} from "../../api/BankAccount.js";
export default {
  name: "addProcOrder",
  data() {
    return {
      bankAccountList:[],
      saleOrder:{
        orderNo:"",
        orderDate:"",
        orderBy:"",
        bankAccountId:""
      },
      medicineDetailList:[],
      checkedDetail: [],
    };
  },
  mounted() {
    this.getAllBankCountList();
  },
  methods: {
    async getAllBankCountList() {
      let data = await getAllBankCountList();
      this.bankAccountList=data.data;
    },
    rowClassName({ row, rowIndex }) {
    row.xh = rowIndex + 1;
    },
    chandleDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.tb.clearSelection();
        this.$refs.tb.toggleRowSelection(selection.pop());
      } else {
        this.checkedDetail = selection;
      }
    },
    handleAddDetails() {
      if (this.bcglXiangXiList == undefined) {
        this.bcglXiangXiList = new Array();
      }
      let obj = {};
      obj.ts = "1";
      obj.dkdd = "1";
      obj.sjfw = ["07:00","07:30"];
      obj.jxsjfw = ["06:00","12:00"];

      this.bcglXiangXiList.push(obj);
    },
    handleDeleteDetails() {
      if (this.checkedDetail.length == 0) {
        this.$alert("请先选择要删除的数据", "提示", {
          confirmButtonText: "确定",
        });
      } else {
        this.bcglXiangXiList.splice(this.checkedDetail[0].xh - 1, 1);
      }
    },
    handleDeleteAllDetails() {
  this.bcglXiangXiList = undefined;
},


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