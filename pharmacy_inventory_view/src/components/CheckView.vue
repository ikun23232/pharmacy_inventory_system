<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="StoreCheck"
      :rules="rules"
      ref="StoreCheck"
      label-width="100px"
      class="demo-ruleForm"
      disabled
    >
      <el-row :gutter="20">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input
                type="text"
                v-model="StoreCheck.code"
                disabled
              ></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据日期" prop="billDate">
              <el-date-picker
                v-model="StoreCheck.billDate"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                :disabled="true"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="执行阶段" prop="orderStatusName">
              <el-input
                type="text"
                v-model="StoreCheck.orderStatusName"
                :disabled="true"
              ></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="生效日期" prop="effectiveTime">
              <el-date-picker
                v-model="StoreCheck.effectiveTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                :disabled="true"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="核批人" prop="approverByName">
              <el-input
                type="text"
                v-model="StoreCheck.approverByName"
                :disabled="true"
              ></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="制单人" prop="createByName">
              <el-input
                type="text"
                v-model="StoreCheck.createByName"
                :disabled="true"
              ></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="修改日期" prop="updateTime">
              <el-date-picker
                v-model="StoreCheck.updateTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                :disabled="true"
              >
              </el-date-picker>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="修改人" prop="updateByName">
              <el-input
                type="text"
                v-model="StoreCheck.updateByName"
                :disabled="true"
              ></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据主题" prop="subject">
              <el-input type="text" v-model="StoreCheck.subject"></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="盘点人" prop="checkerBy">
              <el-select
                v-model="StoreCheck.checkerBy"
                placeholder="请选择盘点人"
                clearable
                filterable
              >
                <el-option
                  v-for="item in Useroptions"
                  :key="item.userid"
                  :label="item.username"
                  :value="item.userid"
                >
                </el-option>
              </el-select>
            </el-form-item></div
        ></el-col>

        <el-col :span="24">
          <div style="text-align: center; margin-top: 10px; position: relative">
            <div
              style="
                display: inline-block;
                width: 50%;
                height: 1px;
                background-color: #ccc;
              "
            ></div>
            <strong
              style="
                position: absolute;
                top: -10px;
                background-color: #fff;
                padding: 0 10px;
                color: #333;
              "
              >盘点范围</strong
            >
            <div
              style="
                display: inline-block;
                width: 50%;
                height: 1px;
                background-color: #ccc;
              "
            ></div>
          </div>
        </el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="仓库" prop="warehouseId">
              <el-select
                v-model="StoreCheck.warehouseId"
                placeholder="请选择仓库"
                :disabled="true"
              >
                <el-option
                  v-for="item in warehouseList"
                  :label="item.name"
                  :value="item.id"
                  :key="item.id"
                ></el-option>
              </el-select>
            </el-form-item></div
        ></el-col>

        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="医药商品选择">
              <el-cascader
                :options="medicineoptions"
                placeholder="请选择"
                v-model="StoreCheck.materialCategoryId"
                @change="onChange($event)"
                :disabled="true"
              ></el-cascader>
            </el-form-item></div
        ></el-col>
      </el-row>

      <el-tabs v-model="activeName">
        <el-tab-pane label="明细" name="first">
          <el-table
            :data="bcglXiangXiList"
            :row-class-name="rowClassName"
            @selection-change="chandleDetailSelectionChange"
            ref="tb"
            show-summary
            v-loading="loading"
          >
            <el-table-column label="序号" align="center" prop="xh" width="50">
            </el-table-column>

            <el-table-column label="仓库" width="250" prop="warehouseName">
            </el-table-column>

            <el-table-column
              label="医用商品名称"
              align="center"
              prop="medecineName"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="供应商"
              align="center"
              prop="providername"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="单位"
              align="center"
              prop="unitName"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="账存数量"
              align="center"
              prop="totalQuantity"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="实存数量"
              align="center"
              prop="exactCount"
              width="150"
            >
              <template slot-scope="scope">
                <el-input
                  v-model="scope.row.exactCount"
                  placeholder="请输入数量"
                ></el-input>
              </template>
            </el-table-column>

            <el-table-column
              label="盘盈数量"
              align="center"
              prop="profitCount"
              width="150"
            >
              <template slot-scope="scope">
                {{ getProfitQuantity(scope.row) }}
              </template>
            </el-table-column>

            <el-table-column
              label="盘亏数量"
              align="center"
              prop="lossCount"
              width="150"
            >
              <template slot-scope="scope">
                {{ getLossQuantity(scope.row) }}
              </template>
            </el-table-column>

            <el-table-column
              label="备注"
              align="center"
              prop="mark"
              width="150"
            >
              <template slot-scope="scope">
                <el-input v-model="scope.row.mark"></el-input>
              </template>
            </el-table-column>
          </el-table>

          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
        </el-tab-pane>
        <el-tab-pane label="账外物料">
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
            @click="handleDeleteDetails()"
            >删除</el-button
          >
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="handleDeleteAllDetails()"
            >清空</el-button
          >

          <el-table
            :data="bcglXiangXiList1"
            :row-class-name="rowClassName"
            @selection-change="chandleDetailSelectionChange"
            ref="tb"
            show-summary
            v-loading="loading"
          >
            <el-table-column type="selection" width="30" align="center" />
            <el-table-column
              label="序号"
              align="center"
              prop="xh"
              width="50"
            ></el-table-column>

            <el-table-column label="仓库" width="250" prop="warehouseName">
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.storehouseId"
                  placeholder="请选择仓库"
                  @change="onMedicineChange(scope.row)"
                >
                  <el-option
                    v-for="item in warehouseList"
                    :label="item.name"
                    :value="item.id"
                    :key="item.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>

            <el-table-column
              label="医用商品名称"
              align="center"
              prop="medecineName"
              width="150"
            >
              <template slot-scope="scope">
                <el-cascader
                  :options="medicineoptions"
                  placeholder="请选择"
                  v-model="scope.row.medicineId"
                  @change="onChange($event, scope.row)"
                ></el-cascader>
              </template>
            </el-table-column>

            <el-table-column
              label="供应商"
              align="center"
              prop="providerId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.providername"
                  placeholder="请选择供应商"
                >
                  <el-option
                    v-for="item in providerList"
                    :label="item.name"
                    :value="item.id"
                    :key="item.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>

            <el-table-column
              label="单位"
              align="center"
              prop="unitName"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="账存数量"
              align="center"
              prop="totalQuantity"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="实存数量"
              align="center"
              prop="exactCount"
              width="150"
            >
              <template slot-scope="scope">
                <el-input
                  v-model="scope.row.exactCount"
                  placeholder="请输入数量"
                ></el-input>
              </template>
            </el-table-column>

            <el-table-column
              label="盘盈数量"
              align="center"
              prop="profitCount"
              width="150"
            >
              <template slot-scope="scope">
                {{ getProfitQuantity(scope.row) }}
              </template>
            </el-table-column>

            <el-table-column
              label="盘亏数量"
              align="center"
              prop="lossCount"
              width="150"
            >
              <template slot-scope="scope">
                {{ getLossQuantity(scope.row) }}
              </template>
            </el-table-column>

            <el-table-column
              label="备注"
              align="center"
              prop="mark"
              width="150"
            >
              <template slot-scope="scope">
                <el-input v-model="scope.row.mark"></el-input>
              </template>
            </el-table-column>
          </el-table>
          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
        </el-tab-pane>
      </el-tabs>
      <div style="text-align: left">
        <span>备注:</span
        ><el-input
          class=""
          v-model="StoreCheck.remark"
          placeholder="请输入备注"
          style="width: 1000px"
        ></el-input>
      </div>
      <el-divider></el-divider>
      <div style="text-align: left">
        <div class="demo-input-suffix">
          核批意见:
          <el-input
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="StoreCheck.approverRemark"
            style="width: 1000px"
            disabled
          >
          </el-input>
        </div>

        <div style="margin-top: 20px; margin-bottom: 25px">
          核批结果:
          <el-select
            v-model="StoreCheck.isApproved"
            placeholder="请选择"
            disabled
          >
            <el-option
              v-if="StoreCheck.isApproved === 0"
              :label="'未审核'"
              :value="0"
            >
            </el-option>
            <el-option
              v-if="StoreCheck.isApproved === 2"
              :label="'通过'"
              :value="2"
            >
            </el-option>
            <el-option
              v-if="StoreCheck.isApproved === 1"
              :label="'未通过'"
              :value="1"
            >
            </el-option>
          </el-select>
        </div>
      </div>
    </el-form>
    <el-button
      class="anniu"
      type="primary"
      @click="cancel()"
      style="position: relative; top: -20px; left: 1120px"
      >关 闭</el-button
    >
  </span>
