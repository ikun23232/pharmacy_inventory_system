<template>
  <div style="text-align: center">
    <h2>你好！{{ userInfo.username }}</h2>

    <el-form
      :model="passForm"
      status-icon
      :rules="rules"
      ref="passForm"
      label-width="100px"
    >
      <el-form-item label="旧密码" prop="password">
        <el-input
          type="password"
          v-model="passForm.password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newpassword">
        <el-input
          type="password"
          v-model="passForm.newpassword"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
          type="password"
          v-model="passForm.checkPass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('passForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('passForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { selectUser } from "@/api/test";
export default {
  name: "Login",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.passForm.newpassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };

    var validatePass1 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== null) {
        this.$axios.post("/user/existLogin", this.passForm).then((res) => {
          if (res.code !== "200") {
            callback(new Error(res.message));
          } else {
            callback();
          }
        });
      }
    };
    return {
      userInfo: {
        userid: "",
        username: "",
      },
      passForm: {
        newpassword: "",
        checkPass: "",
        password: "",
      },
      rules: {
        newpassword: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          {
            min: 6,
            max: 12,
            message: "长度在 6 到 12 个字符",
            trigger: "blur",
          },
        ],
        checkPass: [
          { required: true, validator: validatePass, trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入当前密码", trigger: "blur" },
		  { required: true, validator: validatePass1, trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    async getUserInfo() {
      let res = await selectUser();
      if (res.code === "200") {
        console.log(res);
        this.userInfo = res.data;
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .get("/user/updatePass", {params:{password:this.passForm.newpassword}})
            .then((res) => {
             if(res.code==="200"){
				this.$message.success(res.message)
				this.resetForm("passForm")
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
.el-form {
  width: 420px;
  margin: 50px auto;
}
</style>