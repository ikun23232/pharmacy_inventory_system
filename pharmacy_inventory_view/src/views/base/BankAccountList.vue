<template>
    <div id="app">
        <p>
            <label style="font-weight: bolder; font-size: 14px">银行账户:&nbsp;</label>
            <el-input style="width: 200px" placeholder="请输入内容" v-model="bandCount" clearable>
            </el-input>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label style="font-weight: bolder; font-size: 14px">账户名:&nbsp;</label>
            <el-input style="width: 200px" placeholder="请输入内容" v-model="name" clearable>
            </el-input>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label style="font-weight: bolder; font-size: 14px">银行名称:&nbsp;</label>
            <el-input style="width: 200px" placeholder="请输入内容" v-model="belongBank" clearable>
            </el-input>&nbsp;&nbsp;&nbsp;
            <el-button type="primary" icon="el-icon-delete" style=" border: none"
                @click="clearsearch()">重置</el-button>&nbsp;
            <el-button type="primary" icon="el-icon-search" style=" border: none"
                @click="handlegetbankAccountList(1)">搜索</el-button>&nbsp;
            <el-button type="primary" icon="el-icon-search" style=" border: none"
                @click="addBankAccount()">添加</el-button>
        </p>
        <br/>
        <el-table :data="page.list" style="width: 100%; border: black" max-height="1000">
            <el-table-column label="序号" width="120" align="center">
                <template slot-scope="scope">
                    {{ scope.$index + 1 }}
                </template>
            </el-table-column>
            <el-table-column prop="bandCount" label="银行账户" width="130" align="center">
            </el-table-column>
            <el-table-column prop="name" label="账户名" width="130" align="center">
            </el-table-column>
            <el-table-column prop="balance" label="余额" width="130" align="center">
            </el-table-column>
            <el-table-column prop="belongBank" label="所属银行" width="130" align="center">
            </el-table-column>
            <el-table-column prop="tip" label="备注" width="130" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.tip">{{ scope.row.tip }}</span>
                    <span v-if="!scope.row.tip">暂无</span>
                </template>
            </el-table-column>
            <el-table-column prop="createMan" label="创建人" width="130" align="center">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="200" align="center">
            </el-table-column>
            <el-table-column prop="updateMan" label="修改人" width="130" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.updateMan">{{ scope.row.updateMan }}</span>
                    <span v-if="!scope.row.updateMan">暂无</span>
                </template>
            </el-table-column>
            <el-table-column prop="updateTime" label="修改时间" width="200" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.updateTime">{{ scope.row.updateTime }}</span>
                    <span v-if="!scope.row.updateTime">暂无</span>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <p style="width: 200px">
                        <el-button type="primary" style="width: 80px" @click="toUpdata(scope.row)"
                            icon="el-icon-edit">修改</el-button>
                        &nbsp;
                        <el-button type="primary" style="width: 80px" @click="del(scope.row)"
                            icon="el-icon-delete">删除</el-button>
                        &nbsp;
                    </p>
                </template>
            </el-table-column>
        </el-table>
        <!-- 添加弹窗 -->
        <el-dialog title="添加银行账户" :visible.sync="dialogVisible" width="30%" v-if="dialogVisible">
            <AddBankAccount @updateSuccess="handleUpdateSuccess" @close="close"></AddBankAccount>
        </el-dialog>

        <!-- 修改弹窗 -->
        <el-dialog title="修改银行账户" :visible.sync="updatedialogVisible" width="30%" v-if="updatedialogVisible">
            <UpdateBankAccount :bandCount="updateBandCount" :belongBank="updatebelongBank" :name="updatename" :id="id"
                @updateSuccess="handleUpdateSuccess" @close="close"></UpdateBankAccount>
        </el-dialog>

        <div style="width: 100%; height: 30px"></div>
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handlegetbankAccountList"
                :current-page="page.pageNum" :page-sizes="[5, 8]" :page-size="page.pageSize" :page-count="page.pages"
                layout="total, sizes, prev, pager, next, jumper" :total="page.total">
            </el-pagination>
        </div>
    </div>
</template>
<script>
import AddBankAccount from "../../components/AddBankAccount.vue";
import UpdateBankAccount from "../../components/UpdateBankAccount.vue";
import { init, delBankAccount } from "../../api/BankAccount.js";
import { Message } from "element-ui";
export default {
    components: {
        AddBankAccount,
        UpdateBankAccount
    },
    data() {
        return {
            page: {
                currentPageNo: 1,
                pageSize: 5
            },
            //修改需要的id
            id: '',
            updateBandCount: '',
            updatebelongBank: '',
            updatename: '',
            // 条件查询数据
            bandCount: '',
            name: '',
            belongBank: '',
            //添加弹框开关
            dialogVisible: false,
            updatedialogVisible: false,
        }
    },
    mounted() {
        this.handlegetbankAccountList(1)
    },
    methods: {
        async handlegetbankAccountList(currentPageNo) {
            let data = await init(this.bandCount, this.name, this.belongBank, currentPageNo, this.page.pageSize);
            this.page = data.data
            console.log(this.page)

        },
        clearsearch() {
            this.bandCount = '',
                this.name = '',
                this.belongBank = ''
            this.handlegetbankAccountList(1)
        },
        addBankAccount() {
            this.dialogVisible = true
        },
        toUpdata(row) {
            this.id = row.id
            this.updateBandCount = row.bandCount
            this.updatebelongBank = row.belongBank
            this.updatename = row.name
            this.updatedialogVisible = true
        },
        close() {
            this.dialogVisible = false
            this.updatedialogVisible = false
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
                        let data = await delBankAccount(row.bandCount);
                        if (data.code === "200") {
                            Message({
                                type: 'success',
                                message: '删除成功！'
                            })
                            this.handlegetbankAccountList(1)
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
                this.handlegetbankAccountList(1);
            }, 500);
            // 如果需要，可以在这里执行其他操作
        },
        handleSizeChange(val) {
            this.page.pageSize = val;
            this.handlegetbankAccountList(1);
        },
    },
}
</script>