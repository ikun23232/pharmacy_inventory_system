<template>
  <div>
    <h1>采购订单</h1>
    <div>
      单据编号：
      <el-input
        v-model="procPage.code"
        style="width: 200px"
        placeholder="请输入单据编号"
      ></el-input>
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
      >
      </el-date-picker>
      <el-button type="primary" @click="getOrderList(1, 5)">查询</el-button>
      <el-button type="primary" @click="adddialogVisible = true">添加</el-button>
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column prop="id" label="订单序号" width="120">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="订单编码" width="150" fixed>
      </el-table-column>
      <el-table-column prop="type" label="采购类型" width="120">
      </el-table-column>
      <el-table-column prop="demandTime" label="单据日期" width="300">
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
      <el-table-column prop="fax" label="传真" width="120"> </el-table-column>
      <el-table-column prop="deliveryDate" label="交货日期" width="120">
      </el-table-column>
      <el-table-column prop="deliveryName" label="交货人名称" width="120">
      </el-table-column>
      <el-table-column prop="fax" label="传真" width="120"> </el-table-column>
      <el-table-column prop="count" label="数量" width="120"> </el-table-column>
      <el-table-column prop="effectiveTime" label="生效时间" width="120">
      </el-table-column>
      <el-table-column prop="referenceAmount" label="参考金额" width="120">
      </el-table-column>
      <el-table-column prop="referenceAmount" label="单据状态" width="120">
      </el-table-column>
      <el-table-column prop="payType" label="结算方式" width="120">
        <template slot-scope="scope">
          {{ scope.row.payType == 0 ? "货到付款" : "全款后发款" }}
        </template>
      </el-table-column>
      <el-table-column prop="isPay" label="支付状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.voidState == 0 ? "未支付" : "已支付" }}
        </template>
      </el-table-column>
      <el-table-column prop="orderStatus" label="核批结果" width="120">
        <template slot-scope="scope">
          {{ scope.row.orderstatus == 0 ? "未通过" : "通过" }}
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
          <el-button
            @click="updateOrder(scope.row.id)"
            type="primary"
            size="small"
            >编辑
          </el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small"
            >删除
          </el-button>
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
        <addProcOrder
          @handleAddSuccess="handleAddSuccess"
          @cancel="cancel"
        ></addProcOrder>
      </el-pagination>
    </div>
    <el-dialog
      title="采购订单添加"
      :visible.sync="adddialogVisible"
      width="100%"
      v-if="adddialogVisible"
    >
      <addProcOrder
        @handleAddSuccess="handleAddSuccess"
        @cancel="cancel"
      ></addProcOrder>
    </el-dialog>
  </div>
</template>

<script>
import { getProcList } from "@/api/procurementOrder";
import addProcOrder from "./../../components/addProcOrder.vue";
export default {
  name: "procOrder",
  components: {
    addProcOrder,
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
      adddialogVisible: false,
      time: {},
      list: {},
    };
  },
  mounted() {
    this.getOrderList(1, 5);
  },
  methods: {
    getOrderList(currentPageNo, pageSize) {
      this.procPage.pageNum = currentPageNo;
      this.procPage.pageSize = pageSize;
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
      this.page.pageNum = val;
      this.getOrderList(val);
    },
    handleAddSuccess() {
      this.adddialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getList(1, 5);
    },
    cancel() {
      this.adddialogVisible = false; // 关闭 el-dialog
    },
    updateOrder(row) {},
    handleDelete(row) {},
  },
};
</script>

<style>
</style>