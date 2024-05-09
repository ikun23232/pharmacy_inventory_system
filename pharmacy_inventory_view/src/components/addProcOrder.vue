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
          <el-button icon="el-icon-plus" style="float: left">申请单</el-button>
          <el-table
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column prop="id" label="订单序号" width="120">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column label="日期" width="120" fixed>
              <template slot-scope="scope">{{ scope.row.date }}</template>
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址" show-overflow-tooltip>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">明细</el-tab-pane>
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
  </span>
</template>

<script>
import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
export default {
  name: "addProcOrder",
  data() {
    var check = (rule, value, callback) => {
      let data = checkName(value).then((resp) => {
        console.log(resp.data);
        if (resp.code == 200) {
          callback(new Error("仓库名已经存在"));
        } else {
          callback();
        }
      });
    };
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
      activeName: "first",

      //   rules: {
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
      //   },
    };
  },
  methods: {
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
  },
};
</script>

<style>
.anniu {
  float: left;
}
</style>