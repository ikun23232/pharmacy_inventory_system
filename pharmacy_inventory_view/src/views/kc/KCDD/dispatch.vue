<template>
  <div>
    <h1>库存调度</h1>
    <div>
      <el-row :gutter="24" style="margin-bottom: 10px">
        <el-col :span="7"
          ><div class="grid-content bg-purple">
            单据编号：
            <el-input
              v-model="DispatchVO.code"
              style="width: 200px"
              placeholder="请输入单据编号"
            ></el-input></div
        ></el-col>
        <el-col :span="10"
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
        <el-col :span="7"
          ><div class="grid-content bg-purple">
            源仓库：
            <el-select
              v-model="DispatchVO.beforeWarehouseId"
              placeholder="请选择原仓库"
              clearable
              filterable
            >
              <el-option label="全部" :value="0"></el-option>
              <el-option
                v-for="item in storeHouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="24" style="margin-bottom: 10px">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            主题：
            <el-input
              v-model="DispatchVO.subject"
              style="width: 200px"
              placeholder="请输入主题"
            ></el-input></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            审批状态：
            <el-select
              v-model="DispatchVO.approvalStatus"
              placeholder="请选择审批状态"
              filterable
            >
              <el-option label="全部" :value="0"></el-option>
              <el-option label="未通过" :value="1"></el-option>
              <el-option label="通过" :value="2"></el-option>
            </el-select></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            单据状态：
            <el-select
              v-model="DispatchVO.orderStatus"
              filterable
              placeholder=""
            >
              <el-option label="全部" :value="0"></el-option>
              <el-option label="未编制" :value="1"></el-option>
              <el-option label="编制完" :value="2"></el-option>
              <el-option label="执行中" :value="3"></el-option>
              <el-option label="执行完" :value="4"></el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="24" style="margin-bottom: 10px">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            作废状态：
            <el-select
              v-model="DispatchVO.voidState"
              filterable
              placeholder="请选择作废状态"
            >
              <el-option label="全部" :value="-1"></el-option>
              <el-option label="未作废" :value="0"></el-option>
              <el-option label="已作废" :value="1"></el-option>
            </el-select></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-button type="primary" @click="getOrderList(1, 5)"
              >查询</el-button
            >
            <el-button type="primary" @click="adddialogVisible = true"
              >添加</el-button
            >
            <el-button type="primary" @click="excel">导出</el-button>
          </div></el-col
        >
      </el-row>
    </div>
    <el-table :data="list.list" border style="width: 100%">
      <el-table-column prop="id" label="调度单序号" width="120">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="调度单编号" width="150" fixed>
        <template slot-scope="scope">
          <a href="#" @click="viewOrder(scope.row)">{{
            scope.row.code
          }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="subject" label="调度主题" width="120">
      </el-table-column>
      <el-table-column prop="price" label="调度总价格" width="120">
      </el-table-column>
      <el-table-column prop="beforeWareName" label="原仓库" width="120">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120"></el-table-column>
      <el-table-column
        prop="approverName"
        label="核批人"
        width="120"
      ></el-table-column>
      <el-table-column prop="approvalStatus" label="核批结果" width="120">
        <template slot-scope="scope">
          <div v-if="scope.row.approvalStatus == 0">未审批</div>
          <div v-if="scope.row.approvalStatus == 1">未通过</div>
          <div v-if="scope.row.approvalStatus == 2">通过</div>
        </template>
      </el-table-column>
      <el-table-column
        prop="approverRemark"
        label="核批备注"
        width="120"
      ></el-table-column>
      <el-table-column prop="createDate" label="制单时间" width="120">
      </el-table-column>
      <el-table-column prop="orderStatusResult" label="单据状态" width="120">
      </el-table-column>
      <el-table-column prop="voidState" label="作废状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.voidState == 0 ? "未作废" : "已作废" }}
        </template>
      </el-table-column>
      <el-table-column prop="createName" label="制单人" width="120">
      </el-table-column>
      <el-table-column prop="updateName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updateDate" label="修改时间" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            @click="updateOrder(scope.row)"
            :disabled="scope.row.orderStatus != 1 && scope.row.orderStatus != 2"
            type="text"
            >编辑
          </el-button>
          <el-dropdown>
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.row)"
                >删除</el-dropdown-item
              >
              <el-dropdown-item @click.native="setVoidState(scope.row)"
                >作废</el-dropdown-item
              >
              <el-dropdown-item @click.native="printDispatch(scope.row)"
                >打印</el-dropdown-item
              >
              <el-dropdown-item
                @click.native="handleAuditing(scope.row)"
                :disabled="scope.row.orderStatus != 2"
                >审核</el-dropdown-item
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
    <el-dialog
      title="调度单添加"
      :visible.sync="adddialogVisible"
      width="1300px"
      v-if="adddialogVisible"
    >
      <AddDispatch
        @handleAddSuccess="handleAddSuccess"
        @cancel="cancel"
      ></AddDispatch>
    </el-dialog>
    <el-dialog
      title="调度单修改"
      :visible.sync="updatedialogVisible"
      width="1300px"
      v-if="updatedialogVisible">
      <updateDispatch
        :code="this.code"
        :id="this.id"
        @handleUpdateSuccess="handleUpdateSuccess"
        @cancelUpdate="cancelUpdate"
      ></updateDispatch>
    </el-dialog>
    <el-dialog
      title="调度单审核"
      :visible.sync="auditingdialogVisible"
      width="1300px"
      v-if="auditingdialogVisible">
      <AuditingDispatch
        :code="this.code"
        :id="this.id"
        @handleAuditingSuccess="handleAuditingSuccess"
        @cancelAuditing="cancelAuditing"
      >
      </AuditingDispatch>
    </el-dialog>
    <el-dialog
      title="查看调度单"
      :visible.sync="viewdialogVisible"
      width="85%"
      v-if="viewdialogVisible"
    >
      <DispatchViewOrder
        :code="this.code"
        :id="this.id"
        @closeviewOrder="closeviewOrder"
      ></DispatchViewOrder>
    </el-dialog>
  </div>
