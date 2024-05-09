package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;

public interface UnitService extends IService<BaseUnit> {
    public Message getUnitList();
    public Message getUnitListByPage(Page page);

    public Message delUnitById(int id);
    public Message updateUnit(BaseUnit baseUnit);
    public Message addUnit(BaseUnit baseUnit);

    public Message getUnitById(int id);

    /**
     * 校验计量单位名称是否相同
     * @param unit
     * @return
     */
    public Message CheckUnit(String unit,int id);



}
