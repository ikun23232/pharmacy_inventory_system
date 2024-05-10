<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="CgddOrder"
      :rules="cgddRules"
      ref="CgddOrder"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input
                type="text"
                disabled
                v-model="CgddOrder.code"
              ></el-input>
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
                <el-option
                  v-for="item in cgType"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="付款方式" prop="payType">
              <el-select
                v-model="CgddOrder.payType"
                placeholder="请选择付款方式"
                clearable
                filterable
              >
                <!-- <el-option v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option> -->
                <el-option label="货到付款" value="0"> </el-option>
                <el-option label="全款后发货" value="1"> </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
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
                <el-option
                  v-for="item in cgType"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item
              label="联系人"
              style="width: 290px"
              prop="contactperson"
            >
              <el-input
                type="text"
                placeholder="请输入联系人"
                v-model.number="CgddOrder.contactperson"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="传真" prop="fax">
              <el-input
                type="text"
                placeholder="请输入传真"
                v-model.number="CgddOrder.fax"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="邮件" prop="email">
              <el-input type="text" v-model="CgddOrder.email"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据状态" prop="orderStatus">
              <el-select
                disabled
                v-model="CgddOrder.orderStatus"
                placeholder=""
                clearable
                filterable
              >
                <!-- <el-option
                  v-for="item in cgType"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option> -->
                <el-option label="未编制" value="1"></el-option>
                <el-option label="编制完" value="2"></el-option>
                <el-option label="执行中" value="3"></el-option>
                <el-option label="执行完" value="4"></el-option>
              </el-select>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-tabs v-model="activeName">
        <el-tab-pane label="采购申请单" name="first">
          <el-form>
            <el-form-item>
              <el-button
                icon="el-icon-plus"
                @click="getCgsqlist"
                style="float: left"
                >申请单</el-button
              >
              <el-button
                icon="el-icon-delete"
                type="danger"
                @click="deleteCgsq"
                style="float: left"
                >删除</el-button
              >
            </el-form-item>
            <el-table
              :data="cgsqList"
              show-summary
              border
              style="width: 1200px"
              @selection-change="handleCgsqChange"
            >
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column prop="code" label="订单编码" width="150" fixed>
              </el-table-column>
              <el-table-column prop="demandtime" label="单据日期" width="300">
              </el-table-column>
              <el-table-column prop="subject" label="单据主题" width="120">
              </el-table-column>
              <el-table-column prop="cgtype" label="采购类型" width="120">
              </el-table-column>
              <el-table-column
                prop="demanderUserName"
                label="需求人"
                width="120"
              >
              </el-table-column>
              <el-table-column prop="demandTime" label="需求日期" width="120">
              </el-table-column>
              <el-table-column prop="count" label="数量" width="120">
              </el-table-column>
              <el-table-column
                prop="referenceamount"
                label="参考金额"
                width="120"
              >
              </el-table-column>
              <el-table-column prop="remark" label="备注" width="120">
              </el-table-column>
            </el-table>
            <el-divider></el-divider>
            <el-button
              icon="el-icon-plus"
              :disabled="CgddOrder.medicineList == null"
              style="float: left"
              >添加</el-button
            >
            <el-button
              icon="el-icon-delete"
              type="danger"
              style="float: left"
              @click="deleteMedicine"
              >删除</el-button
            >
            <el-table
              :data="CgddOrder.medicineList"
              show-summary
              border
              style="width: 1200px"
              @selection-change="handleCgsqMedicineionChange"
            >
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column prop="code" label="源单据编号" width="150" fixed>
              </el-table-column>
              <el-table-column prop="name" label="医药品名称" width="300">
              </el-table-column>
              <el-table-column
                prop="specification"
                label="医药品规格"
                width="120"
              >
              </el-table-column>
              <el-table-column prop="unitName" label="单位" width="120">
              </el-table-column>
              <el-table-column prop="quantity" label="数量" width="120">
              </el-table-column>
              <el-table-column prop="salePrice" label="售价" width="120">
              </el-table-column>
              <el-table-column prop="totalPrice" label="总价格" width="120">
              </el-table-column>
            </el-table>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">
          <el-button
            icon="el-icon-plus"
            @click="cgsqdialog = true"
            style="float: left"
            >新增</el-button
          >
        </el-tab-pane>
      </el-tabs>
      <el-form-item style="width: 500px">
        <el-row type="flex" justify="center">
          <el-col :span="6"
            ><div class="grid-content bg-purple">
              <el-button type="primary" @click="submitForm('CgddOrder')"
                >立即添加</el-button
              >
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button s @click="resetForm('CgddOrder')">重置</el-button>
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button @click="cancel()">取 消</el-button>
            </div></el-col
          >
        </el-row>
      </el-form-item>
    </el-form>
    <el-dialog
      title="采购申请单"
      :visible.sync="cgsqdialog"
      width="1200px"
      v-if="cgsqdialog"
    >
      <div class="container">
        <!-- 表单部分 -->
        <div>
          <!-- 这里放你的表单内容 -->
          <el-form :inline="true" :model="cgsqList" style="width: 1200px">
            <el-form-item class="anniu" label="单据编号">
              <el-input
                v-model="vo.code"
                placeholder="请输入单据编号"
              ></el-input>
            </el-form-item>
            <el-form-item class="anniu" label="单据主题">
              <el-input
                v-model="vo.subject"
                placeholder="请输入单据编号"
              ></el-input>
            </el-form-item>
            <el-form-item class="anniu" label="采购类型">
              <el-select v-model="vo.type" placeholder="请选择采购类型">
                <el-option label="请选择" value="0"></el-option>
                <el-option label="直接采购" value="1"></el-option>
                <el-option label="紧急采购" value="2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item class="anniu" label="作废状态">
              <el-select v-model="vo.voidState" placeholder="请选择作废状态">
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
              <el-button
                class="anniu"
                type="primary"
                @click="initCgSqOrderList()"
                >查询</el-button
              >
            </el-form-item>
          </el-form>
        </div>
        <!-- 表格部分 -->
        <div class="dataTable">
          <!-- 这里放你的表格内容 -->
          <el-table
            :data="list.list"
            border
            style="width: 1200px"
            @selection-change="handleCgsqSelectionChange"
          >
            <el-table-column type="selection" width="55"></el-table-column>
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
            <el-table-column
              prop="referenceamount"
              label="参考金额"
              width="120"
            >
            </el-table-column>
          </el-table>
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
        </div>
      </div>
    </el-dialog>
  </span>
