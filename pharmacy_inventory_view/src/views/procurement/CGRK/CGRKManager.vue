<template>
  <div id="storeHouse">
    <h1>采购入库订单</h1>
    <p>
      <el-form :inline="true" :model="vo" class="demo-form-inline">
        <el-form-item label="单据编号">
          <el-input v-model="vo.code" placeholder="请输入单据编号"></el-input>
        </el-form-item>
        <el-form-item label="单据主题">
          <el-input v-model="vo.subject" placeholder="请输入单据编号"></el-input>
        </el-form-item>

        <el-form-item label="供应商">
          <el-select v-model="vo.providerId" placeholder="请选择供应商" filterable>
            <el-option label="请选择" value="0"></el-option>
            <el-option v-for="item in providerList" :label="item.name" :value="item.id"  :key="item.id"></el-option>
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

          align="right">
      </el-date-picker>
    </div>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="initCgSqOrderList();">查询</el-button>
      <el-button type="primary" @click="addOrder">添加</el-button>
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
        <template slot-scope="scope">
          <a href="#" @click="viewOrder(scope.row.id)">{{ scope.row.code }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="sourceCode" label="源单号" width="120">
      </el-table-column>
      <el-table-column prop="createTime" label="单据日期" width="200">
      </el-table-column>
      <el-table-column prop="subject" label="单据主题" width="120">
      </el-table-column>
      <el-table-column prop="providerName" label="供应商" width="120">
      </el-table-column>
      <el-table-column prop="demanderUserName" label="需求人" width="120">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="120">
      </el-table-column>

      <el-table-column prop="referenceAmount" label="参考金额" width="120">
      </el-table-column>
      <el-table-column prop="formMoney" label="已入库金额" width="120">
        <template slot-scope="scope">
          {{scope.row.isaddwarehouse === 0 ? "0.0" : scope.row.medicineprice}}
        </template>
      </el-table-column>
      <el-table-column prop="referenceAmount" label="付款金额" width="120">

      </el-table-column>
      <el-table-column prop="effectiveTime" label="生效时间" width="120">
      </el-table-column>
      <el-table-column prop="orderStatueName" label="单据状态" width="120">
      </el-table-column>
      <el-table-column prop="approvalstatus
" label="核批结果" width="120">
        <template slot-scope="scope">
          {{scope.row.approvalstatus === 0 ? "未审核" : (scope.row.approvalstatus === 1 ? "未通过" : "通过")}}

        </template>
      </el-table-column>
      <el-table-column prop="voidState" label="作废状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.voidstate == 0 ? "否" : "是" }}
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120">
      </el-table-column>
      <el-table-column prop="demanderUserName" label="制单人" width="120">
      </el-table-column>
      <el-table-column prop="updateUserName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="120">
      </el-table-column>


      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
              @click="updateOrder(scope.row.id)"
              type="primary"
              size="small"
              :disabled="scope.row.orderStatus>2 || scope.row.voidstate==1 || scope.row.approvalstatus==1||scope.row.approvalstatus==2"
          >编辑
          </el-button>
          <el-dropdown>
      <span class="el-dropdown-link">
        更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item ><el-button @click="handleDelete(scope.row)" type="danger" size="small">删除
              </el-button></el-dropdown-item>

              <el-dropdown-item ><el-button @click="voidOrder(scope.row)" :disabled="scope.row.voidstate==1" type="info" size="small">作废
              </el-button></el-dropdown-item>

              <el-dropdown-item ><el-button @click="approveOrder(scope.row.id)" :disabled="scope.row.orderStatus==2 && scope.row.approvalstatus==0" type="success" size="small">审核
              </el-button></el-dropdown-item>
              <el-dropdown-item ><el-button @click="printSaleOrder(scope.row.id)" type="primary" size="small">打印
              </el-button></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @current-change="initCgSqOrderList"
          :current-page.sync="list.pageNum"
          :page-size="list.pageSize"
          layout="prev, pager, next, jumper"
          :total="list.total"
      >
      </el-pagination>
    </div>
    <!-- 修改订单状态 -->
    <el-dialog
        title="查看采购申请单"
        :visible.sync="viewdialogVisible"
        width="85%"

        v-if="viewdialogVisible"
    >
      <CGRKViewOrder
          :id="this.id"
          @closeviewOrder="closeviewOrder"
      ></CGRKViewOrder>
    </el-dialog>

    <el-dialog
        title="修改采购入库单"
        :visible.sync="updatedialogVisible"
        width="85%"
        v-if="updatedialogVisible"
    >
      <CGRKUpdateOrder
          :id="this.id"
          @closeUpdateDiago="closeUpdateDiago"
      ></CGRKUpdateOrder>
    </el-dialog>
    <el-dialog
        title="审核采购申请单"
        :visible.sync="approvedialogVisible"
        width="85%"
        v-if="approvedialogVisible"
    >
      <CGRKApproveOrder
          :id="this.id"
          @closeapproveDiago="closeapproveDiago"
      ></CGRKApproveOrder>
    </el-dialog>

    <el-dialog
        title="采购入库单添加"
        :visible.sync="adddialogVisible"
        width="85%"
        v-if="adddialogVisible"
    >
      <AddCGRKOrder
          width="75%"
          :id="id"
          @addSuccess="addSuccess">
      </AddCGRKOrder>
    </el-dialog>
  </div>
</template>

<script>
// import { delUnit, initUnit } from "@/api/BaseUnit";
import {initCgrkOrderList, delCgrqOrderById, voidCgrkOrderById, cgrkExcel} from '@/api/CgrkOrder'
import {init} from '@/api/BaseProvider'
import {Message} from "element-ui";
import AddCGRKOrder from "@/components/AddCGRKOrder.vue";


import {getPayType} from "@/api/public";
import CGRKUpdateOrder from "@/components/WarHouse/CGRKUpdateOrder";
import CGRKApproveOrder from "@/components/WarHouse/CGRKApproveOrder";
import CGRKViewOrder from "@/components/WarHouse/CGRKViewOrder";
import {cgddExcel} from "@/api/procurementOrder";
// import AddUnit from "./AddUnit.vue";



export default {
  name: "storeHouse",
  components: {
    AddCGRKOrder,
    CGRKUpdateOrder,
    CGRKApproveOrder,
    CGRKViewOrder
  },
  data() {
    return {
      code: "",
      page: {
        pageNum: 1,
        pageSize: 5,
      },
      vo: {
        currentPageNo: 1,
        pageSize: 5,
        code: '',
        subject: '',
        type: '0',
        startTime: '',
        endTime: '',
        voidState: "-1"
      },
      serialNumber: "",
      list: {},
      //添加弹框开关
      dialogVisible: false,
      updatedialogVisible: false,
      adddialogVisible: false,
      viewdialogVisible: false,
      approvedialogVisible:false,
      formDisabled:false,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: '',
      providerList:[]
    };
  },
  mounted() {
    this.initCgSqOrderList(1);
    this.initProvider();
  },
  methods: {
    async initCgSqOrderList(currentPageNo) {
      this.vo.currentPageNo=currentPageNo
      this.vo.startTime = ''
      this.vo.endTime = ''
      if (this.value2 != null && this.value2.length > 0) {
        this.vo.startTime = this.value2[0];
        this.vo.endTime = this.value2[1];
      }
      let data = await initCgrkOrderList(this.vo);
      console.log(data);
      this.list = data.data;

    },
    async printExcel() {
      await cgrkExcel();
    },
    async initProvider(){
      let resp = await init('',0,1,10);
      console.log(resp)
      this.providerList=resp.data.list
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let resp = await delCgrqOrderById(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "删除成功",
        });
        this.initCgSqOrderList(1);
      }
    },
    updateOrder(id) {
      this.id = id;
      this.updatedialogVisible = true;
    },
    addOrder() {
      this.adddialogVisible = true;
    },
    approveOrder(id) {
      this.id = id;
      this.approvedialogVisible = true;
    },
    viewOrder(id) {
      this.id = id;
      this.viewdialogVisible = true;
    },
    closeUpdateDiago() {
      this.updatedialogVisible = false;
      this.initCgSqOrderList(1);
    },
    closeviewOrder(){
      this.viewdialogVisible = false;
    },
    closeapproveDiago() {
      this.approvedialogVisible = false;
      this.initCgSqOrderList(1);
    },
    addSuccess() {
      this.adddialogVisible = false;
      this.initCgSqOrderList(1);
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
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
        this.initCgSqOrderList(1);
      }
    },
    handleEdit(row) {
      this.$router.push({
        name: "updateUser",
        params: {
          userName: row.userName,
        },
      });
    },
    printSaleOrder(orderNo){
      const newPage= this.$router.resolve({
        path: "/printCGRKOrder",
        query:{ //要传的参数 可传多个
          orderNo:orderNo
        }})
      window.open(newPage.href,'_blank')
    }

  }
};
</script>

<style>
</style>