package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.UnitMapper;
import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-05-07 10:02
 **/
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, BaseUnit> implements UnitService {
    @Autowired
    private UnitMapper unitMapper;

    @Override
    public Message getUnitList() {
        List<BaseUnit> baseUnits = unitMapper.selectList(null);
        return Message.success(baseUnits);
    }

    @Override
    public Message getUnitListByPage(Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<BaseUnit> unitListByPage = unitMapper.getUnitListByPage();
        PageInfo<BaseUnit> pageInfo = new PageInfo<>(unitListByPage);
        return Message.success(pageInfo);
    }

    @Override
    public Message delUnitById(int id) {
        int updateRow = unitMapper.deleteById(id);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateUnit(BaseUnit baseUnit) {
        int updateRow = unitMapper.updateById(baseUnit);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message getUnitById(int id) {
        BaseUnit baseUnit = unitMapper.selectById(id);
        return Message.success(baseUnit);
    }

    @Override
    public Message CheckUnit(String unit, int id) {
        // 查询数据库，看是否存在与新用户名相同的记录，且不是当前要修改的记录
        QueryWrapper<BaseUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", unit).ne("id", id); // 查询条件：name 字段等于新用户名，并且 id 不等于当前记录的 id
        int updateRow = Math.toIntExact(unitMapper.selectCount(queryWrapper));
        if (updateRow > 0) {
            return Message.error("单位已经存在");
        }
        return Message.success();
    }

}
