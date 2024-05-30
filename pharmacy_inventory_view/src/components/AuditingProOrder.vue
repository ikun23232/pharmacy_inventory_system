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
                disabled
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
              <el-input
                disabled
                type="text"
                v-model="CgddOrder.subject"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="采购类型" prop="type">
              <el-select
                disabled
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
                disabled
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
                <el-option label="货到付款" :value="0"> </el-option>
                <el-option label="全款后发货" :value="1"> </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                disabled
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
              <el-input
                disabled
                type="text"
                v-model="CgddOrder.phone"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="供应商" prop="providerId">
              <el-select
                disabled
                v-model="CgddOrder.providerId"
                placeholder="请选择供应商"
                clearable
                filterable
              >
                <el-option
                  v-for="item in providerList"
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
                disabled
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
                disabled
                type="text"
                placeholder="请输入传真"
                v-model.number="CgddOrder.fax"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="邮件" prop="email">
              <el-input
                disabled
                type="text"
                v-model="CgddOrder.email"
              ></el-input>
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
                <el-option label="未编制" :value="1"></el-option>
                <el-option label="编制完" :value="2"></el-option>
                <el-option label="执行中" :value="3"></el-option>
                <el-option label="执行完" :value="4"></el-option>
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
                disabled
                icon="el-icon-plus"
                @click="getCgsqlist"
                style="float: left"
                >申请单</el-button
              >
              <el-button
                disabled
                icon="el-icon-delete"
                type="danger"
                @click="deleteCgsq"
                style="float: left"
                >删除</el-button
              >
            </el-form-item>
            <el-table
              disabled
              :data="cgsqList"
              show-summary
              :summary-method="(param) => getSummaries(param, ['count', 'referenceamount'])"             
              border
              style="width: 1200px"
            >
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
              disabled
              icon="el-icon-plus"
              @click="getMedicineListDetail"
              style="float: left"
              >添加</el-button
            >
            <el-button
              disabled
              icon="el-icon-delete"
              type="danger"
              style="float: left"
              @click="deleteMedicine"
              >删除</el-button
            >
            <el-table
              disabled
              :data="medicineListTemp"
              show-summary
              :summary-method="(param) => getSummaries(param, ['quantity', 'purchasePrice','totalPrice'])"
              border
              style="width: 1200px"
            >
              <el-table-column
                prop="sourceCode"
                label="源单据编号"
                width="150"
                fixed
              >
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
              <el-table-column prop="purchasePrice" label="采购价" width="120">
              </el-table-column>
              <el-table-column prop="totalPrice" label="总价格" width="120">
              </el-table-column>
            </el-table>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">
          <el-button
            disabled
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAddDetails"
            >添加</el-button
          >
          <el-button
            disabled
            type="success"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteDetails"
            >删除</el-button
          >
          <el-button
            disabled
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteAllDetails"
            >清空</el-button
          >
          <el-table
            disabled
            v-loading="loading"
            :data="bcglXiangXiList"
            :row-class-name="rowClassName"
            ref="tb"
          >
            <el-table-column
              label="序号"
              align="center"
              prop="xh"
              width="50"
            ></el-table-column>

            <!-- <el-table-column label="供应商" width="250" prop="providerId">
              <template slot-scope="scope">
                <el-select
                  clearable
                  filterable
                  v-model="bcglXiangXiList[scope.row.xh - 1].providerId"
                >
                  <el-option
                    v-for="dict in providerList"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"
                  />
                </el-select>
              </template>
            </el-table-column> -->

            <el-table-column
              label="药品"
              align="center"
              prop="medicineId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  disabled
                  clearable
                  @change="changeMedicine(scope.row)"
                  v-model="bcglXiangXiList[scope.row.xh - 1].medicineId"
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
            <el-table-column
              label="规格型号"
              align="center"
              prop="measureId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  clearable
                  @change="changezdts(scope.row)"
                  v-model="bcglXiangXiList[scope.row.xh - 1].specification"
                  disabled
                >
                  <el-option
                    :label="scope.row.specification"
                    :value="scope.row.specification"
                  />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column
              label="单位"
              align="center"
              prop="unitId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  clearable
                  @change="changezdts(scope.row)"
                  v-model="bcglXiangXiList[scope.row.xh - 1].unitName"
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
            <el-table-column
              label="数量"
              align="center"
              prop="totalPrice"
              width="150"
            >
              <template slot-scope="scope">
                <el-input-number
                  disabled
                  v-model="bcglXiangXiList[scope.row.xh - 1].quantity"
                  controls-position="right"
                  @change="handleChange"
                  :min="1"
                  :max="10"
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column
              label="单价"
              align="center"
              prop="price"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  clearable
                  @change="changezdts(scope.row)"
                  v-model="bcglXiangXiList[scope.row.xh - 1].price"
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
            <el-table-column
              label="参考总价"
              align="center"
              prop="totalPrice"
              width="150"
            >
              <template slot-scope="scope">
                {{ calculatedTotalPrice(scope.row) }}
              </template>
            </el-table-column>
          </el-table>
          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          <div style="text-align: left">
            <div style="margin-bottom: 25px">合计{{ totalPrice }}元</div>
          </div>
        </el-tab-pane>
      </el-tabs>
      <el-row>
        <el-col :span="8">
          <el-form-item
            disabled
            label="备注 "
            style="margin-top: 10px"
            prop="remark"
          >
            <el-input
              disabled
              style="width: 300px"
              type="text"
              v-model="CgddOrder.remark"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item
            label="核批意见"
            style="margin-top: 10px"
            prop="approverRemark"
          >
            <el-form label-width="100px" :model="form">
              <el-input
                style="width: 300px"
                type="text"
                v-model="CgddOrder.approverRemark"
              ></el-input>
            </el-form>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item
            label="核批结果"
            style="margin-top: 10px"
            prop="approvalStatus"
          >
            <el-select
              v-model="CgddOrder.approvalStatus"
              placeholder="请选择审批结果"
              clearable
              filterable
            >
              <el-option label="不通过" :value="1"></el-option>
              <el-option label="通过" :value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
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
import { initCgSqOrderList, getCgsqOrderByCode } from "@/api/CgsdOrder";
import { init } from "../api/BaseProvider.js";
import { getMedicineListByCode } from "@/api/baseMedicine";
import { getCgddByCode, auditingOrder } from "../api/procurementOrder.js";
import { getBaseMedicineListByProviderId } from "@/api/baseMedicine";
import { getPayType } from "../api/public.js";
export default {
  name: "auditingProcOrder",
  props: {
    code: {
      type: String,
      required: true,
    },
    id: {
      type: Number,
      require: true,
    },
  },
  data() {
    return {
      bcglXiangXiList: [],
      CgddOrder: {
        approvalStatus: "",
        approverRemark: "",
        remark: "",
        orderStatus: "",
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
        documenterBy: 1,
        medicineList: [],
        effectiveTime: new Date(),
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
      changeMedicineList: [],
      providerList: [],
      cgddMedicineionList: [],
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
            pattern: /^[1][3,5,7,8][0-9]{9}$/,
            message: "请输入1至3、5、7,8开头的电话格式",
            trigger: "blur",
          },
        ],
        approvalStatus: [
          { required: true, message: "请审批", trigger: "change" },
        ],
        approverRemark: [
          { required: true, message: "审批备注不能为空", trigger: "blur" },
          { min: 1, max: 10, message: "联系人为1-10个字符", trigger: "blur" },
        ],
      },
    };
  },
  async mounted() {
    await this.initProvider();
    let data = await getPayType();
    this.cgType = data.data;
    let cgdd = await getCgddByCode(this.code);
    this.CgddOrder = cgdd.data;
    let medicineList = await getMedicineListByCode(this.code);
    for (let index = 0; index < medicineList.data.length; index++) {
      if (
        medicineList.data[index].sourceCode != null &&
        medicineList.data[index].sourceCode != ""
      ) {
        let cgsq = await getCgsqOrderByCode(
          medicineList.data[index].sourceCode
        );
        this.cgsqList.push(cgsq.data);
      }
    }
    this.medicineListTemp = medicineList.data;
    this.cgddMedicineionList = this.medicineListTemp;
    await this.getMedicineListDetail();
    await this.initCgSqOrderList();
    this.CgddOrder.approvalStatus = "";
  },
  methods: {
    async initCgSqOrderList() {
      let data = await initCgSqOrderList(this.vo);
      console.log(data);
      this.list = data.data;
      console.log(this.list);
    },
    async initProvider() {
      let resp = await init("", 0, 1, 5);
      this.providerList = resp.data.list;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          auditingOrder(this.CgddOrder).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "审核成功!",
                type: "success",
                center: "true",
              });
              this.$emit("handleAuditingSuccess");
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
      this.$emit("cancelAuditing");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleCgsqSelectionChange(val) {
      this.cgsqListTemp = val;
      console.log("cgsqList", this.cgsqList.length);
    },
    handleCgsqMedicineionChange(val) {
      this.cgddMedicineionList = val;
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
            this.medicineListTemp.push(data.data[i]);
          }
        }
        console.log("medicineListTemp:", this.medicineListTemp);
      }
      this.cgsqdialog = false;
      this.cgsqList = this.cgsqListTemp;
      this.cgsqListTemp = [];
      this.initCgSqOrderList();
    },
    async deleteCgsq() {
      if (this.cgsqListTemp.length >= 0) {
        var data = [];
        for (let index = 0; index < this.cgsqListTemp.length; index++) {
          for (let i = 0; i < this.medicineListTemp.length; i++) {
            var temp = [];
            temp.push(this.medicineListTemp[i]);
            alert(
              this.cgsqListTemp[index].code == this.medicineListTemp[i].code
            );
            console.log("medicineListTemp:", this.medicineListTemp);
            console.log("temp:", temp);
            if (
              this.cgsqListTemp[index].code == this.medicineListTemp[i].code
            ) {
              const medicineData = this.medicineListTemp.filter(
                (item) => !temp.includes(item)
              );
              data = medicineData;
            }
            temp = [];
          }
          // var data = await getMedicineListByCode(cgsq.code);
          // console.log("data:", data);
        }
        if (this.medicineListTemp.length == 1) {
          this.medicineListTemp = [];
        } else {
          if (data.length != 0) {
            this.medicineListTemp = data;
          }
        }
      }
      const newData = this.cgsqList.filter(
        (item) => !this.cgsqListTemp.includes(item)
      );
      console.log("newData", newData);
      this.cgsqList = newData;
    },
    deleteMedicine() {
      const newData = this.medicineListTemp.filter(
        (item) => !this.changeMedicineList.includes(item)
      );
      this.medicineListTemp = newData;
      this.changeMedicineList = [];
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
    async handleAddDetails() {
      if (this.CgddOrder.providerId == 0 || this.CgddOrder.providerId == "") {
        Message({
          message: "请先选择供应商",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.bcglXiangXiList == undefined) {
        this.bcglXiangXiList = new Array();
      }
      if (this.bcglXiangXiList.length > 0) {
        for (const objElement of this.bcglXiangXiList) {
          if (objElement.medicineId == "") {
            Message({
              message: "请先填写完别的商品明细先!",
              type: "error",
              center: "true",
            });
            return;
          }
        }
      }
      let resp = await getBaseMedicineListByProviderId(
        this.CgddOrder.providerId
      );
      let obj = {
        // providerList: [],
        medicineList: resp.data.data,
        providerId: this.CgddOrder.providerId,
        medicineId: "",
        unitName: "",
        specification: "",
        price: "",
        totalPrice: "",
        quantity: "",
      };
      console.log(this.providerList);
      // obj.providerList = this.providerList;
      obj.dkdd = "1";
      obj.sjfw = ["07:00", "07:30"];
      obj.jxsjfw = ["06:00", "12:00"];
      this.bcglXiangXiList.push(obj);
      this.CgddOrder.medicineList = this.bcglXiangXiList;
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

    async changeMedicine(obj) {
      console.log(obj);
      console.log(this.bcglXiangXiList);
      for (let i = 0; i <= this.bcglXiangXiList.length - 2; i++) {
        if (this.bcglXiangXiList[i].medicineId == obj.medicineId) {
          if (this.bcglXiangXiList[i].providerId != obj.providerId) {
            break;
          }
          Message({
            message: "您重复添加了商品!",
            type: "error",
            center: "true",
          });
          obj.medicineId = "";
          return;
        }
      }
      for (const objElement of obj.medicineList) {
        if (obj.medicineId == objElement.id) {
          obj.unitName = objElement.unitName;
          obj.specification = objElement.specification;
          obj.price = objElement.purchasePrice;
          obj.id = obj.medicineId;
          obj.purchasePrice = objElement.purchasePrice;
        }
      }
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
    async getMedicineListDetail() {
      if (this.cgddMedicineionList.length == 0) {
        Message({
          message: "请选择药品！",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.cgddMedicineionList.length > 0) {
        for (let index = 0; index < this.cgddMedicineionList.length; index++) {
          if (this.bcglXiangXiList == undefined) {
            this.bcglXiangXiList = new Array();
          }
          let resp = await getBaseMedicineListByProviderId(
            this.CgddOrder.providerId
          );
          console.log("cgddMedicineionList:" + this.cgddMedicineionList[index]);
          let obj = {
            medicineList: resp.data.data,
            providerId: this.CgddOrder.providerId,
            medicineId: this.cgddMedicineionList[index].id,
            unitName: this.cgddMedicineionList[index].unitName,
            specification: this.cgddMedicineionList[index].specification,
            price: this.cgddMedicineionList[index].purchasePrice,
            totalPrice: this.cgddMedicineionList[index].totalPrice,
            quantity: this.cgddMedicineionList[index].quantity,
            medicineOrderId: this.cgddMedicineionList[index].medicineOrderId,
          };
          obj.dkdd = "1";
          obj.sjfw = ["07:00", "07:30"];
          obj.jxsjfw = ["06:00", "12:00"];
          this.bcglXiangXiList.push(obj);
        }
        this.CgddOrder.medicineList = this.bcglXiangXiList;
      }
    },
    getSummaries(param, targetColumns) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
          return;
        }
        if (targetColumns.includes(column.property)) {
          const values = data.map((item) => Number(item[column.property]));

          if (!values.every((value) => isNaN(value))) {
            sums[index] =
              values
                .reduce((prev, curr) => {
                  return prev + curr;
                }, 0)
                .toFixed(2) + "";
          } else {
            sums[index] = "N/A";
          }
        } else {
          sums[index] = "";
        }
      });

      return sums;
    },
  },
  computed: {
    totalPrice() {
      // 使用 reduce 方法计算总价
      if (this.bcglXiangXiList == undefined) {
        return 0;
      }
      return this.bcglXiangXiList.reduce(
        (total, item) => total + item.totalPrice * item.quantity,
        0
      );
    },
    calculatedTotalPrice() {
      return (row) => {
        row.totalPrice = row.quantity * row.price;
        return row.quantity * row.price;
      };
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