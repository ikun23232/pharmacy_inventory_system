<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="CgddOrder"
      :rules="rules"
      ref="CgddOrder"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-row :gutter="20">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input type="text" v-model="CgddOrder.code" disabled></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据日期" prop="createTime">
              <el-date-picker
                v-model="CgddOrder.createTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                :disabled="true">
              </el-date-picker>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据主题" prop="subject">
              <el-input type="text" v-model="CgddOrder.subject"></el-input>
            </el-form-item></div
        ></el-col>
        
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="采购类型" prop="type">
              <el-select
                v-model="CgddOrder.type"
                placeholder="请选择采购类型1"
                clearable
                filterable
              >
            <el-option label="请选择" value="0"></el-option>
      <el-option label="直接采购" value="1"></el-option>
      <el-option label="紧急采购" value="2"></el-option>
                
              </el-select>
            </el-form-item>
          </div></el-col
        >
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="需求人" prop="type">
              <el-select
                v-model="CgddOrder.demainerId"
                placeholder="请选择需求人"
                clearable
                filterable
              >
                <!-- <el-option v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option> -->
              </el-select>
            </el-form-item>
          </div></el-col
        >
      </el-row>

      
      <el-tabs v-model="activeName">
      
        <el-tab-pane label="明细" name="first">
          <!-- <el-table
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column prop="id" label="#" width="120">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column label="建议零货商" width="120" fixed>
              <template slot-scope="scope">{{ scope.row.date }}</template>
            </el-table-column>
            <el-table-column prop="name" label="商品" width="120">
            </el-table-column>
            <el-table-column prop="address" label="规格型号" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="address" label="单位" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="address" label="数量" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="address" label="总价" show-overflow-tooltip>
            </el-table-column>
          
          </el-table> -->

          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDetails">添加</el-button>
          			 
			 <el-button
              type="success"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteDetails"
            >删除</el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteAllDetails"
            >清空</el-button>



          <el-table
         v-loading="loading"
         :data="bcglXiangXiList"
         :row-class-name="rowClassName"
         @selection-change="handleDetailSelectionChange"
         ref="tb"
       >
         <el-table-column type="selection" width="30" align="center" />
         <el-table-column label="序号" align="center" prop="xh" width="50"></el-table-column>

         <el-table-column label="建议供应商" width="250" prop="providerId">
          <template slot-scope="scope">
             <el-select
               clearable
               filterable
               @change="changeProvider(scope.row)"
               v-model="bcglXiangXiList[scope.row.xh-1].providerId"
             >
               <el-option
                 v-for="dict in providerList"
                 :key="dict.id"
                 :label="dict.name"
                 :value="dict.id"/>
             </el-select>

           </template>
         </el-table-column>

         <el-table-column label="药品" align="center" prop="medicineId" width="150">
           <template slot-scope="scope">
             <el-select
               clearable
               @change="changeMedicine(scope.row)"
               v-model="bcglXiangXiList[scope.row.xh-1].medicineId"
             >
               <el-option
                 v-for="dict in scope.row.medicineList"
                 :key="dict.id"
                 :label="dict.name"
                 :value="dict.id"
               />
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="规格型号" align="center" prop="measureId" width="150">
           <template slot-scope="scope">
             <el-select
               clearable
               @change="changezdts(scope.row)"
               v-model="bcglXiangXiList[scope.row.xh-1].specification"
               disabled
             >
               <el-option
                 :label="scope.row.specification"
                 :value="scope.row.specification"
               />
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="单位" align="center" prop="unitId" width="150">
           <template slot-scope="scope">
             <el-select
               clearable
               @change="changezdts(scope.row)"
               v-model="bcglXiangXiList[scope.row.xh-1].unitName"
               disabled
             >
               <el-option
                 v-for="dict in zdtsOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="dict.dictValue"
               />
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="数量" align="center" prop="price" width="150">
           <template slot-scope="scope">

               <el-input-number v-model="bcglXiangXiList[scope.row.xh-1].quantity" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
           </template>

         </el-table-column>
         <el-table-column label="单价" align="center" prop="price" width="150">
           <template slot-scope="scope">
             <el-select
               clearable
               @change="changezdts(scope.row)"
               v-model="bcglXiangXiList[scope.row.xh-1].price"
               disabled
             >
               <el-option
                 v-for="dict in zdtsOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="dict.dictValue"
               />
             </el-select>
           </template>
         </el-table-column>
         <el-table-column label="参考总价" align="center" prop="totalPrice" width="150">
           <template slot-scope="scope">
          {{scope.row.quantity*scope.row.price}}
           </template>
         </el-table-column>
        

       
       </el-table>
      

       
       <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
       <div style="text-align: left;">
       <div style=" margin-bottom:25px;">合计7878元</div> 

     <span>备注:</span><el-input class="" v-model="CgddOrder.remark"  placeholder="请输入备注" style="width: 1000px;"></el-input>
    </div>


        </el-tab-pane>
        <el-tab-pane label="采购申请单" >
          <el-button
            icon="el-icon-plus"
            @click="cgsqdialog = true"
            style="float: left"
            >申请单</el-button
          >
         
        </el-tab-pane>
      </el-tabs>
      <el-divider></el-divider>
      <div style="text-align: left;">
      <div class="demo-input-suffix">
  核批意见:

  <el-input
    placeholder="请输入内容"
    prefix-icon="el-icon-search"
    v-model="input2"
    style="width: 1000px;">
  </el-input>
