<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="CgddOrder"
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
                <el-option label="货到付款" :value="0"> </el-option>
                <el-option label="全款后发货" :value="1"> </el-option>
                <el-option label="直接付款" :value="2"> </el-option>
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
                placeholder=""
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
            <el-table
              disabled
              :data="medicineListTemp"
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
                disabled
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
              disabled
              placeholder="请选择审批结果"
              clearable
              filterable
            >
              <el-option label="未审批" :value="0"></el-option>
              <el-option label="不通过" :value="1"></el-option>
              <el-option label="通过" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="2">
          <el-button @click="cancel">取消</el-button>
        </el-col>
      </el-row>
    </el-form>
  </span>
</template>

<script>
import { init } from "../api/BaseProvider.js";
import { getMedicineListByCode } from "@/api/baseMedicine";
import { getCgddByCode } from "../api/procurementOrder.js";
import { getPayType } from "../api/public.js";
export default {
  name: "CGDDviewOrdr",
  props: {
    code: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
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
      list: {},
      cgType: {},
      activeName: "first",
      medicineListTemp: [],
      providerList: [],
    };
  },
  async mounted() {
    await this.initProvider();
    let data = await getPayType();
    this.cgType = data.data;
    let cgdd = await getCgddByCode(this.code);
    this.CgddOrder = cgdd.data;
    let medicineList = await getMedicineListByCode(this.code);
    this.medicineListTemp = medicineList.data;
  },
  methods: {
    async initProvider() {
      let resp = await init("", 0, 1, 5);
      this.providerList = resp.data.list;
    },
    cancel() {
      this.$emit("closeviewOrder");
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