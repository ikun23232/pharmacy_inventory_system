<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="KcDispatch"
      :rules="dispatchRules"
      ref="KcDispatch"
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
                v-model="KcDispatch.code"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据状态" prop="orderStatus">
              <el-select
                disabled
                v-model="KcDispatch.orderStatus"
                placeholder=""
                clearable
                filterable
              >
                <el-option label="未编制" :value="1"></el-option>
                <el-option label="编制完" :value="2"></el-option>
                <el-option label="执行中" :value="3"></el-option>
                <el-option label="执行完" :value="4"></el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="源仓库" prop="beforeWarehouseId">
              <el-select
                v-model="KcDispatch.beforeWarehouseId"
                placeholder="请选择原仓库"
                clearable
                filterable
                disabled
                @change="updateDispatchDetails"
              >
                <el-option
                  v-for="item in storeHouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button
              disabled
              icon="el-icon-plus"
              @click="addDispatchMedicine"
              style="float: left"
            >
              添加
            </el-button>
          </div></el-col
        >
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="调度主题" prop="subject">
              <el-input
                disabled
                type="text"
                v-model="KcDispatch.subject"
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="调度时间" prop="dispatchTime">
              <el-date-picker
                disabled
                v-model="KcDispatch.dispatchTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>
      </el-row>
      <el-tabs v-model="activeName">
        <el-tab-pane label="调度申请单" name="first">
          <el-button
            icon="el-icon-plus"
            disabled
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
            border
            style="width: 1200px"
            @selection-change="handleKcmxMedicineionChange"
          >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="batchCode" label="批次号" width="150" fixed>
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
            <el-table-column prop="stock" label="调度数量" width="120">
            </el-table-column>
            <el-table-column prop="purchasePrice" label="进价" width="120">
            </el-table-column>
            <el-table-column label="总价格" width="120">
              <template slot-scope="scope">
                {{ scope.row.stock * scope.row.purchasePrice }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">
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

            <el-table-column label="批次号" width="250" prop="batchCode">
              <template slot-scope="scope">
                <el-input
                  disabled
                  v-model="bcglXiangXiList[scope.row.xh - 1].batchCode"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="药品"
              align="center"
              prop="name"
              width="150"
            >
              <template slot-scope="scope">
                <el-input
                  disabled
                  v-model="bcglXiangXiList[scope.row.xh - 1].name"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="规格型号"
              align="center"
              prop="specification"
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
              label="调度数量"
              align="center"
              prop="quantity"
              width="150"
            >
              <template slot-scope="scope">
                <el-input-number
                  disabled
                  v-model="bcglXiangXiList[scope.row.xh - 1].quantity"
                  controls-position="right"
                  @change="
                    handleChange(
                      bcglXiangXiList[scope.row.xh - 1].quantity,
                      bcglXiangXiList[scope.row.xh - 1].maxStock
                    )
                  "
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column
              label="进价"
              align="center"
              prop="purchasePrice"
              width="150"
            >
              <template slot-scope="scope">
                <el-input
                  disabled
                  v-model="bcglXiangXiList[scope.row.xh - 1].purchasePrice"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="目标仓库"
              align="center"
              prop="aimStoreHouseId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  disabled
                  clearable
                  @change="changezdts(scope.row)"
                  v-model="bcglXiangXiList[scope.row.xh - 1].aimStoreHouseId"
                >
                  <el-option
                    v-for="dict in storeHouseList"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"
                  />
                </el-select>
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
              disabled
              style="width: 300px"
              type="text"
              v-model="KcDispatch.remark"
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
              style="width: 300px"
              type="text"
              v-model="KcDispatch.approverRemark"
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
              v-model="KcDispatch.approvalStatus"
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
              <el-button type="primary" @click="commit('KcDispatch')"
                >提交</el-button
              >
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
      title="库存明细"
      :visible.sync="kcmxdialog"
      width="1200px"
      v-if="kcmxdialog"
    >
      <wareDetails
        :wareHouseId="KcDispatch.beforeWarehouseId"
        @handleKcmxSuccess="handleKcmxSuccess"
        @cancelKcmx="cancelKcmx"
      ></wareDetails>
    </el-dialog>
  </span>
</template>

<script>
import wareDetails from "./wareDetails.vue";
import { Message } from "element-ui";
import { auditingDispatch, getKcDispatchById } from "./../api/KcDispatch";
import { getKcDetailsList } from "@/api/kcDisparchDetails";
import { getAllStoreHouseList } from "@/api/storeHouse.js";
export default {
  name: "AuditingDispatch",
  components: {
    wareDetails,
  },
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
      KcDispatch: {
        id: this.id,
        remark: "",
        orderStatus: "1",
        code: this.code,
        beforeWarehouseId: "",
        createDate: "",
        subject: "",
        isCommit: "",
        dispatchTime: "",
        medicineList: [],
        approvalStatus: "",
      },
      storeHouseList: [],
      list: {},
      activeName: "first",
      adddialogVisible: false,
      kcmxdialog: false,
      kcmxListTemp: [],
      kcmxList: [],
      medicineListTemp: [],
      changeMedicineList: [],
      providerList: [],
      dispatchRules: {
        beforeWarehouseId: [
          { required: true, message: "请输入源仓库", trigger: "change" },
        ],
        AimWarehouseId: [
          { required: true, message: "请输入目标仓库", trigger: "change" },
        ],
        subject: [
          { required: true, message: "请输入调度主题", trigger: "blur" },
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
    let data = await getAllStoreHouseList();
    console.log("data:", data);
    this.storeHouseList = data.data;
    let dispatch = await getKcDispatchById(this.id);
    this.KcDispatch = dispatch.data;
    this.KcDispatch.approvalStatus = "";
    let kcDetailsList = await getKcDetailsList(this.KcDispatch.code);
    this.medicineListTemp = kcDetailsList.data;
    await this.getMedicineListDetail();
  },
  methods: {
    commit(formName) {
      this.KcDispatch.isCommit = 1;
      this.submitForm(formName);
    },
    save(formName) {
      this.KcDispatch.isCommit = 2;
      this.submitForm(formName);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          for (
            let index = 0;
            index < this.KcDispatch.medicineList.length;
            index++
          ) {
            const element = this.KcDispatch.medicineList[index];
            if (element.aimStoreHouseId == "" || element.aimStoreHouseId == 0) {
              Message({
                message: "请添加目标仓库!",
                type: "error",
                center: "true",
              });
              return;
            }
          }
          auditingDispatch(this.KcDispatch).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "审批成功!",
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
      var data = this.KcDispatch.code;
      this.$refs[formName].resetFields();
      this.KcDispatch.code = data;
    },
    cancel() {
      this.$emit("cancelAuditing");
    },
    handleKcmxMedicineionChange(val) {
      this.changeMedicineList = val;
    },
    deleteMedicine() {
      const newData = this.medicineListTemp.filter(
        (item) => !this.changeMedicineList.includes(item)
      );
      this.medicineListTemp = newData;
      this.changeMedicineList = [];
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
      if (this.medicineListTemp.length == 0) {
        Message({
          message: "请选择药品！",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.medicineListTemp.length > 0) {
        for (let index = 0; index < this.medicineListTemp.length; index++) {
          if (this.bcglXiangXiList == undefined) {
            this.bcglXiangXiList = new Array();
          }
          if (this.medicineListTemp[index].aimStoreHouseId == 0) {
            this.medicineListTemp[index].aimStoreHouseId = "";
          }
          let obj = {
            aimStoreHouseList: this.storeHouseList,
            batchCode: this.medicineListTemp[index].batchCode,
            name: this.medicineListTemp[index].name,
            medicineId: this.medicineListTemp[index].medicineId,
            unitId: this.medicineListTemp[index].unitId,
            unitName: this.medicineListTemp[index].unitName,
            specification: this.medicineListTemp[index].specification,
            purchasePrice: this.medicineListTemp[index].purchasePrice,
            aimStoreHouseId: this.medicineListTemp[index].aimStoreHouseId,
            quantity: this.medicineListTemp[index].stock,
            maxStock: this.medicineListTemp[index].stock,
            providerId: this.medicineListTemp[index].providerId,
            isRight: true,
          };
          obj.dkdd = "1";
          obj.sjfw = ["07:00", "07:30"];
          obj.jxsjfw = ["06:00", "12:00"];
          this.bcglXiangXiList.push(obj);
        }
        this.KcDispatch.medicineList = this.bcglXiangXiList;
      }
    },
    addDispatchMedicine() {
      if (
        this.KcDispatch.beforeWarehouseId <= 0 ||
        this.KcDispatch.beforeWarehouseId == ""
      ) {
        Message({
          message: "请选择原仓库！",
          type: "error",
          center: "true",
        });
        return;
      }
      this.kcmxdialog = true;
    },
    handleKcmxSuccess(list) {
      this.kcmxdialog = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      // this.getOrderList(1, 5);
      this.medicineListTemp = list;
    },
    cancelKcmx() {
      this.kcmxdialog = false;
    },
    handleChange(val, maxVal) {
      if (val > maxVal) {
        this.$message({
          message: "最大值不超过" + maxVal,
          type: "error",
        });
      }
    },
    updateDispatchDetails() {
      this.medicineListTemp = [];
      this.bcglXiangXiList = [];
    },
  },
  computed: {
    totalPrice() {
      if (this.bcglXiangXiList === undefined) {
        return 0;
      }
      return this.bcglXiangXiList.reduce((total, element) => {
        return total + element.quantity * element.purchasePrice;
      }, 0);
    },
    calculatedTotalPrice() {
      return (row) => {
        row.totalPrice = row.quantity * row.purchasePrice;
        return row.quantity * row.purchasePrice;
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