<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.username" placeholder="用户名" clearable>
        </el-input>
        <el-select
          v-model="searchForm.sex"
          placeholder="请选择性别"
          style="width: 300px"
        >
          <el-option label="全部" value="-1"></el-option>
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="0"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="getUserList">搜索</el-button>
      </el-form-item>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 1000px"
        @row-click="singleElection"
        border
        stripe
        @selection-change="handleSelectionChange"
      >
        <el-table-column width="55">
          <template slot-scope="scope">
            <el-radio
              class="radio"
              v-model="templateSelection"
              :label="scope.$index"
              >&nbsp;</el-radio
            >
          </template>
        </el-table-column>
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
        <el-table-column prop="createdate" width="200" label="创建时间">
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[10, 20, 50, 100]"
        :current-page="current"
        :page-size="size"
        :total="total"
      >
      </el-pagination>
      <div>
        <el-row type="flex" justify="center">
          <el-col :span="6"
            ><div class="grid-content bg-purple">
              <el-button type="primary" @click="submitForm()">选中</el-button>
            </div></el-col
          >
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button @click="cancelKcmx()">取 消</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>
  
<script>
export default {
  name: "RoleList",
  data() {
    return {
      id: 0,
      username: "",
      searchForm: {},
      total: 0,
      size: 10,
      current: 1,
      tableData: [],
      multipleSelection: [],
      roleTreeData: [],
      templateSelection: [],
      AlluserList:{},
      userid:0,
      isflag:false
    };
  },
  created() {
    this.getUserList();
    this.$axios.get("/user/role/AllList").then((res) => {
      this.roleTreeData = res.data;
    });
  },
  methods: {
    handleSelectionChange(val) {
      console.log("勾选");
      console.log(val);
      this.multipleSelection = val;
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
    getUserList() {
      this.$axios
        .get("/user/getUserListByPage", {
          params: {
            username: this.searchForm.username,
            sex: this.searchForm.sex,
            isstate: this.searchForm.isstate,
            currentNo: this.current,
          },
        })
        .then((res) => {
          this.tableData = res.data.list;
          this.size = res.data.pageSize;
          this.current = res.data.pageNum;
          this.total = res.data.total;
        });
    },
    singleElection(row) {
      console.log(row);
      
      this.AlluserList = row
      console.log(this.AlluserList);
    },
    submitForm() {
      console.log(this.AlluserList,"dyf");
      if (this.AlluserList == null ) {
        this.$message({
          message: "请选择执行人！",
          type: "error",
        });
        this.isflag=true
      }
      if(!this.isflag){
        this.$emit("handleKcmxSuccess", this.AlluserList);

      }
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