package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDistoware;
import com.kgc.entity.Message;
import com.kgc.vo.DispatchVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface KcDistowareService extends IService<KcDistoware> {
    Message getKcDistowareList(DispatchVO vo);
    Message getKcDistowareOrder(int id);
    Message delKcDistoware(int id);
    /**
     * 导出
     * @param
     * @param response
     */
    public void ddrkExcel(KcDistoware kcDisfromware, HttpServletResponse response);

}
