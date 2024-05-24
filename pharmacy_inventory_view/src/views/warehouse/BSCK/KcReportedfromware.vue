<script>
import {getKcReportedfromware, getKcReportedListById, getStorehouseList,getReportedType} from '@/api/KcReported';
import DetailsReported from "@/components/DetailsReported.vue";
export default {
  name: "KcReportedfromware",
  components: {DetailsReported},
  data() {
    return {
      kcReportedfromwarePage:{
        pageNum:1,
        pageSize:3,
        total:0,
        list:[]
      },
      kcReportedfromwareSelect:{
        code:'',
        reportedCode:'',
        storehouseId:0,
        reportedTypeId:0,
      },
      // 详情库存报损对话框
      detailsReportedVisible:false,
      // 修改库存报损数据
      updateReported:{
      },
      reportedData:{
      },
      storehouseList:[],
      reportedTypeList:[]
   }
  },
  mounted() {
    this.getReportedTypeLists()
    this.getStorehouseLists()
    this.getKcReportedfromwaress(1);

  },
  methods: {
    getKcReportedfromwaress(val){
      if (val){
        this.kcReportedfromwarePage.pageNum=val
      }
      getKcReportedfromware(this.kcReportedfromwareSelect,this.kcReportedfromwarePage.pageNum,this.kcReportedfromwarePage.pageSize).then(resp=>{
        if (resp.code!=200){// 失败
          this.kcReportedfromwarePage.list=[]
          return
        }
        this.kcReportedfromwarePage=resp.data
      })
    },
    detailsReporteds(row) {
      this.getKcReportedListByIds(row.reportedId)
      this.detailsReportedVisible = true;
    },
    getKcReportedListByIds(val){
      getKcReportedListById(val).then(resp=>{
        if (resp.code!=200){
          return
        }
        this.reportedData=resp.data
      })
    },
    getStorehouseLists(){
      getStorehouseList().then(resp=>{
        if (resp.code!=200){
          return
        }
        this.storehouseList=resp.data
      })
    },
    // 获取报损类型列表
    getReportedTypeLists(){
      getReportedType().then(resp=>{
        if (resp.code!=200){
          return
        }
        this.reportedTypeList=resp.data
      })
    },
    clear(){
      this.kcReportedfromwareSelect.code=''
      this.kcReportedfromwareSelect.reportedCode=''
      this.kcReportedfromwareSelect.storehouseId=0
      this.kcReportedfromwareSelect.reportedTypeId=0
    }
  }
}
</script>

<template>
<div>
    <div>
      报损出库编号:
      <el-input v-model="kcReportedfromwareSelect.code" placeholder="请输入报损出库编号" style="width: 200px" />
      报损订单号:
      <el-input v-model="kcReportedfromwareSelect.reportedCode" placeholder="请输入报损订单号" style="width: 200px" />
      仓库:
      <el-select v-model="kcReportedfromwareSelect.storehouseId" placeholder="请选择仓库">
        <el-option
          label="全部"
          value="0">
        </el-option>
        <el-option
          v-for="item in storehouseList"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      报损类型:
      <el-select v-model="kcReportedfromwareSelect.reportedTypeId" placeholder="请选择报损类型">
        <el-option
          label="全部"
          value="0">
        </el-option>
        <el-option
          v-for="item in reportedTypeList"
          :key="item.id"
          :label="item.reportedName"
          :value="item.id">
        </el-option>
      </el-select>
      &nbsp;&nbsp;&nbsp;

      <el-button type="primary" @click="getKcReportedfromwaress(1)">查询</el-button>
      <el-button type="primary" @click="clear()">清空</el-button>
      <br/>
    </div>
    <br/>
    <el-table :data="kcReportedfromwarePage.list" border style="width: 100%">
    <el-table-column prop="id" label="订单序号" width="120">
    </el-table-column>
    <el-table-column prop="code" label="订单编码" width="150" fixed>
    </el-table-column>
    <el-table-column prop="reportedCode" label="报损订单号" width="120">
    </el-table-column>
    <el-table-column prop="reportedTypeName" label="报损类型" width="120">
    </el-table-column>
    <el-table-column prop="storehouseName" label="报损仓库" width="120">
    </el-table-column>
    <el-table-column prop="modificationTime" label="出库时间" width="300">
    </el-table-column>
    <el-table-column prop="modificationName" label="审批人" width="120">
    </el-table-column>

    <el-table-column fixed="right" label="操作" width="200">
      <template slot-scope="scope">
        <el-button
            appplication="button"
            @click="detailsReporteds(scope.row)"
            type="text"
        >详细
        </el-button>
        <el-dropdown>
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item
            >删除</el-dropdown-item
            >
            <el-dropdown-item
            >打印</el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </template>
    </el-table-column>
  </el-table>

  <div>
    <el-pagination
        :current-page="kcReportedfromwarePage.pageNum"
        :page-size="kcReportedfromwarePage.pageSize"
        background
        layout="prev, pager, next"
        :total="kcReportedfromwarePage.total"
        @current-change="getKcReportedfromwaress">
    </el-pagination>
  </div>
  <div>
    <el-dialog title="详情报损" :visible.sync="detailsReportedVisible" width="1500px">
      <DetailsReported :row-data="reportedData"
                       @cancel="detailsReportedVisible=false"/>
    </el-dialog>
  </div>
</div>

</template>

<style scoped>

</style>