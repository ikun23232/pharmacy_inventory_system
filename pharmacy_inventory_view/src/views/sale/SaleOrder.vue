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
                 <el-button type="text" icon="el-icon-plus" @click="handleAdd('addSaleOrder')">添加</el-button>
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
        <el-dropdown-item @click.native="printSaleOrder">打印</el-dropdown-item>
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

    <el-dialog title="" :visible.sync="dialogVisible" width="100%">
      <div id="printView">
        <router-view></router-view>
      </div> 
      <span slot="footer" class="dialog-footer">
        <el-button v-print="printViewInfo" type="primary">打 印</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</div>
</template>

<script>
import {initSaleOrder,addSaleOrder,getSaleOrderById,updateSaleOrder,deleteSaleOrder} from "../../api/saleOrder.js";
export default {
  name:"SaleOrder",
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
        dialogVisible: false,
      msg: "打印",
      printViewInfo: {
        id: "printView", //打印区域的唯一的id属性
        popTitle: '配置页眉标题', // 页眉文字 （不设置时显示undifined）（页眉页脚可以在打印页面的更多设置的选项中取消勾选）
        extraHead: '打印，印刷', // 最左上方的头部文字，附加在head标签上的额外标签，使用逗号分割
        preview: false, // 是否启动预览模式，默认是false （开启预览模式ture会占满整个屏幕，不建议开启，除非业务需要）
        previewTitle: '预览的标题', // 打印预览的 标题(预览模式preview为true时才显示)
        previewPrintBtnLabel: '预览结束，开始打印', // 打印预览的标题下方的按钮文本，点击可进入打印(预览模式preview为true时才显示)
        zIndex: 20002, // 预览窗口的z-index，默认是20002，最好比默认值更高
        previewBeforeOpenCallback (that) { console.log('正在加载预览窗口！'); console.log(that.msg, this) }, // 预览窗口打开之前的callback (预览模式preview为true时才执行) （that可以取到data里的变量值）
        previewOpenCallback () { console.log('已经加载完预览窗口，预览打开了！') }, // 预览窗口打开时的callback (预览模式preview为true时才执行)
        beforeOpenCallback () { console.log('开始打印之前！') }, // 开始打印之前的callback
        openCallback () { console.log('执行打印了！') }, // 调用打印时的callback
        closeCallback () { console.log('关闭了打印工具！') }, // 关闭打印的callback(无法区分确认or取消)
        clickMounted () { console.log('点击v-print绑定的按钮了！') },
        standard: '',
        extarCss: ''
      },
      list:[{
          id:0,
          value:"1"
      }]
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
    printSaleOrder(){
      this.dialogVisible = true
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