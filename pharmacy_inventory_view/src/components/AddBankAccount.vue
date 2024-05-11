<template>
    <span slot="footer" class="dialog-footer">
        <el-form :model="bankAccount" :rules="rules" ref="bankAccount" id="bankAccount" label-width="100px"
            class="demo-ruleForm">
            <el-form-item label="银行账户" prop="bandCount">
                <el-input v-model="bankAccount.bandCount" name="bandCount" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="账户名" prop="name">
                <el-input v-model="bankAccount.name" name="name" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="余额" prop="balance">
                <el-input v-model="bankAccount.balance" name="balance" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="tip">
                <el-input v-model="bankAccount.tip" name="tip" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="所属银行" prop="belongBank">
                <el-input v-model="bankAccount.belongBank" name="belongBank" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="创建人" prop="createBy">
                <input type="hidden" v-model="user.userId" name="createBy"/>
                <el-input v-model="user.userName" style="width: 300px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('bankAccount')">立即创建</el-button>
                <el-button @click="close()">取 消</el-button>
                <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
        </el-form>
    </span>
</template>
<script>
import { addBankAccount,checkaddBankAccount } from "../api/BankAccount.js";
import { Message } from "element-ui";
export default {
    data() {
        return {
            //添加弹框开关
            dialogVisible: false,
            bankAccount: {
                bandCount: '',
                name: '',
                balance: '',
                tip: '',
                belongBank: ''
            },
            flag:{
                bandCountFlag:false,
                nameFlag:false,
                belongBank:false
            },
            user: {
                userId: 1,
                userName: "admin"
            },
            rules: {
                bandCount: [
                    { required: true, message: "请输入银行账户", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                    { validator: this.checkaddBankAccount, trigger: 'blur' }
                ],
                name: [
                    { required: true, message: "请输入账户名", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                    { validator: this.checkaddBankAccount, trigger: 'blur' }
                ],
                balance: [
                    { required: true, message: "请输入余额", trigger: "blur" },
                ],
                tip: [
                    { required: true, message: "请选择备注", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                ],
                belongBank: [
                    { required: true, message: "请输入所属银行", trigger: "blur" },
                    { min: 3, max: 14, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                    { validator: this.checkaddBankAccount, trigger: 'blur' }
                ],
            }
        }
    },
    methods: {
        resetForm() {
            this.bankAccount.bandCount = '',
                this.bankAccount.name = '',
                this.bankAccount.balance = '',
                this.bankAccount.tip = '',
                this.bankAccount.belongBank = ''
        },
        async checkaddBankAccount(rule, value, callback) {
            try {
                let data = await checkaddBankAccount(this.bankAccount.belongBank,this.bankAccount.name,this.bankAccount.bandCount);
                if (data.code ==="200") {
                    this.flag.nameFlag = true
                    this.flag.bandCountFlag = true
                    this.flag.belongBank = true
                    callback();
                } else {
                    this.flag.nameFlag = false
                    this.flag.bandCountFlag = false
                    this.flag.belongBank = false
                    callback(new Error("此银行已存在该银行账户和账户名！"))
                }
            } catch (error) {
                callback(new Error('验证失败，请重试')); // 验证出错
            }
        },
        submitForm(bankAccount) {
            return new Promise((resolve, reject) => {
                this.$refs[bankAccount].validate((valid) => {
                    if (valid) {
                        const formdata = new FormData(document.getElementById("bankAccount"))
                        console.log(formdata)
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
        close(){
            this.$emit("close");
        },
        async add(formdata) {
            let data = await addBankAccount(formdata)
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
