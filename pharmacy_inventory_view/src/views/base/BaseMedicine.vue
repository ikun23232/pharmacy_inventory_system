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
        @click="init(1)">搜索</el-button>
      <el-button type="primary" icon="el-icon-search" style="background-color: #ff4e00; border: none"
        @click="addProvider(1)">添加</el-button>
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
      <el-table-column prop="address" label="供应商地址" width="130" align="center">
      </el-table-column>
      <el-table-column prop="createBy" label="创建人" width="300" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="100" align="center">
      </el-table-column>
      <el-table-column prop="updateBy" label="修改人" width="100" align="center">
      </el-table-column>
      <el-table-column prop="updateDate" label="修改时间" width="100" align="center">
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
    <div style="width: 100%; height: 30px"></div>
    <div class="block">
      <el-pagination @size-change="handleSizeChange" @current-change="init" :current-page="page.pageNum"
        :page-sizes="[5, 8]" :page-size="page.pageSize" :page-count="page.pages"
        layout="total, sizes, prev, pager, next, jumper" :total="page.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '../utils/request.js';
export default {
    data(){

    }
}
</script>

<style>

</style>