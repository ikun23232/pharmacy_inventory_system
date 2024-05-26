<template>
  <div id="check">
    <h1>盘盈入库</h1>
    <p>
      <el-form :inline="true" :model="vo" class="demo-form-inline">
        <el-form-item label="单据编号">
          <el-input
            v-model="vo.inventoryCode"
            placeholder="请输入单据编号"
          ></el-input>
        </el-form-item>

        <el-form-item label="仓库">
          <el-select v-model="vo.storehouseId" placeholder="请选择仓库">
            <el-option :label="'请选择仓库'" :value="0"></el-option>
            <el-option
              v-for="item in warehouseList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <div>
          <el-form-item label="审核状态">
            <el-select v-model="vo.isApproved" placeholder="请选择审核类型">
              <el-option label="请选择" value="-1"></el-option>
              <el-option label="未审核" value="0"></el-option>
              <el-option label="未通过" value="1"></el-option>
              <el-option label="已通过" value="2"></el-option>
            </el-select>
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
        </div>

        <el-form-item>
          <el-button type="primary" @click="initCheckList()">查询</el-button>
          <el-button type="success" @click="excelContent()">导出</el-button>
        </el-form-item>
      </el-form>
    </p>

    <el-table
      v-loading="loading"
      :data="list.list"
      tooltip-effect="dark"
      style="width: 100%"
      border
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" fixed></el-table-column>
      <el-table-column prop="id" label="#" width="75" fixed>
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="inventoryCode" label="订单编码" width="150" fixed>
      </el-table-column>
      <el-table-column prop="code" label="源订单编码" width="150" fixed>
      </el-table-column>
      <el-table-column prop="createDate" label="单据日期" width="300">
      </el-table-column>
      <el-table-column prop="rkQuantity" label="盘盈数量" width="300">
      </el-table-column>
      <el-table-column prop="storehouseName" label="仓库" width="120">
      </el-table-column>
      <el-table-column prop="providerName" label="供应商" width="120">
      </el-table-column>
      <el-table-column prop="materialCategoryName" label="物料分类" width="120">
      </el-table-column>
      <el-table-column
        prop="isApproved
          "
        label="核批结果"
        width="120"
      >
        <template slot-scope="scope">
          {{
            scope.row.isApproved === 0
              ? "未审核"
              : scope.row.isApproved === 1
              ? "未通过"
              : "通过"
          }}
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="320">
        <template slot-scope="scope">
          <el-button
            @click="approveRK(scope.row.id,1)"
            v-if="scope.row.isApproved == 0"
            type="danger"
            size="small" 
            >未通过
          </el-button>
          <span style="margin-right: 10px"></span>
          <el-button
            @click="approveRK(scope.row.id,2)"
            v-if="scope.row.isApproved == 0"
            type="success"
            size="small"
            >通过
          </el-button>
          <span style="margin-right: 10px"></span>
          <el-dropdown>
            <el-button type="plain" size="small" class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-popconfirm
                  title="这是一段内容确定删除吗？"
                  @confirm="handleDelete(scope.row.id)"
                >
                  <el-button
                    type="danger"
                    slot="reference"
                    size="small"
                    >删除</el-button
                  >
                </el-popconfirm>
              </el-dropdown-item>

              <el-dropdown-item
                ><el-button type="primary" size="small" @click="printContent(scope.row.id)"
                  >打印
                </el-button></el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        layout="prev, pager, next, jumper"
        :current-page="vo.current"
        :page-size="size"
        :total="total"
      >
      </el-pagination>
    </div>
    <!-- 修改订单状态 -->
    
  </div>
</template>
    
    <script>

