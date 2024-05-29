<template>
  <div>
    <el-card style="width: 30%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>概况</span>
        <el-button style="float: right; padding: 3px 0" type="text" size="big"
          ><i class="el-icon-refresh"></i
        ></el-button>
      </div>
      <div style="font-size: 18px; color: gray">
        <div style="float: left; margin-left: 40px">
          <p>本月支出(元)</p>
          <p>999.00</p>
        </div>
        <div>
          <p>本月收出(元)</p>
          <p>20,000.00</p>
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>库存告警</span>
        <el-button
          v-if="warningCount == 0"
          style="float: right; padding: 3px 0"
          type="text">
          <i class="el-icon-refresh"></i>暂无</el-button>
        <el-button
          v-if="warningCount > 0"
          style="float: right; padding: 3px 0"
          type="text"
          @click="toKCGJManager">
          <i class="el-icon-refresh">
          </i>更多({{ warningCount }})</el-button>
      </div>
      <div class="content" >
        <div v-for="o in alarmList" :key="o.id" class="text item">
          <el-tag size="small" style="margin-right: 10px; margin-bottom: 5px">{{o.warningType == 1 ? "本地仓库告警":"储存总数报警"}}</el-tag>{{ o.medicineName }}
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>公告</span>
        <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-refresh"></i></el-button>
      </div>
      <div class="content">
        <div v-for="o in 4" :key="o" class="text item">
          <el-tag size="small" style="margin-right: 10px; margin-bottom: 5px">公告</el-tag>{{ "列表内容 " + o }}
        </div>
      </div>
    </el-card>
    <el-card style="width: 20%; height: 210px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>待办事项</span>
        <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-refresh"></i></el-button>
      </div>
      <div class="content" style="height: 20px">
        <div class="scroll-container" >
          <div v-for="item in CheckHintList" class="text item" :key="item.id">
            <el-tag size="small" type="danger" style="margin-right: 10px; margin-bottom: 5px">审批</el-tag>
            <router-link :to="{ path: item.path, query: { code: item.code } }">{{ item.documenterName }}的{{ item.tableName }}审批</router-link>
          </div>
        </div>
      </div>
    </el-card><br/>
    <el-card style="width: 20%; height: 500px; float: left; margin: 10px">
      <div slot="header" class="clearfix">
        <span>销售订单</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="refreshData">
          <i class="el-icon-refresh"></i>
        </el-button>
      </div>
      <div class="content">
        <div>销售</div>
        <div class="statistics">
          <div class="statistic-item">
            <div class="statistic-label">今日笔数</div>
            <div class="statistic-value">{{ SalesStatistics.daySalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">今日金额</div>
            <div class="statistic-value">{{ SalesStatistics.daySalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月笔数:{{ SalesStatistics.monthsSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本月金额:{{ SalesStatistics.monthSalesPrice }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周笔数:{{ SalesStatistics.weekSalesNum }}</div>
          </div>
          <div class="statistic-item">
            <div class="statistic-label">本周金额:{{ SalesStatistics.weekSalesPrice }}</div>
          </div>
        </div>

      </div>
    </el-card>

  </div>
</template>

<script>
import { getAlarmCount, initKcAlarmList,checkWarning} from "@/api/KcWarning";
import {getCheckHintList} from "@/api/procurementOrder";
import {getSalesStatistics} from "@/api/saleStatistics";
export default {
  name: "index",
  data() {
    return {
      orderMedicineList: [],
      saleOrderMoneyList: [],
      saleOrderNumberList: [],
      CheckHintList: [],
      warningCount: "",
      alarmList: [],
      vo: {
        currentPageNo: 1,
        pageSize: 4,
        code: "",
        medicineId: "",
        warningType: "",
        startTime: "",
        endTime: "",
      },
      SalesStatistics:{

      }
    };
  },
  async mounted() {
    this.getCheckHintList();
    await checkWarning();
    getAlarmCount().then((resp) => {
      this.warningCount = resp.data;
    });
    initKcAlarmList(this.vo).then((resp) => {

      this.alarmList = resp.data.list;
    });
    this.getSalesStatisticses()
  },
  methods: {
    getCheckHintList() {
      getCheckHintList().then((resp) => {
        if (resp.code == 200) {
          this.CheckHintList = resp.data;

        }
      });
    },

    toKCGJManager(){
      this.$router.push({
        path:"/KCGJManager"
      })
    },
    getSalesStatisticses(){
      getSalesStatistics().then(resp=>{
        if(resp.code==200){
          this.SalesStatistics=resp.data;
          console.log(this.SalesStatistics);
        }
      })
    }
  },
  components: {
  },
  directives: {
  },
};
</script>

<style>
.clearfix {
  font-size: 20px;
  text-align: left;
}
.content {
  font-size: 14px;
  text-align: left;
}
.el-icon-refresh {
  font-size: 20px;
}

.scroll-container {
  height: 116px; /* 设置一个固定的高度 */
  overflow-y: auto; /* 启用垂直滚动 */
}
.statistics {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.statistic-item {
  flex: 1 0 50%;
  text-align: center;
  margin-bottom: 10px;
}

.statistic-label {
  font-size: 14px;
  color: #606266;
}

.statistic-value {
  font-size: 20px;
  color: #303133;
}

.statistic-value.large {
  font-size: 24px;
}

.statistic-value.black {
  font-weight: bold;
}
</style>