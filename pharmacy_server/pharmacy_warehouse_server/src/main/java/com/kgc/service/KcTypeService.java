package com.kgc.service;

import com.kgc.entity.KcType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface KcTypeService extends IService<KcType> {


   Message  getKcTypeList();

}
