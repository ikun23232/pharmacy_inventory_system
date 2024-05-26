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
      <el-form-item label="分类名" prop="name">
        <el-input v-model="reOrder.name"></el-input>
      </el-form-item>
        <el-form-item label="分类方向" prop="parentId">
      <el-tree
        :data="categories"
        node-key="id"
        :props="{ label: 'name' }"
        :highlight-current="true"
        @node-click="handleCascaderChange"
      ></el-tree>
    </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('reOrder')"
          >立即添加</el-button
        >
        <el-button @click="close">取 消</el-button>
      </el-form-item>
    </el-form>
  </span>
</template>
  <script>
import { addCat, checkCat, initCat } from "@/api/BaseCategory";
import { Message } from "element-ui";
export default {
  data() {
    var validatePass = async (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入分类名称"));
      } else {
        let resp = await checkCat(this.reOrder.name);
        console.log(resp);
        if (resp.code == 200) {
          callback();
        } else {
          callback(new Error("分类名称已经重复"));
        }
      }
    };
    return {
      categories: {},
      reOrder: {
        name: "",
        parentId: null,
      },
      rules: {
        name: [
          { required: true, message: "请输入分类名称", trigger: "blur" },
          { validator: validatePass, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    handleCascaderChange(value) {
      // 将返回的值转换为数组
      const data= [value];
      this.reOrder.parentId=data[0].id
    },
    async getList() {
      let data = await initCat();
      this.categories = data.data;

      // 创建一个新的父节点对象
      let newParent = { id: 0, name: "全部", children: this.categories };

      // 更新所有子节点的parentId为新增节点的id
      this.categories.forEach((category) => {
        category.parentId = newParent.id;
      });
      // 更新this.categories为包含新增节点的数组
      this.categories = [newParent];
    },
    submitForm(reOrder) {
      this.$refs[reOrder].validate((valid) => {
        if (valid) {
          this.add(this.reOrder);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async add(formdata) {
      let respones = await addCat(formdata);
      console.log(respones);
      if (respones.code == "200") {
        Message({
          type: "success",
          message: "添加成功",
        });
        this.$emit("addSuccess");
      } else {
        Message({
          type: "success",
          message: "添加失败",
        });
      }
    },
    close() {
      this.$emit("addSuccess");
    },
  },
  mounted() {
    this.getList();
  },
};
</script>
  