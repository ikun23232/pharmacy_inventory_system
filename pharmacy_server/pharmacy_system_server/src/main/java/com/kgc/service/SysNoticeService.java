package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    public Message getNoticeList(String noticetitle, String startTime, String endTime, Page page);
    public Message existNotice(String noticetitle,Integer id);

    public Message delUserById( Integer[] ids);

    public Message updateNotice(SysNotice sysNotice);
    public Message saveNotice(SysNotice sysNotice);
}
