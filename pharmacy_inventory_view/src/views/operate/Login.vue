<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :xl="6" :lg="7">
      <h2>欢迎来到医药商品进销存管理系统</h2>
    </el-col>

    <el-col :span="1">
      <el-divider direction="vertical"></el-divider>
    </el-col>
    <el-col :xl="6" :lg="7">
      <el-form
        :model="loginForm"
        :rules="rules"
        ref="loginForm"
        label-width="80px"
      >
        <el-form-item label="用户名" prop="username" style="width: 380px">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="width: 380px">
          <el-input v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div style="display: flex">
            <el-input
              placeholder="请输入验证码"
              prefix-icon="el-icon-circle-check"
              size="medium"
              style="flex: 1"
              v-model="loginForm.code"
            ></el-input>
            <div style="flex: 1; height: 36px">
              <valid-code @update:value="getCode" />
            </div>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')"
            >登录</el-button
          >
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>

import ValidCode from "@/utils/ValidCode"
import { getLogin } from "@/api/test.js";

export default {
  name: "Login",
  components: {
    ValidCode
  },
  data() {
    const validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if (value.toLowerCase() !== this.code) {
        callback(new Error('验证码错误'))
      } else {
        callback()
      }
    }

    return {
      code:'',
      loginForm: {
        username: "",
        password: "",
        code: "",
        token: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        code: [
          { validator: validateCode, trigger: 'blur' }
        ],
      },
    };
  },
  methods: {
    getCode(code) {
      this.code = code.toLowerCase()
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getLogin(this.loginForm).then((res) => {
            
            if(res.code == "200"){
              this.$store.commit("SET_TOKEN", res.data.tokenValue);
              console.log(res+"1yyyyy");
              console.log(res);
              this.$router.push("/index");
            this.$message.success('登录成功')
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
 
};
</script>

<style scoped>
.el-row {
  background-color: #fafafa;
  height: 100%;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
}

.el-divider {
  height: 200px;
}

.captchaImg {
  float: left;
  margin-left: 8px;
  border-radius: 4px;
}
</style>