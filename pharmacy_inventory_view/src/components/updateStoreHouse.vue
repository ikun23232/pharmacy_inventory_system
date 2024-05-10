<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="BaseStorehouse"
      :rules="rules"
      ref="storeHouse"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="仓库编号" prop="code">
        <el-input
          type="text"
          disabled
          placeholder="请输入仓库名称"
          v-model="BaseStorehouse.code"
        ></el-input>
      </el-form-item>
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
          type="number"
          placeholder="请输入仓库面积"
          v-model="BaseStorehouse.capacity"
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
          >立即修改</el-button
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
import {
  getStoreHouseById,
  checkName,
  updateStoreHouse,
} from "@/api/storeHouse.js";
import { Message } from "element-ui";
export default {
  name: "storeHouse",
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  data() {
    var check = (rule, value, callback) => {
      if (this.name != value) {
        let data = checkName(value).then((resp) => {
          console.log(resp.data);
          if (resp.code == 200) {
            callback(new Error("仓库名已经存在"));
          } else {
            callback();
          }
        });
      }else{
         callback();
      }
    };
    var validateCapacity = (rule, value, callback) => {
      //对传入的字符串类型数据进行手动转换为数字类型
      const capacityNumber = value ? Number(value) : null;
      if (
        capacityNumber !== null &&
        !isNaN(capacityNumber) &&
        capacityNumber >= 1 &&
        capacityNumber <= 10000
      ) {
        callback(); // 校验通过
      } else {
        callback(new Error("仓库容量必须为范围在1-10000的整数")); // 校验失败
      }
    };
    return {
      BaseStorehouse: {},
      name: "",
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
          { validator: validateCapacity, trigger: "blur" },
        ],
      },
    };
  },
  async mounted() {
    let data = await getStoreHouseById(this.id);
    console.log(data);
    this.code = data.data.code;
    this.name = data.data.name;
    this.BaseStorehouse = data.data;
    this.BaseStorehouse.updateBy = 1;
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateStoreHouse(this.BaseStorehouse).then((resp) => {
            console.log(resp);
            if (resp.code == 200) {
              Message({
                message: "修改成功!",
                type: "success",
                center: "true",
              });
              this.$emit("handleUpdateSuccess");
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
      this.BaseStorehouse.code = this.code;
    },
    cancel() {
      this.$emit("cancelUpdate");
    },
  },
};
</script>

<style>
.anniu {
  float: left;
}
</style>