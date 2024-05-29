<script>
import {getCwbsysList,getStorehouseList,cwbsysExcel} from '@/api/finance';
export default {
  name: "breakageReceivable",
  data() {
    return {
      cwBsysPage: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      cwBsys: {
        code: '',
        originalOrder: '',
        storehouseId:0
      },
      time:{},
      storehouseList:[]
    }
  },
  mounted() {
    this.getCwbsysLists();
    this.getStorehouseLists();
  },
  methods: {
    getCwbsysLists() {
      if (Array.isArray(this.time) && this.time.length > 0) {
        this.cwBsys.beginTime = this.time[0];
        this.cwBsys.endTime = this.time[1];
      } else {
        this.cwBsys.beginTime = null;
        this.cwBsys.endTime = null;
      }
      getCwbsysList(this.cwBsys, this.cwBsysPage.pageNum, this.cwBsysPage.pageSize).then(resp => {
        if (resp.code != 200) {// 失败
          this.cwBsysPage.list = []
          this.cwBsysPage.total = 0
          this.cwBsysPage.pageNum = 1
        }
        this.cwBsysPage = resp.data
      })
    },
    clean() {
      this.cwBsys.code = '';
      this.cwBsys.originalOrder = '';
      this.cwBsys.storehouseId=0;
      this.time = {};
      this.getCwbsysLists()
    },
    getStorehouseLists() {
      getStorehouseList().then(resp => {
        if (resp.code != 200) {// 失败
          this.storehouseList = []
          return
        }
        this.storehouseList = resp.data
      })
    },
    async printExcel(){
      await cwbsysExcel();
    },
    print(code) {
      const newPage = this.$router.resolve({
        path: "/PrintBreakageReceivable",
        query: { //要传的参数 可传多个
          code: code
        }
      })
      window.open(newPage.href, '_blank')
    }
  }
}
</script>

<template>
  <div>
    <h1>报损应收</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="8"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwBsys.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="8"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwBsys.originalOrder"
              style="width: 200px"
              placeholder="请输入原单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="8">
          报损仓库:
          <el-select v-model="cwBsys.storehouseId" placeholder="请选择报损仓库">
            <el-option label="全部" :value="0"/>
            <el-option
                v-for="item in storehouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            />
          </el-select>
        </el-col>

      </el-row>
      单号日期：
      <el-date-picker
          v-model="time"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
      />&nbsp;&nbsp;&nbsp;
      <el-button type="primary" @click="getCwbsysLists()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button>
      <el-button type="primary" @click="printExcel">导出</el-button><br/><br>
    </div>
    <div class="table">
      <el-table :data="cwBsysPage.list" border style="width: 100%">
        <el-table-column prop="id" label="报损应收id" />
        <el-table-column prop="code" label="报损应收编号"  fixed/>
        <el-table-column prop="originalOrder" label="报损申请编号" fixed/>
        <el-table-column prop="createTime" label="单号生成时间" />
        <el-table-column prop="cost" label="应收金额"/>
        <el-table-column prop="storehouseName" label="报损仓库"/>
        <el-table-column prop="reportedTypeName" label="报损原因"/>
        <el-table-column prop="approverByName" label="审批人"/>
        <el-table-column prop="createByName" label="报损人"/>
        <el-table-column align="center" label="操作" fixed="right" >
          <template #default="{ row }">
<!--            <el-button type="primary" plain>详情</el-button>&nbsp;-->
            <el-button type="primary" plain @click.native="print(row.code)">打印</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
          @current-change="getCwbsysLists()"
          :current-page.sync="cwBsysPage.pageNum"
          :page-size="cwBsysPage.pageSize"
          layout="prev, pager, next, jumper"
          :total="cwBsysPage.total"
      >
      </el-pagination>
    </div>

  </div>
</template>

<style scoped>

</style>