package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseMedicineService extends IService<BaseMedicine> {

    public Message getBaseMedicineList(int currentPage);

    public Message addBaseMedicine(BaseMedicine baseMedicine);

    public Message updateBaseMedicineById(BaseMedicine baseMedicine);

    public Message deleteBaseMedicineById(int id);

}
