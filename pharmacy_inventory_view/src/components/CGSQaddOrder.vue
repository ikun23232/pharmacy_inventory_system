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
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input type="text" v-model="CgddOrder.code"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据日期" prop="createTime">
              <el-date-picker
                v-model="CgddOrder.createTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
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
                placeholder="请选择采购类型"
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
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="付款方式" prop="payType">
              <el-select
                v-model="CgddOrder.payType"
                placeholder="请选择采购类型"
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
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                v-model="CgddOrder.deliveryDate"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="联系电话" prop="phone">
              <el-input type="text" v-model="CgddOrder.phone"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="供应商" prop="providerId">
              <el-select
                v-model="CgddOrder.providerId"
                placeholder="请选择采购类型"
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
      <el-form-item label="联系人" style="width: 290px" prop="contactperson">
        <el-input
          type="text"
          placeholder="请输入联系人"
          v-model.number="CgddOrder.contactperson"
        ></el-input>
      </el-form-item>
      <el-tabs v-model="activeName">
        <el-tab-pane label="采购申请单" name="first">
          <el-button
            icon="el-icon-plus"
            @click="cgsqdialog = true"
            style="float: left"
            >申请单</el-button
          >
         
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">
          <el-table
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
          
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <el-form-item style="width: 500px">
        <el-button class="anniu" type="primary" @click="submitForm('CgddOrder')"
          >立即添加</el-button
        >
        <el-button class="anniu" s @click="resetForm('CgddOrder')"
          >重置</el-button
        >
        <el-button class="anniu" @click="cancel()">取 消</el-button>
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
      CgddOrder: {
        code: "",
        createTime: "",
        phone: "",
        contactperson: "",
        providerId: 1,
        deliveryDate: "",
        payType: "",
        type: "",
        subject: "",
        createTime: "",
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
    getMedicineList(){
        
    }
  },
};
</script>

<style>
.anniu {
  float: left;
}
</style>