</template>

<script>
// import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
import { initCgSqOrderList } from "@/api/CgsdOrder";
import { getMedicineListByCode } from "@/api/baseMedicine";
import { getCurrentTime } from "./../api/util.js";
import { addCgddOrder } from "./../api/procurementOrder.js";
import { getPayType } from "./../api/public.js";
export default {
  name: "addProcOrder",
  data() {
    return {
      CgddOrder: {
        orderStatus: "1",
        email: "",
        fax: "",
        code: "",
        createTime: new Date(),
        phone: "",
        contactperson: "",
        providerId: "",
        deliveryDate: "",
        payType: "",
        type: "",
        subject: "",
        createTime: new Date(),
        documenterBy: 1,
        medicineList: [],
      },
      vo: {
        currentPageNo: 1,
        pageSize: 5,
        code: "",
        subject: "",
        type: "0",
        startTime: "",
        endTime: "",
        voidState: 0,
        approvalStatus: 1,
      },
      list: {},
      cgType: {},
      activeName: "first",
      adddialogVisible: false,
      cgsqdialog: false,
      cgsqListTemp: [],
      cgsqList: [],
      medicineListTemp: [],
      cgddRules: {
        type: [
          { required: true, message: "请输入采购类型", trigger: "change" },
        ],
        providerId: [
          { required: true, message: "请输入供应商", trigger: "change" },
        ],
        deliveryDate: [
          { required: true, message: "交货日期不能为空", trigger: "change" },
        ],
        payType: [
          { required: true, message: "付款方式不能为空", trigger: "change" },
        ],
        contactperson: [
          { required: true, message: "联系人不能为空", trigger: "blur" },
          { min: 2, max: 10, message: "联系人为2-10个字符", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            pattern: /\w+@qq.com/,
            message: "请输入——@qq.com的邮箱格式",
            trigger: "blur",
          },
        ],
        phone: [
          { required: true, message: "请输入电话", trigger: "blur" },
          {
            pattern: /^[1][3,5,7][0-9]{9}$/,
            message: "请输入1至3、5、7开头的电话格式",
            trigger: "blur",
          },
        ],
        // fax: [
        //   { required: true, message: "传真不能为空", trigger: "blur" },
        //   { min: 2, max: 10, message: "传真限制为为2-10个字符", trigger: "blur" },
        // ],
      },
    };
  },
  async mounted() {
    await this.initCgSqOrderList();
    this.CgddOrder.code = await getCurrentTime("CGDD");
    let data = await getPayType();
    this.cgType = data.data;
    console.log(this.cgType);
  },
  methods: {
    async initCgSqOrderList() {
      let data = await initCgSqOrderList(this.vo);
      console.log(data);
      this.list = data.data;
      console.log(this.list);
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addCgddOrder(this.CgddOrder).then((resp) => {
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
      var data = this.CgddOrder.code;
      this.$refs[formName].resetFields();
      this.CgddOrder.code = data;
    },
    cancel() {
      this.$emit("cancel");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleCgsqSelectionChange(val) {
      this.cgsqListTemp = val;
      console.log("cgsqList", this.cgsqList.length);
    },
    handleCgsqMedicineionChange(val) {
      this.medicineListTemp = val;
    },
    handleCgsqChange(val) {
      this.cgsqListTemp = val;
    },
    async getMedicineList() {
      console.log(this.cgsqListTemp.length <= 0);
      if (this.cgsqListTemp.length <= 0) {
        Message({
          message: "请选择采购申请单",
          type: "error",
          center: "true",
        });
        return;
      }
      console.log(this.cgsqListTemp.length);
      for (let index = 0; index < this.cgsqListTemp.length; index++) {
        const cgsq = this.cgsqListTemp[index];
        console.log("cgsq", cgsq.code);
        var data = await getMedicineListByCode(cgsq.code);
        console.log("data:", data);
        for (let i = 0; i < data.data.length; i++) {
          if (data.data[i].providerId == this.CgddOrder.providerId) {
            this.CgddOrder.medicineList.push(data.data[i]);
          }
        }
        console.log("medicineList:", this.CgddOrder.medicineList);
      }
      this.cgsqdialog = false;
      this.cgsqList = this.cgsqListTemp;
      this.cgsqListTemp = [];
      this.initCgSqOrderList();
    },
    async deleteCgsq() {
      const newData = this.cgsqList.filter(
        (item) => !this.cgsqListTemp.includes(item)
      );
      console.log(this.cgsqList);
      if (this.CgddOrder.medicineList.length > 0) {
        for (let index = 0; index < this.cgsqList.length; index++) {
          const cgsq = this.newData[index];
          console.log("cgsq", cgsq.code);
          var data = await getMedicineListByCode(cgsq.code);
          console.log("data:", data);
          this.CgddOrder.medicineList = data.data;
        }
      }
      this.cgsqList = newData;
      this.cgsqListTemp = [];
    },
    deleteMedicine() {
      const newData = this.CgddOrder.medicineList.filter(
        (item) => !this.medicineListTemp.includes(item)
      );
      this.CgddOrder.medicineList = newData;
      this.medicineListTemp = [];
    },
    getCgsqlist() {
      if (this.CgddOrder.providerId == 0 || this.CgddOrder.providerId == "") {
        Message({
          message: "请先选择供应商",
          type: "error",
          center: "true",
        });
        return;
      }
      this.cgsqdialog = true;
    },
  },
};
</script>

<style>
.anniu {
  float: left;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center; /* 如果需要居中对齐可以添加这个样式 */
}

.dataTable {
  margin-top: 20px; /* 设置表格与表单之间的间距 */
}
</style>