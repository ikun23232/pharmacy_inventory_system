package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwBsysDao;
import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;
import com.kgc.feign.KcMedicineFeign;
import com.kgc.service.CwBsysService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwBsysVO;
import com.kgc.vo.KcMedicineBSVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class CwBsysServiceImpl extends ServiceImpl<CwBsysDao, CwBsys> implements CwBsysService {

    @Autowired
    private CwBsysDao cwBsysDao;

    @Autowired
    private KcMedicineFeign kcMedicineFeign;

    @Override
    public Message getCwbsysList(CwBsys cwBsys, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwBsys> list=cwBsysDao.getCwbsysList(cwBsys);
        PageInfo<CwBsys> pageInfo=new PageInfo<>(list);
        if (list!=null){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message getCwbsysByCode(String code) {
        CwBsys cwBsys = cwBsysDao.getCwbsysByCode(code);
        if (cwBsys!=null){
            return Message.success(cwBsys);
        }
        return Message.error();
    }

    @Override
    public Message getStorehouseList() {
        List<BaseStorehouse> list = cwBsysDao.getStorehouseList();
        if (list!=null){
            return Message.success(list);
        }
        return Message.error();
    }

    @Override
    public void cwbsysExcel(HttpServletResponse response) {
        List<CwBsysVO> listExcel = cwBsysDao.getCwbsysVOList();
        List<CwBsysVO> listExcels=new ArrayList<>();
        for (CwBsysVO cwBsysVO : listExcel){
            List<KcMedicineBSVO> medicineList = kcMedicineFeign.getKcMedicineByReportedCodeTo(cwBsysVO.getOriginalOrder());
            cwBsysVO.setMedicineList(medicineList);
            listExcels.add(cwBsysVO);
        }

        try {
            ExeclUtil.write(listExcels, CwBsysVO.class,response,"报损应收");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addCwbsys(CwBsys cwBsys) {
        int isAdd = cwBsysDao.insert(cwBsys);
        if (isAdd>0){
            return Message.success(isAdd);
        }
        return Message.error();
    }

    @Override
    public boolean saveBatch(Collection<CwBsys> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<CwBsys> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<CwBsys> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(CwBsys entity) {
        return false;
    }

    @Override
    public CwBsys getOne(Wrapper<CwBsys> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<CwBsys> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<CwBsys> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public CwBsysDao getBaseMapper() {
        return null;
    }

    @Override
    public Class<CwBsys> getEntityClass() {
        return null;
    }
}
