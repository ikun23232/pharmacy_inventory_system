<!--<template>-->
<!--  <span slot="footer" class="dialog-footer">-->
<!--    <el-form-->
<!--      :model="users"-->
<!--      :rules="rules"-->
<!--      ref="users"-->
<!--      label-width="100px"-->
<!--      class="demo-ruleForm"-->
<!--    >-->
<!--      <el-form-item label="账户名" prop="username">-->
<!--        <el-input-->
<!--          type="text"-->
<!--          placeholder="请输入账户名"-->
<!--          v-model="users.username"-->
<!--        ></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="密码" prop="password">-->
<!--        <el-input-->
<!--          type="text"-->
<!--          placeholder="请输入密码"-->
<!--          v-model="users.password"-->
<!--        ></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="仓库容量" prop="capacity">-->
<!--        <el-input-->
<!--          type="text"-->
<!--          placeholder="请输入仓库容量"-->
<!--          v-model.number="users.capacity"-->
<!--        ></el-input>-->
<!--      </el-form-item>-->
<!--      &lt;!&ndash; <el-form-item label="负责人" prop="userId">-->
<!--        -->
<!--      </el-form-item> &ndash;&gt;-->
<!--      <el-form-item style="width: 500px">-->
<!--        <el-button-->
<!--          class="anniu"-->
<!--          size="mini"-->
<!--          type="primary"-->
<!--          @click="submitForm('users')"-->
<!--          >立即添加</el-button-->
<!--        >-->
<!--        <el-button class="anniu" size="mini" @click="resetForm('users')"-->
<!--          >重置</el-button-->
<!--        >-->
<!--        <el-button class="anniu" size="mini" @click="cancel()">取 消</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--  </span>-->
<!--</template>-->

<!--&lt;!&ndash; <script>-->
<!--import { addStoreHouse, checkName } from "@/api/storeHouse.js";-->
<!--export default {-->
<!--  name: "users",-->
<!--  data() {-->
<!--    var check = (rule, value, callback) => {-->
<!--      let data = checkName(value).then((resp) => {-->
<!--        console.log(resp.data);-->
<!--        if (resp.code == 200) {-->
<!--          callback(new Error("仓库名已经存在"));-->
<!--        } else {-->
<!--          callback();-->
<!--        }-->
<!--      });-->
<!--    };-->
<!--    return {-->
<!--      users: {-->
<!--        name: "",-->
<!--        address: "",-->
<!--        capacity: "",-->
<!--        userId: 1,-->
<!--      },-->
<!--      rules: {-->
<!--        name: [-->
<!--          { required: true, message: "请输入仓库名称", trigger: "blur" },-->
<!--          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },-->
<!--          { validator: check, trigger: "blur" },-->
<!--        ],-->
<!--        address: [-->
<!--          { required: true, message: "请输入仓库地址", trigger: "blur" },-->
<!--          { min: 3, max: 5, message: "长度在 2 到 10 个字符", trigger: "blur" },-->
<!--        ],-->
<!--        capacity: [-->
<!--          { required: true, message: "仓库容量不能为空" },-->
<!--          { type: "number", min:1,max:10000, message: "仓库容量必须为范围在1-10000的整数" },-->
<!--        ],-->
<!--      },-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    submitForm(formName) {-->
<!--      this.$refs[formName].validate((valid) => {-->
<!--        if (valid) {-->
<!--          addStoreHouse(this.users).then((resp) => {-->
<!--            console.log(resp.data);-->
<!--          });-->
<!--        } else {-->
<!--          console.log("error submit!!");-->
<!--          return false;-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    resetForm(formName) {-->
<!--      this.$refs[formName].resetFields();-->
<!--    },-->
<!--    cancel() {-->
<!--      this.$emit("cancel");-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script> &ndash;&gt;-->

<!--<style>-->
<!--.anniu {-->
<!--  float: left;-->
<!--}-->
<!--</style>-->