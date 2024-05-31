<template>
  <div id="check">
    <h1>库存盘点</h1>
    <p>
      <el-form :inline="true" :model="vo" class="demo-form-inline">
        <el-form-item label="单据编号">
          <el-input v-model="vo.code" placeholder="请输入单据编号"></el-input>
        </el-form-item>
        <el-form-item label="单据主题">
          <el-input
            v-model="vo.subject"
            placeholder="请输入单据主题"
          ></el-input>
        </el-form-item>

        <el-form-item label="仓库">
          <el-select v-model="vo.warehouseId" placeholder="请选择仓库">
            <el-option :label="'请选择仓库'" :value="0"></el-option>
            <el-option
              v-for="item in warehouseList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="单据阶段">
          <el-select v-model="vo.orderStatus" placeholder="请选择单据阶段">
            <el-option label="请选择" value="-1"></el-option>
            <el-option label="编制未完成" value="1"></el-option>
            <el-option label="编制完成" value="2"></el-option>
            <el-option label="执行中" value="3"></el-option>
            <el-option label="执行完" value="4"></el-option>
          </el-select>
        </el-form-item>
        <div>
          <el-form-item label="作废状态">
            <el-select v-model="vo.isVoided" placeholder="请选择作废类型">
              <el-option label="请选择" value="-1"></el-option>
              <el-option label="未作废" value="0"></el-option>
              <el-option label="已作废" value="1"></el-option>
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
          <el-button type="primary" @click="addOrder">新增盘点卡</el-button>
          <el-button type="success" @click="excelcheck">导出</el-button>
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
      <el-table-column prop="code" label="订单编码" width="150" fixed>
        <template slot-scope="scope">
          <a href="#" @click="viewOrder(scope.row.id)">{{ scope.row.code }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="billDate" label="单据日期" width="300">
      </el-table-column>
      <el-table-column prop="subject" label="单据主题" width="120">
      </el-table-column>
      <el-table-column prop="warehouseName" label="仓库" width="120">
      </el-table-column>
      <el-table-column prop="checkerByName" label="盘点人" width="120">
      </el-table-column>
      <el-table-column prop="materialCategoryName" label="物料分类" width="120">
      </el-table-column>
      <el-table-column prop="effectiveTime" label="生效时间" width="120">
      </el-table-column>

      <el-table-column prop="orderStatusName" label="单据阶段" width="120">
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
      <el-table-column prop="isVoided" label="作废状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.voidstate == 0 ? "否" : "是" }}
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120">
      </el-table-column>
      <el-table-column prop="approverByName" label="核批人" width="120">
      </el-table-column>
      <el-table-column prop="createByName" label="制单人" width="120">
      </el-table-column>
      <el-table-column prop="updateByName" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="120">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            @click="updateOrder(scope.row.id)"
            type="primary"
            size="small"
            :disabled="
              (scope.row.orderStatus > 2 && scope.row.isApproved === 2) ||
              scope.row.isVoided === 1 ||
              scope.row.isApproved === 2
            "
            >盘点
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
                    v-if="scope.row.orderStatus == 1"
                    >删除</el-button
                  >
                </el-popconfirm>
              </el-dropdown-item>
              <el-dropdown-item
                ><el-button
                  @click="voidOrder(scope.row.id)"
                  type="info"
                  size="small"
                  >作废
                </el-button></el-dropdown-item
              >

              <el-dropdown-item
                ><el-button
                  @click="approveOrder(scope.row.id)"
                  v-if="scope.row.orderStatus == 2"
                  type="success"
                  size="small"
                  >审核
                </el-button></el-dropdown-item
              >
              <el-dropdown-item
                ><el-button type="primary" size="small"  @click="printContent(scope.row.id)"
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
    <el-dialog
      title="查看盘点申请单"
      :visible.sync="viewdialogVisible"
      width="85%"
      v-if="viewdialogVisible"
    >
      <CheckView
        :id="parseInt(this.id)"
        @closeviewOrder="closeviewOrder"
      ></CheckView>
    </el-dialog>

    <el-dialog
      title="进行盘点"
      :visible.sync="updatedialogVisible"
      width="85%"
      v-if="updatedialogVisible"
    >
      <CheckAddUpdate
        :id="parseInt(this.id)"
        @closeUpdateDiago="closeUpdateDiago"
      ></CheckAddUpdate>
    </el-dialog>
    <el-dialog
      title="审核盘点申请"
      :visible.sync="approvedialogVisible"
      width="85%"
      v-if="approvedialogVisible"
    >
      <CheckApprove
        :id="this.id"
        @closeapproveDiago="closeapproveDiago"
      ></CheckApprove>
    </el-dialog>

    <el-dialog
      title="盘点申请添加"
      :visible.sync="adddialogVisible"
      width="1600px"
      v-if="adddialogVisible"
    >
      <StoreCheck width="75%" @addSuccess="addSuccess"> </StoreCheck>
    </el-dialog>
  </div>
</template>
  
  <script>
//   import {initCgSqOrderList, delCgsqOrderById, voidCgsqOrderById} from '@/api/CgsdOrder'
import StoreCheck from "@/components/CheckAdd";
import CheckAddUpdate from "@/components/CheckAddUpdate";
import CheckApprove from "@/components/CheckApprove";
import CheckView from "@/components/CheckView";
//   import {getPayType} from "@/api/public";

export default {
  name: "check",
  components: {
    StoreCheck,
    CheckAddUpdate,
    CheckApprove,
    CheckView,
  },
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
        current: 1,
        code: "",
        subject: "",
        warehouseId: 0,
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
        .post("/warehouse/check/getAllCheckByPage", this.vo)
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

      this.$axios.post("/warehouse/check/deleteCheck", ids).then((res) => {
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
    approveOrder(id) {
      this.id = id;
      this.approvedialogVisible = true;
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
    excelcheck() {
      this.$axios
        .post("/warehouse/check/checkExcel", this.vo, {
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
    handleEdit(row) {
      this.$router.push({
        name: "updateUser",
        params: {
          userName: row.userName,
        },
      });
    },
    printContent(id) {
      const newPage= this.$router.resolve({
        path: "/printcheck",
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