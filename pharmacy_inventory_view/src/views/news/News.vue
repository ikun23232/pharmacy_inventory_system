<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.noticeTitle" placeholder="标题" clearable>
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
            v-if="hasAuth('sys:notice:delete')"
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

      <el-table-column prop="noticetitle" label="咨询名" width="120">
      </el-table-column>
      <el-table-column
        label="咨询要求"
        align="left"
        min-width="300"
        :show-overflow-tooltip="true"
        prop="noticecontent"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.noticecontent }}</span>
        </template>
      </el-table-column>
      

      <el-table-column prop="createdate" width="200" label="创建时间">
      </el-table-column>
      <el-table-column prop="icon" width="260px" label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="editHandle(scope.row.noticeid)"
            >编辑</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <template>
            <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="delHandle(scope.row.noticeid)"
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

    <!--新增对话框-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="600px"
      :before-close="handleClose"
    >
      <el-form :model="editForm" :rules="editFormRules" ref="editForm">
        <el-form-item label="咨询" prop="noticetitle" label-width="100px">
          <el-input
            v-model="editForm.noticetitle"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="咨询内容" prop="noticecontent" label-width="100px">
          <el-input
            v-model="editForm.noticecontent"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('editForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('editForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
import { checkNotice } from "@/api/notice";
export default {
  name: "Notice",

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
        noticeTitle:'',
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

      editFormRules: {
        noticetitle: [
          { required: true, message: "请输入标题名", trigger: "blur" },
          {
            validator: this.validatePass,
            trigger: "blur",
          },
        ],
        noticecontent: [
          { required: true, message: "请输入内容", trigger: "blur" },
        ],
        istate: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },
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
    handleClose() {
      this.resetForm("editForm");
    },

    getNoticeList() {
      this.searchForm.startTime = ''
      this.searchForm.endTime = ''
      if (this.value2 != null && this.value2.length > 0) {
        this.searchForm.startTime = this.value2[0];
        this.searchForm.endTime = this.value2[1];
      }
      this.$axios
        .post("/user/sysNotice/getNoticeList", this.searchForm)
        .then((res) => {
          this.tableData = res.data.list;
          this.size = res.data.pageSize;
          this.searchForm.current = res.data.pageNum;
          this.total = res.data.total;
        });
    },
    addHandle() {
      this.id = 0; // 在新增操作时设置id为空
      this.dialogVisible = true;
    },
    editHandle(id) {
      this.$axios.get("/user/sysNotice/info/" + id).then((res) => {
        this.editForm = res.data;
        this.dialogVisible = true;
        this.id = id;
      });
    },
    async validatePass(rule, value, callback) {
      if (value === "") {
        callback(new Error("请输入咨询名称"));
      } else {
        let resp = await checkNotice(value, this.id);
        console.log("jkkkkkkdasdasd");
        console.log(resp);
        if (resp.code == "200") {
          callback();
        } else if (resp.code == "202") {
          callback(new Error("咨询名称已经重复"));
        }
      }
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("sdsdsadsadasdasdasd");
          console.log(this.editForm.noticeid);
          this.$axios
            .post(
              "/user/sysNotice/" + (this.editForm.noticeid ? "updateNotice" : "saveNotice"),
              this.editForm
            )
            .then((res) => {
              this.$message({
                showClose: true,
                message: "恭喜你，操作成功",
                type: "success",
              });
              this.getNoticeList();

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
          ids.push(row.noticeid);
        });
      }

      console.log(ids);

      this.$axios.post("/user/sysNotice/delUserById", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          
        });
        this.getNoticeList();
      });
    },
    excelContent() {
      this.$axios
        .post("/user/sysNotice/Newsexcel", this.searchForm, {
          responseType: "blob",
        })
        .then((resp) => {
          let blob = new Blob([resp], { type: "application/xlsx" });
          let url = window.URL.createObjectURL(blob);
          const link = document.createElement("a"); //创建a标签
          link.href = url;
          link.download = "公告明细.xlsx"; //重命名文件
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
.el-tooltip__popper {
  font-size: 14px;
  max-width: 40%;
} /* 设置显示隐藏部分内容，按40%显示 */
</style>