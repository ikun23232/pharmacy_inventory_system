<template>
  <div id="storeHouse">
    <h1>调度入库订单</h1>
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

        <el-form-item label="原仓库">
          <el-select v-model="vo.beforeWarehouseId" placeholder="请选择原仓库">
            <el-option label="请选择" value="0"></el-option>
            <el-option
              v-for="item in warhouseList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作废状态">
          <el-select v-model="vo.voidState" placeholder="请选择作废类型">
            <el-option label="请选择" value="-1"></el-option>
            <el-option label="未作废" value="0"></el-option>
            <el-option label="已作废" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日期">
          <div class="block">
            <el-date-picker
              v-model="value2"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
            >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="initDdrkOrderList()"
            >查询</el-button
          >
          <el-button type="success" @click="printExcel">导出</el-button>
        </el-form-item>
      </el-form>
    </p>

    <el-table :data="list.list" border style="width: 100%">
      <el-table-column type="selection" width="55" fixed></el-table-column>
      <el-table-column prop="id" label="#" width="75" fixed>
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="订单编码" width="150" fixed>
        <!-- <template slot-scope="scope">
          <a href="#" @click="viewOrder(scope.row)">{{ scope.row.code }}</a>
        </template> -->
      </el-table-column>
      <el-table-column prop="dispatchCode" label="源单号" width="120">
      </el-table-column>
      <el-table-column prop="dispatchTime" label="调度日期" width="200">
      </el-table-column>
      <el-table-column prop="subject" label="单据主题" width="120">
      </el-table-column>
      <el-table-column prop="aimStoreHouseName" label="调入目标仓库" width="120">
      </el-table-column>
      <el-table-column prop="batchCode" label="批次号" width="120">
      </el-table-column>
      <el-table-column prop="medicineName" label="药品名称" width="120">
      </el-table-column>      
      <el-table-column prop="specification" label="药品规格" width="120">
      </el-table-column>      
      <el-table-column prop="unitName" label="药品计量单位" width="120">
      </el-table-column>      
      <el-table-column prop="providerName" label="药品供应商" width="120">
      </el-table-column>      
      <el-table-column prop="quantity" label="调度数量" width="120">
      </el-table-column>
      <el-table-column prop="dispatchPrice" label="调度金额" width="120">
      </el-table-column>
      <el-table-column prop="approvalstatus" label="核批结果" width="120">
        <template slot-scope="scope">
          {{
            scope.row.approvalstatus === null
              ? "未审核"
              : scope.row.approvalstatus === 0
              ? "未通过"
              : "通过"
          }}
        </template>
      </el-table-column>
      <el-table-column prop="voidState" label="作废状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.voidstate == 0 ? "否" : "是" }}
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope"> 
          <el-dropdown>
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                ><el-button
                  @click="handleDelete(scope.row)"
                  type="danger"
                  size="small"
                  >删除
                </el-button></el-dropdown-item
              >
              <el-dropdown-item
                ><el-button
                  @click="printSaleOrder(scope.row)"
                  type="primary"
                  size="small">打印
                </el-button></el-dropdown-item>
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
      title="调度出库详情"
      :visible.sync="viewdialogVisible"
      width="85%"
      v-if="viewdialogVisible"
    >
      <DDRKViewOrder
        :id="this.id"
        :code="this.code"
        @closeviewOrder="closeviewOrder"
      ></DDRKViewOrder>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from "element-ui";
import {
  ddckExcel,
  delKcDistoware,
  initKcDistowareList,
} from "@/api/DdrkOrder";
import { getStoreList } from "@/api/storeHouse";
import DDRKViewOrder from "@/components/DDRKViewOrder.vue";
export default {
  name: "DDRKManager",
  components:{
    DDRKViewOrder
  },
  data() {
    return {
      id:"",
      code: "",
      page: {
        pageNum: 1,
        pageSize: 5,
      },
      vo: {
        currentPageNo: 1,
        pageSize: 5,
        code: "",
        subject: "",
        type: "0",
        startTime: "",
        endTime: "",
        voidState: "-1",
      },
      list: {},
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
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: "",
      providerList: [],
      warhouseList: [],
      viewdialogVisible: false,
    };
  },
  mounted() {
    this.initDdrkOrderList(1);
    this.initWarHourseList();
  },
  methods: {
    async initDdrkOrderList(currentPageNo) {
      this.vo.currentPageNo = currentPageNo;
      this.vo.startTime = "";
      this.vo.endTime = "";
      if (this.value2 != null && this.value2.length > 0) {
        this.vo.startTime = this.value2[0];
        this.vo.endTime = this.value2[1];
      }
      let data = await initKcDistowareList(this.vo);
      console.log(data);
      this.list = data.data;
    },
    async printExcel() {
      await ddckExcel();
    },
    async initWarHourseList() {
      let resp = await getStoreList(1, 10, "");
      console.log(resp);
      this.warhouseList = resp.data.list;
    },
    handleCurrentChange(val) {
      this.initDdrkOrderList(val);
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let resp = await delKcDistoware(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "删除成功",
        });
        this.initDdrkOrderList(1);
      }
    },
    async voidOrder(row) {
      if (!confirm("你确定要作废吗？")) {
        return;
      }
      let resp = await voidCgrkOrderById(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "作废成功",
        });
        this.initDdrkOrderList(1);
      }
    },
    printSaleOrder(row) {
      const newPage = this.$router.resolve({
        path: "/printDDRKOrder",
        query: row,
      });
      window.open(newPage.href, "_blank");
    },
    viewOrder(row) {
      this.id = row.dispatchid;
      this.code = row.dispatchCode;
      this.viewdialogVisible = true;
    },
    closeviewOrder() {
      this.viewdialogVisible = false;
    },
  },
};
</script>

<style>
</style>