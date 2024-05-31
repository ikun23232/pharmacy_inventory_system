<template>
  <div class="login-page" >
    <div class="login-box" v-loading=loading>
      <div class="left-half">
        <div class="logo">
          <div
            style="
              color: #8d92a1;
              margin-left: 55px;
              font-size: 35px;
              margin-top: -11px;
            "
          >
            医药进销存系统
          </div>
        </div>
        <ul class="geek-slide">
          <li>
            <img src="../../.././public/1.jpg" style="position: relative;height: 208px;top: -34px;">
          </li>
        </ul>
      </div>
      <div class="right-half" >
        <div class="form-box">
          <div class="login-form" style="margin-left: -52px; margin-top: 30px">
            <el-header class="header" height="45px">
              <h2 class="title">登录</h2>
            </el-header>
            <div class="form">
              <el-form
                
                :model="loginForm"
                :rules="rules"
                ref="loginForm"
                label-width="80px"
              >
                <el-form-item prop="userAccount">
                  <el-input
                    v-model="loginForm.username"
                    autocomplete="on"
                    placeholder="请输入帐号"
                    prefix-icon="el-icon-user"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="passWord" style="margin-bottom: 10px">
                  <el-input
                    type="password"
                    show-password
                    v-model="loginForm.password"
                    autocomplete="off"
                    placeholder="请输入密码"
                    prefix-icon="el-icon-lock"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="code">
                  <div style="display: flex; width: 275px">
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
                <el-form-item style="margin-bottom: 5px"> </el-form-item>
                <el-form-item style="margin-bottom: 10px">
                  <el-button type="primary" @click="submitForm('loginForm')"
                    >登录</el-button
                  >
                  <el-button @click="resetForm('loginForm')">重置</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div style="width: 90%; margin: 0 auto; text-align: center">
              <el-divider>其他方式登录</el-divider>
              <div>
                <el-icon><Shop /></el-icon>
                <el-icon><SwitchFilled /></el-icon>
                <el-icon><WindPower /></el-icon>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
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
      loading:false,
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
      this.loading=true
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getLogin(this.loginForm).then((res) => {
            if (res.code == "200") {
              this.$store.commit("SET_TOKEN", res.data.tokenValue);
              let hours = new Date().getHours();
              let str;
              if (hours > 7 && hours < 12) {
                str = "上午好!";
              } else if (hours >= 12 && hours <= 13) {
                str = "中午好!";
              } else if (hours > 13 && hours <= 18) {
                str = "下午好!";
              } else if (hours > 18 && hours < 22) {
                str = "晚上好!";
              } else {
                str = "晚上好!";
              }
              this.$notify({
                title: str,
                message: "欢迎登录",
                type: "success",
                offset: 50,
              });
              this.$router.push("/index");
              this.loading=false
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
.login-page {
  width: 100%;
  height: 100vh;
  background: #5dd5c8
    url( ../../.././public/5.jpg) center
   
    bottom no-repeat;
  background-size: cover;
}

.login-page .login-header {
  margin: 0;
  position: absolute;
  top: 50px;
  right: 75px;
  width: 250px;
  color: #ffffff;
  opacity: 0.8;
}

.login-page .login-header span {
  font-size: 16px;
}

.login-page .login-box {
  margin: 0;
  display: grid;
  grid-template-columns: 250px 1fr;
  position: absolute;
  width: 750px;
  height: 524px;
  padding: 0;
  background: #fff;
  box-shadow: 0 6px 13px 0 rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-page .login-box .left-half {
  height: 100%;
}

.login-page .login-box .left-half .logo {
  position: absolute;
  top: 60px;
  left: 40px;
}

.login-page .left-half .logo p {
  margin: 3px 0 0;
  line-height: 20px;
  font-size: 14px;
  text-align: left;
}

.login-page .left-half .geek-slide {
  position: absolute;
  top: 160px;
  left: 60px;
  list-style: none;
  text-align: left;
  padding: 0;
  margin: 0;
}

.login-page .left-half .geek-slide li {
  margin-bottom: 32px;
  position: relative;
}

.login-page .left-half .geek-slide li i {
  position: absolute;
  top: 16px;
  font-size: 24px;
  color: #8c8c8c;
}

.login-page .left-half .geek-slide .title {
  display: block;
  margin-left: 50px;
  line-height: 22px;
  color: #8d92a1;
  font-size: 16px;
  font-weight: 700;
}

.login-page .left-half .geek-slide .description {
  display: block;
  margin-left: 50px;
  margin-top: 2px;
  line-height: 18px;
  color: #b0b4c1;
  font-size: 13px;
}

.login-page .login-box .right-half {
  padding: 60px 40px 0 100px;
}

/*右侧登录盒子*/
.login-page .login-box .right-half .form-box {
  width: 90%;
  height: 310px;
  margin: 0 auto;
}
.login-form .header {
  position: relative;
  text-align: left;
  border: none;
  padding: 0;
}

.login-form .header .title {
  margin-left: 175px;
}

.login-form .header .pull-right {
  position: absolute;
  top: 15px;
  right: 0;
  font-size: 14px;
}

.login-form .header .pull-right .link {
  text-decoration: none;
  cursor: pointer;
  color: #005980;
}

.login-form .form {
  text-align: left;
}

.login-form .form .check-code {
  position: absolute;
  right: 20px;
  cursor: pointer;
}

.login-form .form .phone-code {
  float: right;
  text-decoration: none;
  color: #005980;
}

.login-form .form .retrieve-password {
  text-decoration: none;
  float: right;
  color: #005980;
}

.login-form .form .submit {
  width: 100%;
}

/*第三方登录图标*/
.login-form .my-icon {
  user-select: none;
}

.login-form .my-icon i {
  user-select: none;
  cursor: pointer;
  font-size: 32px;
  margin-left: 48px;
}

.login-form .el-divider--horizontal {
  margin: 40px 0 20px;
}

.login-form .qq {
  color: #00b0fb;
  margin-left: 0 !important;
}

.login-form .weixin {
  color: #46d800;
}

.login-form .dingding {
  color: #3795f9;
}
</style>
