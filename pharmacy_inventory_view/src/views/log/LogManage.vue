<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.username" placeholder="请输入用户名" clearable>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.ip" placeholder="请输入IP地址" clearable>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.title" placeholder="请输入标题" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="日期">
        <div class="block">
          <el-date-picker
            v-model="value2"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button @click="getNoticeList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
          <el-button
            type="danger"
            slot="reference"
            :disabled="delBtlStatu"
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
    >
      <el-table-column type="selection" width="55"> </el-table-column>

      <el-table-column prop="username" label="账号名" width="120">
      </el-table-column>
      <el-table-column prop="title" label="操作标题" width="120">
      </el-table-column>
      <el-table-column prop="ip" label="ip地址" width="120">
        <el-table-column prop="usageTime" label="耗时" width="120">
      </el-table-column>
      </el-table-column>
      <el-table-column
        label="响应地址"
        align="left"
        min-width="300"
        :show-overflow-tooltip="true"
        prop="requestUrl"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.requestUrl }}</span>
        </template>
      </el-table-column>
     
      <el-table-column
        label="响应方法"
        align="left"
        min-width="300"
        :show-overflow-tooltip="true"
        prop="requestMethod"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.requestMethod }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="requestParams" label="响应参数" width="120">
      </el-table-column>
      
      <el-table-column
        label="响应结果"
        align="left"
        min-width="300"
        :show-overflow-tooltip="true"
        prop="responseResult"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.responseResult }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="operationTime" width="200" label="操作时间">
      </el-table-column>
      <el-table-column prop="icon" width="260px" label="操作">
        <template slot-scope="scope">
          
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

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100]"
      :current-page="searchForm.current"
      :page-size="size"
      :total="total"
    >
    </el-pagination>

   
  </div>
</template>
  
  <script>
export default {
  name: "LogManage",

  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: '',
      id: 0,
      searchForm: {
        username:'',
        ip:"",
        title:"",
        startTime:null,
        endTime:null,
        current: 1,
      },
      delBtlStatu: true,

      total: 0,
      size: 10,
      

      dialogVisible: false,
      editForm: {},

      tableData: [],

      validateRules: {},
      multipleSelection: [],

      roleDialogFormVisible: false,
      roleForm: {},
      treeCheckedKeys: [],
      checkStrictly: true,
    };
  },
  created() {
    this.getNoticeList();
  },
  methods: {
    
    handleSelectionChange(val) {
      console.log("勾选");
      console.log(val);
      this.multipleSelection = val;

      this.delBtlStatu = val.length == 0;
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val;
      this.getNoticeList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.searchForm.current = val;
      this.getNoticeList();
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogVisible = false;
      this.editForm = {};
    },
  
    getNoticeList() {
      this.searchForm.startTime = ''
      this.searchForm.endTime = ''
      if (this.value2 != null && this.value2.length > 0) {
        this.searchForm.startTime = this.value2[0];
        this.searchForm.endTime = this.value2[1];
      }
      this.$axios
        .post("/user/sysLogManage/list", this.searchForm)
        .then((res) => {
          this.tableData = res.data.list;
          this.size = res.data.pageSize;
          this.searchForm.current = res.data.pageNum;
          this.total = res.data.total;
        });
    },
  
    delHandle(id) {
      var ids = [];

      if (id) {
        ids.push(id);
      } else {
        this.multipleSelection.forEach((row) => {
          ids.push(row.id);
        });
      }

      console.log(ids);

      this.$axios.post("/user/sysLogManage/removeBatchByIds", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          
        });
        this.getNoticeList();
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
.el-tooltip__popper {
  font-size: 14px;
  max-width: 40%;
} /* 设置显示隐藏部分内容，按40%显示 */
</style>