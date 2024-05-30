package com.kgc.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.BaseMedicineCategoryMapper;
import com.kgc.entity.BaseMedicineCategory;
import com.kgc.entity.Message;
import com.kgc.entity.SysUser;
import com.kgc.service.BaseMedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
@Transactional
public class BaseMedicineCategoryServiceImpl extends ServiceImpl<BaseMedicineCategoryMapper, BaseMedicineCategory> implements BaseMedicineCategoryService {

    @Autowired
    private BaseMedicineCategoryMapper baseMedicineCategoryMapper;

    @Override
    public Message listWithTree() {

        // 1.查询出所有的分类
        List<BaseMedicineCategory> entities = baseMedicineCategoryMapper.getAllCategoryList();
        // 2.组装成父子的树形结构
        List<BaseMedicineCategory> level1Menus = new ArrayList<>();
        // 找到所有的一级分类
        for (BaseMedicineCategory entity : entities) {
            if (entity.getParentId() == 0) {
                level1Menus.add(entity);
            }
        }
        for (BaseMedicineCategory level1Menu : level1Menus) {
            level1Menu.setChildren(getChildrens(level1Menu, entities));
        }
        //排序
        return Message.success(level1Menus);
    }

    @Override
    public Message delCatById(int id) {
        List<BaseMedicineCategory> baseMedicineCategories = baseMedicineCategoryMapper.checkCatByIdLst(id);
        if (!baseMedicineCategories.isEmpty()) {
            return Message.error("无法删除 里面仍然有数据哦！");
        }
        int updateRow = baseMedicineCategoryMapper.deleteById(id);

        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateCat(BaseMedicineCategory baseMedicineCategory) {
//        String token = GetUser.getUser();
//        SysUser loginUser = sysUserMapper.existUser(token,null);
        SysUser loginUser = (SysUser) StpUtil.getSession().get("user");
        baseMedicineCategory.setUpdateby(loginUser.getUserid());
        baseMedicineCategory.setUpdatedate(new Date());
        int updateRow = baseMedicineCategoryMapper.updateById(baseMedicineCategory);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message addCat(BaseMedicineCategory baseMedicineCategory) {
        baseMedicineCategory.setCreatedate(new Date());
        //写死添加人
        int userId = 1;
        baseMedicineCategory.setCreateby(userId);
        int updateRow = baseMedicineCategoryMapper.insert(baseMedicineCategory);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("添加失败");
    }

    @Override
    public Message checkCatByName(String name) {
        BaseMedicineCategory baseMedicineCategory = baseMedicineCategoryMapper.checkCatByName(name);
        if (baseMedicineCategory != null) {
            return Message.error("数据已经存在", baseMedicineCategory);
        }
        return Message.success(baseMedicineCategory);
    }

    @Override
    public Message checkCatById(Integer id) {
        BaseMedicineCategory baseMedicineCategory = baseMedicineCategoryMapper.checkCatById(id);
        if (baseMedicineCategory == null) {
            return Message.error("数据不存在", baseMedicineCategory);
        }
        return Message.success(baseMedicineCategory);
    }

    @Override
    public Message getAllBaseCategory() {
        List<BaseMedicineCategory> baseMedicineCategoryList=baseMedicineCategoryMapper.getAllBaseCategory();
       if(baseMedicineCategoryList!=null){
           return Message.success(baseMedicineCategoryList);
       }else {
           return Message.error();
       }
    }

    /**
     * 递归查找所有的下级分类
     * 在这一级别的分类中找下级分类
     *
     * @param root 当前级别的分类
     * @param all  全部分类
     * @return 下一级分类
     */
    private List<BaseMedicineCategory> getChildrens(BaseMedicineCategory root, List<BaseMedicineCategory> all) {
        List<BaseMedicineCategory> children = new ArrayList<>();
        for (BaseMedicineCategory a : all) {
            if (a.getParentId().equals(root.getId())) {
                a.setChildren(getChildrens(a, all));
                children.add(a);
            }
        }
        return children;
    }
}