</template>
<script>
import { setExcel } from "@/api/public.js";
import {
  getKcDispathList,
  deleteDispatch,
  updateVoidStatus,
  excelDispatch
} from "@/api/KcDispatch";
import { Message } from "element-ui";
import { getAllStoreHouseList } from "@/api/storeHouse.js";
import AddDispatch from "../../../components/AddDispatch.vue";
import updateDispatch from "./../../../components/updateDispatch.vue";
import AuditingDispatch from "./../../../components/AuditingDispatch.vue";
import DispatchViewOrder from "./../../../components/DispatchViewOrder .vue";
export default {
  name: "dispatch",
  components: {
    AddDispatch,
    updateDispatch,
    AuditingDispatch,
    DispatchViewOrder,
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
      DispatchVO: {
        code: "",
        startTime: "",
        endTime: "",
        orderStatus: 0,
        beforeWarehouseId: 0,
        currentPageNo: "",
        pageSize: "",
        approvalStatus: 0,
        subject: "",
        voidState: -1,
      },
      voidState: false,
      adddialogVisible: false,
      updatedialogVisible: false,
      auditingdialogVisible: false,
      viewdialogVisible: false,
      storeHouseList: [],
      time: {},
      list: {},
      code: "",
      id: "",
    };
  },
  async mounted() {
    this.getOrderList(1, 5);
    let data = await getAllStoreHouseList();
    console.log("data:", data);
    this.storeHouseList = data.data;
  },
  methods: {
      async excel(){
        await excelDispatch();
    },
    viewOrder(row) {
      this.code = row.code;
      this.id = row.id
      this.viewdialogVisible = true;
    },
    closeviewOrder() {
      this.viewdialogVisible = false;
    },
    getOrderList(currentPageNo, pageSize) {
      this.DispatchVO.currentPageNo = currentPageNo;
      this.DispatchVO.pageSize = pageSize;
      if (this.time.length > 0) {
        this.DispatchVO.startTime = this.time[0];
        this.DispatchVO.endTime = this.time[1];
      }
      getKcDispathList(this.DispatchVO).then((resp) => {
        console.log(resp);
        this.list = resp.data;
      });
    },
    handleCurrentChange(val) {
      this.getOrderList(val, this.DispatchVO.pageSize);
    },
    handleAddSuccess() {
      this.adddialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getOrderList(1, 5);
    },
    cancel() {
      this.adddialogVisible = false; // 关闭 el-
      this.getOrderList(1, 5);
    },
    handleUpdateSuccess() {
      this.updatedialogVisible = false; // 关闭 el-dialog
      // 如果需要，可以在这里执行其他操作
      this.getOrderList(1, 5);
    },
    cancelUpdate() {
      this.updatedialogVisible = false; // 关闭 el-
      this.getOrderList(1, 5);
    },
    // handleUpdateSuccess() {
    //   this.updatedialogVisible = false; // 关闭 el-dialog
    //   // 如果需要，可以在这里执行其他操作
    //   this.getOrderList(1, 5);
    // },
    // cancelUpdate() {
    //   this.updatedialogVisible = false; // 关闭 el-
    //   this.getOrderList(1, 5);
    // },
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
        deleteDispatch(row.id).then((resp) => {
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
      await setExcel(this.list.list, "库存调度");
    },
    async setVoidState(row) {
      if (confirm("你确定要作废吗？")) {
        let data = await updateVoidStatus(row.id, 1);
        if (data.code == 200) {
          Message({
            message: "成功作废!",
            type: "success",
            center: "true",
          });
          this.getOrderList(1, 5);
        }
      }
    },
    updateOrder(row) {
      this.code = row.code;
      this.id = row.id;
      this.updatedialogVisible = true;
    },
    handleAuditing(row) {
      this.code = row.code;
      this.id = row.id;
      this.auditingdialogVisible = true;
    },
    printDispatch(row) {
      const newPage = this.$router.resolve({
        path: "/printDispatchOrder",
        query: {
          //要传的参数 可传多个
          id: row.id,
        },
      });
      window.open(newPage.href, "_blank");
    },
  },
};
</script>

<style>
</style>