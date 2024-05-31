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
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input
                type="text"
                disabled
                v-model="CgddOrder.code"
              ></el-input>
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
                disabled
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据主题" prop="subject">
              <el-input type="text" v-model="CgddOrder.subject"></el-input>
            </el-form-item></div
        ></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="供应商" prop="providerId">
              <el-select
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
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-button
              icon="el-icon-plus"
              @click="getCgsqlist"
              style="float: left"
              >采购订单</el-button
            >
          </div>
        </el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="采购订单编号">
              <el-input type="text" v-model="code" disabled></el-input>
            </el-form-item></div
        ></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="库管员" prop="warhouseUserId">
              <el-select
                v-model="CgddOrder.warhouseUserId"
                placeholder="请选择库管员"
                clearable
                filterable
              >
              <el-option
              v-for="item in kcadminlist"
              :label="item.username"
              :value="item.userid"
              :key="item.userid"
            ></el-option>
              </el-select>
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
        <el-tab-pane label="添加采购订单" name="first">
          <el-form>
            <el-form-item>
              <el-button
                icon="el-icon-plus"
                @click="getCgsqlist"
                style="float: left"
                >添加</el-button
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
              :summary-method="(param) => getSummaries(param, ['count', 'referenceAmount'])"
              border
              style="width: 1200px"
              @selection-change="handleCgsqChange"
            >
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column prop="code" label="订单编码" width="150" fixed>
              </el-table-column>
              <el-table-column
                prop="orderTypeName"
                label="采购类型"
                width="120"
              >
              </el-table-column>
              <el-table-column prop="createTime" label="单据日期" width="300">
              </el-table-column>
              <el-table-column prop="subject" label="单据主题" width="120">
              </el-table-column>
              <el-table-column
                prop="demanderName"
                label="需求人"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="providerName"
                label="供应商名字"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="contactperson"
                label="联系人"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="phone"
                label="电话"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="fax"
                label="传真"
                width="120"
              ></el-table-column>
              <el-table-column prop="deliveryDate" label="交货日期" width="120">
              </el-table-column>
              <el-table-column
                prop="deliveryName"
                label="交货人名称"
                width="120"
              >
              </el-table-column>
              <el-table-column
                prop="fax"
                label="传真"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="count"
                label="数量"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="effectiveTime"
                label="生效时间"
                width="120"
              >
              </el-table-column>
              <el-table-column
                prop="referenceAmount"
                label="参考金额"
                width="120"
              >
              </el-table-column>
              <el-table-column label="单据状态" width="120">
                <template slot-scope="scope">
                  {{
                    scope.row.orderStatus == 1
                      ? "编制中"
                      : scope.row.orderStatus == 2
                      ? "编制完"
                      : scope.row.orderStatus == 3
                      ? "执行中"
                      : scope.row.orderStatus == 4
                      ? "执行完"
                      : "未知状态"
                  }}
                </template>
              </el-table-column>
              <el-table-column prop="payType" label="结算方式" width="120">
                <template slot-scope="scope">
                  <div v-if="scope.row.payType == 2">直接付款</div>
                  <div v-if="scope.row.payType != 2">
                    {{ scope.row.payType == 0 ? "货到付款" : "全款后发款" }}
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="isPay" label="支付状态" width="120">
                <template slot-scope="scope">
                  {{ scope.row.isPay == 0 ? "未支付" : "已支付" }}
                </template>
              </el-table-column>
              <el-table-column
                prop="approverRemark"
                label="审核意见"
                width="120"
              >
              </el-table-column>
              <el-table-column
                prop="approvalStatus"
                label="核批结果"
                width="120"
              >
                <template slot-scope="scope">
                  {{ scope.row.approvalStatus == 0 ? "未通过" : "通过" }}
                </template>
              </el-table-column>
              <el-table-column prop="voidState" label="作废状态" width="120">
                <template slot-scope="scope">
                  {{ scope.row.voidState == 0 ? "未作废" : "已作废" }}
                </template>
              </el-table-column>
              <el-table-column prop="payTime" label="结算日期" width="120">
              </el-table-column>
              <el-table-column prop="remark" label="备注" width="120">
              </el-table-column>
              <el-table-column prop="documenterName" label="制单人" width="120">
              </el-table-column>
              <el-table-column prop="updateName" label="修改人" width="120">
              </el-table-column>
              <el-table-column prop="updateTime" label="修改时间" width="120">
              </el-table-column>
            </el-table>
            <el-divider></el-divider>
            <div>
              <el-button
                icon="el-icon-plus"
                :disabled="CgddOrder.medicineList == null"
                @click="getMedicineListDetail"
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
            </div>

            <el-table
              :data="medicineListTemp"
              show-summary
              :summary-method="(param) => getSummaries(param, ['quantity', 'totalPrice'])"             
              border
              style="width: 1200px"
              @selection-change="handleCgsqMedicineionChange"
            >
              <el-table-column type="selection" width="55"></el-table-column>
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
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAddDetails"
            disabled
            >添加</el-button
          >
          <el-button
            type="success"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteDetails"
            disabled
            >删除</el-button
          >
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteAllDetails"
            >清空</el-button
          >
          <el-table
            v-loading="loading"
            :data="bcglXiangXiList"
            :row-class-name="rowClassName"
            @selection-change="chandleDetailSelectionChange"
            ref="tb"
          >
            <el-table-column type="selection" width="30" align="center" />
            <el-table-column
              label="序号"
              align="center"
              prop="xh"
              width="50"
            ></el-table-column>

            <el-table-column
              label="药品"
              align="center"
              prop="medicineId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
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
                  v-model="bcglXiangXiList[scope.row.xh - 1].quantity"
                  controls-position="right"
                  @change="handleChange"
                  disabled
                  :min="1"
                  :max="10000"
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="目标仓库" width="250" prop="providerId">
              <template slot-scope="scope">
                <el-select
                  clearable
                  filterable
                  v-model="bcglXiangXiList[scope.row.xh - 1].fowardWarHouseId"
                >
                  <el-option
                    v-for="dict in warhouseList"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"
                  />
                </el-select>
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
          <el-form-item label="备注 " style="margin-top: 10px" prop="remark">
            <el-input
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
            <el-input
              disabled
              style="width: 300px"
              type="text"
              v-model="CgddOrder.approverRemark"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item
            label="核批结果"
            style="margin-top: 10px"
            prop="approvalStatus"
          >
            <el-select
              disabled
              v-model="CgddOrder.approvalStatus"
              placeholder="请选择审批结果"
              clearable
              filterable
            >
              <el-option label="通过" value="0"></el-option>
              <el-option label="不通过" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item style="width: 500px">
        <el-row type="flex" justify="center">
          <el-col :span="6"
            ><div class="grid-content bg-purple">
              <el-button type="primary" @click="submitForm('CgddOrder', 1)"
                >保存</el-button
              >
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button s @click="submitForm('CgddOrder', 2)">提交</el-button>
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
      title="选择采购订单"
      :visible.sync="cgsqdialog"
      width="1400px"
      v-if="cgsqdialog"
    >
      <div class="container">
        <!-- 表单部分 -->
        <div>
          <CGDDOrderManager
            width="25%"
            @getRow="getRow"
            :providerId="this.CgddOrder.providerId"
          >
          </CGDDOrderManager>
          <el-row type="flex" justify="center">
            <el-col :span="2">
              <el-button type="primary" @click="getMedicineList()"
                >确认1</el-button
              >
            </el-col>
            <el-col :span="2">
              <el-button @click="cgsqdialog = false">取消</el-button>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-dialog>
  </span>
