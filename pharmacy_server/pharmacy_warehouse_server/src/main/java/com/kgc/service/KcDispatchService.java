package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcDispatch;
import com.kgc.entity.Message;
import com.kgc.vo.DispatchVO;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:09
 */
public interface KcDispatchService extends IService<KcDispatch> {
    public Message getKcDispathList(DispatchVO dispatchVO);
}
