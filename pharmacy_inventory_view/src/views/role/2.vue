<template>
	<div>
		<el-form :inline="true">
			<el-form-item>
				<el-input
						v-model="searchForm.name"
						placeholder="请输入角色名"
						clearable
				>
				</el-input>
			</el-form-item>

			<el-form-item>
				<el-button @click="getRoleList">搜索</el-button>
			</el-form-item>

			<el-form-item>
				<el-button type="primary" @click="dialogVisible = true">新增</el-button>
			</el-form-item>
			<el-form-item>
				<el-popconfirm title="这是确定批量删除吗？" @confirm="delHandle(null)">
					<el-button type="danger" slot="reference" :disabled="delBtlStatu">批量删除</el-button>
				</el-popconfirm>
			</el-form-item>
		</el-form>

		<el-table
				ref="multipleTable"
				:data="tableData"
				tooltip-effect="dark"
				style="width: 100%"
				border
				stripe
				@selection-change="handleSelectionChange">

			<el-table-column
					type="selection"
					width="55">
			</el-table-column>

			<el-table-column
					prop="rolename"
					label="名称"
					width="120">
			</el-table-column>
			<el-table-column
					prop="code"
					label="唯一编码"
					show-overflow-tooltip>
			</el-table-column>
			<el-table-column
					prop="isUse"
					label="状态">
				<template slot-scope="scope">
					<el-tag size="small" v-if="scope.row.isUse === 1" type="success">正常</el-tag>
					<el-tag size="small" v-else-if="scope.row.isUse === 0" type="danger">禁用</el-tag>
				</template>

			</el-table-column>
			<el-table-column
					prop="icon"
					label="操作">

				<template slot-scope="scope">
					<el-button type="text" @click="permHandle(scope.row.roleId)">分配权限</el-button>
					<el-divider direction="vertical"></el-divider>

					<el-button type="text" @click="editHandle(scope.row.roleId)">编辑</el-button>
					<el-divider direction="vertical"></el-divider>

					<template>
						<el-popconfirm title="这是一段内容确定删除吗？" @confirm="delHandle(scope.row.roleId)">
							<el-button type="text" slot="reference">删除</el-button>
						</el-popconfirm>
					</template>

				</template>
			</el-table-column>

		</el-table>


		<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				layout="total, sizes, prev, pager, next, jumper"
				:page-sizes="[10, 20, 50, 100]"
				:current-page="current"
				:page-size="size"
				:total="total">
		</el-pagination>
	</div>
</template>

<script>
	export default {
		name: "Role",
		data() {
			return {
				searchForm: {},
				delBtlStatu: true,
				total: 0,
				size: 10,
				current: 1,
				dialogVisible: false,
				editForm: {
				},
				tableData: [],

				editFormRules: {
					name: [
						{required: true, message: '请输入角色名称', trigger: 'blur'}
					],
					code: [
						{required: true, message: '请输入编码', trigger: 'blur'}
					],
					statu: [
						{required: true, message: '请选择状态', trigger: 'blur'}
					]
				},

				multipleSelection: [],

				permDialogVisible: false,
				permForm: {},
				defaultProps: {
					children: 'children',
					label: 'name'
				},
			}
		},
		created() {
			this.getRoleList()

			
		},
		methods: {
			
			

			
		

			
			getRoleList() {
				this.$axios.get("/user/role/list", {
					params: {
						name: this.searchForm.name,
						current: this.current,
					}
				}).then(res => {
                    console.log(1000);
                    console.log(res.data);
					this.tableData = res.data.data.list
					this.size = res.data.data.size
					this.current = res.data.data.current
					this.total = res.data.data.total
				})
			},

			
        }
	}
</script>

<style scoped>

	.el-pagination {
		float: right;
		margin-top: 22px;
	}

</style>