<script>
import {getStorehouseList,getReportedType,addKcReported,getKcMedicine} from '@/api/KcReported';
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
        documenterBy:1
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
        this.reportedTypeList=resp.data
      })
    },
    //随机生成报损编号
    getCode(){
      let code = "BX" + new Date().getTime() + Math.floor(Math.random() * 1000);
      this.addReportedData.code = code;
    },
    // 添加报损
    submitForm(addReported) {
      this.$refs[addReported].validate((valid) => {
        if (valid) {
          addKcReported(this.addReportedData).then(resp=>{
            if (resp.code!=200){
              Message({
                message: resp.message,
                type: 'error',
                duration: 5 * 1000
              })
              return
            }
            Message({
              message: '添加成功',
              type: 'success',
              duration: 5 * 1000
            })
            this.getCode()
            this.addReportedData.storehouseId = '';
            this.addReportedData.reportedTypeId = '';
            this.$emit("handleAddSuccess");
         })
       }
      })
    },
    // 取消
    cancel() {
      this.$emit("cancel");
    },
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
        }
      })

    }
  }
}
</script>

<template>
  <div>
    <el-form :model="addReportedData" :rules="rules" ref="addReported" label-width="100px" class="demo-ruleForm">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="报损编号" prop="code">
            <el-input v-model="addReportedData.code" placeholder="请输入报损编号" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="报损仓库" prop="storehouseId">
            <el-select v-model="addReportedData.storehouseId" placeholder="请选择报损仓库" name="storehouseId">
              <el-option
                  v-for="item in storehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="报损类型" prop="reportedTypeId">
            <el-select v-model="addReportedData.reportedTypeId" placeholder="请选择报损类型" name="reportedTypeId">
              <el-option
                  v-for="item in reportedTypeList"
                  :key="item.id"
                  :label="item.reportedname"
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
        <el-table
            :data="medicineList.list"
            border
        >
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="medicineId" label="药品id" width="120">
          </el-table-column>
          <el-table-column prop="medicineName" label="药品名称" width="240">
          </el-table-column>
          <el-table-column prop="providerName" label="供应商名称" width="240">
          </el-table-column>
          <el-table-column prop="batchCode" label="批次编号" width="120">
          </el-table-column>
          <el-table-column prop="quantity" label="药品库存" width="120">
          </el-table-column>
          <el-table-column prop="money" label="药品金额" width="120">
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




    <el-table
        :data="reportedDetailList"
        border
        style="width: 1000px"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="subject" label="药品id" width="120">
      </el-table-column>
      <el-table-column prop="cgtype" label="药品名称" width="240">
      </el-table-column>
      <el-table-column prop="cgtype" label="药品名称" width="240">
      </el-table-column>
      <el-table-column prop="cgtype" label="报损数量" width="120">
      </el-table-column>
      <el-table-column prop="cgtype" label="药品总数" width="120">
      </el-table-column>
      <el-table-column prop="cgtype" label="报损金额" width="120">
      </el-table-column>
    </el-table>

    <el-form>
      <el-form-item>
        <el-button type="primary" @click="submitForm('addReported')">立即创建</el-button>
        <el-button @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.centered-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;      /* 垂直居中 */
}
</style>