package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface SysNoticeService extends IService<SysNotice> {

    public Message getSysNoticeList();

}
