package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.*;
import com.kgc.dao.CwCgyfDao;
import com.kgc.feign.BaseMedineFeign;
import com.kgc.feign.ProcurementOrderFeign;
import com.kgc.service.CwAccountsService;
import com.kgc.service.CwCgyfService;
//import com.kgc.service.ProcurementOrderService;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwCgyfVO;
import com.kgc.vo.MedicineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * (CwCgyf)表服务实现类
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
@Service("cwCgyfService")
public class CwCgyfServiceImpl extends ServiceImpl<CwCgyfDao, CwCgyf> implements CwCgyfService {

    @Resource
    private CwCgyfDao cwCgyfDao;

    @Resource
    private BaseMedineFeign baseMedineFeign;

    @Resource
    private CwAccountsService cwAccountsService;
//    @Resource
//    private ProcurementOrderService procurementOrderService;


    @Resource
    private ProcurementOrderFeign procurementOrderFeign;

    @Override
    public Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwCgyf> list = cwCgyfDao.getCwCgyfList(cwCgyf);
        PageInfo<CwCgyf> pageInfo = new PageInfo<>(list);
        if (!list.isEmpty()){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message updateCwCgyf(CwCgyf cwCgyf) {
        CwCgyf updateCwCgyf = new CwCgyf();
        updateCwCgyf.setId(cwCgyf.getId());
        updateCwCgyf.setIsPay(2);
        int isPay = cwCgyfDao.updateById(updateCwCgyf);

        CgddOrder cgddOrder = new CgddOrder();
        cgddOrder.setId(cwCgyf.getCgddId());
        cgddOrder.setIsPay(1);
        cgddOrder.setPayTime(new Date());
        int isPay1 = (int) procurementOrderFeign.updateCgddIsPayById(cgddOrder).getData();
        if (isPay1 <= 0){
            return Message.error();
        }
        CwCgyf cwCgyfById = cwCgyfDao.getCwCgyfById(cwCgyf.getId());
        CwAccounts cwAccounts = new CwAccounts();
//        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String code = CodeUtil.createCode("CG");
        cwAccounts.setCode(code);
        cwAccounts.setCategoryId(5);
        cwAccounts.setCost(cwCgyfById.getCost());
        cwAccounts.setAccountsCategoryName("采购付款");
        cwAccounts.setCreateTime(new Date());
        cwAccounts.setOrderCode(cwCgyfById.getCode());
        cwAccounts.setCreateBy(cwCgyf.getPayUserId());
        cwAccountsService.addCwAccounts(cwAccounts);
        if (isPay > 0){
            return Message.success();
        }
        return Message.error();
    }

    @Override
    public Message getCwCgyfByCode(String code) {
        CwCgyf cwCgyfByCode = cwCgyfDao.getCwCgyfByCode(code);
        if (cwCgyfByCode != null){
            return Message.success(cwCgyfByCode);
        }
        return Message.error();
    }

    @Override
    public CwCgyf getCwCgyfById(int id) {
        return cwCgyfDao.getCwCgyfById(id);
    }

    @Override
    public Message getProviderList() {
        List<BaseProvider> list = cwCgyfDao.getProviderList();
        if (!list.isEmpty()){
            return Message.success(list);
        }
        return Message.error();

    }

    @Override
    public Message getCgddByCode(CgddOrder cgddOrder) {
        CgddOrder cgddByCode = cwCgyfDao.getCgddByCode(cgddOrder);
        if (cgddByCode != null){
            return Message.success(cgddByCode);
        }
        return Message.error("该订单编号没有订单");
    }

    @Override
    public void cwCgyfExcel(HttpServletResponse response) {
        List<CwCgyfVO> listExcel = cwCgyfDao.getCwCgyfVOList();
        List<CwCgyfVO> listExcels = new ArrayList<>();
        for (CwCgyfVO cwCgyfVO : listExcel){
            List<MedicineVO> medicineVOList = baseMedineFeign.getMedicineVOListByCodes(cwCgyfVO.getCgddCode());
            cwCgyfVO.setMedicineVOList(medicineVOList);
            listExcels.add(cwCgyfVO);
        }
        try {
            ExeclUtil.write(listExcels, CwCgyfVO.class,response,"采购应付");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addCgyf(CwCgyf cwCgyf) {
        int insert = cwCgyfDao.insert(cwCgyf);
        if (insert >0){
            return Message.success();
        }
        return Message.error("添加失败");
    }
}
