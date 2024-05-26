<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="StoreCheck"
      :rules="rules"
      ref="StoreCheck"
      label-width="100px"
      class="demo-ruleForm"
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
                v-model="billDate"
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
              ></el-cascader>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-button type="primary" @click="initcheckShop">确认</el-button>
          </div></el-col
        >
      </el-row>

      <el-tabs v-model="activeName">
        <el-tab-pane label="明细" name="first">
          <el-table
            :data="bcglXiangXiList"
            :row-class-name="rowClassName"
            @selection-change="chandleDetailSelectionChange"
            ref="tb"
            show-summary
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
            </el-table-column>

            <el-table-column
              label="盘盈数量"
              align="center"
              prop="profitCount"
              width="150"
            >
            </el-table-column>
            <el-table-column
              label="盘亏数量"
              align="center"
              prop="lossCount"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="备注"
              align="center"
              prop="remark"
              width="150"
            >
            </el-table-column>
          </el-table>

          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          <div style="text-align: left">
            <span>备注:</span
            ><el-input
              class=""
              v-model="StoreCheck.remark"
              placeholder="请输入备注"
              style="width: 1000px"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="账外物料">
          <!-- <el-button
            icon="el-icon-plus"
            @click="checkdialog = true"
            style="float: left"
            >申请单</el-button
          > -->
          <el-table>
            <el-table-column
              label="序号"
              align="center"
              prop="xh"
              width="50"
            ></el-table-column>

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
            </el-table-column>

            <el-table-column
              label="盘盈数量"
              align="center"
              prop="profitCount"
              width="150"
            >
            </el-table-column>
            <el-table-column
              label="盘亏数量"
              align="center"
              prop="lossCount"
              width="150"
            >
            </el-table-column>

            <el-table-column
              label="备注"
              align="center"
              prop="remark"
              width="150"
            >
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <el-divider></el-divider>
      <div style="text-align: left">
        <div class="demo-input-suffix">
          核批意见:
          <el-input
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="StoreCheck.approveCommend"
            style="width: 1000px"
            disabled
          >
          </el-input>
        </div>

        <div style="margin-top: 20px; margin-bottom: 25px">
          核批结果:<el-select
            v-model="StoreCheck.approvement"
            placeholder="请选择"
            disabled
          >
            <el-option label="未通过" value="0"> </el-option>
            <el-option label="通过" value="1"> </el-option>
          </el-select>
        </div>
      </div>

      <el-form-item style="width: 500px">
        <el-button class="anniu" type="primary" @click="cancel()"
          >取 消</el-button
        >

        <el-button class="anniu" @click="submitForm('StoreCheck')"
          >提 交</el-button
        >
      </el-form-item>
    </el-form>
  </span>
</template>
  
  <script>
//   import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
import { getCurrentTime } from "./../api/util.js";
export default {
  name: "addCheck",
  data() {
    return {
      Useroptions: [],
      billDate: new Date(),
      medicineoptions: [],
      selectedProduct: [],
      warehouseList: [],
      bcglXiangXiList: [],
      //选中的从表数据
      checkedDetail: [],
      StoreCheck: {
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
  async mounted() {
    await this.initStoreHouse();
    await this.getAllMedicine();
    await this.initCheckUser();
    this.StoreCheck.code = await getCurrentTime("KCPD");
  },
  methods: {
    onChange(value) {
      console.log(value);
      if (value && value.length >= 3) {
        console.log(value[2]);
        this.StoreCheck.materialCategoryId = value[2];
      } else {
        this.StoreCheck.materialCategoryId = 0; // 默认值或者处理其他逻辑
      }
    },
    async getAllMedicine() {
      await this.$axios.get("/base/getTreeMedicine").then((resp) => {
        const mappedData = resp.data.map((item) => ({
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

        this.medicineoptions = [{ value: 0, label: "请选择" }, ...mappedData];
      });
    },
    async initCheckUser() {
      await this.$axios.get("/user/getAllUser").then((resp) => {
        console.log("2312321312312llllllllll");
        console.log(resp.data);
        this.Useroptions = resp.data;
      });
    },
    async initStoreHouse() {
      await this.$axios.get("/base/getAllStoreHouseList").then((resp) => {
        this.warehouseList = resp.data;
      });
    },
    async initcheckShop() {
      await this.$axios
        .get("/warehouse/check/getKcMedicineByMedicine", {
          params: {
            warehouseId: this.StoreCheck.warehouseId,
            materialCategoryId: this.StoreCheck.materialCategoryId,
          },
        })
        .then((resp) => {
          console.log(this.bcglXiangXiList);

          this.bcglXiangXiList = resp.data;
        });
    },

    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.StoreCheck.kcMedicineList = this.bcglXiangXiList;
          this.$axios
            .post("/warehouse/check/addCheck", this.StoreCheck, {
              headers: {
                "Content-Type": "application/json",
              },
            })
            .then((resp) => {
              if (resp.status === 200) {
                if (resp.code === "200") {
                  this.$message({
                    message: "添加成功!",
                    type: "success",
                    center: true,
                  });
                  this.$emit("addSuccess");
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    cancel() {
      this.$emit("addSuccess");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
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

    cacltotalPrice(row) {
      alert(row);
    },
  },
  computed: {
    totalPrice() {
      // 使用 reduce 方法计算总价
      if (this.bcglXiangXiList == undefined) {
        return 0;
      }
      return this.bcglXiangXiList.reduce(
        (total, item) => total + item.totalPrice * item.totalQuantity,
        0
      );
    },
    calculatedTotalPrice() {
      return (row) => {
        row.totalPrice = row.totalQuantity * row.price;
        return row.totalQuantity * row.price;
      };
    },
  },
};
</script>
  
  <style>
.anniu {
  float: left;
}
</style>