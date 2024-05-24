<script>
import { getCwInvoice,getCategoryString } from '@/api/finance'
export default {
  name: "invoice",
  data() {
    return {
      cwInvoicePage: {
        pageNum: 1,
        pageSize: 3,
        total: 0,
        list: []
      },
      cwInvoice: {
        code: '',
        orderNumber: '',
        categoryId:0,
      },
      category:[]
    }
  },
  mounted() {
    this.getCategoryStrings()
    this.getCwInvoices()

  },
  methods: {
    getCwInvoices() {
      getCwInvoice(this.cwInvoice, this.cwInvoicePage.pageNum, this.cwInvoicePage.pageSize).then(resp => {
        if (resp.code != 200){
          this.cwInvoicePage.list = []
          this.cwInvoicePage.total = 0
          this.cwInvoicePage.pageNum = 1
          return;
        }
        this.cwInvoicePage = resp.data
      })
    },
    getCategoryStrings(){
      getCategoryString().then(resp=>{
        this.category=resp.data
      })
    },
    clean() {
      this.cwInvoice.code = ''
      this.cwInvoice.orderNumber = ''
      this.cwInvoice.categoryId=0
      this.getCwInvoices()
    },
  }
}
</script>

<template>
  <div>
    <div>
      <el-row :gutter="20">
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwInvoice.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwInvoice.orderNumber"
              style="width: 200px"
              placeholder="请输入原单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          类型：
          <el-select v-model="cwInvoice.categoryId" placeholder="请选择类型">
            <el-option label="全部类型" :value=0></el-option>
            <el-option
                v-for="item in category"
                :key="item.id"
                :label="item.accountsCategoryName"
                :value="item.id"
            ></el-option>
          </el-select></div
        ></el-col>

      </el-row>
      <el-button type="primary" @click="getCwInvoices()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button>
    </div>
    <div class="table">
      <el-table :data="cwInvoicePage.list" border style="width: 100%">
        <el-table-column prop="categoryId" label="发票id" width="120"/>
        <el-table-column prop="code" label="发票单据" width="150" fixed/>
        <el-table-column prop="categoryName" label="类型" width="150" />
        <el-table-column prop="orderNumber" label="订单编号" width="120"/>
        <el-table-column prop="createName" label="创建人" width="120"/>
        <el-table-column prop="createTime" label="创建时间" width="120"/>
        <el-table-column prop="modificationName" label="修改人" width="120"/>
        <el-table-column prop="modificationTime" label="修改时间" width="120"/>
        <el-table-column prop="cost" label="发票总金额" width="120"/>
        <el-table-column align="center" label="操作" fixed="right" width="200">
          <template #default="{ row }">
            <el-button type="primary" plain>详情</el-button>&nbsp;
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
          @current-change="getCwInvoices()"
          :current-page.sync="cwInvoicePage.pageNum"
          :page-size="cwInvoicePage.pageSize"
          layout="prev, pager, next, jumper"
          :total="cwInvoicePage.total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<style scoped>

</style>