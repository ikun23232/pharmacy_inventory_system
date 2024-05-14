<template>
	<el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab" @tab-click="clickTab">
		<el-tab-pane
				v-for="(item) in editableTabs"
				:key="item.name"
				:label="item.title"
				:name="item.name"
		>

		</el-tab-pane>
	</el-tabs>
</template>

<script>
	export default {
		name: "Tabs",
		data() {
			return {
				// editableTabsValue: this.$store.state.menus.editableTabsValue,
				// editableTabs: this.$store.state.menus.editableTabs
			}
		},
		computed: {
			editableTabs: {
				get() {
					
					return this.$store.state.menus.editableTabs
				},
				set(val) {
					console.log(22323);
					console.log(val);
					this.$store.state.menus.editableTabs = val
				}
			},
			editableTabsValue: {
				get() {
					
					return this.$store.state.menus.editableTabsValue
				},
				set(val) {
					console.log(5322123);
					console.log(this.$store.state.menus.editableTabsValue);
					this.$store.state.menus.editableTabsValue = val
				}
			}
		},
		methods: {

			removeTab(targetName) {
				let tabs = this.editableTabs;
				let activeName = this.editableTabsValue;
				console.log(2322123);
				console.log(this.editableTabs);
					console.log(this.editableTabsValue);
				if (activeName === 'Index') {
					return
				}

				if (activeName === targetName) {
					tabs.forEach((tab, index) => {
						if (tab.name === targetName) {
							
							let nextTab = tabs[index + 1] || tabs[index - 1];
							if (nextTab) {
								
								activeName = nextTab.name;
							}
						}
					});
				}

				this.editableTabsValue = activeName;
				this.editableTabs = tabs.filter(tab => tab.name !== targetName);

				this.$router.push({name: activeName})

			},
			clickTab (target) {
				this.$router.push({name: target.name})
			}
		}
	}
</script>

<style scoped>

</style>