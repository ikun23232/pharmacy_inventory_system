package com.kgc.service;

import com.kgc.entity.KcOutintodetial;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface KcOutintodetialService extends IService<KcOutintodetial> {

    Message getKcOutinTOdetailList(DispatchVO dispatchVO);
    Message delKcOutinTodetail(int id);

    void crkmxExcel(DispatchVO vo, HttpServletResponse response);

    Message addKcOutinTodetail(KcOutintodetial kcOutintodetial);

}
