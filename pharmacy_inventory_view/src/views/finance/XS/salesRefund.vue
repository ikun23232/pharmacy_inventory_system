<script>
import { getXstkList,cwXstkExcel} from '@/api/finance'
export default {
  name: "salesRefund",
  data() {
    return {
      cwXstkPage: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      cwXstk: {
        code: '',
        originalOrder: ''
      },
      time:{}
    }
  },
  mounted() {
    this.getXstkLists()
  },
  methods: {
    getXstkLists() {
      if (Array.isArray(this.time) && this.time.length > 0) {
        this.cwXstk.beginTime = this.time[0];
        this.cwXstk.endTime = this.time[1];
      } else {
        this.cwXstk.beginTime = null;
        this.cwXstk.endTime = null;
      }
      getXstkList(this.cwXstk, this.cwXstkPage.pageNum, this.cwXstkPage.pageSize).then(resp => {
        if (resp.code != 200){
          this.cwXstkPage.list = []
          this.cwXstkPage.total = 0
          this.cwXstkPage.pageNum = 1
          return;
        }
        this.cwXstkPage = resp.data
        console.log(this.cwXstkPage)
      })
    },
    clean() {
      this.cwXstk.code = '';
      this.cwXstk.originalOrder = '';
      this.time = {};
      this.getXstkLists()
    },
    async printExcel(){
      await cwXstkExcel();
    },
    print(code) {
      const newPage = this.$router.resolve({
        path: "/printSalesRefund",
        query: {
          code: code,
        },
      });
      window.open(newPage.href, "_blank");
    },
  }
}
</script>

<template>
  <div>
    <h1>销售退款</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwXstk.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwXstk.originalOrder"
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
      <el-button type="primary" @click="getXstkLists()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button>
      <el-button type="primary" @click="printExcel()">导出</el-button><br/><br/>
    </div>
    <div class="table">
      <el-table :data="cwXstkPage.list" border style="width: 100%">
        <el-table-column prop="id" label="销售退款id" />
        <el-table-column prop="code" label="销售退款收编号" fixed/>
        <el-table-column prop="originalOrder" label="原单号" fixed/>
        <el-table-column prop="backReson" label="退货原因" />
        <el-table-column prop="createTime" label="单号生成时间"/>
        <el-table-column prop="cost" label="应收金额"/>
        <el-table-column prop="createName" label="销售员" />
        <el-table-column prop="bandCount" label="银行账户"/>
        <el-table-column prop="refundType" label="退款类型"/>
        <el-table-column prop="opinion" label="审核意见" />
        <el-table-column align="center" label="操作"  width="200">
          <template #default="{ row }">
            <el-button type="primary" plain @click="print(row.code)">打印</el-button>&nbsp;
            <el-button type="primary" plain>详情</el-button>&nbsp;
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
          @current-change="getXstkLists()"
          :current-page.sync="cwXstkPage.pageNum"
          :page-size="cwXstkPage.pageSize"
          layout="prev, pager, next, jumper"
          :total="cwXstkPage.total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<style scoped>

</style>