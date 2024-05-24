<script>
import {getStorehouseList,getReportedType,addKcReported,getKcMedicine,addKcReportedAll} from '@/api/KcReported';
import { Message } from "element-ui";
export default {
  name: "AddReported",
  data() {
    return {
      // 登录用户
      loginUser:1,
      // 仓库列表
      storehouseList:[],
      // 报损类型列表
      reportedTypeList:[],
      // 添加报损表单验证
      rules: {
        storehouseId: [
          { required: true, message: "请选择仓库", trigger: "blur" },
        ],
        reportedTypeId: [
          { required: true, message: "请选择类别", trigger: "blur" },
        ],


      },
      // 添加报损数据
      addReportedData:{
        code:'',
        storehouseId:'',
        reportedTypeId:'',
        documenterBy:1,
        list:[]
      },
      // 添加报损详情
      addReportedDetail:false,
      // 药品列表
      medicineList:{
        pageNum:1,
        pageSize:5,
        total:0,
        list:[]
      },
      // 药品名称
      medicineName:'',
      //报损单药品列表
      reportedDetailList:[],
      // 将要报损药品列表
      wereAddList:[]
    }
  },
  mounted() {
    // 初始化仓库列表
    this.getStorehouseLists()
    // 初始化报损类型列表
    this.getReportedTypeLists()
    // 初始化报损编号
    this.getCode()
  },
  methods: {
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
        console.log(resp.data)
        this.reportedTypeList=resp.data
      })
    },
    //随机生成报损编号
    getCode(){
      let code = "BX" + new Date().getTime() + Math.floor(Math.random() * 1000);
      this.addReportedData.code = code;
    },
    // 取消
    cancel() {
      this.$emit("cancel");
    },
    // 获取药品列表
    getKcMedicines(){
      if (this.addReportedData.storehouseId==''){
        Message({
          message: '请选择仓库',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      const kcMedicine = {
        storehouseId:this.addReportedData.storehouseId,
        medicineName:this.medicineName,
      }
      getKcMedicine(kcMedicine,this.medicineList.pageNum,this.medicineList.pageSize).then(resp=>{
        console.log(resp.data)
        if (resp.code==200){

          this.medicineList=resp.data
          this.addReportedDetail=true
        }else {
          Message({
            message: '该仓库没有药品',
            type: 'error',
            duration: 5 * 1000
          })
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
    // 提交报损
    submitForm(addReported) {
      if (this.wereAddList.length==0){
        Message({
          message: '请添加药品',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      this.$refs[addReported].validate((valid) => {
        if (valid) {
          this.addReportedData.list=this.wereAddList
          addKcReportedAll(this.addReportedData).then(resp=>{
            if (resp.code!=200){
              Message({
                message: resp.message,
                type: 'error',
                duration: 5 * 1000
              })
            }
            Message({
              message: '添加成功',
              type: 'success',
              duration: 5 * 1000
            })
            this.getCode()
            this.wereAddList=[];
            this.addReportedData.storehouseId = '';
            this.addReportedData.reportedTypeId = '';
            this.$emit("handleAddSuccess");

          })
        }
      })
    },
  },
  computed: {
    // 计算属性，根据wereAddList的长度决定是否禁用仓库选择框
    disabledStorehouse() {
      return this.wereAddList.length > 0;
    }
  }
}
</script>

<template>
  <div>
    <el-form :model="addReportedData" :rules="rules" ref="addReported" label-width="100px" class="demo-ruleForm">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="报损编号" prop="code">
            <el-input v-model="addReportedData.code" placeholder="请输入报损编号" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="报损仓库" prop="storehouseId">
            <el-select v-model="addReportedData.storehouseId" placeholder="请选择报损仓库" name="storehouseId" :disabled="disabledStorehouse">
              <el-option
                  v-for="item in storehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="报损类型" prop="reportedTypeId">
            <el-select v-model="addReportedData.reportedTypeId" placeholder="请选择报损类型" name="reportedTypeId">
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
          <el-table-column prop="money" label="药品单价" width="120">
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
        <el-button type="primary" @click="submitForm('addReported')">立即创建</el-button>
        <el-button @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>