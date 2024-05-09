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
            <el-form-item label="创建时间" prop="createTime">
                <el-input v-model="bankAccount.createTime" name="createTime" style="width: 300px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('bankAccount')">立即创建</el-button>
                <el-button @click="updatedialogVisible = false">取 消</el-button>
                <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
        </el-form>
    </span>
</template>
<script>
import {getBankAccountBybandCount,updateBankAccount} from "../api/BankAccount.js"
import { Message } from "element-ui";
export default {
    props: {
        bandCount: {
            type: String,
            required: true
        },
    },
    data() {
        return {
            //添加弹框开关
            updatedialogVisible: false,
            bankAccount: {
                bandCount: this.bandCount,
                name: '',
                balance: '',
                tip: '',
                belongBank: ''
            },
            user: {
                userId: 1,
                userName: "admin"
            },
            rules: {
                bandCount: [
                    { required: true, message: "请输入银行账户", trigger: "blur" },
                ],
                name: [
                    { required: true, message: "请输入账户名", trigger: "blur" },
                ],
                balance: [
                    { required: true, message: "请输入余额", trigger: "blur" },
                ],
                tip: [
                    { required: true, message: "请选择备注", trigger: "blur" },
                ],
                belongBank: [
                    { required: true, message: "请输入所属银行", trigger: "blur" },
                ],
            }
        }
    },
    mounted() {
        this.getBankAccountBybandCount()
    },
    methods: {
        async getBankAccountBybandCount() {
            let data = await getBankAccountBybandCount(this.bankAccount.bandCount);
            console.log(data);
            this.bankAccount = data.data
        },

        resetForm() {
            this.bankAccount.bandCount = '',
                this.bankAccount.name = '',
                this.bankAccount.balance = '',
                this.bankAccount.tip = '',
                this.bankAccount.belongBank = ''
        },

        submitForm(bankAccount) {
            return new Promise((resolve, reject) => {
                this.$refs[bankAccount].validate((valid) => {
                    if (valid) {
                        const formdata = new FormData(document.getElementById("bankAccount"))
                        console.log(formdata)
                        formdata.set("createBy", this.bankAccount.createBy)
                        formdata.set("updateBy", this.user.userId)
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
            let data = await updateBankAccount(formdata)
            if (data.code === "200") {
                Message({
                    type: 'success',
                    message: '修改成功！'
                })
                this.$emit("updateSuccess");
            } else {
                Message({
                    type: 'error',
                    message: '修改失败！'
                })
            }
        },
    },
}
</script>
