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
                <el-option label="未编制" value="1"></el-option>
                <el-option label="编制完" value="2"></el-option>
                <el-option label="执行中" value="3"></el-option>
                <el-option label="执行完" value="4"></el-option>
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
              icon="el-icon-plus"
              @click="addDispatchMedicine"
              style="float: left">
                添加
              </el-button>
          </div></el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="调度主题" prop="subject">
              <el-input type="text" v-model="KcDispatch.subject"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="目标仓库" prop="AimWarehouseId">
              <el-select
                v-model="KcDispatch.AimWarehouseId"
                filterable
                placeholder="请选择目标仓库"
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
      </el-row>
      <el-tabs v-model="activeName">
        <el-tab-pane label="采购申请单" name="first">
          <el-button
            icon="el-icon-plus"
            :disabled="KcDispatch.medicineList.length == 0"
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
          <el-table
            :data="medicineListTemp"
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
            <el-table-column prop="purchasePrice" label="采购价" width="120">
            </el-table-column>
            <el-table-column prop="totalPrice" label="总价格" width="120">
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="明细" name="second">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAddDetails"
            >添加</el-button
          >
          <el-button
            type="success"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteDetails"
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
          <el-form-item label="备注 " style="margin-top: 10px" prop="remark">
            <el-input
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
              disabled
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
              disabled
              v-model="KcDispatch.approvalStatus"
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
              <el-button type="primary" @click="submitForm('KcDispatch')"
                >立即添加</el-button
              >
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button s @click="resetForm('KcDispatch')">重置</el-button>
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
        @handleKcmxSuccess="handleKcmxSuccess"
        @kcmxdialog="kcmxdialog"
     ></wareDetails>
    </el-dialog>
  </span>
</template>

<script>
import wareDetails from './wareDetails.vue'
import { Message } from "element-ui";
import { initCgSqOrderList } from "@/api/CgsdOrder";
import { getMedicineListByCode } from "@/api/baseMedicine";
import { getCurrentTime } from "./../api/util.js";
import { addKcDispatch } from "./../api/procurementOrder.js";
import { getBaseMedicineListByProviderId } from "@/api/baseMedicine";
import { getAllStoreHouseList } from "@/api/storeHouse.js";
export default {
  name: "addDispatch",
  components:{
    wareDetails
  },
  data() {
    return {
      bcglXiangXiList: [],
      KcDispatch: {
        remark: "",
        orderStatus: "1",
        code: "",
        beforeWarehouseId: "",
        AimWarehouseId: "",
        createTime: new Date(),
        subject: "",
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
      dispatchMedicineionList: [],
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
      },
    };
  },
  async mounted() {
    // await this.initCgSqOrderList();
    this.KcDispatch.code = await getCurrentTime("KCDD");
    let data = await getAllStoreHouseList();
    console.log("data:", data);
    this.storeHouseList = data.data;
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
          addKcDispatch(this.KcDispatch).then((resp) => {
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
      var data = this.KcDispatch.code;
      this.$refs[formName].resetFields();
      this.KcDispatch.code = data;
    },
    cancel() {
      this.$emit("cancel");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleCgsqSelectionChange(val) {
      this.kcmxListTemp = val;
      console.log("kcmxList", this.kcmxList.length);
    },
    handleCgsqMedicineionChange(val) {
      this.dispatchMedicineionList = val;
    },
    handleCgsqChange(val) {
      this.kcmxListTemp = val;
    },
    async getMedicineList() {
      console.log(this.kcmxListTemp.length <= 0);
      if (this.kcmxListTemp.length <= 0) {
        Message({
          message: "请选择采购申请单",
          type: "error",
          center: "true",
        });
        return;
      }
      console.log(this.kcmxListTemp.length);
      for (let index = 0; index < this.kcmxListTemp.length; index++) {
        const kcmx = this.kcmxListTemp[index];
        console.log("kcmx", kcmx.code);
        var data = await getMedicineListByCode(kcmx.code);
        console.log("data:", data);
        for (let i = 0; i < data.data.length; i++) {
          if (data.data[i].providerId == this.KcDispatch.providerId) {
            this.medicineListTemp.push(data.data[i]);
          }
        }
        console.log("medicineListTemp:", this.medicineListTemp);
      }
      this.kcmxdialog = false;
      this.kcmxList = this.kcmxListTemp;
      this.kcmxListTemp = [];
      this.initCgSqOrderList();
    },
    deleteMedicine() {
      const newData = this.medicineListTemp.filter(
        (item) => !this.changeMedicineList.includes(item)
      );
      this.medicineListTemp = newData;
      this.changeMedicineList = [];
    },
    async handleAddDetails() {
      if (this.KcDispatch.providerId == 0 || this.KcDispatch.providerId == "") {
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
        this.KcDispatch.providerId
      );
      let obj = {
        // providerList: [],
        medicineList: resp.data.data,
        providerId: this.KcDispatch.providerId,
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
      this.KcDispatch.medicineList = this.bcglXiangXiList;
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
    //     this.KcDispatch.providerId
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
      if (this.dispatchMedicineionList.length == 0) {
        Message({
          message: "请选择药品！",
          type: "error",
          center: "true",
        });
        return;
      }
      if (this.dispatchMedicineionList.length > 0) {
        for (
          let index = 0;
          index < this.dispatchMedicineionList.length;
          index++
        ) {
          if (this.bcglXiangXiList == undefined) {
            this.bcglXiangXiList = new Array();
          }
          let resp = await getBaseMedicineListByProviderId(
            this.KcDispatch.providerId
          );
          console.log(
            "dispatchMedicineionList:" + this.dispatchMedicineionList[index]
          );
          let obj = {
            medicineList: resp.data.data,
            providerId: this.KcDispatch.providerId,
            medicineId: this.dispatchMedicineionList[index].id,
            unitName: this.dispatchMedicineionList[index].unitName,
            specification: this.dispatchMedicineionList[index].specification,
            price: this.dispatchMedicineionList[index].purchasePrice,
            totalPrice: this.dispatchMedicineionList[index].totalPrice,
            quantity: this.dispatchMedicineionList[index].quantity,
            sourceCode: this.dispatchMedicineionList[index].code,
          };
          obj.dkdd = "1";
          obj.sjfw = ["07:00", "07:30"];
          obj.jxsjfw = ["06:00", "12:00"];
          this.bcglXiangXiList.push(obj);
        }
        this.KcDispatch.medicineList = this.bcglXiangXiList;
      }
    },
    addDispatchMedicine(){

    },
    handleKcmxSuccess() {
      this.kcmxdialog = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getOrderList(1, 5);
    },
    cancelKcmx() {
      this.kcmxdialog = false; 
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