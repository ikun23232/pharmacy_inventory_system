<script>
import { getProcList, deleteById, setVoidState,cgddExcel } from "@/api/procurementOrder";
import addProcOrder from "../../../components/addProcOrder.vue";
import updateProOrder from "./../../../components/updateProOrder.vue";
import auditingProOrder from "./../../../components/AuditingProOrder.vue";
import { Message } from "element-ui";
import { getPayType } from "@/api/public";
import { getAllBaseProvider } from "@/api/BaseProvider.js";
import AddProcuementPay from "@/components/AddProcuementPay.vue";
export default {
  name: "procurementPay",
  components: {
    AddProcuementPay,
    addProcOrder,
    updateProOrder,
    auditingProOrder,
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      procPage: {
        code: "",
        beginTime: "",
        endTime: "",
        orderStatus: "",
        providerId: 0,
        type: 0,
        pageNum: 0,
        pageSize: 0,
      },
      voidState: false,
      auditingdialogVisible: false,
      time: {},
      list: {},
      cgType: [],
      code: "",
      id: "",
      providerList: [],
    };
  },
  async mounted() {
    this.getOrderList(1, 5);
    let data = await getPayType();
    console.log("data:", data.data);
    this.cgType = data.data;
    let providerList = await getAllBaseProvider();
    console.log("providerList:", providerList);
    this.providerList = providerList.data;
  },
  methods: {
    getOrderList(currentPageNo, pageSize) {
      this.procPage.pageNum = currentPageNo;
      this.procPage.pageSize = pageSize;
      this.procPage.approvalStatus = 1;
      if (this.time.length > 0) {
        this.procPage.beginTime = this.time[0];
        this.procPage.endTime = this.time[1];
      }
      getProcList(this.procPage).then((resp) => {
        console.log(resp);
        this.list = resp.data;
      });
    },
    handleCurrentChange(val) {
      this.getOrderList(val, this.procPage.pageSize);
    },

    handleAuditingSuccess() {
      this.auditingdialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getOrderList(1, 5);
    },
    cancelAuditing() {
      this.auditingdialogVisible = false; // 关闭 el-
      this.getOrderList(1, 5);
    },
    handleDelete(row) {
      if (confirm("你确定要删除吗？")) {
        deleteById(row.id).then((resp) => {
          console.log(resp);
          if (resp.code == 200) {
            Message({
              message: "删除成功!",
              type: "success",
              center: "true",
            });
            this.getOrderList(1, 5);
          } else {
            Message({
              message: "删除失败!",
              type: "error",
              center: "true",
            });
          }
        });
      }
    },
    test(val) {
      alert(val);
    },
    async printExcel() {
      await cgddExcel();
    },
    handleAuditing(row) {
      this.code = row.code;
      this.id = row.id;
      this.auditingdialogVisible = true;
    },
  },
};
</script>

<template>
  <div>
    <h1>付款订单</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="procPage.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="8"
        ><div class="grid-content bg-purple">
          单据日期：
          <el-date-picker
              v-model="time"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions"
              @change="test"
          >
          </el-date-picker></div
        ></el-col>
        <el-col :span="5"
        ><div class="grid-content bg-purple">
          采购类型：
          <el-select
              v-model="procPage.type"
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
          </el-select></div
        ></el-col>
        <el-col :span="5"
        ><div class="grid-content bg-purple">
          供应商：
          <el-select
              v-model="procPage.providerId"
              filterable
              placeholder="请选择供应商"
          >
            <el-option
                v-for="item in providerList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            >
            </el-option>
          </el-select></div
        ></el-col>
      </el-row>

      <el-button type="primary" @click="getOrderList(1, 5)">查询</el-button>
      <el-button type="primary" @click="printExcel">导出</el-button>
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column prop="id" label="订单序号" width="120">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="订单编码" width="150" fixed>
      </el-table-column>
      <el-table-column prop="orderTypeName" label="采购类型" width="120">
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
      <el-table-column prop="phone" label="电话" width="120"></el-table-column>
      <el-table-column prop="fax" label="传真" width="120"></el-table-column>
      <el-table-column prop="deliveryDate" label="交货日期" width="120">
      </el-table-column>
      <el-table-column prop="deliveryName" label="交货人名称" width="120">
      </el-table-column>
      <el-table-column prop="fax" label="传真" width="120"></el-table-column>
      <el-table-column prop="count" label="数量" width="120"></el-table-column>
      <el-table-column prop="effectiveTime" label="生效时间" width="120">
      </el-table-column>
      <el-table-column prop="referenceAmount" label="参考金额" width="120">
      </el-table-column>
      <el-table-column prop="orderStatus" label="单据状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.orderStatus }}
        </template>
      </el-table-column>
      <el-table-column prop="payType" label="结算方式" width="120">
        <template slot-scope="scope">
          {{ scope.row.payType == 0 ? "货到付款" : "全款后发款" }}
        </template>
      </el-table-column>
      <el-table-column prop="isPay" label="支付状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.isPay == 0 ? "未支付" : "已支付" }}
        </template>
      </el-table-column>
      <el-table-column prop="approverRemark" label="审核意见" width="120">
      </el-table-column>
      <el-table-column prop="approvalStatus" label="核批结果" width="120">
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
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">

          <el-dropdown>
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.row)"
              >删除</el-dropdown-item
              >
              <el-dropdown-item @click.native="handleAuditing(scope.row)"

              >付款</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </template>
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
<!--    <el-dialog-->
<!--        title="采购订单付款"-->
<!--        :visible.sync="auditingdialogVisible"-->
<!--        width="1300px"-->
<!--        v-if="auditingdialogVisible"-->
<!--    >-->
<!--      <AddProcuementPay-->
<!--          :code="this.code"-->
<!--          :id="this.id"-->
<!--          @handleAuditingSuccess="handleAuditingSuccess"-->
<!--          @cancelAuditing="cancelAuditing"-->
<!--      >-->
<!--      </AddProcuementPay>-->
<!--    </el-dialog>-->
  </div>
</template>


<style>
</style>