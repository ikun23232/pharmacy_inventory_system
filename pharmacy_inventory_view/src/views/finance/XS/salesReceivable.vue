<script>
import { getXsysList ,cwXsysExcel} from '@/api/finance'
import SaleOrderDetail from "@/views/sale/SaleOrderDetail.vue";
export default {
  name: "salesReceivable",
  components: {SaleOrderDetail},
  data() {
    return {
      orderNo:'',
      cwXsysPage: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      cwXsys: {
        code: '',
        originalOrder: ''
      },
      time:{},
      detailDialogFormVisible:false,
    }
  },
  mounted() {
    this.getXsysLists()
  },
  methods: {
    getXsysLists() {
      if (Array.isArray(this.time) && this.time.length > 0) {
        this.cwXsys.beginTime = this.time[0];
        this.cwXsys.endTime = this.time[1];
      } else {
        this.cwXsys.beginTime = null;
        this.cwXsys.endTime = null;
      }
      getXsysList(this.cwXsys, this.cwXsysPage.pageNum, this.cwXsysPage.pageSize).then(resp => {
        console.log(resp)
        if (resp.code != 200) {// 失败
          this.cwXsysPage.list = []
          this.cwXsysPage.total = 0
          this.cwXsysPage.pageNum = 1
          return
        }
        this.cwXsysPage = resp.data
      })
    },
    clean() {
      this.cwXsys.code = '';
      this.cwXsys.originalOrder = '';
      this.time = {};
      this.getXsysLists()
    },
    async printExcel(){
      await cwXsysExcel();
    },
    print(code) {
      const newPage = this.$router.resolve({
        path: "/printSalesReceivable",
        query: {
          code: code,
        },
      });
      window.open(newPage.href, "_blank");
    },
    lookSaleOrderDetail(orderNo){
      this.detailDialogFormVisible=true;
      this.orderNo=orderNo;
    },
    changeDialogFormVisible(val){
      // this.addDialogFormVisible=val;
      // this.updateDialogFormVisible=val;
      this.detailDialogFormVisible=val;
      // this.initSaleOrderByPage(this.object.currentPage);
    },
  }
}
</script>

<template>
  <div>
    <h1>销售应收</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          销售应收编号：
          <el-input
              v-model="cwXsys.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          销售订单编号：
          <el-input
              v-model="cwXsys.originalOrder"
              style="width: 200px"
              placeholder="请输入原单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="10">
          单号日期：
          <el-date-picker
              v-model="time"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
          />
        </el-col>


      </el-row>
      <el-button type="primary" @click="getXsysLists()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button>
      <el-button type="primary" @click="printExcel()">导出</el-button><br/><br/>
    </div>
    <div class="table">
      <el-table :data="cwXsysPage.list" border style="width: 100%">
        <el-table-column prop="id" label="销售应收id" />
        <el-table-column prop="code" label="销售应收编号" fixed/>
        <el-table-column prop="originalOrder" label="销售申请编号"  fixed/>
        <el-table-column prop="createTime" label="单号生成时间" />
        <el-table-column prop="cost" label="应收金额"/>
        <el-table-column prop="createName" label="销售员"/>
        <el-table-column prop="bandCount" label="银行账户" />
        <el-table-column prop="opinion" label="审核意见" />
        <el-table-column align="center" label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" plain @click="print(row.code)">打印</el-button>
            <el-button type="primary" plain @click="lookSaleOrderDetail(row.originalOrder)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
          @current-change="getXsysLists()"
          :current-page.sync="cwXsysPage.pageNum"
          :page-size="cwXsysPage.pageSize"
          layout="prev, pager, next, jumper"
          :total="cwXsysPage.total"
      >
      </el-pagination>
    </div>

    <el-dialog
        title="销售订单-详情"
        :visible.sync="detailDialogFormVisible"
        width="1000px"
        v-if="detailDialogFormVisible"
    >
      <SaleOrderDetail @handleDialogFormVisible="changeDialogFormVisible" :orderNo="orderNo"></SaleOrderDetail>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>