<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :xl="6" :lg="7">
      <h2>登录</h2>
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
    <div style="width: 90%;margin: 0 auto;text-align: center">
      <el-divider>其他方式登录</el-divider>
      <div class="my-icon">
        <i class="iconfont iconqq qq"></i>
        <i class="iconfont iconweixin weixin"></i>
        <i class="iconfont icondingding01 dingding"></i>
      </div>
    </div>
  </el-row>
</template>

<script>
import ValidCode from "@/utils/ValidCode";
import { getLogin } from "@/api/test.js";

export default {
  name: "Login",
  components: {
    ValidCode,
  },
  data() {
    const validateCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else if (value.toLowerCase() !== this.code) {
        callback(new Error("验证码错误"));
      } else {
        callback();
      }
    };

    return {
      code: "",
      loginForm: {
        username: "",
        password: "",
        code: "",
        token: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
          {
            pattern: /^.{2,10}$/,
            message: "用户名长度为2-10个字符",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
          {
            pattern: /^.{4,15}$/,
            message: "密码长度为4-15个字符",
            trigger: "blur",
          },
        ],
        code: [
          {
            validator: validateCode,
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    getCode(code) {
      this.code = code.toLowerCase();
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getLogin(this.loginForm).then((res) => {
            loading.close();
            if (res.code == "200") {
              this.$store.commit("SET_TOKEN", res.data.tokenValue);
              let hours = new Date().getHours();
                  let str;
                  if (hours > 7 && hours < 12) {
                    str = '上午好!'
                  } else if (hours >= 12 && hours <= 13) {
                    str = '中午好!'
                  } else if (hours > 13 && hours <= 18) {
                    str = '下午好!'
                  } else if (hours > 18 && hours < 22) {
                    str = '晚上好!'
                  } else {
                    str = '晚上好!'
                  }
                  this.$notify({title: str, message: "欢迎登录", type: 'success', offset: 50});
              this.$router.push("/index");
            } else {
              this.$message.error(res.message);
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