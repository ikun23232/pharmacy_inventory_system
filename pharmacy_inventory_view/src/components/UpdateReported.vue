<script>
import {getKcMedicine, getReportedType, getStorehouseList,getKcMedicineByReportedCode,updateReportedByCode} from "@/api/KcReported";
import {Message} from "element-ui";
import kcReported from "@/views/warehouse/KCBC/KcReported.vue";

export default {
  name: "UpdateReported",
  data() {
    return {
      // 登录用户
      loginUser:1,
      // 仓库列表
      storehouseList:[],
      // 报损类型列表
      reportedTypeList:[],
      // 报损修改数据
      reportedData:{},
      // 添加报损详情
      addReportedDetail:false,
      // 将要报损药品列表
      wereAddList:[],
      // 药品名称
      medicineName:'',
      // 药品列表
      medicineList:{
        pageNum:1,
        pageSize:5,
        total:0,
        list:[]
      },

    }
  },
  props: {
    // 定义一个名为 rowData 的 prop，并指定它应该是一个对象
    rowData: {
      type: Object,
      required: true
    }
  },
  // 使用 mounted 生命周期钩子来在组件挂载后打印 rowData
  mounted() {
    this.reportedData = this.rowData;
    getStorehouseList().then(resp=>{
      if (resp.code!=200){
        return
      }
      this.storehouseList=resp.data
    })
    getReportedType().then(resp=>{
      if (resp.code!=200){
        return
      }
      this.reportedTypeList=resp.data
    })
    this.getKcMedicineByReportedCodes()

  },
  watch: {
    // 观察 rowData 属性的变化
    rowData: {
      handler(newValue) {
        // 当 rowData 更改时，更新 reportedData
        this.reportedData = Object.assign({}, newValue);
        this.wereAddList= []
        this.getKcMedicineByReportedCodes()

      },
      deep: true // 使用深度观察来检测嵌套数据的变化
    }
  },
  methods: {
    // 取消
    cancel() {
      this.$emit("cancel");
    },
    // 获取药品列表
    getKcMedicines(){
      if (this.reportedData.storehouseId==''){
        Message({
          message: '请选择仓库',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      const kcMedicine = {
        storehouseId:this.reportedData.storehouseId,
        medicineName:this.medicineName,
      }
      getKcMedicine(kcMedicine,this.medicineList.pageNum,this.medicineList.pageSize).then(resp=>{
        if (resp.code==200){

          this.medicineList=resp.data
          this.addReportedDetail=true
        }
      })

    },
    // 添加报损详情到待添加列表
    addReportedDetails(row){
      if (row.quantity<=0){
        Message({
          message: '该药品库存不足',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      row.reportedNum=1
      row.allPrice=row.money * row.reportedNum
      if (this.wereAddList.length==0){
        this.wereAddList.push(row)
      }else if (this.wereAddList.length>4){
        Message({
          message: '报损单已满上限5个',
          type: 'error',
          duration: 5 * 1000
        })
      }else{
        for (let i=0;i<this.wereAddList.length;i++){
          if (this.wereAddList[i].id==row.id){
            Message({
              message: '该药品已添加',
              type: 'error',
              duration: 5 * 1000
            })
            return
          }
        }
        this.wereAddList.push(row)
        Message({
          message: '添加成功',
          type: 'success',
          duration: 5 * 1000
        })

      }
    },
    // 报损数量改变
    handleDamageChange(row) {
      // console.log(this.wereAddList)
      // 确保报损数量不大于库存数量
      row.reportedNum = Math.min(row.reportedNum, row.quantity);
      // 计算总价，这里假设row.onePrice是药品的单价
      row.allPrice=row.money*row.reportedNum;
    },
    // 删除报损详情
    removeReportedDetails(row){
      for (let i=0;i<this.wereAddList.length;i++){
        if (this.wereAddList[i].id==row.id){
          this.wereAddList.splice(i,1)
        }
      }
    },
    // 获取报损药品
    getKcMedicineByReportedCodes(){
      getKcMedicineByReportedCode(this.reportedData.storehouseId,this.reportedData.code).then(resp=>{
        if (resp.code!=200){
          return
        }
        this.wereAddList=resp.data
        this.getNumByReported()
      })
    },
    getNumByReported(){
      for (let i=0;i<this.wereAddList.length;i++){
        this.wereAddList[i].allPrice=this.wereAddList[i].quantity*this.wereAddList[i].money
      }
    },
    updateReporteds(){
      if (this.wereAddList.length==0){
        Message({
          message: '请添加药品',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      for (let i = 0; i < this.wereAddList.length; i++) {
        const item = this.wereAddList[i];
        if (item.quantity === 0) {
          Message({
            message: item.medicineName + '库存不足请移除该报损',
            type: 'error',
            duration: 5 * 1000
          });
          return; // 退出方法
        }
      }
      this.reportedData.modificationBy=this.loginUser;
      const theData = {
        kcReported:this.reportedData,
        kcMedicineList:this.wereAddList
      }
      updateReportedByCode(theData).then(resp=>{
        if (resp.code!=200){
          return
        }
        Message({
          message: '修改成功',
          type: 'success',
          duration: 5 * 1000
        })
        this.$emit("handleAddSuccess");
      })
    }

 },

}
</script>

<template>
  <div>
    <el-form :model="reportedData" ref="addReported" label-width="100px" class="demo-ruleForm">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="报损编号" prop="code">
            <el-input v-model="reportedData.code" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="报损仓库" prop="storehouseName">
            <el-input v-model="reportedData.storehouseName" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="报损类型" prop="reportedTypeId">
            <el-select v-model="reportedData.reportedTypeId" placeholder="请选择报损类型" name="reportedTypeId">
              <el-option
                  v-for="item in reportedTypeList"
                  :key="item.id"
                  :label="item.reportedName"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="报损审批">
            <el-select v-model="reportedData.approvalStatus" placeholder="请选择审批状态" name="approvalStatus">
              <el-option
                  key="0"
                  label="未审批"
                  :value=0>
              </el-option>
              <el-option
                  key="1"
                  label="未通过"
                  :value=1>
              </el-option>
              <el-option
                  key="2"
                  label="通过"
                  :value=2>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span=11>
          <el-input v-model="reportedData.approverRemark" placeholder="请输入审批理由" name="approvalReason"></el-input>
        </el-col>
      </el-row>

    </el-form>
    <hr/>
    <el-button type="primary" @click="getKcMedicines()">添加报损内容</el-button><br/><br/>
    <el-dialog title="添加报损详情" :visible.sync="addReportedDetail" width="1200px">
      <el-form>
        <el-form-item>
          <el-input v-model="medicineName" placeholder="请输入药品关键字" style="width: 200px"></el-input>
          <el-button @click="getKcMedicines()">查询</el-button>
        </el-form-item>
        <el-table :data="medicineList.list" border>
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="medicineId" label="药品id" width="60">
          </el-table-column>
          <el-table-column prop="medicineName" label="药品名称" width="180">
          </el-table-column>
          <el-table-column prop="providerName" label="供应商名称" width="180">
          </el-table-column>
          <el-table-column prop="batchCode" label="批次编号" width="120">
          </el-table-column>
          <el-table-column prop="quantity" label="药品库存" width="120">
          </el-table-column>
          <el-table-column prop="money" label="药品金额" width="120">
          </el-table-column>
          <el-table-column align="center" label="操作" fixed="right" width="200">
            <template #default="{ row }">
              <el-button type="primary" plain @click="addReportedDetails(row)">添加该药品报损</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-form-item>
          <br/>
          <el-pagination
              :current-page="medicineList.pageNum"
              :page-size="medicineList.pageSize"
              background
              layout="prev, pager, next"
              :total="medicineList.total"
              @current-change="getKcMedicines">
          </el-pagination>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-table :data="wereAddList" border style="width: 1000px">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="药品id" width="80">
      </el-table-column>
      <el-table-column prop="medicineName" label="药品名称" width="150">
      </el-table-column>
      <el-table-column prop="batchCode" label="批次编号" width="150">
      </el-table-column>
      <el-table-column prop="quantity" label="报损数量" width="110">
        <template slot-scope="scope">
          <el-input-number
              v-model="scope.row.reportedNum"
              :min="1"
              :max="parseInt(scope.row.quantity)"
              label="报损数量"
              :controls="false"
              @input="handleDamageChange(scope.row)"
              style="width: 80px"
          />
        </template>
      </el-table-column>
      <el-table-column prop="allPrice" label="药品总价" width="120">
      </el-table-column>
      <el-table-column prop="money" label="药品单价" width="90">
      </el-table-column>
      <el-table-column prop="quantity" label="药品库存" width="90">
      </el-table-column>
      <el-table-column align="center" label="操作" fixed="right" width="150" >
        <template #default="{ row }">
          <el-button type="danger" plain @click="removeReportedDetails(row)">移除该药品</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-form>
      <br/><br/>
      <el-form-item>
        <el-button type="primary" @click="updateReporteds()">保存</el-button>
        <el-button @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>