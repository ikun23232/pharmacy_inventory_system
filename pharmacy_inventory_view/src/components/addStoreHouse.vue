<template>
    <span slot="footer" class="dialog-footer">
    <el-form
      :model="storeHouse"
      :rules="rules"
      ref="storeHouse"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="仓库名称" prop="name">
        <el-input type="text" placeholder="请输入仓库名称" v-model="name"></el-input>
      </el-form-item>
      <input name="serialNumber" type="hidden" v-model="reOrder.serialNumber" />
      <el-form-item>
        <el-button type="primary" @click="submitForm('reOrder')"
          >立即添加</el-button
        >
        <el-button @click="dialogVisibleUpdata = false">取 消</el-button>
      </el-form-item>
    </el-form>
  </span>
</template>

<script>
export default {
    name:"storeHouse",
    data() {
        return {
            storeHouse:{
                name:"",
                address:"",
                capacity:"",
                userId:""
            }
        }
    },
}
</script>

<style>

</style>