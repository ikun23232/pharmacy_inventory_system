<template>
  <div id="app">
    <p>
      <label style="font-weight: bolder; font-size: 14px">供应商类别：</label>
      <el-select v-model="type" placeholder="请选择">
        <el-option :value="0" label="全部"></el-option>
        <el-option v-for="item in optionTypeList" :key="item.id" :label="item.type" :value="item.id">
        </el-option>
      </el-select>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <label style="font-weight: bolder; font-size: 14px">供应商名称:&nbsp;</label>
      <el-input style="width: 200px" placeholder="请输入内容" v-model="input" clearable>
      </el-input>
      <el-button type="primary" icon="el-icon-delete" style="background-color: #ff4e00; border: none"
        @click="clearsearch()"></el-button>
      <el-button type="primary" icon="el-icon-search" style="background-color: #ff4e00; border: none"
        @click="handlegetproviderList(1)">搜索</el-button>
      <el-button type="primary" icon="el-icon-search" style="background-color: #ff4e00; border: none"
        @click="addProvider()">添加</el-button>
    </p>
    <el-table :data="page.list" style="width: 100%; border: black" max-height="1000">
      <el-table-column label="序号" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="130" align="center">
      </el-table-column>
      <el-table-column prop="typeName" label="类别" width="130" align="center">
      </el-table-column>
      <el-table-column prop="address" label="供应商地址" width="150" align="center">
      </el-table-column>
      <el-table-column prop="createMan" label="创建人" width="100" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="200" align="center">
      </el-table-column>
      <el-table-column prop="updateMan" label="修改人" width="100" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.updateMan">{{ scope.row.updateMan }}</span>
          <span v-if="!scope.row.updateMan">暂无</span>
        </template>
      </el-table-column>
      <el-table-column prop="updateDate" label="修改时间" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.updateDate">{{ scope.row.updateDate }}</span>
          <span v-if="!scope.row.updateDate">暂无</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <p style="width: 200px">
            <el-button type="primary" style="width: 80px" @click="toUpdata(scope.row)"
              icon="el-icon-edit">修改</el-button>
            &nbsp;
            <el-button type="primary" style="width: 80px" @click="del(scope.row)" icon="el-icon-delete">删除</el-button>
            &nbsp;
          </p>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加弹窗 -->
    <el-dialog title="添加供应商" :visible.sync="dialogVisible" width="30%" v-if="dialogVisible">
      <addProvider @updateSuccess="handleUpdateSuccess"></addProvider>
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog title="修改供应商信息" :visible.sync="updatedialogVisible" width="30%" v-if="updatedialogVisible">
      <updateProvider :id="id" @updateSuccess="handleUpdateSuccess"></updateProvider>
    </el-dialog>

    <div style="width: 100%; height: 30px"></div>
    <div class="block">
      <el-pagination @size-change="handleSizeChange" @current-change="handlegetproviderList" :current-page="page.pageNum"
        :page-sizes="[5, 8]" :page-size="page.pageSize" :page-count="page.pages"
        layout="total, sizes, prev, pager, next, jumper" :total="page.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import AddProvider from "../../components/AddProvider.vue";
import UpdateProvider from "../../components/UpdateProvider.vue";
import { init, getProviderType, delBaseProvider } from "../../api/BaseProvider.js";
import { Message } from "element-ui";
export default {
  components: {
    AddProvider,
    UpdateProvider
  },
  data() {
    return {
      page: {
        currentPageNo: 1,
        pageSize: 5
      },
      // 供应商分类
      optionTypeList: {},
      //修改需要的id
      id: '',
      // 条件查询数据
      type: 0,
      input: '',
      //添加弹框开关
      dialogVisible: false,
      updatedialogVisible: false,
    }
  },
  mounted() {
    this.getProviderType();
    this.handlegetproviderList(1)
  },
  methods: {
    async handlegetproviderList(currentPageNo) {
      let data = await init(this.input, this.type, currentPageNo, this.page.pageSize);
      this.page = data.data
      console.log(this.page)

    },
    async getProviderType() {
      let data = await getProviderType();
      console.log(data)
      this.optionTypeList = data.data;
    },
    clearsearch() {

    },
    addProvider() {
      this.dialogVisible = true
    },
    toUpdata(row) {
      this.id = row.id
      this.updatedialogVisible = true
    },
    async del(row) {
      this.$messagebox
        .confirm("此操作将删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          try {
            let data = await delBaseProvider(row.id);
            if (data.code === "200") {
              Message({
                type: 'success',
                message: '删除成功！'
              })
              this.handlegetproviderList(1)
            } else {
              Message({
                type: 'error',
                message: '删除失败！'
              })
            }
          } catch (error) {
            Message({
              type: 'error',
              message: '删除失败！'
            })
          }
        })
        .catch(() => {
          Message({
            type: 'success',
            message: '取消删除成功！'
          })
        });
    },
    //弹窗中添加成功或者修改成功后调用的父类方法
    handleUpdateSuccess() {
      this.dialogVisible = false;
      this.updatedialogVisible = false; // 关闭 el-dialog
      setTimeout(() => {
        this.handlegetproviderList(1);
      }, 500);
      // 如果需要，可以在这里执行其他操作
    },
    handleSizeChange(val) {
      this.page.pageSize = val;
      this.handlegetproviderList(1);
    },
  },
}
</script>
