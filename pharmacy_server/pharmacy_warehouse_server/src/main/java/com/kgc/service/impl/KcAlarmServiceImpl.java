package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcAlarmMapper;
import com.kgc.dao.KcMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcAlarm;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.service.KcAlarmService;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.KcAlarmVO;
import com.kgc.vo.WarningVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 10:28
 */
@Service
@Transactional
public class KcAlarmServiceImpl extends ServiceImpl<KcAlarmMapper, KcAlarm> implements KcAlarmService {
    @Autowired
    private KcAlarmMapper kcAlarmMapper;
    @Autowired
    private KcMedicineMapper kcMedicineMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Message getKcAlarmList(WarningVo warningVo) {
        PageHelper.startPage(warningVo.getCurrentPageNo(), warningVo.getPageSize());
        List<KcAlarm> kcAlarmList = kcAlarmMapper.getKcAlarmList(warningVo);
        PageInfo pageInfo = new PageInfo(kcAlarmList);
        return Message.success(pageInfo);
    }

    @Override
    public Message deleteAlarmList(Integer id) {
        int i = kcAlarmMapper.deleteById(id);
        if (i > 0){
            return Message.success();
        }
        return Message.error("删除成功!");
    }

    @Override
    public Message updateRemind(Integer id) {
        KcAlarm kcAlarm = new KcAlarm();
        kcAlarm.setId(id);
        kcAlarm.setIsRemind(1);
        kcAlarm.setHandleDate(new Date());
        kcAlarm.setHandleBy(1);
        kcAlarm.setIsKnow(1);
        int i = kcAlarmMapper.updateById(kcAlarm);
        if (i > 0){
            return Message.success();
        }
        return Message.error("处理失败！");
    }

    @Override
    public void excelKcAlarm(KcAlarmVO kcAlarmVO, HttpServletResponse response) {
        List<KcAlarmVO> kcAlarmVOS = kcAlarmMapper.excelAlarmList(kcAlarmVO);
        try {
            ExeclUtil.write(kcAlarmVOS,KcAlarmVO.class,response,"库存告警");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkWarning(){
        List<KcAlarm> kcAlarmList = kcAlarmMapper.getKcAlarmList(null);
        List<KcMedicine> warningByLocalHouse = kcMedicineMapper.getWarningByLocalHouse();
        List<BaseMedicine> warningByTotalCount = kcMedicineMapper.getWarningByTotalCount();
        logger.debug("KcAlarmServiceImpl checkWarning warningByLocalHouse:"+warningByLocalHouse+",warningByTotalCount:"+warningByTotalCount);
        LambdaUpdateWrapper<KcAlarm> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(KcAlarm::getIsDel, 1);
        if (kcAlarmList.size() != 0){
            int update = kcAlarmMapper.update(null, updateWrapper);
            if (update <= 0){
                throw new RuntimeException("删除全部信息失败！");
            }
        }
        if (kcAlarmList.size() != 0){
            //第一个循环，当告警数据列表全为空时，便利循环添加本地仓库报警不为空时的记录
            if (warningByLocalHouse.size() != 0){
                for (KcMedicine kcMedicine :warningByLocalHouse) {
                    int count = 0;
                    for (KcAlarm kcAlarm :kcAlarmList) {
                        logger.debug("KcAlarmServiceImpl checkWarning kcAlarm："+kcAlarm);
                        if (kcAlarm.getMedicineId().equals(kcMedicine.getMedicineId())) {
                            KcAlarm temp = new KcAlarm();
                            if (kcAlarm.getWarningType() == 1) {
                                temp.setId(kcAlarm.getId());
                                temp.setIsDel(0);
                                temp.setWarningCount(kcMedicine.getQuantity());
                                int i = kcAlarmMapper.updateAlarm(temp);
                                if (i == 0) {
                                    throw new RuntimeException("修改本地仓库告警失败！");
                                }
                                break;
                            }
                        }
                        count++;
                        //当循环次数达到最后的告警列表长度，代表告警列没有这个数据，然后添加告警数据
                        if (count == kcAlarmList.size()){
                            KcAlarm temp = new KcAlarm();
                            temp.setWarningCount(kcMedicine.getQuantity());
                            temp.setCode(CodeUtil.createCode("KCGJ"));
                            temp.setMedicineId(kcMedicine.getMedicineId());
                            temp.setWarningType(1);
                            temp.setCreateDate(new Date());
                            int insert = kcAlarmMapper.insert(temp);
                            if (insert == 0){
                                throw new RuntimeException("添加告警信息失败！");
                            }
                        }
                    }
                }
            }
            //当全部库存总数告警数据查询出有新的数据时
            if (warningByTotalCount.size() != 0){
                for (BaseMedicine baseMedicine: warningByTotalCount) {
                    int count = 0;
                    for (KcAlarm kcAlarm :kcAlarmList) {
                        if (kcAlarm.getMedicineId().equals(baseMedicine.getId())) {
                            KcAlarm temp = new KcAlarm();
                            if (kcAlarm.getWarningType() == 2) {
                                temp.setId(kcAlarm.getId());
                                temp.setIsDel(0);
                                temp.setWarningCount(baseMedicine.getStock());
                                int i = kcAlarmMapper.updateAlarm(temp);
                                if (i == 0) {
                                    throw new RuntimeException("修改本地仓库告警失败！");
                                }
                                break;
                            }
                        }
                        count++;
                        if (count == kcAlarmList.size()){
                            KcAlarm temp = new KcAlarm();
                            temp.setWarningCount(baseMedicine.getStock());
                            temp.setCode(CodeUtil.createCode("KCGJ"));
                            temp.setMedicineId(baseMedicine.getId());
                            temp.setWarningType(2);
                            temp.setCreateDate(new Date());
                            int insert = kcAlarmMapper.insert(temp);
                            if (insert == 0){
                                throw new RuntimeException("添加告警信息失败！");
                            }
                        }
                    }
                }
            }
        }else {
             if (warningByLocalHouse.size() != 0) {
                for (KcMedicine kcMedicine :warningByLocalHouse) {
                    KcAlarm temp = new KcAlarm();
                    temp.setWarningCount(kcMedicine.getQuantity());
                    temp.setCode(CodeUtil.createCode("KCGJ"));
                    temp.setMedicineId(kcMedicine.getMedicineId());
                    temp.setWarningType(1);
                    temp.setCreateDate(new Date());
                    int insert = kcAlarmMapper.insert(temp);
                    if (insert == 0) {
                        throw new RuntimeException("添加告警信息失败！");
                    }
                }
            }
            if (warningByTotalCount.size() != 0) {
                for (BaseMedicine baseMedicine :warningByTotalCount) {
                    KcAlarm temp = new KcAlarm();
                    temp.setWarningCount(baseMedicine.getStock());
                    temp.setCode(CodeUtil.createCode("KCGJ"));
                    temp.setMedicineId(baseMedicine.getId());
                    temp.setWarningType(2);
                    temp.setCreateDate(new Date());
                    int insert = kcAlarmMapper.insert(temp);
                    if (insert == 0) {
                        throw new RuntimeException("添加告警信息失败！");
                    }
                }
            }
        }
    }

    @Override
    public Message getAlarmCount() {
        QueryWrapper<KcAlarm> queryWrapper = new QueryWrapper<>();
        Long count = kcAlarmMapper.selectCount(queryWrapper);
        return Message.success(count);
    }
}
