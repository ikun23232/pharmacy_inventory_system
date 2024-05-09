<template>
  <div id="baseMedicine">
  <h1>医用商品</h1>
  <div>
    <div style="padding-top: 15px;padding-left: 20px;">
        <el-form :inline="true" >
            <el-form-item label="医用商品编码">
                <el-input placeholder="医用商品编码" v-model="object.id"></el-input>
            </el-form-item>
            <el-form-item label="医用商品名称">
                <el-input placeholder="医用商品名称" v-model="object.name"></el-input>
            </el-form-item>
            <el-form-item label="医用商品类型">
            <el-select v-model="object.categoryId" placeholder="医用商品类型">
                <!-- <el-option :label="item.name" :value="item.id" v-for="(item,index) in categoryList" :key="index"></el-option> -->
            </el-select>
            </el-form-item>
            <el-form-item label="医用商品规格">
                <el-input placeholder="医用商品规格" v-model="object.specification"></el-input>
            </el-form-item>
            <el-form-item label="计量单位">
            <el-select v-model="object.unitId" placeholder="计量单位">
                <!-- <el-option :label="item.name" :value="item.id" v-for="(item,index) in categoryList" :key="index"></el-option> -->
            </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="initMedicineListByPage(1)">查询</el-button>
                <el-button icon="el-icon-refresh-right" >重置</el-button>
                 <el-button type="text" icon="el-icon-plus" @click="handleAdd('addMedicine')">添加</el-button>
            <el-button type="text" icon="el-icon-download" style="margin-left:18px">导出</el-button>
            <el-button type="text" icon="el-icon-download" style="margin-left:18px">导入</el-button>
            </el-form-item>
        </el-form>
        </div>

  </div>
  <el-table stripe="true" :data="list" border style="width: 100%;text-align: center;">
    <el-table-column fixed prop="index" label="#" width="60">
        <template #default="scope">
        {{ scope.$index +(pageInfo.pageNum - 1) * pageInfo.pageSize+ 1 }}
        </template>
      </el-table-column>
    <el-table-column prop="id" label="医用商品编码" width="120">
    </el-table-column>
    <el-table-column prop="name" label="医用商品名称" width="150">
    </el-table-column>
    <el-table-column prop="categoryName" label="医用商品类型" width="120">
    </el-table-column>
    <el-table-column prop="specification" label="医用商品规格" width="120">
    </el-table-column>
    <el-table-column prop="unitName" label="计量单位" width="120">
    </el-table-column>
    <el-table-column prop="salePrice" label="零售价" width="120">
    </el-table-column>
    <el-table-column prop="warning" label="库存预警值" width="120">
    </el-table-column>
    <el-table-column prop="createByName" label="创建人" width="120">
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间" width="120">
    </el-table-column>
    <el-table-column prop="updateByName" label="修改人" width="120">
    </el-table-column>
    <el-table-column prop="updateTime" label="修改时间" width="120">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="200">
      <template slot-scope="scope">
        <el-button @click="handleUpdate(scope.row.id)" type="primary" size="small"
        >编辑
        </el-button
        >
        <el-button @click="handleDelete(scope.row.id)" type="danger" size="small"
        >删除
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <div class="block">
    <p>
      <el-pagination background layout="prev, pager, next" :total="pageInfo.total" :page-size=pageInfo.size @current-change="handleCurrentChange" style="float: right;"></el-pagination>
      <span style="color: gray;float: right;margin-top: 5px;">共{{ pageInfo.total }}条</span>
    </p>

    <el-dialog :title="title" :visible.sync="dialogFormVisible">
        <el-form :model="baseMedicine" ref="baseMedicineForm" status-icon :rules="rules" auto-complete="on" style="width: 500px;">
        <el-form-item label="医用商品编号" prop="id" :label-width="formLabelWidth">
        <el-input v-model="baseMedicine.id" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="医用商品名称" prop="name" :label-width="formLabelWidth">
        <el-input v-model="baseMedicine.name" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="医用商品类型" prop="categoryId" :label-width="formLabelWidth">
            <el-select v-model="baseMedicine.categoryId" placeholder="请选择" style="width: 100%;" >
                <!-- <el-option :label="item.name" :value="item.id" v-for="(item,index) in categoryList" :key="index"></el-option> -->
            </el-select>
        </el-form-item>
        <el-form-item label="医用商品规格" prop="specification" :label-width="formLabelWidth">
        <el-input v-model="baseMedicine.specification" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" prop="unitId" :label-width="formLabelWidth">
            <el-select v-model="baseMedicine.unitId" placeholder="请选择" style="width: 100%;">
                <el-option :label="item.name" :value="item.id" v-for="(item,index) in list" :key="index" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="零售价" prop="salePrice" :label-width="formLabelWidth">
        <el-input v-model="baseMedicine.salePrice" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="库存预警值" prop="warning" :label-width="formLabelWidth">
        <el-input v-model="baseMedicine.warning" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="handleCancel">取 消</el-button>
            <el-button type="primary" @click="addBaseMedicine" v-if="title=='添加医用商品信息'">确 定</el-button>
            <el-button type="primary" @click="updateBaseMedicine" v-if="title=='修改医用商品信息'">修 改</el-button>
        </div>
    </el-dialog>
  </div>
