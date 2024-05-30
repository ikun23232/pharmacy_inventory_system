<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="CgsqOrder"
      :rules="rules"
      ref="CgsqOrder"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-row :gutter="20">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据编号" prop="code">
              <el-input
                type="text"
                v-model="CgsqOrder.code"
                disabled
              ></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据日期">
              <el-input
                type="text"
                v-model="CgsqOrder.createtime"
                disabled
              ></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="单据主题" prop="subject">
              <el-input type="text" v-model="CgsqOrder.subject"></el-input>
            </el-form-item></div
        ></el-col>

        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="采购类型" prop="type">
              <el-select
                v-model="CgsqOrder.type"
                placeholder="请选择采购类型"
                clearable
                filterable
              >
                <el-option
                  v-for="item in cgTypeList"
                  :label="item.name"
                  :value="item.id"
                  :key="item.id"
                ></el-option>
              </el-select>
            </el-form-item></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <el-form-item label="需求人" prop="demainerId">
              <el-select
                v-model="CgsqOrder.demainerId"
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
            show-summary
            :summary-method="
              (param) => getSummaries(param, ['quantity', 'totalPrice'])"
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

            <el-table-column label="建议供应商" width="250" prop="providerId">
              <template slot-scope="scope">
                <el-select
                  clearable
                  filterable
                  @change="handleSelectChange(scope.row)"
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
            </el-table-column>

            <el-table-column
              label="药品"
              align="center"
              prop="medicineId"
              width="150"
            >
              <template slot-scope="scope">
                <el-select
                  clearable
                  filterable
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
                  v-model="bcglXiangXiList[scope.row.xh - 1].unitName"
                  disabled
                >
                </el-select>
              </template>
            </el-table-column>
            <el-table-column
              label="数量"
              align="center"
              prop="quantity"
              width="150"
            >
              <template slot-scope="scope">
                <el-input-number
                  v-model="bcglXiangXiList[scope.row.xh - 1].quantity"
                  controls-position="right"
                  @change="handleChange"
                  :min="1"
                  :max="100"
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
                  v-model="bcglXiangXiList[scope.row.xh - 1].price"
                  disabled
                >
                </el-select>
              </template>
            </el-table-column>
            <el-table-column
              label="参考总价"
              align="center"
              prop="totalPrice"
              width="150"
            >
              <template slot-scope="scope" @change="cacltotalPrice(scope.row)">
                {{ calculatedTotalPrice(scope.row) }}
              </template>
            </el-table-column>
          </el-table>

          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          <div style="text-align: left">
            <span>备注:</span
            ><el-input
              class=""
              v-model="CgsqOrder.remark"
              placeholder="请输入备注"
              style="width: 1000px"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="采购申请单">
          <el-button
            icon="el-icon-plus"
            @click="cgsqdialog = true"
            style="float: left"
            >申请单</el-button
          >
        </el-tab-pane>
      </el-tabs>
      <el-divider></el-divider>

      <div style="text-align: left">
        <div class="demo-input-suffix">
          核批意见:
          <el-input
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="CgsqOrder.approverremark"
            style="width: 1000px"
            disabled
          >
          </el-input>
        </div>

        <div style="margin-top: 20px; margin-bottom: 25px">
          核批结果:<el-select
            v-model="CgsqOrder.approvalstatus"
            placeholder="请选择"
            disabled
          >
            <el-option label="未审批" :value="0"> </el-option>
            <el-option label="未通过" :value="1"> </el-option>
            <el-option label="通过" :value="2"> </el-option>
          </el-select>
        </div>
      </div>

      <el-form-item style="width: 500px">
        <el-button class="anniu" type="primary" @click="cancel()"
          >取 消</el-button
        >

        <el-button class="anniu" s @click="submitForm('CgsqOrder')"
          >保 存</el-button
        >
        <el-button class="anniu" @click="submitForm('CgsqOrder', 1)"
          >提 交</el-button
        >
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
            <el-input v-model="vo.code" placeholder="请输入单据编号"></el-input>
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

          <el-form-item>
            <el-button type="primary" @click="initCgSqOrderList()"
              >查询</el-button
            >
          </el-form-item>
        </el-form>
      </p>
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
            <el-button @click="cancel">取消</el-button>
          </el-col>
        </el-row>
      </div>
    </el-dialog>
  </span>
</template>

<script>
// import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { getPayType } from "@/api/public";
import { getBaseMedicineListByProviderId } from "@/api/baseMedicine";
import { init } from "../api/BaseProvider.js";
import { Message } from "element-ui";
import { updateCgsqOrder, getCgsqOrderById } from "@/api/CgsdOrder";

