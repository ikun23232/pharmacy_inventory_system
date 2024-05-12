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
          :default-expanded-keys="expandedKeys"
          :default-checked-keys="checkedKeys"
          :highlight-current="true"
          @node-click="handleCascaderChange"
        ></el-tree>
      </el-form-item>

      <input name="id" type="hidden" v-model="reOrder.id" />
      <el-form-item>
        <el-button type="primary" @click="submitForm('reOrder')"
          >立即修改</el-button
        >
        <el-button @click="close()">取 消</el-button>
      </el-form-item>
    </el-form>
  </span>
</template>
  <script>
import { checkCatById, updateCat, checkCat, initCat } from "@/api/BaseCategory";
import { Message } from "element-ui";
export default {
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  data() {
    var validatePass = async (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入分类名称"));
      } else {
        let resp = await checkCat(this.reOrder.name);
        if (resp.data.code == 200) {
          callback();
        } else {
          const id1 = parseInt(resp.data.data.id);
          const id2 = parseInt(this.id);
          if (id1 === id2) {
            callback();
          }
          callback(new Error("分类名称已经重复"));
        }
      }
    };
    return {
      parentId: null,
      expandedKeys: [],
      checkedKeys: [],
      categories: {},
      reOrder: {
        id: this.id,
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
  mounted() {
    // this.getStatusList();
    this.init();
    this.getList();
  },
  methods: {
    async init() {
      let response = await checkCatById(this.reOrder.id);
      console.log(response.data);
      this.reOrder = response.data.data;
      this.parentId = response.data.data.id;
      console.log(this.parentId);
      this.setTreeData(this.parentId);
    },
    setTreeData(parentId) {
      if (parentId) {
        // 设置默认展开的节点为当前节点的parentId
        this.expandedKeys = [parentId];
        // 设置默认选中的节点为当前节点的parentId
        this.checkedKeys = [parentId];
      }
    },
    handleCascaderChange(value) {
      // 将返回的值转换为数组
      const data = [value];
      this.reOrder.parentId = data[0].id;
    },
    async getList() {
      let data = await initCat();
      this.categories = data.data.data;

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
          console.log(this.reOrder);
          this.update(this.reOrder);
          
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async update(formdata) {
      let respones = await updateCat(formdata);
      if (respones.data.code == "200") {
        Message({
          type: "success",
          message: "修改成功",
        });
        this.$emit("closeUpdateDiago");
      } else {
        Message({
          type: "success",
          message: "修改失败",
        });
      }
    },
    close() {
      this.$emit("closeUpdateDiago");
    },
  },
};
</script>