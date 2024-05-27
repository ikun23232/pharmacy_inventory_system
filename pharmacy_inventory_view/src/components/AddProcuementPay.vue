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
                :data="cgsqList"
                show-summary
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


            <el-table
                disabled
                :data="medicineListTemp"
                show-summary
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
            <el-form label-width="100px" :model="CgddOrder">
              <el-input
                  disabled
                  style="width: 300px"
                  type="text"
                  v-model="CgddOrder.approverRemark"
              ></el-input>
            </el-form>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item style="width: 500px">
        <el-row type="flex" justify="center">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button @click="cancel()">取 消</el-button>
            </div></el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button type="primary" @click="addcgyfS()"
                >付款</el-button
              >
            </div>
          </el-col>
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

          </div>
        </div>
      </div>
    </el-dialog>
  </span>
</template>

<script>
import { initCgSqOrderList, getCgsqOrderByCode } from "@/api/CgsdOrder";
import { init } from "@/api/BaseProvider";
import { getMedicineListByCode } from "@/api/baseMedicine";
import { getCgddByCode ,addcgyf} from "@/api/procurementOrder";
import { getPayType } from "@/api/public";
export default {
  name: "AddProcuementPay",
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
      cwCgyf:{
        id:1,
        originalOrder:""
      }
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
    await this.initCgSqOrderList();
    this.CgddOrder.approvalStatus=""
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
    chandleDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.tb.clearSelection();
        this.$refs.tb.toggleRowSelection(selection.pop());
      } else {
        this.checkedDetail = selection;
      }
    },
    addcgyfS(){

      this.cwCgyf.originalOrder=this.code
      console.log("151515")
      console.log(this.cwCgyf)
      addcgyf(this.cwCgyf).then((resp)=>{
        if(resp.code==200){
          this.$message({
            message: "添加成功",
            type: "success",
            center: "true",
          });
          this.adddialogVisible = false;
        }else{
          this.$message({
            message: "添加失败",
            type: "error",
            center: "true",
          });
        }
      })
    }

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
  align-items: center; /* 如果需要居中对齐可以这个样式 */
}

.dataTable {
  margin-top: 20px; /* 设置表格与表单之间的间距 */
}
</style>