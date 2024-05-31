<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.username" placeholder="用户名" clearable>
        </el-input>
        <el-select
          v-model="searchForm.sex"
          placeholder="请选择性别"
          clearable
          style="width: 300px"
          
        >
          <!-- <el-option label="全部" value="-1"></el-option> -->
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="0"></el-option>
        </el-select>

        <el-select
          v-model="searchForm.roleId"
          placeholder="请选择身份"
          clearable
          style="width: 300px"
        >
          <el-option
            v-for="role in roleTreeData"
            :key="role.roleid"
            :label="role.rolename"
            :value="role.roleid"
          ></el-option>
        </el-select>

        <el-select
          v-model="searchForm.isstate"
          placeholder="请选择状态"
          clearable
          style="width: 300px"
        >
          <!-- <el-option label="全部" value="-1"></el-option> -->
          <el-option label="正常" value="1"></el-option>
          <el-option label="禁用" value="0"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="getUserList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="addHandle">新增</el-button>
        <el-button type="success" @click="excelContent()">导出</el-button>
        <!-- <el-button type="primary" @click="dialogVisible = true" v-if="hasAuth('sys:user:save')">新增</el-button> -->
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <el-button
            type="danger"
            slot="reference"
            :disabled="delBtlStatu"
            v-if="hasAuth('sys:user:delete')"
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
      v-loading="loading"
    >
      <el-table-column type="selection" width="55"> </el-table-column>

      <el-table-column prop="username" label="用户名" width="120">
      </el-table-column>
      <el-table-column prop="code" label="角色名称">
        <template slot-scope="scope">
          <el-tag
            size="small"
            type="info"
            v-for="item in scope.row.sysRoles"
            :key="item.roleid"
            >{{ item.rolename }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="email" label="邮箱"> </el-table-column>
      <el-table-column prop="sex" label="年龄">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.sex === 1">男</el-tag>
          <el-tag size="small" v-else-if="scope.row.sex === 0">女</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="isstate" label="状态">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.isstate === 1" type="success"
            >正常</el-tag
          >
          <el-tag size="small" v-else-if="scope.row.isstate === 0" type="danger"
            >禁用</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="createdate" width="200" label="创建时间">
      </el-table-column>
      <el-table-column prop="icon" width="260px" label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="roleHandle(scope.row.userid)"
            >分配角色</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <el-button
            type="text"
            @click="repassHandle(scope.row.userid, scope.row.username)"
            >重置密码</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <el-button type="text" @click="editHandle(scope.row.userid)"
            >编辑</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <template>
            <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="delHandle(scope.row.userid)"
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
      <el-form :model="editForm" :rules="editFormRules" ref="editForm">
        <el-form-item label="用户名" prop="username" label-width="100px">
          <el-input v-model="editForm.username" autocomplete="off"></el-input>
          <el-alert
            title="初始密码为123456"
            :closable="false"
            type="info"
            style="line-height: 12px"
          ></el-alert>
        </el-form-item>

        <el-form-item label="邮箱" prop="email" label-width="100px">
          <el-input v-model="editForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex" label-width="100px">
          <el-select
            v-model="editForm.sex"
            placeholder="请选择"
            value-key="value"
          >
            <el-option label="女生" :value="0"></el-option>
            <el-option label="男生" :value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="状态" prop="isstate" label-width="100px">
          <el-radio-group v-model="editForm.isstate">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">正常</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('editForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('editForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <!-- 分配权限对话框 -->
    <el-dialog
      title="分配角色"
      :visible.sync="roleDialogFormVisible"
      width="600px"
    >
      <el-form :model="roleForm">
        <el-tree
          :data="roleTreeData"
          show-checkbox
          ref="roleTree"
          :check-strictly="checkStrictly"
          node-key="roleid"
          :default-expand-all="true"
          :props="defaultProps"
        >
        </el-tree>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="roleDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitRoleHandle('roleForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
import { checkUsername } from "@/api/test";
export default {
  name: "Role",

  data() {
    return {
      loading: false,
      id: 0,
      username: "",
      searchForm: {},
      delBtlStatu: true,

      total: 0,
      size: 10,
      current: 1,

      dialogVisible: false,
      editForm: {},

      tableData: [],

      editFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
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
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        istate: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },
      validateRules: {},
      multipleSelection: [],

      roleDialogFormVisible: false,
      defaultProps: {
        children: "children",
        label: "rolename",
      },
      roleForm: {},
      roleTreeData: [],
      treeCheckedKeys: [],
      checkStrictly: true,
    };
  },
  created() {
    this.getUserList();

    this.$axios.get("/user/role/AllList").then((res) => {
      console.log("sdlaldasldsaldlasdlasdlasdas");
      console.log(res.data);
      this.roleTreeData = res.data;
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
      console.log("勾选");
      console.log(val);
      this.multipleSelection = val;

      this.delBtlStatu = val.length == 0;
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val;
      this.getUserList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val;
      this.getUserList();
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false;
      this.editForm = {};
    },
    handleClose() {
      this.resetForm("editForm");
    },

    getUserList() {
      this.loading = true;

      this.$axios
        .get("/user/getUserListByPage", {
          params: {
            username: this.searchForm.username,
            sex: this.searchForm.sex,
            isstate: this.searchForm.isstate,
            roleId: this.searchForm.roleId,
            currentNo: this.current,
          },
        })
        .then((res) => {
          this.tableData = res.data.list;
          this.size = res.data.pageSize;
          this.current = res.data.pageNum;
          this.total = res.data.total;
          this.loading = false;
        });
    },
    addHandle() {
      this.id = 0; // 在新增操作时设置id为空
      this.dialogVisible = true;
    },
    editHandle(id) {
      this.$axios.get("/user/info/" + id).then((res) => {
        this.editForm = res.data;
        this.dialogVisible = true;
        this.id = id;
      });
    },
    async validatePass(rule, value, callback) {
      if (value === "") {
        callback(new Error("请输入用户名称"));
      } else {
        let resp = await checkUsername(value, this.id);
        console.log("jkkkkkkdasdasd");
        console.log(resp);
        if (resp.code == "200") {
          callback();
        } else if (resp.code == "202") {
          callback(new Error("用户名称已经重复"));
        }
      }
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post(
              "/user/" + (this.editForm.userid ? "updateUser" : "saveUser"),
              this.editForm
            )
            .then((res) => {
              this.$message({
                showClose: true,
                message: "恭喜你，操作成功",
                type: "success",
              });
              this.getUserList();

              this.dialogVisible = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    delHandle(id) {
      var ids = [];

      if (id) {
        ids.push(id);
      } else {
        this.multipleSelection.forEach((row) => {
          ids.push(row.userid);
        });
      }

      console.log(ids);

      this.$axios.post("/user/delUserById", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
        });
        this.getUserList();
      });
    },

    roleHandle(id) {
      this.roleDialogFormVisible = true;

      this.$axios.get("/user/info/" + id).then((res) => {
        this.roleForm = res.data;

        let roleIds = [];
        res.data.sysRoles.forEach((row) => {
          roleIds.push(row.roleid);
        });

        this.$refs.roleTree.setCheckedKeys(roleIds);
      });
    },
    submitRoleHandle(formName) {
      var roleIds = this.$refs.roleTree.getCheckedKeys();

      console.log(roleIds);

      this.$axios
        .post("/user/role/" + this.roleForm.userid, roleIds)
        .then((res) => {
          this.$message({
            showClose: true,
            message: "恭喜你，操作成功",
            type: "success",
          });
          this.getUserList();
          this.roleDialogFormVisible = false;
        });
    },
    repassHandle(id, username) {
      console.log(id, username);
      this.$confirm("将重置用户【" + username + "】的密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$axios
          .get("/user/repass", {
            params: {
              userId: id,
            },
          })
          .then((res) => {
            this.$message({
              showClose: true,
              message: "恭喜你，操作成功",
              type: "success",
              onClose: () => {},
            });
          });
      });
    },
    excelContent() {
      this.$axios
        .post("/user/Userexcel", this.searchForm, {
          responseType: "blob",
        })
        .then((resp) => {
          let blob = new Blob([resp], { type: "application/xlsx" });
          let url = window.URL.createObjectURL(blob);
          const link = document.createElement("a"); //创建a标签
          link.href = url;
          link.download = "用户明细.xlsx"; //重命名文件
          link.click();
          URL.revokeObjectURL(url);
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