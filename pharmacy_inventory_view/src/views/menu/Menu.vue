<template>
  <div>
    <el-form :inline="true" style="float: left">
      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="tableData"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      stripe
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="menuname" label="名称" sortable width="180">
      </el-table-column>
      <el-table-column prop="perms" label="权限编码" sortable width="180">
      </el-table-column>

      <el-table-column prop="type" label="类型">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.type === 0">目录</el-tag>
          <el-tag size="small" v-else-if="scope.row.type === 1" type="success"
            >菜单</el-tag
          >
          <el-tag size="small" v-else-if="scope.row.type === 2" type="info"
            >按钮</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column prop="path" label="菜单URL"> </el-table-column>
      <el-table-column prop="component" label="菜单组件"> </el-table-column>
      <el-table-column prop="statu" label="状态">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.statu === 1" type="success"
            >正常</el-tag
          >
          <el-tag size="small" v-else-if="scope.row.statu === 0" type="danger"
            >禁用</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="icon" label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="editHandle(scope.row.id)"
            >编辑</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <template>
            <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="delHandle(scope.row.id)"
            >
              <el-button type="text" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </template>
      </el-table-column>
    </el-table>

    <!--新增对话框-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="600px"
      :before-close="handleClose"
    >
      <el-form
        :model="editForm"
        :rules="editFormRules"
        ref="editForm"
        label-width="100px"
        class="demo-editForm"
      >
        <!-- <el-form-item label="上级菜单" prop="parentid">
					<el-select v-model="editForm.parentid" placeholder="请选择上级菜单">
						<el-option :label="'一级目录'" :value="0"></el-option>
						<template v-for="item in tableData">
							<el-option :label="item.menuname" :value="item.id" :key="item.id"></el-option>
							<template v-for="child in item.children">
								<el-option :label="child.menuname" :value="child.id" :key="child.id">
									<span>{{ "- " + child.menuname }}</span>
								</el-option>
							</template>
						</template>
					</el-select>
				</el-form-item> -->
        <el-form-item label="上级菜单" prop="parentid">
          <el-cascader
            v-model="selectedMenu"
            :options="cascaderData"
            :props="{
              label: 'menuname',
              value: 'id',
              children: 'children',
              checkStrictly: true,
            }"
            placeholder="默认一级菜单"
            @change="handleCascaderChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuname" label-width="100px">
          <el-input v-model="editForm.menuname" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="权限编码" prop="perms" label-width="100px">
          <el-input v-model="editForm.perms" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="菜单URL" prop="path" label-width="100px">
          <el-input v-model="editForm.path" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="菜单组件" prop="component" label-width="100px">
          <el-input v-model="editForm.component" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="类型" prop="type" label-width="100px">
          <el-radio-group v-model="editForm.type">
            <el-radio :label="0">目录</el-radio>
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="状态" prop="statu" label-width="100px">
          <el-radio-group v-model="editForm.statu">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">正常</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('editForm')"
            >立即创建</el-button
          >
          <el-button @click="resetForm('editForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  inject: ["reload"],
  name: "Menu",
  data() {
    return {
      id: null,
      dialogVisible: false,
      editForm: { parentid: 0 },
      editFormRules: {
        menuname: [
          {
            required: true,
            message: "请输入名称",
            trigger: "blur",
          },
          {
            validator: this.validatePass,
            trigger: "blur",
          },
          {
            pattern: /^.{2,10}$/,
            message: "用户名长度为2-10个字符",
            trigger: "blur",
          },
        ],
        perms: [
          { required: true, message: "请输入权限编码", trigger: "blur" },
          {
            validator: this.validatePass1,
            trigger: "blur",
          },
        ],
        type: [{ required: true, message: "请选择状态", trigger: "blur" }],
        statu: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },
      tableData: [],
      cascaderData: [],
      selectedMenu: [],
    };
  },
  async created() {
    this.getMenuTree();
  },
  methods: {
    getMenuTree() {
      this.$axios.get("/user/menu/list").then((res) => {
        console.log("kkk");
        console.log(res.data);
        this.tableData = res.data;
        this.handleAddMenu(this.tableData);
      });
    },
    handleAddMenu(data) {
      const traverseData = (data) => {
        return data.map((item) => formatData(item));
      };

      const formatData = (item) => {
        return {
          id: item.id,
          menuname: item.menuname,
          children: item.children
            ? item.children.map((child) => formatData(child))
            : [],
        };
      };
      console.log(traverseData(data));
      this.cascaderData = traverseData(data);
    },

    handleCascaderChange(value) {
      this.selectedMenu = value;
      this.editForm.parentid = value[value.length - 1];
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post(
              "/user/menu/" + (this.editForm.id ? "update" : "save"),
              this.editForm
            )
            .then((res) => {
              if (res.code === "200") {
                this.$message({
                  showClose: true,
                  message: "恭喜你，操作成功",
                  type: "success",
                });
                this.getMenuTree();
                this.reload();
                this.dialogVisible = false;
              }
              this.$message.error(res.message);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    editHandle(id) {
      this.$axios.get("/user/menu/info/" + id).then((res) => {
        console.log("111lllppp");
        console.log(res.data);
        this.editForm = res.data;
        this.dialogVisible = true;
        this.id = id;
      });
    },
    async validatePass(rule, value, callback) {
      if (value === "") {
        callback(new Error("请输入菜单名称"));
      } else {
        await this.$axios
          .get("/user/menu/exsitMenuName", {
            params: { menuname: value, id: this.id }
          })
          .then((resp) => {
            console.log(resp,"dyf");
            if (resp.code == "200") {
              callback();
            } else if (resp.code == "202") {
              callback(new Error("菜单名已经重复"));
            }
          });
      }
    },
    async validatePass1(rule, value, callback) {
      if (value === "") {
        callback(new Error("请输入权限名称"));
      } else {
        await this.$axios
          .get("/user/menu/exsitMenuPerms", {
            params: { perms: value, id: this.id }
          })
          .then((resp) => {
            if (resp.code == "200") {
              callback();
            } else if (resp.code == "202") {
              callback(new Error("权限名已经重复"));
            }
          });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false;
      this.editForm = {};
    },
    handleClose() {
      this.resetForm("editForm");
    },
    delHandle(id) {
      this.$axios.post("/user/menu/delete/" + id).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
        });
        this.getMenuTree();
        this.reload();
      });
    },
  },
};
</script>

<style scoped>
</style>