export default {
  name: "addProcOrder",
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      bcglXiangXiList: [],
      //选中的从表数据
      checkedDetail: [],
      CgsqOrder: {
        code: "",
        phone: "",
        contactperson: "",
        providerId: 1,
        deliveryDate: "",
        payType: "",
        type: "",
        subject: "",
        createTime: "",
        remark: "",
        approvement: "",
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
        voidState: "",
      },
      list: {},
      falg: false,
      activeName: "first",
      adddialogVisible: false,
      cgsqdialog: false,
      cgsqList: [],
      providerList: [],
      cgTypeList: [],
      rules: {
        subject: [
          { required: true, message: "请输入采购主题", trigger: "blur" },
          {
            min: 4,
            max: 12,
            message: "长度在 4 到 12 个字符",
            trigger: "blur",
          },
        ],
        type: [
          { required: true, message: "请选择采购类型", trigger: "change" },
        ],
      },
      loading: true,
    };
  },

  async mounted() {
    await this.initCgSqOrder(this.id);
    this.initProvider();
    this.initCgType();
    this.loading = false;
  },
  methods: {
    async initCgSqOrder(id) {
      let resp = await getCgsqOrderById(id);
      this.CgsqOrder = resp.data;
      console.log("hhhh", this.CgsqOrder);
      for (const medicineListElement of resp.data.medicineList) {
        medicineListElement.medicineId = medicineListElement.id;
      }
      // console.log(resp.data[0].medicineList)
      this.bcglXiangXiList = resp.data.medicineList;
      this.checkFalg = false;
      for (const Obj of this.bcglXiangXiList) {
        await this.changeProvider(Obj);
        this.changeMedicine(Obj);
        console.log("-------");
        console.log(Obj);
      }
      this.checkFalg = true;
    },
    async initCgType() {
      let resp = await getPayType();
      this.cgTypeList = resp.data;
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    submitForm(formName, type) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.bcglXiangXiList.length == 0) {
            Message({
              message: "请输入对应采购商品",
              type: "error",
              center: "true",
            });
            return;
          } else {
            for (const obj of this.bcglXiangXiList) {
              if (
                obj.providerId == "" ||
                obj.providerId == undefined ||
                obj.medicineId == "" ||
                obj.medicineId == undefined
              ) {
                Message({
                  message: "您商品明细没输入全哦",
                  type: "error",
                  center: "true",
                });
                return;
              }
            }
          }

          if (type == 1) {
            this.CgsqOrder.orderstatus = 2;
          }
          this.CgsqOrder.medicineList = this.bcglXiangXiList;
          updateCgsqOrder(this.CgsqOrder).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "修改成功!",
                type: "success",
                center: "true",
              });
              this.$emit("closeUpdateDiago");
            } else {
              Message({
                message: "修改失败!",
                type: "error",
                center: "true",
              });
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
      this.$emit("closeUpdateDiago");
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
    checkj() {
      // alert(1)
      this.$forceUpdate();
    },
    chandleDetailSelectionChange(selection) {
      // alert(1)
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
      let obj = {
        providerList: [],
        medicineList: [],
        providerId: "",
        medicineId: "",
        unitName: "",
        specification: "",
        price: "",
        totalPrice: "",
        quantity: "",
      };
      console.log(this.providerList);
      obj.providerList = this.providerList;
      obj.dkdd = "1";
      obj.sjfw = ["07:00", "07:30"];
      obj.jxsjfw = ["06:00", "12:00"];
      this.bcglXiangXiList.push(obj);
    },
    async initProvider() {
      let resp = await init("", 0, 1, 100);
      this.providerList = resp.data.list;
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

    async changeProvider(obj) {
      if (this.checkFalg) {
        obj.medicineId = "";
      }
      obj.medicineList = [];
      if (obj.providerId != "") {
        // alert("bushikond")
        let resp = await getBaseMedicineListByProviderId(obj.providerId);
        // obj.medicineList = resp.data.data
        this.$set(obj, "medicineList", resp.data.data);
        console.log("看看我", obj.medicineList);
        console.log("我是失败", this.bcglXiangXiList);
      }
      if (obj.providerId == "") {
        // alert("konle")

        obj.medicineId = "";
        obj.specification = "";
        obj.price = "";
        obj.totalPrice = "";
        obj.unitName = "";
      }
      this.bcglXiangXiList = [...this.bcglXiangXiList]; // 通过展开运算符创建数组的浅拷贝

      // this.$set(obj,'medicineList',obj.medicineList)

      // this.$forceUpdate();
    },

    handleSelectChange(row) {
      // 这里可以添加逻辑，根据row.selectedValue来更新表格数据
      // 例如，可以调用一个方法来重新获取或更新tableData
      this.fetchData(row);
      this.falg = !this.falg;
    },
    fetchData(row) {
      // 模拟从API获取数据
      // 这里可以替换为实际的API调用逻辑
      setTimeout(() => {
        this.tableData = [this.changeProvider(row)];
      }, 10);
    },
    async changeMedicine(obj) {
      console.log("bcgl", this.bcglXiangXiList);
      console.log("obj", obj);
      if (this.checkFalg) {
        let isDuplicate = false;
        for (let i = 0; i < this.bcglXiangXiList.length; i++) {
          if (this.bcglXiangXiList[i].xh === obj.xh || obj.medicineId == "") {
            continue;
          }
          if (
            this.bcglXiangXiList[i].medicineId === obj.medicineId &&
            this.bcglXiangXiList[i].providerId === obj.providerId
          ) {
            // 如果找到重复项
            isDuplicate = true;
            break;
          }
        }
        if (isDuplicate) {
          Message({
            message: "您重复添加了商品!",
            type: "error",
            center: true,
          });
          obj.medicineId = ""; // 清空输入
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
    cacltotalPrice(row) {
      alert(row);
    },
    closeDiago() {
      this.formDisabled = false;
      this.$emit("closeUpdateDiago");
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
      // 检查bcglXiangXiList是否存在且不为空
      if (!this.bcglXiangXiList || this.bcglXiangXiList.length === 0) {
        return 0;
      }

      // 使用reduce方法计算总价，并确保price和quantity都是数字
      return this.bcglXiangXiList.reduce((total, item) => {
        // 确保price和quantity都是数字
        const price = Number(item.price);
        const quantity = Number(item.quantity);

        // 如果price或quantity不是数字，返回0
        if (isNaN(price) || isNaN(quantity)) {
          console.error("价格或数量不是数字，无法计算总价。");
          return this.CgsqOrder.referenceamount;
        }

        return total + price * quantity;
      }, 0);
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
</style>