</div>

      <div style="margin-top: 20px; margin-bottom: 25px;">核批结果:<el-select  v-model="CgddOrder.approvement" placeholder="请选择">

    <el-option
      label="未通过"
     value="0">
    </el-option>
    <el-option
      label="通过"
     value="1">
    </el-option>
  </el-select>
</div>
</div>

      <el-form-item style="width: 500px">
        <el-button class="anniu" type="primary" @click="submitForm('CgddOrder')"
          >取 消</el-button
        >
        <el-button class="anniu" s @click="resetForm('CgddOrder')"
          >保 存</el-button
        >
        <el-button class="anniu" @click="cancel()">提 交</el-button>
      </el-form-item>
    </el-form>
    <el-dialog
      title="采购申请单"
      :visible.sync="cgsqdialog"
      width="100%"
      v-if="cgsqdialog"
    >
        <p>
          <el-form :inline="true" :model="vo" class="demo-form-inline">
            <el-form-item label="单据编号">
              <el-input
                v-model="vo.code"
                placeholder="请输入单据编号"
              ></el-input>
            </el-form-item>
            <el-form-item label="单据主题">
              <el-input
                v-model="vo.subject"
                placeholder="请输入单据编号"
              ></el-input>
            </el-form-item>

            <el-form-item label="采购类型">
              <el-select v-model="vo.type" placeholder="请选择采购类型">
                <el-option label="请选择" value="0"></el-option>
                <el-option label="直接采购" value="1"></el-option>
                <el-option label="紧急采购" value="2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="作废状态">
              <el-select v-model="vo.voidState" placeholder="请选择采购类型">
                <el-option label="请选择" value="-1"></el-option>
                <el-option label="已作废" value="0"></el-option>
                <el-option label="未作废" value="1"></el-option>
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="日期">
            <div class="block">
              <el-date-picker
                v-model="value2"
                type="datetimerange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                align="right"
              >
              </el-date-picker>
            </div>
          </el-form-item> -->
            <el-form-item>
              <el-button type="primary" @click="initCgSqOrderList()"
                >查询</el-button
              >
            </el-form-item>
          </el-form>
        </p>

        <!-- <el-table
          :data="list.list"
          border
          style="width: 100%"
          @selection-change="handleCgsqSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="订单序号" width="120">
            <template slot-scope="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="code" label="订单编码" width="150" fixed>
          </el-table-column>
          <el-table-column prop="demandtime" label="单据日期" width="300">
          </el-table-column>
          <el-table-column prop="subject" label="单据主题" width="120">
          </el-table-column>
          <el-table-column prop="cgtype" label="采购类型" width="120">
          </el-table-column>
          <el-table-column prop="demanderUserName" label="需求人" width="120">
          </el-table-column>
          <el-table-column prop="demandTime" label="需求日期" width="120">
          </el-table-column>
          <el-table-column prop="count" label="数量" width="120">
          </el-table-column>
          <el-table-column prop="effectivetime" label="生效时间" width="120">
          </el-table-column>
          <el-table-column prop="referenceamount" label="参考金额" width="120">
          </el-table-column>
        </el-table> -->
        <div class="block">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="list.pageNum"
            :page-size="list.pageSize"
            layout="prev, pager, next, jumper"
            :total="list.total"
          >
          </el-pagination>
          <el-row type="flex" justify="center">
            <el-col :span="2">
              <el-button type="primary" @click="getMedicineList()"
                >确认</el-button
              >
            </el-col>
            <el-col :span="2">
              <el-button @click="cgsqdialog = false">取消</el-button>
            </el-col>
          </el-row>
        </div>
    </el-dialog>
  </span>