</div>
</template>

<script>
import {initMedicine,addBaseMedicine,getBaseMedicineById,updateBaseMedicine,deleteBaseMedicine} from "../../api/baseMedicine.js";
import { Message } from "element-ui";

export default {
  name: "baseMedicine",
  data() {
    return {
        object:{
            id:"",
            name:"",
            categoryId:"",
            specification:"",
            unitId:"",
            currentPage:1,
        },
        pageInfo:"",
        list:"",
        title:"",
        baseMedicine:{
            id:"",
            name:"",
            categoryId:"",
            specification:"",
            unitId:"",
            salePrice:"",
            warning:"",
       },
       dialogFormVisible:false,
       formLabelWidth: '120px',
       rules: {
        id: [
            { required: true, message: '请输入医用商品编号', trigger:'blur'},
        ],
        name: [
            { required: true, message: '请输入医用商品名称', trigger:'blur'},
        ],
        categoryId: [
            { required: true, message: '请输入医用商品类型', trigger:'blur'},
        ],
        specification: [
            { required: true, message: '请输入医用商品规格', trigger:'blur'},
        ],
        unitId: [
            { required: true, message: '请输入计量单位', trigger:'blur'},
        ],
        salePrice: [
            { required: true, message: '请输入零售价', trigger:'blur'},
        ],
        warning: [
            { required: true, message: '请输入库存预警值', trigger:'blur'},
        ],                 
        }            
    };
  },
  mounted() {
    this.initMedicineListByPage(1);
  },
  methods: {
    async initMedicineListByPage(currentPage) {
        this.object.currentPage=currentPage;
        let data = await initMedicine(this.object);
        this.pageInfo=data.data;
        this.list = data.data.list;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.initMedicineListByPage(this.currentPage);
    },
    handleCancel(){
        this.dialogFormVisible=false;
        this.$refs["baseMedicineForm"].resetFields();
        this.$refs["baseMedicineForm"].clearValidate();
    },
    handleAdd(){
        this.title="添加医用商品信息";
        this.dialogFormVisible=true;  
    },
     addBaseMedicine(){
        return new Promise((resolve, reject) => {
            this.$refs["baseMedicineForm"].validate((valid) => {
                if (valid) {
                    let data = addBaseMedicine(this.baseMedicine);
                    if(data.code=="200"){
                        Message({type: 'success',message: '添加成功！'})
                    }
                    this.dialogFormVisible=false;  
                    this.$refs["baseMedicineForm"].resetFields();
                    this.$refs["baseMedicineForm"].clearValidate(); 
            
                } else {
                    console.log("error submit!!")
                    reject("Validation failed");
                }
            })
        });     
    },
    async handleUpdate(id){
        this.title="修改医用商品信息";
        this.dialogFormVisible=true;  
        let data = await getBaseMedicineById(id);
       this.baseMedicine=data.data;
    },
    updateBaseMedicine(){
        return new Promise((resolve, reject) => {
            this.$refs["baseMedicineForm"].validate((valid) => {
                if (valid) {
                    let data=updateBaseMedicine(this.baseMedicine);
                    if(data.code=="200"){
                        Message({type: 'success',message: '修改成功！'})
                    }
                    this.dialogFormVisible=false;  
                    this.$refs["baseMedicineForm"].resetFields();
                    this.$refs["baseMedicineForm"].clearValidate(); 
                } else {
                    console.log("error submit!!");
                    reject("Validation failed");
                }
            })
        });     

    },
    async handleDelete(id){
        this.$messagebox
        .confirm("此操作将删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          try {
            let data = await deleteBaseMedicine(id);
            if (data.code === "200") {
              Message({type: 'success',message: '删除成功！'})
              this.initMedicineListByPage(this.object.currentPage);
            } else {
              Message({type: 'error',message: '删除失败！'})
            }
          } catch (error) {
            Message({type: 'error',message: '删除失败！'})
          }
        })
        .catch(() => {
          Message({
            type: 'success',
            message: '取消删除成功！'
          })
        });
      
    },
    
  }
}
</script>

<style>
.el-dialog__body {
 
 display: flex;

 justify-content: center;

 align-content: center;

}
</style>