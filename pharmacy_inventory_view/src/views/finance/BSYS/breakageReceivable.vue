<script>
import {getCwbsysList} from '@/api/finance';
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
        originalOrder: ''
      },
      time:{}
    }
  },
  mounted() {
    this.getCwbsysLists();
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
      this.cwBsys.code = ''
      this.cwBsys.originalOrder = ''
      this.time = {};
      this.getCwbsysLists()
    },
  }
}
</script>

<template>
  <div>
    <h1>报损应收</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwBsys.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwBsys.originalOrder"
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
      <el-button type="primary" @click="getCwbsysLists()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button><br/><br>
    </div>
    <div class="table">
      <el-table :data="cwBsysPage.list" border style="width: 100%">
        <el-table-column prop="id" label="报损应收id" width="120"/>
        <el-table-column prop="code" label="报损应收编号" width="150" fixed/>
        <el-table-column prop="originalOrder" label="报损申请编号" width="150" fixed/>
        <el-table-column prop="createTime" label="单号生成时间" width="120"/>
        <el-table-column prop="cost" label="应收金额" width="120"/>
        <el-table-column align="center" label="操作" fixed="right" width="200">
          <template #default="{ row }">
            <el-button type="primary" plain>详情</el-button>&nbsp;
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