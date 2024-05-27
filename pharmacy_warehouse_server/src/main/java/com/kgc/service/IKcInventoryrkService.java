package com.kgc.service;

import com.kgc.entity.KcInventoryrk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.vo.KcInventoryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-05-25
 */
public interface IKcInventoryrkService extends IService<KcInventoryrk> {
    public Message getAllCheckRkByPage(KcInventoryrk vo, Page page);
    public Message getAllCheckById(Integer id);
    public void getAllCheckRkexcel(KcInventoryrk kcInventoryrk,HttpServletResponse response);
    public Message approveRK(Integer id,Integer status);

}
