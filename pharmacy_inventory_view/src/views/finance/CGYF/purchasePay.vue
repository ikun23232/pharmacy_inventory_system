<script>
import {getCwCgyfList,getCgddByCode} from '@/api/finance';
export default {
  name: "purchasePay",
  data() {
    return {
      // 分页
      cwCgyfPage: {
        pageNum:1,
        pageSize:3,
        total:0,
        list:[]
      },
      // 采购应付查询数据
      cwCgyf:{
        code:'',
        cgddCode:''
      },
      //采购订单数据
      cgdd:{
        code:'',//采购订单编号
        subject:'',//采购订单主题
        orderTypeName:'',//采购订单类型名称
        demanderName:'',//需求人名
        demandTime:'',//需求时间
        count:'',//数量
        referenceAmount:'',//参考金额
        orderStatus:'',//订单状态
        remark:'',//备注
        effectiveTime:'',//生效时间
        approverName:'',//审批人
        approverRemark:'',//审批备注
        documenterName:'',//制单人
        voidState:'',//作废状态
        createTime:'',//创建时间
        updateName:'',//更新人
        updateTime:'',//更新时间
        providerName:'',//供应商名称
        contactperson:'',//联系人
        phone:'',//联系电话
        fax:'',//传真
        email:'',//邮箱
        deliveryName:'',//交货人名字
        payType:'',//付款方式
        payTime:'',//付款时间
        isPay:'',//是否付款

      },
      cgddVisible:false,
    }
  },
  mounted() {
    this.getCwCgyfLists();
  },
  methods: {
    getCwCgyfLists(){
      getCwCgyfList(this.cwCgyf,this.cwCgyfPage.pageNum,this.cwCgyfPage.pageSize).then(resp=>{
        console.log(resp)
        if (resp.code!=200){// 失败
          this.cwCgyfPage.list=[]
          this.cwCgyfPage.total=0
          this.cwCgyfPage.pageNum=1
          return
        }
        this.cwCgyfPage=resp.data
      })
    },
    formatPayStatus(row, column) {
      // 根据isPay的值返回对应的状态文本
      switch (row.isPay) {
        case 0:
          return '未知';
        case 1:
          return '待付款';
        case 2:
          return '已付款';
        default:
          return '未知状态';
      }
    },
    getCgddByCodes(row){
      getCgddByCode(row.cgddCode).then(resp=>{
        console.log(resp)
        if (resp.code!=200){// 失败
          return
        }
        this.cgdd=resp.data
        this.cgddVisible=true
      })
    },
    clean() {
      this.cwCgyf.code = "";
      this.cwCgyf.cgddCode = "";
      this.getCwCgyfLists();
    }
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
              v-model="cwCgyf.code"
              style="width: 200px"
              placeholder="请输入单据编号"
          ></el-input></div
        ></el-col>
        <el-col :span="6"
        ><div class="grid-content bg-purple">
          单据编号：
          <el-input
              v-model="cwCgyf.cgddCode"
              style="width: 200px"
              placeholder="请输入原单据编号"
          ></el-input></div
        ></el-col>


      </el-row>
      <el-button type="primary" @click="getCwCgyfLists()">查询</el-button>
      <el-button type="primary" @click="clean()">清空</el-button>
    </div>
    <div class="table">
      <el-table :data="cwCgyfPage.list" border style="width: 100%">
        <el-table-column prop="id" label="采购应付id" width="120"/>
        <el-table-column prop="code" label="采购应付编号" width="150" fixed/>
        <el-table-column prop="cgddCode" label="采购订单编号" width="150" fixed/>
        <el-table-column prop="providerName" label="供应商" width="120"/>
        <el-table-column prop="createTime" label="单号生成时间" width="120"/>
        <el-table-column prop="paymentTime" label="付款时间" width="120"/>
        <el-table-column prop="cost" label="应付金额" width="120"/>
        <el-table-column prop="isPay" label="是否支付" width="120" :formatter="formatPayStatus"/>
        <el-table-column align="center" label="操作" fixed="right" width="200">
          <template #default="{ row }">
            <el-button type="primary" plain>付款</el-button>&nbsp;
            <el-button type="primary" plain @click="getCgddByCodes(row)">详情</el-button>&nbsp;
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
          @current-change="getCwCgyfLists()"
          :current-page.sync="cwCgyfPage.pageNum"
          :page-size="cwCgyfPage.pageSize"
          layout="prev, pager, next, jumper"
          :total="cwCgyfPage.total"
      >
      </el-pagination>
    </div>


    <div>
      <el-dialog title="采购订单详情" v-if="cgddVisible" :visible.sync="cgddVisible" width="60%">
        <el-form>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="采购订单编号">
                <el-input v-model="cgdd.code" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="采购订单主题">
                <el-input v-model="cgdd.subject" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="采购订单类型名称">
                <el-input v-model="cgdd.orderTypeName" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="需求人名">
                <el-input v-model="cgdd.demanderName" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="需求时间">
                <el-input v-model="cgdd.demandTime" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="数量">
                <el-input v-model="cgdd.count" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="参考金额">
                <el-input v-model="cgdd.referenceAmount" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="订单状态">
                <el-input v-model="cgdd.orderStatus" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="备注">
                <el-input v-model="cgdd.remark" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="生效时间">
                <el-input v-model="cgdd.effectiveTime" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="审批人">
                <el-input v-model="cgdd.approverName" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="审批备注">
                <el-input v-model="cgdd.approverRemark" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="联系电话">
                <el-input v-model="cgdd.phone" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="传真">
                <el-input v-model="cgdd.fax" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="电子邮箱">
                <el-input v-model="cgdd.email" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="联系人">
                <el-input v-model="cgdd.contactperson" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="供应商">
                <el-input v-model="cgdd.providerName" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="交货日期">
                <el-input v-model="cgdd.deliveryDate" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="付款类型">
                <el-input v-model="cgdd.payType" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="付款时间">
                <el-input v-model="cgdd.payTime" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="付款金额">
                <el-input v-model="cgdd.referenceAmount" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="付款状态">
                <el-input v-model="cgdd.isPay" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="付款备注">
                <el-input v-model="cgdd.remark" disabled/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="付款时间">
                <el-input v-model="cgdd.payTime" disabled/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>
    </div>

  </div>
</template>

<style scoped>

</style>