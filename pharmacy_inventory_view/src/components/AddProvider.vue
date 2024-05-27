<template>
    <span slot="footer" class="dialog-footer">
        <el-form :model="baseProvider" :rules="rules" ref="baseProvider" id="baseProvider" label-width="100px"
            class="demo-ruleForm">
            <el-form-item label="供应商名称" prop="name">
                <el-input v-model="baseProvider.name" name="name" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="供应商地址" prop="address">
                <el-input v-model="baseProvider.address" name="address" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="供应商类别" prop="type">
                <el-select v-model="baseProvider.type" name="type" placeholder="请选择" style="width: 300px">
                    <el-option v-for="item in optionTypeList" :key="item.id" :label="item.type" :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="创建人" prop="createBy">
                <input type="hidden" v-model="user.userId" name="createBy" />
                <el-input v-model="user.userName" style="width: 300px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('baseProvider')">立即创建</el-button>
                <el-button @click="close()">取 消</el-button>
                <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
        </el-form>
    </span>
</template>
<script>
import { getProviderType, addBaseProvider,checkaddBaseProvider} from "../api/BaseProvider.js"
import { Message } from "element-ui";
export default {
    data() {
        return {
            optionTypeList: {},
            //添加弹框开关
            dialogVisible: false,
            baseProvider: {
                name: '',
                address: '',
                createBy: '',
                type: '',
            },
            flag:{
                nameFlag:false
            },
            user: {
                userId: 1,
                userName: "admin"
            },
            rules: {
                name: [
                    { required: true, message: "请输入供应商名", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                    { validator: this.checkaddBaseProvider, trigger: 'blur' }
                ],
                type: [
                    { required: true, message: "请选择类别", trigger: "blur" },
                ],
                address: [
                    { required: true, message: "请输入地址", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 5 到 14 个字符', trigger: 'blur' },
                ],
            }
        }
    },
    mounted() {
        this.getProviderType()
    },
    methods: {
        async getProviderType() {
            let data = await getProviderType();
            console.log(data)
            this.optionTypeList = data.data;
        },
        async checkaddBaseProvider(rule, value, callback) {
            try {
                let data = await checkaddBaseProvider(this.baseProvider.name);
                if (data.code ==="200") {
                    this.flag.nameFlag = true
                    callback();
                } else {
                    this.flag.nameFlag = false
                    callback(new Error("该供应商公司已被注册！"))
                }
            } catch (error) {
                callback(new Error('验证失败，请重试')); // 验证出错
            }
        },
        close(){
            this.$emit("close");
        },
        resetForm() {
            this.baseProvider.name = '',
                this.baseProvider.address = '',
                this.baseProvider.type = '',
                this.baseProvider.createdate = ''
        },

        submitForm(baseProvider) {
            return new Promise((resolve, reject) => {
                this.$refs[baseProvider].validate((valid) => {
                    if (valid) {
                        const formdata = new FormData(document.getElementById("baseProvider"))
                        console.log(formdata)
                        formdata.set("type", this.baseProvider.type)
                        formdata.set("createBy", this.user.userId)
                        this.add(formdata)
                        resolve(); // Resolve the promise if validation is successful
                    } else {
                        console.log("error submit!!")
                        reject("Validation failed"); // Reject the promise if validation fails
                    }
                })
            });
        },
        async add(formdata) {
            let data = await addBaseProvider(formdata)
            if (data.code === "200") {
                Message({
                    type: 'success',
                    message: '添加成功！'
                })
                this.$emit("updateSuccess");
            } else {
                Message({
                    type: 'error',
                    message: '添加失败！'
                })
            }
        },
    },
}
</script>
