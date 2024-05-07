<template>
    <span slot="footer" class="dialog-footer">
        <el-form :model="baseProvider" :rules="rules" ref="menu" id="menu" label-width="100px" class="demo-ruleForm">
            <el-form-item label="供应商名称" prop="name">
                <el-input v-model="baseProvider.name" name="name" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="供应商地址" prop="price">
                <el-input v-model="baseProvider.price" name="price" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="供应商类别" prop="classId">
                <el-select v-model="baseProvider.classId" name="classId" placeholder="请选择">
                    <el-option v-for="item in optionClassList" :key="item.id" :label="item.className" :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="创建人" prop="tasteId">
                <el-select v-model="baseProvider.tasteId" name="tasteId" placeholder="请选择">
                    <el-option v-for="item in optionTasteList" :key="item.id" :label="item.tasteName" :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="创建时间" prop="price">
                <el-input v-model="baseProvider.price" name="price" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="修改人" prop="tasteId">
                <el-select v-model="baseProvider.tasteId" name="tasteId" placeholder="请选择">
                    <el-option v-for="item in optionTasteList" :key="item.id" :label="item.tasteName" :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="修改时间" prop="price">
                <el-input v-model="baseProvider.price" name="price" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('menu')">立即创建</el-button>
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
        </el-form>
    </span>
</template>