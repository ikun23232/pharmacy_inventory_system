<template>
  <div id="saleOrder">
  <h1>销售订单</h1>
  <div>
    <div style="padding-top: 15px;padding-left: 20px;">
        <el-form :inline="true" >
            <el-form-item label="单据编号">
                <el-input placeholder="单据编号" v-model="object.orderNo"></el-input>
            </el-form-item>
            <el-form-item label="单据日期">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="请选择开始" v-model="object.orderDateBegin" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col class="line" :span="1">~</el-col>
                <el-col :span="11">
                <el-date-picker type="date" placeholder="请选择结束" v-model="object.orderDateEnd" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="创建人">
                <el-input placeholder="创建人" v-model="object.createByName"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="(1)">查询</el-button>
                <el-button icon="el-icon-refresh-right" >重置</el-button>
                 <el-button type="text" icon="el-icon-plus" @click="handleAdd">添加</el-button>
            <el-button type="text" icon="el-icon-download" style="margin-left:18px">导出</el-button>
            <el-button type="text" icon="el-icon-download" style="margin-left:18px">导入</el-button>
            </el-form-item>
        </el-form>
        </div>

  </div>
  <el-table stripe="true" :data="list" border style="width: 100%;text-align: center;">
    <el-table-column fixed prop="index" label="#" width="60">
        <template #default="scope">
        {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
        </template>
      </el-table-column>
    <el-table-column prop="orderNo" label="单据编号" width="120">
    </el-table-column>
    <el-table-column prop="orderDate" label="单据日期" width="150">
    </el-table-column>
    <el-table-column prop="totalNumber" label="总数量" width="120">
    </el-table-column>
    <el-table-column prop="totalPrice" label="总金额" width="120">
    </el-table-column>
    <el-table-column prop="bankAccountName" label="银行账户" width="120">
    </el-table-column>
    <el-table-column prop="remark" label="备注" width="120">
    </el-table-column>
    <el-table-column prop="createByName" label="创建人" width="120">
    </el-table-column>
    <el-table-column prop="updateByName" label="修改人" width="120">
    </el-table-column>
    <el-table-column prop="updateDate" label="修改时间" width="120">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template slot-scope="scope">
        <el-button @click="handleUpdate(scope.row.id)" type="text">编辑
        </el-button>
        <el-dropdown>
          <span class="el-dropdown-link">
            更多<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>删除</el-dropdown-item>
            <el-dropdown-item>作废</el-dropdown-item>
            <el-dropdown-item @click.native="printSaleOrder(scope.row.orderNo)">打印</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
    </el-table-column>
  </el-table>
  <div class="block">
    <p>
      <el-pagination background layout="prev, pager, next" :total="pageInfo.total" :page-size=pageInfo.size @current-change="handleCurrentChange" style="float: right;"></el-pagination>
      <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
    </p>

    <!-- 添加销售订单 -->
    <el-dialog
      title="销售订单-添加"
      :visible.sync="dialogFormVisible"
      width="1000px"
      @handleDialogFormVisible="changeDialogFormVisible"
    >
      <AddSaleOrder></AddSaleOrder>
    </el-dialog>

  </div>
</div>
</template>

<script>
import {initSaleOrder} from "../../api/saleOrder.js";
import AddSaleOrder from "../sale/AddSaleOrder.vue";
export default {
  name:"SaleOrder",
  components: {
    AddSaleOrder,
  },
  data(){
    return{
      object:{
            orderNo:"",
            orderDateBegin:"",
            orderDateEnd:"",
            createByName:"",
            currentPage:1, 
        },
        pageInfo:"",
        list:"",
        dialogFormVisible:false,
    }
  },
  mounted() {
    this.initSaleOrderByPage(1);
  },
  methods: {
    async initSaleOrderByPage(currentPage) {
        this.object.currentPage=currentPage;
        let data = await initSaleOrder(this.object);
        this.pageInfo=data.data;
        this.list = data.data.list;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.initMedicineListByPage(this.currentPage);
    },
    printSaleOrder(orderNo){
      // this.$router.push("/printSaleOrder");
      const newPage= this.$router.resolve({ 
        path: "/printSaleOrder",
        query:{ //要传的参数 可传多个
        orderNo:orderNo
      }})
      window.open(newPage.href,'_blank')
    },
    handleAdd(){
      this.dialogFormVisible=true;
    },
    changeDialogFormVisible(val){
      console.log("123",val)
        this.dialogFormVisible=val;
    }
  }
}
</script>

<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
    margin-left: 10px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>