</template>
      
      <script>
//   import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
export default {
  name: "addCheck",
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      loading: true,
      selectedItems: [],
      kid: null,
      providerList: [],
      Useroptions: [],
      medicineoptions: [],
      selectedProduct: [],
      warehouseList: [],
      bcglXiangXiList: [],
      bcglXiangXiList1: [],
      //选中的从表数据
      checkedDetail: [],
      StoreCheck: {
        id: null,
        code: "",
        billDate: "",
        orderStatusName: "",
        effectiveTime: "",
        approverByName: "",
        createByName: "",
        updateTime: "",
        updateByName: "",
        warehouseId: null,
        materialCategoryId: null,
        subject: "",
        checkerBy: "",
        remark: "",
        approveCommend: "",
        approvement: "",
        kcMedicineList: [],
        kcInventorydetailList: [],
        exactCount: null,
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
      checkdialog: false,
      rules: {
        // subject: [
        //   { required: true, message: "请输入主题名称", trigger: "blur" },
        //   { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        // ],
        // type: [{ required: true, message: "请选择采购类型", trigger: "blur" }],
        // warehouseId: [
        //   { required: true, message: "仓库面积不能为空", trigger: "blur" },
        // ],
      },
    };
  },
  async created() {
    // 在created生命周期钩子中将props中的id值赋给StoreCheck对象中的id属性
    this.kid = this.id;
  },
  async mounted() {
    this.initStoreHouse();
    this.getAllMedicine();
    this.initCheckUser();
    this.initInventoryDetail();
    this.initkk();
    this.loading = false;
  },
  methods: {
    onMedicineChange(row) {
      console.log("mmsadasdasdasdsadsa");
      console.log(row);
      this.selectedItems = [];

      this.selectedItems.push({
        storehouseId: row.storehouseId,
        medicineId: row.medicineId,
      });

      this.initallProvider(row.storehouseId, row.medicineId);
      this.fetchData(row.xh);
    },
    async fetchData(xh) {
      console.log(this.selectedItems);
      await this.$axios
        .get("warehouse/check/getKcInventoryUnitName", {
          params: {
            warehouseId: this.selectedItems[0].storehouseId,
            medecineId: this.selectedItems[0].medicineId,
          },
        })
        .then((response) => {
          // console.log( this.bcglXiangXiList1);

          this.bcglXiangXiList1[xh - 1].unitName = response.data.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    onChange(value, row) {
      console.log(value[2]);
      this.StoreCheck.materialCategoryId = value[2];
      this.bcglXiangXiList1[row.xh - 1].medicineId = value[2];
      this.onMedicineChange(row);
    },
    async getAllMedicine() {
      await this.$axios.get("/base/getTreeMedicine").then((resp) => {
        this.medicineoptions = resp.data.data.map((item) => ({
          value: item.id,
          label: item.name,
          children: item.children
            ? item.children.map((child) => ({
                value: child.id,
                label: child.name,
                children: child.medicine
                  ? child.medicine.map((grandchild) => ({
                      value: grandchild.id,
                      label: grandchild.name,
                    }))
                  : [],
              }))
            : [],
        }));
      });
    },
    async initCheckUser() {
      await this.$axios.get("/user/getAllUser").then((resp) => {
        this.Useroptions = resp.data.data;
      });
    },
    async initallProvider(value1, value2) {
      console.log(value1, value2);

      await this.$axios
        .get("/base/getProviderByWareAndMe", {
          params: { warehouseId: value1, medecineId: value2 },
        })
        .then((resp) => {
          console.log(resp.data);
          this.providerList = resp.data.data;
        });
    },
    async initStoreHouse() {
      await this.$axios.get("/base/getAllStoreHouseList").then((resp) => {
        this.warehouseList = resp.data.data;
      });
    },
    async initkk() {
      await this.$axios
        .get("/warehouse/check/getKcInventoryDetailByIdwithother", {
          params: { id: this.kid },
        })
        .then((resp) => {
          this.bcglXiangXiList1 = resp.data.data;
        });
    },
    async initInventoryDetail() {
      await this.$axios
        .get("/warehouse/check/getKcInventoryVoById", {
          params: { id: this.kid },
        })
        .then((resp) => {
          console.log("oooooooooooooooooooooo");
          console.log(this.StoreCheck);
          this.StoreCheck = resp.data.data;
          this.bcglXiangXiList = this.StoreCheck.kcInventorydetailList;
        });
    },

    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.bcglXiangXiList = [
            ...this.bcglXiangXiList,
            ...this.bcglXiangXiList1,
          ];
          console.log(this.bcglXiangXiList);
          this.StoreCheck.kcInventorydetailList = this.bcglXiangXiList;
          console.log(this.StoreCheck);
          this.$axios
            .post("/warehouse/check/updateCheck", this.StoreCheck, {
              headers: {
                "Content-Type": "application/json",
              },
            })
            .then((resp) => {
              if (resp.status === 200) {
                if (resp.data.code === "200") {
                  this.$message({
                    message: "添加成功!",
                    type: "success",
                    center: true,
                  });
                  this.$emit("closeUpdateDiago");
                } else {
                  this.$message({
                    message: "添加失败!",
                    type: "error",
                    center: true,
                  });
                }
              } else {
                console.log("Response status is not 200");
              }
            })
            .catch((error) => {
              console.error("Error in sending request:", error);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async tosumbit() {
      this.$axios
        .get("/warehouse/check/updateorderStatus", {
          params: { id: this.kid },
        })
        .then((resp) => {
          if (resp.data.code === "200") {
            this.$message({
              message: "提交成功!",
              type: "success",
              center: true,
            });
            this.$emit("closeUpdateDiago");
          } else {
            this.$message({
              message: "提交失败!",
              type: "error",
              center: true,
            });
          }
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    cancel() {
      this.$emit("closeviewOrder");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    getMedicineList() {},
    rowClassName({ row, rowIndex }) {
      row.xh = rowIndex + 1;
    },
    chandleDetailSelectionChange(selection) {
      this.checkedDetail = selection;
    },

    getProfitQuantity(row) {
      if (row.exactCount > row.totalQuantity) {
        return row.exactCount - row.totalQuantity;
      } else {
        return 0;
      }
    },
    getLossQuantity(row) {
      console.log(row);
      if (row.exactCount === null || row.exactCount === undefined) {
        return 0;
      }
      if (row.exactCount < row.totalQuantity) {
        return row.totalQuantity - row.exactCount;
      } else {
        return 0;
      }
    },
    handleAddDetails() {
      this.bcglXiangXiList1.push({
        xh: this.bcglXiangXiList1.length + 1,
        warehouseName: "",
        storehouseId: null,
        medecineName: "",
        medicineId: null,
        providername: "",
        providerId: null,
        unitName: null,
        quantity: null,
        totalQuantity: null,
        exactCount: null,
        profitCount: null,
        lossCount: null,
        remark: "",
        editing: true, // 标记该行数据处于编辑状态
      });
    },
    handleDeleteDetails() {
      if (this.checkedDetail.length == 0) {
        this.$alert("请先选择要删除的数据", "提示", {
          confirmButtonText: "确定",
        });
      } else {
        this.bcglXiangXiList1.splice(this.checkedDetail[0].xh - 1, 1);
      }
    },
    handleDeleteAllDetails() {
      this.bcglXiangXiList1 = [];
    },
  },
};
</script>
      
      <style>
.anniu {
  float: left;
}
</style>