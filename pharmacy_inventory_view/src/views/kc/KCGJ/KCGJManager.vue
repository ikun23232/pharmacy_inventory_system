<template>
  <div id="storeHouse">
    <h1>库存告警</h1>
    <p>
      <el-form :inline="true" :model="vo" class="demo-form-inline">
        <el-form-item label="单据编号">
          <el-input v-model="vo.code" placeholder="请输入单据编号"></el-input>
        </el-form-item>
        <el-form-item label="药品">
          <el-select v-model="vo.medicineId" placeholder="请选择药品">
            <el-option label="请选择" value="0"></el-option>
            <el-option
              v-for="item in medicineList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
                <el-form-item label="告警类型">
          <el-select v-model="vo.warningType" placeholder="请选择告警类型">
            <el-option label="全部" value="0"></el-option>
             <el-option label="本地仓库告警" value="1"></el-option>
            <el-option label="库存总数告警" value="2"></el-option>
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
              align="right">
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="initList();">查询</el-button>
          <el-button type="success" @click="excel">导出</el-button>
        </el-form-item>
      </el-form>
    </p>

    <el-table   header-cell-style="text-align:center" cell-style="text-align:center" :data="list.list" border style="width: 100%;text-align: center;">
      <el-table-column type="selection" width="55" fixed></el-table-column>
      <el-table-column prop="id" label="序号" fixed>
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="告警编号" fixed>
        <template slot-scope="scope">
          <a href="#" @click="viewOrder(scope.row.id)">{{ scope.row.code }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="warningType" label="告警类型">
        <template slot-scope="scope">
          {{scope.row.warningType == 1 ? "本地仓库预警":"库存总数预警"}}
        </template>
      </el-table-column>
      <el-table-column prop="warningCount" label="当前库存值">
      </el-table-column>
      <el-table-column prop="medicineName" label="告警药品">
      </el-table-column>
      <el-table-column prop="isKnow" label="是否处理">
        <template slot-scope="scope">
          {{scope.row.isKnow == 0 ? "未处理" :"已处理"}}
        </template>
      </el-table-column>
      <el-table-column prop="createDate" label="创造时间">
      </el-table-column>
      <el-table-column  label="操作">
        <template slot-scope="scope">
       <el-button
                  @click="handleDelete(scope.row)"
                  type="danger"
                  size="small"
                  >删除
                </el-button>
         <!-- <el-button
                :disabled = "scope.row.isKnow == 1"
                  @click="voidOrder(scope.row)"
                  type="info"
                  size="small"
                  >处理
                </el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="list.pageNum"
        :page-size="list.pageSize"
        layout="prev, pager, next, jumper"
        :total="list.total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { Message } from "element-ui";
import { getAllBaseMedicine } from "@/api/baseMedicine";
import {initKcAlarmList,deleteAlarmList,updateRemind,excelKcAlarm,checkWarning} from "@/api/KcWarning";

export default {
  name: "DDRKManager",
  data() {
    return {
      code: "",
      page: {
        pageNum: 1,
        pageSize: 5,
      },
      vo: {
        currentPageNo: 1,
        pageSize: 5,
        code: "",
        medicineId: "",
        warningType: "",
        startTime: "",
        endTime: "",
      },
      list: {},
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
      medicineList: [],
    };
  },
  async mounted() {
    await checkWarning();
    this.initList(1);
    this.initMedicineList();
  },
  methods: {
    async initList(currentPageNo) {
      this.vo.currentPageNo = currentPageNo;
      this.vo.startTime = "";
      this.vo.endTime = "";
      if (this.value2 != null && this.value2.length > 0) {
        this.vo.startTime = this.value2[0];
        this.vo.endTime = this.value2[1];
      }
      let data = await initKcAlarmList(this.vo);
      console.log(data);
      this.list = data.data;
    },
    async excel() {
      await excelKcAlarm();
    },
    async initMedicineList() {
      let resp = await getAllBaseMedicine();
      console.log(resp);
      this.medicineList = resp.data;
    },
    handleCurrentChange(val) {
      this.initList(val);
    },
    async handleDelete(row) {
      if (!confirm("你确定要删除吗？")) {
        return;
      }
      let resp = await deleteAlarmList(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "删除成功",
        });
        this.initList(1);
      }
    },
    async voidOrder(row) {
      if (!confirm("你确定要处理吗？")) {
        return;
      }
      let resp = await updateRemind(row.id);
      console.log(resp);
      if (resp.code == "200") {
        Message({
          type: "success",
          message: "处理成功",
        });
        this.initList(1);
      }
    },
  },
};
</script>

<style>

</style>