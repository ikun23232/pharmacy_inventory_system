package com.kgc.service;

import com.kgc.entity.CgrkOrder;
import com.kgc.entity.KcDisfromware;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.vo.DispatchVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface KcDisfromwareService extends IService<KcDisfromware> {
   Message getKcDisfromwareList(DispatchVO vo);
   Message getKcDisfromwareOrder(int id);
   Message delKcDisfromware(int id);
   /**
    * 导出
    * @param
    * @param response
    */
   public void ddckExcel(KcDisfromware kcDisfromware, HttpServletResponse response);

}
