<template>
    <span slot="footer" class="dialog-footer">
      <el-form
        :model="reOrder"
        :rules="rules"
        ref="reOrder"
        id="reOrder"
        label-width="100px"
        class="demo-ruleForm"
      >
      <el-form-item label="计量单位名" prop="name">
      <el-input v-model="reOrder.name"></el-input>
    </el-form-item>
    <el-form-item label="计量单位符号" prop="sign">
      <el-input v-model="reOrder.sign"></el-input>
    </el-form-item>
       
        <input name="serialNumber" type="hidden" v-model="reOrder.serialNumber" />
        <el-form-item>
          <el-button type="primary" @click="submitForm('reOrder')"
            >立即修改</el-button
          >
          <el-button @click="close">取 消</el-button>
        </el-form-item>
      </el-form>
    </span>
  </template>
  <script>
  import {addUnit,CheckUnit} from '../api/BaseUnit'
  import { Message } from "element-ui";
  export default {
    props: {
      serialNumber: {
        type: String,
        required: true,
      },
    },
    data() {
      var validatePass = async(rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入计量单位名称'));
          } else {
  
            let resp=await CheckUnit(this.reOrder.name,0);
            if(resp.code==200){
              callback();
            }else{
              callback(new Error('计量单位已经重复'));
            }
      
          }
        }
      return {
        optionStatusList: {},
        reOrder: {
          serialNumber: this.serialNumber,
          statusId: "",
          name:'',
          sign:''
        },
        imageUrl: "",
        rules: {
         
          name: [{ required: true, message: "请输入计量单位名", trigger: "blur" },
          { validator: validatePass, trigger: 'blur' }],
          sign: [{ required: true, message: "请输入计量符号", trigger: "blur" }],
        },
      };
    },
    mounted() {
      // this.getStatusList();
      // this.init();
    },
    methods: {   
      submitForm(reOrder) {
        this.$refs[reOrder].validate((valid) => {
          if (valid) {
      
            this.add(this.reOrder);
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      },
      async add(formdata) {
         let respones = await addUnit(formdata);
          console.log(respones);
          if (respones.code == "200") {
            Message({
          type: "success",
          message: "添加成功",
        });
            this.$emit("addSuccess");
          } else {
            Message({
          type: "success",
          message: "添加失败",
        });
          }
        },
        close(){
        this.$emit("addSuccess");
      }
      ,
    },
  };
  </script>
  