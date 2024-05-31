<template>
  <div id="storeHouse">
    <h1>出入库明细</h1>
    <p>
      <el-form :inline="true" :model="vo" class="demo-form-inline">
        <el-form-item label="单据编号">
          <el-input v-model="vo.code" placeholder="请输入单据编号"></el-input>
        </el-form-item>

        <el-form-item label="单据类型">
          <el-select v-model="vo.type" placeholder="请选择单据类型">
            <el-option label="请选择" value="0"></el-option>
            <el-option v-for="item in KcTypeList" :label="item.type" :value="item.id"  :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="原仓库">
          <el-select v-model="vo.beforeWarehouseId" placeholder="请选择原仓库">
            <el-option label="请选择" value="0"></el-option>
            <el-option v-for="item in warhouseList" :label="item.name" :value="item.id"  :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次号">
          <el-input v-model="vo.batchCode" placeholder="请输入批次号"></el-input>
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
          <a href="#" >{{ scope.row.code }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="createDate" label="单据日期" width="200">
      </el-table-column>
      <el-table-column prop="type" label="单据类型" width="120">
      </el-table-column>

      <el-table-column prop="providerName" label="供应商" width="120">
      </el-table-column>
      <el-table-column prop="medicineName" label="商品名称" width="120">

      </el-table-column>

      <el-table-column prop="specification" label="规格型号" width="120">
      </el-table-column>
      <el-table-column prop="unitName" label="单位" width="120">
      </el-table-column>
      <el-table-column prop="batchCode" label="批次号" width="120">
      </el-table-column>
      <el-table-column prop="warehouseName" label="仓库名称" width="120">
      </el-table-column>
      <el-table-column prop="toStockQuantity" label="入库数量" width="120">
      </el-table-column>
      <el-table-column prop="toStockMoney" label="入库金额" width="120">
      </el-table-column>
      <el-table-column prop="fromStockQuantity" label="出库数量" width="120">
      </el-table-column>
      <el-table-column prop="fromStockMoney" label="出库金额" width="120">
      </el-table-column>

      <el-table-column prop="price" label="单位金额" width="120">
      </el-table-column>




      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除
          </el-button>

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
import {DdckExcel, delKcDisfromware, initKcDisfromwareList} from "@/api/DdckOrder";
import {getStoreList} from "@/api/storeHouse";
import {CRKMXExcel, getKcTypeList, initCRKMXDetailList} from "@/api/CRKMXOrder";
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
        voidState: "-1",
        batchCode:'',
        beforeWarehouseId:'0'
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
      providerList:[],
      warhouseList:[],
      KcTypeList:[]
    };
  },
  mounted() {
    this.initCgSqOrderList(1);
    this.initWarHourseList();
    this.initKcType()
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
      let data = await initCRKMXDetailList(this.vo);
      console.log(data.data)
      this.list = data.data;

    },
    async printExcel() {
      await CRKMXExcel();
    },
    async initWarHourseList(){
      let resp = await getStoreList(1,10,'');
      console.log(resp)
      this.warhouseList=resp.data.list
    },
    async initKcType(){
      let resp=await getKcTypeList();
      this.KcTypeList=resp.data
      console.log(resp)
    },
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getList(this.page);
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let resp = await delKcOutinTodetail(row.id);
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
        path: "/printDDCKOrder",
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