</template>

<script>
// import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import {getBaseMedicineListByProviderId} from "@/api/baseMedicine"
import {init}from "../api/BaseProvider.js"
import { Message } from "element-ui";
import { initCgSqOrderList } from "@/api/CgsdOrder";
export default {
  name: "addProcOrder",
  data() {
    // var check = (rule, value, callback) => {
    //   let data = checkName(value).then((resp) => {
    //     console.log(resp.data);
    //     if (resp.code == 200) {
    //       callback(new Error("仓库名已经存在"));
    //     } else {
    //       callback();
    //     }
    //   });
    // };
    return {
      bcglXiangXiList: [],
      //选中的从表数据
      checkedDetail: [],

      CgddOrder: {
        code: "",
        phone: "",
        contactperson: "",
        providerId: 1,
        deliveryDate: "",
        payType: "",
        type: "",
        subject: "",
        createTime: new Date(),
        remark: "",
        approvement:''
      },
      vo: {
        currentPageNo: 1,
        pageSize: 5,
        code: "",
        subject: "",
        type: "0",
        startTime: "",
        endTime: "",
        voidState: "",
      },
      list: {},
      activeName: "first",
      adddialogVisible: false,
      cgsqdialog: false,
      cgsqList: [],
      providerList:[],
      rules: {
        //     name: [
        //       { required: true, message: "请输入仓库名称", trigger: "blur" },
        //       { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        //       { validator: check, trigger: "blur" },
        //     ],
        //     address: [
        //       { required: true, message: "请输入仓库地址", trigger: "blur" },
        //       { min: 3, max: 5, message: "长度在 2 到 10 个字符", trigger: "blur" },
        //     ],
        //     capacity: [
        //       { required: true, message: "仓库面积不能为空" },
        //       {
        //         type: "number",
        //         min: 1,
        //         max: 10000,
        //         message: "仓库面积必须为范围在1-10000的整数",
        //       },
        //     ],
      },
    };
  },
  mounted() {
    this.initCgSqOrderList();
    this.initProvider()
  },
  methods: {
    async initCgSqOrderList() {
      let data = await initCgSqOrderList(this.vo);
      console.log(data);
      this.list = data.data;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addStoreHouse(this.CgddOrder).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "添加成功!",
                type: "success",
                center: "true",
              });
              this.$emit("handleAddSuccess");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    cancel() {
      this.$emit("cancel");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleCgsqSelectionChange(val) {
      this.cgsqList = val;
    },
    getMedicineList() {},
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
    async handleAddDetails() {
      if (this.bcglXiangXiList == undefined) {
        this.bcglXiangXiList = new Array();
      }
      let obj = {
        providerList:[],
        medicineList:[],
        providerId:'',
        medicineId:'',
        unitName:'',
        specification:'',
        price:'',
        totalPrice:'',
        quantity:'',
      };
      console.log(this.providerList)
      obj.providerList = this.providerList;
      obj.dkdd = "1";
      obj.sjfw = ["07:00", "07:30"];
      obj.jxsjfw = ["06:00", "12:00"];
      this.bcglXiangXiList.push(obj);
    },
    async initProvider(){
      let resp=await init('',0,1,5);
      this.providerList=resp.data.list
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
   async changeProvider(obj){
      obj.medicineList=[]
     let resp=await getBaseMedicineListByProviderId(obj.providerId)
     // console.log(resp)
     obj.medicineList=resp.data.data
     console.log(obj.medicineList)
    },
    async changeMedicine(obj){
      console.log(obj)



      // alert(this.bcglXiangXiList.length)
      // for (const objElement of this.bcglXiangXiList) {
      //     let index=1
      //   if (index==1){
      //     index++;
      //     break;
      //   }
      //
      //   if (obj.medicineId==objElement.medicineId){
      //     alert("您重复添加了商品")
      //     return
      //   }
      // }


      for (const objElement of obj.medicineList) {
        if (obj.medicineId==objElement.id){
             obj.unitName=objElement.unitName
          obj.specification=objElement.specification
          obj.price=objElement.purchasePrice
        }
      }

      // obj.medicineList=[]
      // alert(obj.providerId)
      // let resp=await getBaseMedicineListByProviderId(obj.providerId)
      // // console.log(resp)
      // obj.medicineList=resp.data.data
      // console.log(obj.medicineList)
    }
  },



};
</script>

<style>
.anniu {
  float: left;
}
</style>