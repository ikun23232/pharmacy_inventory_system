package com.kgc.service;

import com.kgc.entity.KcInventorydetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-05-21
 */
public interface IKcInventorydetailService extends IService<KcInventorydetail> {


    public boolean updateCheck(KcInventorydetail kcInventorydetail,Integer id);
    public Message getKcInventoryUnitName(Integer warehouseId,Integer medecineId);
    public Message getKcInventoryDetailByIdwithother(Integer id);
    public Message getAllKcInventoryDetailById(Integer id);

}
