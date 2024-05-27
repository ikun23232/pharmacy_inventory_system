<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="BaseStorehouse"
      :rules="rules"
      ref="storeHouse"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="仓库名称" prop="name">
        <el-input
          type="text"
          placeholder="请输入仓库名称"
          v-model="BaseStorehouse.name"
        ></el-input>
      </el-form-item>
      <el-form-item label="仓库地址" prop="address">
        <el-input
          type="text"
          placeholder="请输入仓库地址"
          v-model="BaseStorehouse.address"
        ></el-input>
      </el-form-item>
      <el-form-item label="仓库面积" prop="capacity">
        <el-input
          type="text"
          placeholder="请输入仓库面积"
          v-model.number="BaseStorehouse.capacity"
        ></el-input>
      </el-form-item>
      <!-- <el-form-item label="负责人" prop="userId">
        
      </el-form-item> -->
      <el-form-item style="width: 500px">
        <el-button
          class="anniu"
          size="mini"
          type="primary"
          @click="submitForm('storeHouse')"
          >立即添加</el-button
        >
        <el-button class="anniu" size="mini" @click="resetForm('storeHouse')"
          >重置</el-button
        >
        <el-button class="anniu" size="mini" @click="cancel()">取 消</el-button>
      </el-form-item>
    </el-form>
  </span>
</template>

<script>
import { addStoreHouse, checkName } from "@/api/storeHouse.js";
import { Message } from "element-ui";
export default {
  name: "storeHouse",
  data() {
    var check = (rule, value, callback) => {
      let data = checkName(value).then((resp) => {
        console.log(resp.data);
        if (resp.code == 200) {
          callback(new Error("仓库名已经存在"));
        } else {
          callback();
        }
      });
    };
    return {
      BaseStorehouse: {
        name: "",
        address: "",
        capacity: "",
        userId: 1,
        createBy: 1,
      },
      rules: {
        name: [
          { required: true, message: "请输入仓库名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
          { validator: check, trigger: "blur" },
        ],
        address: [
          { required: true, message: "请输入仓库地址", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 2 到 10 个字符", trigger: "blur" },
        ],
        capacity: [
          { required: true, message: "仓库面积不能为空" },
          {
            type: "number",
            min: 1,
            max: 10000,
            message: "仓库面积必须为范围在1-10000的整数",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addStoreHouse(this.BaseStorehouse).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "添加成功!",
                type: "success",
                center: "true",
              });
              this.$emit("handleAddSuccess");
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
    cancel() {
      this.$emit("cancel");
    },
  },
};
</script>

<style>
.anniu {
  float: left;
}
</style>