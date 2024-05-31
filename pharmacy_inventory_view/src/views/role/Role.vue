<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input
          v-model="searchForm.name"
          placeholder="请输入角色名"
          clearable
        >
        </el-input>
        <el-select
          v-model="searchForm.isUse"
          placeholder="请选择状态"
          style="width: 300px"
        >
          <el-option label="全部" value="-1"></el-option>
          <el-option label="禁用" value="2"></el-option>
          <el-option label="正常" value="1"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="getRoleList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true">新增</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" slot="reference" :disabled="delBtlStatu"
            >批量删除</el-button
          >
        </el-popconfirm>
      </el-form-item>
    </el-form>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      border
      stripe
      @selection-change="handleSelectionChange"
      v-loading=loading
    >
      <el-table-column type="selection" width="55"> </el-table-column>

      <el-table-column prop="rolename" label="名称" width="120">
      </el-table-column>
      <el-table-column prop="code" label="唯一编码" show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="isUse" label="状态">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.isUse === 1" type="success"
            >正常</el-tag
          >
          <el-tag size="small" v-else-if="scope.row.isUse === 2" type="danger"
            >禁用</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="icon" label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="permHandle(scope.row.roleid)"
            >分配权限</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <el-button type="text" @click="editHandle(scope.row.roleid)"
            >编辑</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <template>
            <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="delHandle(scope.row.roleid)"
            >
              <el-button type="text" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="current"
      :page-size="size"
      :total="total"
    >
    </el-pagination>

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
        <el-form-item label="角色名称" prop="rolename" label-width="100px">
          <el-input v-model="editForm.rolename" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="唯一编码" prop="code" label-width="100px">
          <el-input v-model="editForm.code" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="状态" prop="isUse" label-width="100px">
          <el-radio-group v-model="editForm.isUse">
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

    <el-dialog title="分配权限" :visible.sync="permDialogVisible" width="600px">
      <el-form :model="permForm">
        <el-tree
          :data="permTreeData"
          show-checkbox
          ref="permTree"
          :default-expand-all="false"
          node-key="id"
          :check-strictly="true"
          :props="defaultProps"
        >
        </el-tree>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="permDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitPermFormHandle('permForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Role",
  data() {
    return {
      loading:false,
      searchForm: {
        name: "",
      },
      delBtlStatu: true,

      total: 0,
      size: 10,
      current: 1,

      dialogVisible: false,
      editForm: {},

      tableData: [],

      editFormRules: {
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
        code: [{ required: true, message: "请输入编码", trigger: "blur" }],
        statu: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },

      multipleSelection: [],

      permDialogVisible: false,
      permForm: {},
      defaultProps: {
        children: "children",
        label: "menuname",
      },
      permTreeData: [],
    };
  },
  created() {
    this.getRoleList();

    this.$axios.get("/user/menu/list").then((res) => {
      this.permTreeData = res.data;
    });
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;

      this.delBtlStatu = val.length == 0;
    },

    handleSizeChange(val) {
      this.size = val;
      this.getRoleList();
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getRoleList();
    },

    resetForm(formName) {
      this.$nextTick(() => {
        this.$refs[formName].resetFields();
      });
      this.dialogVisible = false;
      this.editForm = {};
    },
    handleClose() {
      this.resetForm("editForm");
    },

    getRoleList() {
      this.loading=true
      this.$axios
        .get("/user/role/list", {
          params: {
            rolename: this.searchForm.name,
            isUse:this.searchForm.isUse,
            current: this.current,
          },
        })
        .then((res) => {
          console.log("sdasdasdsadasdsadasdsadsadsadsadasd");
          console.log(res);
          this.tableData = res.data.list;
          this.size = res.data.pageSize;
          this.current = res.data.pageNum;
          this.total = res.data.total;
          this.loading=false
        });
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post(
              "/user/role/" + (this.editForm.roleid ? "update" : "save"),
              this.editForm
            )
            .then((res) => {
              this.$message({
                showClose: true,
                message: "恭喜你，操作成功",
                type: "success",
                onClose: () => {
                  this.getRoleList();
                },
              });

              this.dialogVisible = false;
              this.resetForm(formName);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    editHandle(id) {
      this.$axios.get("/user/role/info/" + id).then((res) => {
        this.editForm = res.data;

        this.dialogVisible = true;
      });
    },
    delHandle(id) {
      var ids = [];

      if (id) {
        ids.push(id);
      } else {
        this.multipleSelection.forEach((row) => {
          ids.push(row.roleid);
        });
      }

      console.log(ids);

      this.$axios.post("/user/role/delete", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          onClose: () => {
            this.getRoleList();
          },
        });
      });
    },
    permHandle(id) {
      this.permDialogVisible = true;

      this.$axios.get("/user/role/info/" + id).then((res) => {
        this.$refs.permTree.setCheckedKeys(res.data.menuIds);
        this.permForm = res.data;
      });
    },

    submitPermFormHandle(formName) {
      var menuIds = this.$refs.permTree.getCheckedKeys();
      console.log("menuIds");

      console.log(menuIds);

      this.$axios
        .post("/user/role/perm/" + this.permForm.roleid, menuIds)
        .then((res) => {
          console.log("perm");

          console.log(res);
		  if(res.code==="200"){
			this.$message({
            showClose: true,
            message: "恭喜你，操作成功",
            type: "success",
            onClose: () => {
              this.getRoleList();
            },
          });
          this.permDialogVisible = false;
          this.resetForm(formName);
		  }
         
        });
    },
  },
};
</script>

<style scoped>
.el-pagination {
  float: right;
  margin-top: 22px;
}
</style>