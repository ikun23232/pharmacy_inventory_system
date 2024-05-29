<script>
import {getKcReportedList,getStorehouseList,getReportedType,delKcReportedAndDetailByCode,kcReportedExcel} from '@/api/KcReported';
import AddReported from "@/components/AddReported.vue";
import { Message } from "element-ui";
import UpdateReported from "@/components/UpdateReported.vue";
import DetailsReported from "@/components/DetailsReported.vue";
export default {
  name: "KcReported",
  components: {DetailsReported, UpdateReported, AddReported},
  data() {
    return {
      // 登录用户
      loginUser:1,
      // 库存报损模糊查询数据
      kcReportedSelect:{
        storehouseId:0,
        reportedTypeId:0,
        beginTime:'',
        endTime:'',
        code:'',
      },
      // 时间
      time:{},
      // 库存报损分页数据
      kcReportedPage:{
        pageNum:1,
        pageSize:5,
        total:0,
        list:[]
      },
      // 时间选择器 默认选项定义
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
      // 仓库列表
      storehouseList:[],
      // 报损类型列表
      reportedTypeList:[],
      // 添加库存报损对话框
      addReportedVisible:false,
      // 添加库存报损数据
      addReported:{
        code:'',
        storehouseId:0,
        reportedTypeId:0,
        reportedTime:'',
        reportedNum:0,
        reportedReason:'',
        reportedUserId:0,
      },
      // 修改库存报损对话框
      updateReportedVisible:false,
      // 修改库存报损数据
      updateReported:{

      },
      // 详情库存报损对话框
      detailsReportedVisible:false,

    }
  },
  mounted() {
    // 初始化分页数据
    this.getKcReportedLists()
    // 初始化仓库列表
    this.getStorehouseLists()
    // 初始化报损类型列表
    this.getReportedTypeLists()
  },
  methods: {
    // 搜索分页表格
    getKcReportedLists(val){
      if (val){
        this.kcReportedPage.pageNum=val
      }
      if (Array.isArray(this.time) && this.time.length > 0) {
        this.kcReportedSelect.beginTime = this.time[0];
        this.kcReportedSelect.endTime = this.time[1];
      } else {
        this.kcReportedSelect.beginTime = null;
        this.kcReportedSelect.endTime = null;
      }

        getKcReportedList(this.kcReportedSelect,this.kcReportedPage.pageNum,this.kcReportedPage.pageSize).then(resp=>{
          if (resp.code!=200){// 失败
            this.kcReportedPage.list=[]
            this.kcReportedPage.total=0
            this.kcReportedPage.pageNum=1
            return
          }
          this.kcReportedPage=resp.data
        })
    },
    // 审批状态
    approvalStatusFormatter(row, column) {
      const status = row[column.property];
      return status === 0 ? '未审批' :
          status === 1 ? '未通过' :
              status === 2 ? '通过' : '未知状态';
    },
    // 获取仓库列表
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
    updateReporteds(row) {
      this.updateReported = Object.assign({}, row); // 深度复制 row 对象，以避免引用相同的对象
      this.updateReportedVisible = true;
    },
    detailsReporteds(row) {
      console.log(row)
      this.updateReported = Object.assign({}, row); // 深度复制 row 对象，以避免引用相同的对象
      this.detailsReportedVisible = true;
    },
    deleteReported(row) {

      const confirmDelete = confirm('确定要删除这条报损记录吗？');

      if (!confirmDelete) {
        // 如果用户点击“取消”，则不执行删除操作
        return;
      }
      if (row.approvalStatus==1){
        Message({
          message: '已审批的报损不能删除',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      if (row.approvalStatus==2){
        Message({
          message: '已审批的报损不能删除',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      delKcReportedAndDetailByCode(row.code).then(resp=>{
        if (resp.code!=200){
          return
        }
        Message({
          message: '删除成功',
          type: 'success',
          duration: 5 * 1000
        })
        this.getKcReportedLists()
      })
    },
    async printExcel(){
      await kcReportedExcel();
    },
    print(code){
      const newPage= this.$router.resolve({
        path: "/PrintKcReported",
        query:{ //要传的参数 可传多个
        code:code
      }})
      window.open(newPage.href,'_blank')
    }
  }

}
</script>

<template>
  <div>
    <div class="title">库存报损</div>
    <!--查询条件-->
    <div class="condition">
      报损编号：
      <el-input
          v-model="kcReportedSelect.code"
          style="width: 200px"
          placeholder="请输入报损编号"
      ></el-input>&nbsp;&nbsp;
      报损仓库：
      <el-select
          v-model="kcReportedSelect.storehouseId"
          placeholder="请选择仓库"
          style="width: 200px"
      >
        <el-option
            label="全部"
            :value="0"
        />
        <el-option
            v-for="item in storehouseList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
        />
      </el-select>&nbsp;&nbsp;
      报损类型：
      <el-select
          v-model="kcReportedSelect.reportedTypeId"
          placeholder="请选择报损类型"
          style="width: 200px"
      >
        <el-option
            label="全部"
            :value="0"
        />
        <el-option
            v-for="item in reportedTypeList"
            :key="item.id"
            :label="item.reportedName"
            :value="item.id"
        />
      </el-select><br/><br/>
      报损日期：
      <el-date-picker
          v-model="time"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
      />&nbsp;&nbsp;&nbsp;&nbsp;
      <el-button type="primary" @click="getKcReportedLists()">查询</el-button>
      <el-button type="primary" @click="addReportedVisible=true">添加</el-button>
      <el-button type="primary" @click="printExcel">导出</el-button>
    </div><br/>
    <!--表格内容-->
    <div class="table">
      <el-table :data="kcReportedPage.list" border style="width: 100%">
        <el-table-column prop="code" label="报损编号" width="150" fixed/>
        <el-table-column prop="storehouseName" label="仓库" width="120"/>
        <el-table-column prop="reportedTypeName" label="报损类型" width="120"/>
        <el-table-column
            prop="approvalStatus"
            label="审批结果"
            width="120"
            :formatter="approvalStatusFormatter"
        />
        <el-table-column prop="approverRemark" label="审批标注" width="120"/>
        <el-table-column prop="createTime" label="报损时间" width="120"/>
        <el-table-column prop="modificationTime" label="修改时间" width="120"/>
        <el-table-column prop="createName" label="创建人" width="120"/>
        <el-table-column prop="documenterName" label="制单人" width="120"/>
        <el-table-column prop="approverName" label="审批人" width="120"/>
        <el-table-column prop="modificationName" label="修改人" width="120"/>
        <el-table-column align="center" label="操作" fixed="right" width="200">
          <template #default="{ row }">
            <el-button type="primary" plain @click="updateReporteds(row)" v-if="row.approvalStatus!=2">修改</el-button>&nbsp;
            <el-button type="primary" plain @click="detailsReporteds(row)" v-if="row.approvalStatus==2">详情</el-button>&nbsp;
            <el-dropdown>
          <span class="el-dropdown-link">
            更多<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="print(row.code)">打印</el-dropdown-item>
                <el-dropdown-item @click.native="deleteReported(row)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--分页器-->
    <div class="pagination">
      <el-pagination
          :current-page="kcReportedPage.pageNum"
          :page-size="kcReportedPage.pageSize"
          background
          layout="prev, pager, next"
          :total="kcReportedPage.total"
          @current-change="getKcReportedLists">
      </el-pagination>
    </div>
    <div>
      <el-dialog title="添加报损" :visible.sync="addReportedVisible" width="1500px">
        <AddReported @handleAddSuccess="addReportedVisible=false;getKcReportedLists(1)"
                     @cancel="addReportedVisible=false"/>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="修改报损" :visible.sync="updateReportedVisible" width="1500px">
        <UpdateReported :row-data="updateReported" @handleAddSuccess="updateReportedVisible=false;;getKcReportedLists(1)"
                        @cancel="updateReportedVisible=false" />
      </el-dialog>
    </div>
    <div>
      <el-dialog title="详情报损" :visible.sync="detailsReportedVisible" width="1500px">
        <DetailsReported :row-data="updateReported"
                         @cancel="detailsReportedVisible=false"/>
      </el-dialog>
    </div>
  </div>
</template>


<style scoped>

</style>