<script>
import {getKcMedicine, getReportedType, getStorehouseList,getKcMedicineByReportedCode} from "@/api/KcReported";
import {Message} from "element-ui";
import kcReported from "@/views/warehouse/KCBC/KcReported.vue";

export default {
  name: "DetailsReported",
  data() {
    return {
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
    // 获取报损药品
    getKcMedicineByReportedCodes(){
      if (!this.reportedData.code || !this.reportedData.storehouseId){
        return
      }
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
        this.wereAddList[i].allPrice=this.wereAddList[i].reportedNum*this.wereAddList[i].money
      }
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
            <el-input v-model="reportedData.reportedTypeName" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="报损标题" prop="title">
            <el-input v-model="reportedData.title" placeholder="请输入报损主题" disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="报损人" prop="documenterName">
            <el-input v-model="reportedData.documenterName" disabled ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="审批人" prop="modificationBy">
            <el-input v-model="reportedData.modificationBy" disabled ></el-input>
          </el-form-item>
        </el-col>


      </el-row>
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="报损审批">
            <el-select v-model="reportedData.approvalStatus" placeholder="请选择审批状态" name="approvalStatus" disabled>
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
          <el-input v-model="reportedData.approverRemark" placeholder="无" name="approvalReason" disabled></el-input>
        </el-col>
      </el-row>

    </el-form>
    <hr/>

    <el-table :data="wereAddList" border style="width: 1000px">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="药品id" width="80">
      </el-table-column>
      <el-table-column prop="medicineName" label="药品名称" width="150">
      </el-table-column>
      <el-table-column prop="batchCode" label="批次编号" width="150">
      </el-table-column>
      <el-table-column prop="reportedNum" label="报损数量" width="110">
      </el-table-column>
      <el-table-column prop="allPrice" label="报损价值" width="120">
      </el-table-column>
      <el-table-column prop="money" label="药品单价" width="90">
      </el-table-column>
      <el-table-column prop="quantity" label="药品库存" width="90">
      </el-table-column>

    </el-table>

    <el-form>
      <br/><br/>
      <el-form-item>
        <el-button @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>