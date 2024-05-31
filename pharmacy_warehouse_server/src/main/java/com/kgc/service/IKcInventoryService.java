package com.kgc.service;

import com.kgc.entity.KcInventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.vo.KcInventoryVo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 盘点单 服务类
 * </p>
 *
 * @author author
 * @since 2024-05-20
 */
public interface IKcInventoryService extends IService<KcInventory> {
    public Message getAllCheckByPage(KcInventoryVo vo, Page page);
    public Message getKcMedicineByMedicine(KcInventoryVo inventory,Page page);
    public Message addCheck(KcInventoryVo inventory);
    public Message getKcInventoryVoById(Integer id);
    public Message updateorderStatus(Integer id);
    public Message updateisVoid(Integer id);

    public Message approveCheck(KcInventoryVo inventory);
    public void checkExcel(KcInventoryVo inventoryVo, HttpServletResponse response);


}
