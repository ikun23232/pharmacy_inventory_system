<template>
  <div id="saleOutWarehouse">
  <h1>销售出库</h1>
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
    <el-table-column prop="code" label="单据编号" width="150">
      <template slot-scope="scope">
        <el-link type="primary" :underline="false" @click.native="lookSaleOrderDetail(scope.row.xsOrder.orderNo)">{{ scope.row.code }}</el-link>
      </template>
    </el-table-column>
    <el-table-column prop="xsOrder.orderDate" label="单据日期" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.orderNo" label="源单号" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.totalNumber" label="出库总数量" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.totalPrice" label="出库总金额" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.remark" label="备注" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.createByName" label="创建人" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.updateByName" label="修改人" width="120">
    </el-table-column>
    <el-table-column prop="xsOrder.updateDate" label="修改时间" width="120">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template slot-scope="scope">
        <el-button @click="printOrder(scope.row.xsOrder.orderNo)" type="text">
          打印
        </el-button>
        <el-button @click="handleDelete(scope.row.code)" type="text">
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="block">
    <p>
      <el-pagination background layout="prev, pager, next" :total="pageInfo.total" page-size=5 @current-change="handleCurrentChange" style="float: right;"></el-pagination>
      <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
    </p>
  </div>
   <!-- 查看销售订单详情 -->
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

<script>
import {initSaleOutWarehouseListByPage,deleteSaleOutWarehouseOrder} from  "../../../api/saleOutWarehouse.js";
import SaleOrderDetail from "../../sale/SaleOrderDetail.vue";
import { Message } from "element-ui";

export default {
  name:"saleOutWarehouse",
  components: {
    SaleOrderDetail
  },
  data(){
    return{
      orderNo:"",
      object:{
            code:"",
            orderDateBegin:"",
            orderDateEnd:"",
            createByName:"",
            currentPage:1, 
        },
        pageInfo:"",
        list:"",
        detailDialogFormVisible:false,
    }
  },
  mounted() {
    this.initSaleOutWarehouseListByPage(1);
  },
  methods: {
    async initSaleOutWarehouseListByPage(currentPage) {
        this.object.currentPage=currentPage;
        let data = await initSaleOutWarehouseListByPage(this.object);
        this.pageInfo=data.data;
        this.list = data.data.list;
        console.log("123",this.list)
    },
    handleCurrentChange(val) {
      this.object.currentPage = val;
      this.initSaleOutWarehouseListByPage(this.object.currentPage);
    },
    printOrder(orderNo){
      const newPage= this.$router.resolve({ 
        path: "/printSaleOutWarehouse",
        query:{ //要传的参数 可传多个
        orderNo:orderNo
      }})
      window.open(newPage.href,'_blank')
    },
    lookSaleOrderDetail(orderNo){
      this.detailDialogFormVisible=true;
      this.orderNo=orderNo;
    },
    changeDialogFormVisible(val){
      this.detailDialogFormVisible=val;
      this.initSaleOrderByPage(this.object.currentPage);
    },
    async handleDelete(code){
      this.$messagebox
        .confirm("此操作将删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          try {
            let data = await deleteSaleOutWarehouseOrder(code);
            if (data.code === "200") {
              Message({type: 'success',message: '删除成功！'})
              this.initSaleOrderByPage(this.object.currentPage);
            } else {
              Message({type: 'error',message: '删除失败！'})
            }
          } catch (error) {
            Message({type: 'error',message: '删除失败！'})
          }
        })
        .catch(() => {
          Message({
            type: 'success',
            message: '取消删除成功！'
          })
        });
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