export default {
  name: "check",
  data() {
    return {
      loading: true,
      code: "",
      page: {
        pageSize: 5,
      },
      size: 0,
      total: 0,
      vo: {
        inventoryCode: "",
        current: 1,
        code: "",
        subject: "",
        storehouseId: 0,
        orderStatus: null,
        startTime: null,
        endTime: null,
        isVoided: null,
      },
      serialNumber: "",
      list: {},
      //添加弹框开关
      dialogVisible: false,
      updatedialogVisible: false,
      adddialogVisible: false,
      viewdialogVisible: false,
      approvedialogVisible: false,
      formDisabled: false,
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: "",
      warehouseList: [],
    };
  },
  mounted() {
    this.initCheckList();
    this.initStoreHouse();
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;

      this.delBtlStatu = val.length == 0;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.vo.current = val;
      this.initCheckList();
    },
    async initCheckList() {
      this.vo.startTime = "";
      this.vo.endTime = "";
      if (this.value2 != null && this.value2.length > 0) {
        this.vo.startTime = this.value2[0];
        this.vo.endTime = this.value2[1];
      }
      this.loading = false;
      await this.$axios
        .post("/warehouse/check/getAllCheckRkByPage", this.vo)
        .then((res) => {
          this.list = res.data;
          this.size = res.data.pageSize;
          this.vo.current = res.data.pageNum;
          this.total = res.data.total;
        });
    },
    async initStoreHouse() {
      await this.$axios.post("/base/getAllStoreHouseList").then((resp) => {
        this.warehouseList = resp.data;
      });
    },
    handleDelete(id) {
      var ids = [];

      if (id) {
        ids.push(id);
      } else {
        this.multipleSelection.forEach((row) => {
          ids.push(row.id);
        });
      }

      this.$axios.post("/warehouse/check/deleteCheckRK", ids).then((res) => {
        console.log(res);

        if (res.code == "200") {
          this.$message({
            type: "success",
            message: "删除成功",
          });
          this.initCheckList();
        }
      });
    },
    updateOrder(id) {
      this.id = id;
      this.updatedialogVisible = true;
    },
    addOrder() {
      this.adddialogVisible = true;
    },
     approveRK(id,statuskk) {
        console.log(id,statuskk);
         this.$axios.get("/warehouse/check/approveRK", {params:{id:id,status:statuskk}}).then((res) => {
        console.log(res);

        if (res.code == "200") {
          this.$message({
            type: "success", 
            message: "审核入库成功",
          });
          this.initCheckList();
        }
      });
    },
    viewOrder(id) {
      this.id = id;
      this.viewdialogVisible = true;
    },
    closeUpdateDiago() {
      this.updatedialogVisible = false;
      this.initCheckList();
    },
    closeviewOrder() {
      this.viewdialogVisible = false;
    },
    closeapproveDiago() {
      this.approvedialogVisible = false;
      this.initCheckList();
    },
    addSuccess() {
      this.adddialogVisible = false;
      this.initCheckList();
    },
    jump(path) {
      this.$router.push({
        name: path,
      });
    },
    async voidOrder(id) {
      console.log(id);

      if (!confirm("你确定要作废吗？")) {
        return;
      }
      this.$axios
        .get("/warehouse/check/updateisVoid", { params: { id: id } })
        .then((res) => {
          if (res.code == "200") {
            this.$message({
              type: "success",
              message: "删除成功",
            });
            this.initCheckList();
          }
        });
    },
    handleEdit(row) {
      this.$router.push({
        name: "updateUser",
        params: {
          userName: row.userName,
        },
      });
    },
    excelContent() {
      this.$axios
        .post("/warehouse/check/getAllCheckRkexcel", this.vo, {
          responseType: "blob",
        })
        .then((resp) => {
          let blob = new Blob([resp], { type: "application/xlsx" });
          let url = window.URL.createObjectURL(blob);
          const link = document.createElement("a"); //创建a标签
          link.href = url;
          link.download = "盘点明细.xlsx"; //重命名文件
          link.click();
          URL.revokeObjectURL(url);
        });
    },
    printContent(id) {
      const newPage= this.$router.resolve({
        path: "/printcheckRK",
        query:{ //要传的参数 可传多个
          id:id
        }})
      window.open(newPage.href,'_blank')
    },
    
  },
};
</script>
    
    <style>
</style>