</template>

<script>
// import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
import { initCgSqOrderList } from "@/api/CgsdOrder";
import { init } from "../api/BaseProvider.js";
import {
  getMedicineListByCode,
  getMedicineListByCodeComblie,
} from "@/api/baseMedicine";
import { getCurrentTime } from "./../api/util.js";
import { addCgddOrder } from "./../api/procurementOrder.js";
import { getBaseMedicineListByProviderId } from "@/api/baseMedicine";
import { getPayType } from "./../api/public.js";

import CGDDOrderManager from "@/components/CGDDOrderManager";
import { getStoreList } from "@/api/storeHouse";
import { addCgrkOrder } from "@/api/CgrkOrder";

export default {
  name: "addProcOrder",
  components: {
    CGDDOrderManager,
  },
  data() {
    return {
      ssss: [],
      bcglXiangXiList: [],
      CgddOrder: {
        approvalStatus: "",
        approverRemark: "",
        remark: "",
        orderStatus: "1",
        email: "",
        fax: "",
        code: "",
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
        warhouseUserId: 1,
        purchaseId: "",
      },
      CgddOrderTempList: [],
      CgrkMedicneList: [],
      code: "",
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
        warhouseList: [],
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
      kcadminlist:[],
      cgddRules: {
        type: [
          { required: true, message: "请输入采购类型", trigger: "change" },
        ],
        providerId: [
          { required: true, message: "请输入供应商", trigger: "change" },
        ],

        payType: [
          { required: true, message: "付款方式不能为空", trigger: "change" },
        ],
        contactperson: [
          { required: true, message: "联系人不能为空", trigger: "blur" },
          { min: 2, max: 10, message: "联系人为2-10个字符", trigger: "blur" },
        ],
        subject: [
          { required: true, message: "单据主题不能为空", trigger: "change" },
        ],

        // email: [
        //   {required: true, message: "请输入邮箱", trigger: "blur"},
        //   {
        //     pattern: /\w+@qq.com/,
        //     message: "请输入——@qq.com的邮箱格式",
        //     trigger: "blur",
        //   },
        // ],
        // phone: [
        //   {required: true, message: "请输入电话", trigger: "blur"},
        //   {
        //     pattern: /^[1][3,5,7,8][0-9]{9}$/,
        //     message: "请输入1至3、5、7、8开头的电话格式",
        //     trigger: "blur",
        //   },
        // ],
      },
    };
  },
  async mounted() {
    await this.getAllKCAdmin()
    await this.initCgSqOrderList();
    this.CgddOrder.code = await getCurrentTime("CGRK");
    this.initProvider();
    this.initWarHourseList();
    let data = await getPayType();
    this.cgType = data.data;
    console.log(this.cgType);
  },
  methods: {
    async getAllKCAdmin() {
      await this.$axios.get("/warehouse/getAllKcAdmin").then((resp) => {
        this.kcadminlist=resp.data
      });
    },
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
    async initWarHourseList() {
      let resp = await getStoreList(1, 10, "");
      this.warhouseList = resp.data.list;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
    },
    submitForm(formName, type) {
      this.CgddOrder.medicineList = this.bcglXiangXiList;
      if (this.CgddOrder.medicineList.length == 0) {
        Message({
          message: "请选择具体商品批次进入哪个仓库哦!",
          type: "error",
          center: "true",
        });
        return;
      }
      for (const medicineListElement of this.CgddOrder.medicineList) {
        if (
          medicineListElement.fowardWarHouseId == undefined ||
          medicineListElement.fowardWarHouseId == ""
        ) {
          Message({
            message: "还有商品未选择进入哪个仓库哦!",
            type: "error",
            center: "true",
          });
          return;
        }
      }
      if (type == 2) {
        this.CgddOrder.orderStatus = 2;
      }
      console.log("cgdd", this.CgddOrder);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addCgrkOrder(this.CgddOrder).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "添加成功!",
                type: "success",
                center: "true",
              });
              this.$emit("addSuccess");
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
      this.$emit("addSuccess");
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
    getRow(val) {
      this.cgsqListTemp = [];
      this.cgsqListTemp.push(val);
    },
    async getMedicineList() {
      console.log(this.cgsqListTemp.length <= 0);
      this.medicineListTemp = [];
      if (this.cgsqListTemp.length <= 0) {
        Message({
          message: "请选择采购订单",
          type: "error",
          center: "true",
        });
        return;
      }
      console.log("看看我", this.cgsqListTemp);
      if (this.cgsqListTemp[0].voidState == 1) {
        Message({
          message: "该采购订单已被作废",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.cgsqListTemp[0].isPay == 0) {
        Message({
          message: "该采购订单未支付哦!请支付后进行入库操作",
          type: "error",
          center: "true",
        });
        return;
      }
      this.code = this.cgsqListTemp[0].code;
      this.CgddOrder.purchaseId = this.cgsqListTemp[0].id;
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
      console.log("我在这", this.cgsqListTemp);
      if (this.cgsqListTemp.length == 0) {
        Message({
          message: "请选择指定采购订单",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.cgsqListTemp.length >= 0) {
        var data = [];
        for (let index = 0; index < this.cgsqListTemp.length; index++) {
          for (let i = 0; i < this.medicineListTemp.length; i++) {
            var temp = [];
            temp.push(this.medicineListTemp[i]);

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
      this.medicineListTemp = [];
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
        purchasePrice: "",
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
      this.bcglXiangXiList = [];
    },
    // async changeProvider() {
    //   // if (obj.providerId == "") {
    //   //   obj.medicineId = "";
    //   //   obj.specification = "";
    //   //   obj.price = "";
    //   //   obj.totalPrice = "";
    //   //   obj.unitName = "";
    //   // }
    //   // obj.medicineList = [];
    //   // if (obj.providerId != "") {
    //   let resp = await getBaseMedicineListByProviderId(
    //     this.CgddOrder.providerId
    //   );
    //   this.bcglXiangXiList.medicineList = resp.data.data;
    //   // }
    //   console.log(obj.medicineList);
    // },
    async changeMedicine(obj) {
      console.log(obj);
      console.log(this.bcglXiangXiList);

      for (let i = 0; i <= this.bcglXiangXiList.length - 2; i++) {
        if (this.bcglXiangXiList[i].medicineId == obj.medicineId) {
          // alert(i)
          // alert(this.bcglXiangXiList[i].providerId)
          // alert(obj.providerId)
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
      if (this.cgddMedicineionList.length != this.medicineListTemp.length) {
        Message({
          message: "请勾选全部！",
          type: "error",
          center: "true",
        });
        return;
      }
      this.bcglXiangXiList = [];
      if (this.cgddMedicineionList.length > 0) {
        for (let index = 0; index < this.cgddMedicineionList.length; index++) {
          if (this.bcglXiangXiList == undefined) {
            this.bcglXiangXiList = {};
          }

          let resp = await getBaseMedicineListByProviderId(
            this.CgddOrder.providerId
          );
          let obj = {
            medicineList: resp.data.data,
            providerId: this.CgddOrder.providerId,
            medicineId: this.cgddMedicineionList[index].id,
            unitName: this.cgddMedicineionList[index].unitName,
            specification: this.cgddMedicineionList[index].specification,
            price: this.cgddMedicineionList[index].purchasePrice,
            totalPrice: this.cgddMedicineionList[index].totalPrice,
            quantity: this.cgddMedicineionList[index].quantity,
            sourceCode: this.cgddMedicineionList[index].code,
            fowardWarHouseId: "",
            purchasePrice: this.cgddMedicineionList[index].purchasePrice,
          };
          let falg = true;
          for (let i = 0; i < this.bcglXiangXiList.length; i++) {
            if (
              this.bcglXiangXiList[i].medicineId ==
              this.cgddMedicineionList[index].id
            ) {
              this.bcglXiangXiList[i].quantity +=
                this.cgddMedicineionList[index].quantity;
              this.bcglXiangXiList[i].quantity =
                this.bcglXiangXiList[i].quantity;
              falg = false;
              break;
            }
          }

          if (falg) {
            this.bcglXiangXiList.push(obj);
          }
          console.log(this.bcglXiangXiList);
        }
      }
      Message({
        message: "添加成功！",
        type: "success",
        center: "true",
      });
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
        (total, item) => total + item.price * item.quantity,
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