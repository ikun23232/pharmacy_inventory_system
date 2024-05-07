<template>
  <span slot="footer" class="dialog-footer">
    <el-form
      :model="reOrder"
      :rules="rules"
      ref="reOrder"
      id="reOrder"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="订单状态" prop="statusId">
        <el-select
          v-model="reOrder.statusId"
          name="statusId"
          placeholder="请选择"
        >
          <el-option
            v-for="item in optionStatusList"
            :key="item.id"
            :label="item.status"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <input name="serialNumber" type="hidden" v-model="reOrder.serialNumber" />
      <el-form-item>
        <el-button type="primary" @click="submitForm('reOrder')"
          >立即修改</el-button
        >
        <el-button @click="dialogVisibleUpdata = false">取 消</el-button>
      </el-form-item>
    </el-form>
  </span>
</template>
<script>
import axios from "axios";
export default {
  props: {
    serialNumber: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      optionStatusList: {},
      reOrder: {
        serialNumber: this.serialNumber,
        statusId: "",
      },
      imageUrl: "",
      rules: {
        statusId: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.getStatusList();
    this.init();
  },
  methods: {
    async init() {
      await axios
        .get("searchMenuByserialNumber", {
          params: {
            serialNumber: this.reOrder.serialNumber,
          },
        })
        .then((response) => {
          console.log(response);
          this.reOrder = response.data.data;
        });
    },
    getStatusList() {
      axios.get("getStatusList").then((response) => {
        console.log(response);
        this.optionStatusList = response.data.data;
      });
    },
    submitForm(reOrder) {
      this.$refs[reOrder].validate((valid) => {
        if (valid) {
          const formdata = new FormData(document.getElementById("reOrder"));
          console.log(formdata);
          formdata.set("statusId", this.reOrder.statusId);
          this.update(formdata);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    update(formdata) {
      axios.post("/updateOrder", formdata).then((respones) => {
        console.log(respones);
        if (respones.data.code == "200") {
          this.$message({
            type: "success",
            message: "修改成功！",
            center: "true",
          });
          this.$emit("updateSuccess");
        } else {
          this.$message({
            type: "error",
            message: "添加失败！",
            center: "true",
          });
        }
      });
    },
  },
};
</script>
