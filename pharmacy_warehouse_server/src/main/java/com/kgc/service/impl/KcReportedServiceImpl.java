package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcReportedMapper;
import com.kgc.entity.*;
import com.kgc.feign.CwAccountsFeign;
import com.kgc.feign.CwBsysFeign;
import com.kgc.service.*;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.KcMedicineBSVO;
import com.kgc.vo.KcReportedVO;
import com.kgc.vo.KcReportedfromwareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
@Transactional
public class KcReportedServiceImpl extends ServiceImpl<KcReportedMapper, KcReported> implements KcReportedService {

    @Autowired
    private KcReportedMapper kcReportedMapper;

    @Autowired
    private KcMedicineService kcMedicineService;

    @Autowired
    private KcOutintodetialService kcOutintodetialService;



//    @Autowired
//    private CwBsysService cwBsysService;

    @Resource
    private CwBsysFeign cwBsysFeign;

//    @Autowired
//    private CwAccountsService cwAccountsService;

    @Resource
    private CwAccountsFeign cwAccountsFeign;

//    private KcOutintodetialService kcOutintodetialService;
    /**
     * 分页获取库存报损列表
     * @param kcReported
     * @param pageNum
     * @param pageSize
     */
    @Override
    public Message getKcReportedList(KcReported kcReported,int pageNum,int pageSize) {
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (kcReported.getBeginTime()!=null&& " ".equals(kcReported.getBeginTime())){
                Date beginDate = inputSdf.parse(kcReported.getBeginTime());
                Date endDate = inputSdf.parse(kcReported.getEndTime());
                kcReported.setBeginTime(outputSdf.format(beginDate));
                kcReported.setEndTime(outputSdf.format(endDate));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<KcReported> kcReportedList = kcReportedMapper.getKcReportedList(kcReported);
        PageInfo<KcReported> kcReportedPageInfo = new PageInfo<>(kcReportedList);
        if (!kcReportedList.isEmpty()){
            return Message.success(kcReportedPageInfo);
        }
        return Message.error();
    }
    /**
     * 根据id获取库存报损
     * @param id
     */
    @Override
    public Message getKcReportedListById(Integer id) {
        KcReported kcReported = kcReportedMapper.getKcReportedListById(id);
        if (kcReported!=null){
            return Message.success(kcReported);
        }
        return Message.error();
    }

    /**
     * 根据code获取code
     * @param code
     */
    @Override
    public Message getCodeByCode(String code) {
        List<String> codeByCode = kcReportedMapper.getCodeByCode(code);
        if (!codeByCode.isEmpty()){
            return Message.success(codeByCode);
        }
        return Message.error();
    }
    /**
     * 获取仓库列表
     */
    @Override
    public Message getStorehouseList() {
        List<BaseStorehouse> storehouseList = kcReportedMapper.getStorehouseList();
        if (!storehouseList.isEmpty()){
            return Message.success(storehouseList);
        }
        return Message.error();
    }
    /**
     * 获取报损类型
     */
    @Override
    public Message getReportedType() {
        List<KcReportedtype> reportedType = kcReportedMapper.getReportedType();
        if (!reportedType.isEmpty()){
            return Message.success(reportedType);
        }
        return Message.error();
    }
    /**
     * 添加库存报损
     * @param kcReported
     */
    @Override
    public Message addKcReported(KcReported kcReported) {
        int isAdd = kcReportedMapper.addKcReported(kcReported);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 添加库存报损明细
     * @param kcReporteddetail
     */
    @Override
    public Message addKcReporteddetail(KcReporteddetail kcReporteddetail) {
        int isAdd = kcReportedMapper.addKcReporteddetail(kcReporteddetail);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }
    /**
     * 修改库存报损
     * @param kcReported
     */
    @Override
    public Message updateReportedByCode(KcReported kcReported) {
        int isUpdate = kcReportedMapper.updateReportedByCode(kcReported);
        if (isUpdate>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 根据code获取库存报损
     * @param code
     * @return
     */
    @Override
    public Message getKcReportedByCode(String code) {
        KcReported kcReported = kcReportedMapper.getKcReportedByCode(code);
        List<KcMedicine> kcMedicineList = kcMedicineService.getKcMedicineByReportedCodeTo(code);
        kcReported.setMedicineList(kcMedicineList);
        if (kcReported!=null){
            return Message.success(kcReported);
        }
        return Message.error();
    }


    /**
     * 删除库存报损明细
     * @param reportedCode
     * @return
     */
    @Override
    public Message delKcReporteddetailByCode(String reportedCode) {
        int isDel = kcReportedMapper.delKcReporteddetailByCode(reportedCode);
        if (isDel>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 删除库存报损
     * @param code
     * @return
     */
    @Override
    public Message delKcReportedByCode(String code) {
        int isDel = kcReportedMapper.delKcReportedByCode(code);
        if (isDel>0){
            return Message.success();
        }
        return Message.error();
    }
    /**
     * 添加库存报损和明细
     * @param map
     */
    @Override
    public Message addKcReportedAndDetail(Map map) {
        Map a = (Map) map.get("theData");
        KcReported kcReported = new KcReported();
        kcReported.setCode((String) a.get("code"));
        kcReported.setStorehouseId((Integer) a.get("storehouseId"));
        kcReported.setReportedTypeId((Integer) a.get("reportedTypeId"));
        kcReported.setDocumenterBy((Integer) a.get("documenterBy"));
        List list = (List) a.get("list");
        for (int i = 0; i < list.size(); i++){
            KcReporteddetail kcReporteddetail = new KcReporteddetail();
            kcReporteddetail.setReportedCode((String) a.get("code"));
            kcReporteddetail.setMedicineId((Integer) ((Map)list.get(i)).get("medicineId"));
            kcReporteddetail.setQuantity((Integer) ((Map)list.get(i)).get("reportedNum"));
            kcReporteddetail.setBatchCode((String) ((Map)list.get(i)).get("batchCode"));
            Message message = addKcReporteddetail(kcReporteddetail);
            if (!message.getCode().equals("200")){
                return message;
            }
        }

        return addKcReported(kcReported);
    }
    /**
     * 修改库存报损和明细
     * @param map
     */
    @Override
    public Message updateReportedAndDetail(Map map) {
        Map theData=(Map)map.get("theData");
        Map kcReportedMap=(Map)theData.get("kcReported");
        KcReported kcReported = new KcReported();
        kcReported.setId((Integer) kcReportedMap.get("id"));
        kcReported.setCode((String) kcReportedMap.get("code"));
        kcReported.setApprovalStatus(Integer.valueOf(kcReportedMap.get("approvalStatus").toString()));
        kcReported.setReportedTypeId(Integer.valueOf(kcReportedMap.get("reportedTypeId").toString()));
        kcReported.setApproverRemark((String) kcReportedMap.get("approverRemark"));
        kcReported.setModificationBy(Integer.parseInt(kcReportedMap.get("modificationBy").toString()));
        if (kcReported.getApprovalStatus()!=0){
            kcReported.setApproverBy(kcReported.getModificationBy());
        }
        List list = (List) theData.get("kcMedicineList");
        if (kcReported.getApprovalStatus()==2){
            this.addKcReportedfromwareByReported(kcReported.getId());
        }
        Message messageDel = delKcReporteddetailByCode(kcReported.getCode());
        if (!messageDel.getCode().equals("200")){
            return messageDel;
        }
        for (int i = 0; i < list.size(); i++){
            if (kcReported.getApprovalStatus()==2){
                KcMedicine kcMedicine = new KcMedicine();
                kcMedicine.setId(Integer.valueOf(((Map)list.get(i)).get("id").toString()));
                kcMedicine.setQuantity(Integer.valueOf(((Map)list.get(i)).get("quantity").toString()));
                kcMedicine.setReportedNum(Integer.valueOf(((Map)list.get(i)).get("reportedNum").toString()));
                this.kcMedicineService.updateQuantityById(kcMedicine);//修改库存数量

                KcOutintodetial kcOutintodetial = new KcOutintodetial();
                kcOutintodetial.setCode(CodeUtil.createCode("CRKMX"));
                kcOutintodetial.setTypeId(4);
                kcOutintodetial.setCreateDate(new Date());
                kcOutintodetial.setCreateBy(kcReported.getModificationBy());
                kcOutintodetial.setOrderCode((String) kcReportedMap.get("code"));
                kcOutintodetial.setMedicineId((Integer) ((Map)list.get(i)).get("medicineId"));
                kcOutintodetial.setProviderId((Integer) ((Map)list.get(i)).get("providerId"));
                kcOutintodetial.setBatchCode((String) ((Map)list.get(i)).get("batchCode"));
//                kcOutintodetial.setFromStockMoney((BigDecimal) ((Map)list.get(i)).get("allPrice"));
                kcOutintodetial.setFromStockQuantity((Integer) ((Map)list.get(i)).get("reportedNum"));
//                kcOutintodetial.setPrice((BigDecimal) ((Map)list.get(i)).get("money"));
                kcOutintodetial.setWareHouseId((Integer) ((Map)list.get(i)).get("storehouseId"));

                BigDecimal allPrice = getBigDecimalFromMap((Map) list.get(i), "allPrice");
                BigDecimal money = getBigDecimalFromMap((Map) list.get(i), "money");

                // 现在设置 kcOutintodetial 对象的属性
                kcOutintodetial.setFromStockMoney(allPrice);
                kcOutintodetial.setPrice(money);

                kcOutintodetialService.addKcOutinTodetail(kcOutintodetial);//添加出入库明细

            }

            KcReporteddetail kcReporteddetail = new KcReporteddetail();
            kcReporteddetail.setReportedCode((String) kcReportedMap.get("code"));
            kcReporteddetail.setMedicineId((Integer) ((Map)list.get(i)).get("medicineId"));
            kcReporteddetail.setQuantity((Integer) ((Map)list.get(i)).get("reportedNum"));
            kcReporteddetail.setBatchCode((String) ((Map)list.get(i)).get("batchCode"));
            Message message = addKcReporteddetail(kcReporteddetail);
            if (!message.getCode().equals("200")){
                return message;
            }
        }
        BigDecimal allCost = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++){
            if (((Map)list.get(i)).get("reportedNum")!=null){
                allCost = allCost.add(new BigDecimal(((Map)list.get(i)).get("allPrice").toString()));
            }

        }
        if (kcReported.getApprovalStatus()==2){

            if (kcReported.getReportedTypeId()==1){
                CwBsys cwBsys = new CwBsys();
//                String code = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
                String code = CodeUtil.createCode("CWBSYS");
                cwBsys.setCode(code);
                cwBsys.setOriginalOrder(kcReported.getCode());
                cwBsys.setCost(allCost);
                cwBsys.setCreateTime(LocalDateTime.now());
                cwBsysFeign.addCwbsys(cwBsys);

                CwAccounts cwAccounts = new CwAccounts();
//                String codes = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
                String codes = CodeUtil.createCode("CWACC");
                cwAccounts.setCode(codes);
                cwAccounts.setCategoryId(7);
                cwAccounts.setCost(allCost);
                cwAccounts.setAccountsCategoryName("供应商退款");
                cwAccounts.setCreateTime(new Date());
                cwAccounts.setOrderCode(kcReported.getCode());
                cwAccounts.setCreateBy(kcReported.getModificationBy());
                cwAccountsFeign.addCwAccounts(cwAccounts);
            }



        }
        Message message = updateReportedByCode(kcReported);
        if (!message.getCode().equals("200")){
            return message;
        }
        return message;
    }

    private BigDecimal getBigDecimalFromMap(Map map, String key) {
        return Optional.ofNullable(map.get(key))
                .filter(o -> o instanceof Number)  // 确保值是数字类型
                .map(Number.class::cast)           // 强制转换为 Number
                .map(Number::doubleValue)         // 转换为 double
                .map(BigDecimal::new)             // 转换为 BigDecimal
                .orElse(BigDecimal.ZERO);          // 如果值不存在或不是数字类型，返回零
    }

    /**
     * 删除库存报损和明细
     * @param code
     * @return
     */
    @Override
    public Message delKcReportedAndDetailByCode(String code) {
        KcReported kcReported = (KcReported) getKcReportedByCode(code).getData();
        if (kcReported.getApprovalStatus()==1){
            return Message.error("该报损单已审批，不能删除");
        }
        Message isDelReported = delKcReportedByCode(code);
        Message isDelDetail = delKcReporteddetailByCode(code);
        if (isDelReported.getCode().equals("200")&&isDelDetail.getCode().equals("200")){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 添加库存报损出库
     * @param reportedId
     * @return
     */
    @Override
    public Message addKcReportedfromwareByReported(int reportedId) {
        KcReportedfromware kcReportedfromware = new KcReportedfromware();
        kcReportedfromware.setReportedId(reportedId);
//        String code = UUID.randomUUID().toString().replace("-", "");
        String code = CodeUtil.createCode("KCBS");
        kcReportedfromware.setCode(code);
        int isAdd=kcReportedMapper.addKcReportedfromwareByReported(kcReportedfromware);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 获取库存报损出库
     * @param kcReportedfromware
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Message getKcReportedfromware(KcReportedfromware kcReportedfromware,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<KcReportedfromware> list = kcReportedMapper.getKcReportedfromware(kcReportedfromware);
        PageInfo<KcReportedfromware> pageInfo = new PageInfo<>(list);
        if (list.size()>0){
            return Message.success(pageInfo);
        }
        return Message.error();

    }

    /**
     * 获取所有库存报损
     * @return
     */
    @Override
    public List<KcReported> getAllKcReported() {
        return kcReportedMapper.getKcReportedList(null);
    }

    /**
     * 库存报损导出
     * @param kcReportedVO
     * @param response
     */
    @Override
    public void kcReportedExcel(KcReportedVO kcReportedVO, HttpServletResponse response) {
        List<KcReportedVO> list = kcReportedMapper.getKcReportedVOList(kcReportedVO);
        List<KcReportedVO> listExcel = new ArrayList<>();
        for (KcReportedVO kcReportedVO1 : list){
            List<KcMedicineBSVO> medicineList = kcMedicineService.getKcMedicineVOByReportedCode(kcReportedVO1.getCode());
            kcReportedVO1.setMedicineList(medicineList);
            listExcel.add(kcReportedVO1);
        }
        try {
            ExeclUtil.write(listExcel, KcReportedVO.class,response,"库存报损");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 库存报损出库导出
     * @param response
     */
    @Override
    public void kcReportedfromwareExcel(HttpServletResponse response) {
        List<KcReportedfromwareVO> list = kcReportedMapper.getKcReportedfromwareVO();
        List<KcReportedfromwareVO> listExcel = new ArrayList<>();
        for (KcReportedfromwareVO kcReportedfromwareVO : list){
            List<KcMedicineBSVO> medicineList = kcMedicineService.getKcMedicineVOByReportedCode(kcReportedfromwareVO.getCode());
            kcReportedfromwareVO.setMedicineList(medicineList);
            listExcel.add(kcReportedfromwareVO);
        }
        try {
            ExeclUtil.write(listExcel, KcReportedfromwareVO.class,response,"报损出库");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addKcReportedAllByPk(Map map) {
//        String code = UUID.randomUUID().toString().replace("-", "");
        String code = CodeUtil.createCode("KCBS");
        Map a = (Map) map.get("theData");
        KcReported kcReported = new KcReported();
        kcReported.setCode(code);
        kcReported.setStorehouseId((Integer) a.get("storehouseId"));
        kcReported.setReportedTypeId(3);
        kcReported.setDocumenterBy((Integer) a.get("documenterBy"));
        kcReported.setApprovalStatus(2);
        kcReported.setCreateTime(new Date());
        kcReported.setApproverRemark("盘亏");

        List list = (List) a.get("list");
        for (int i = 0; i < list.size(); i++){

            Integer reportedNum = (Integer) ((Map)list.get(i)).get("reportedNum");
            Integer storehouseId = (Integer) a.get("storehouseId");
            Integer medicineId = (Integer) ((Map)list.get(i)).get("medicineId");
            KcMedicine kcMedicine = new KcMedicine();
            kcMedicine.setStorehouseId(storehouseId);
            kcMedicine.setMedicineId(medicineId);
            List<KcMedicine> medicineList = kcMedicineService.getMedicineByStorehouseIdMedicineId(kcMedicine);
            for (KcMedicine kcMedicine1 : medicineList){
                if (kcMedicine1.getQuantity()==0){
                    continue;
                }
                if (kcMedicine1.getQuantity()<=reportedNum){
                    int quantity = kcMedicine1.getQuantity();
                    reportedNum = reportedNum-kcMedicine1.getQuantity();
                    //添加明细
                    KcReporteddetail kcReporteddetail = new KcReporteddetail();
                    kcReporteddetail.setReportedCode(code);
                    kcReporteddetail.setQuantity(quantity);
                    kcReporteddetail.setBatchCode(kcMedicine1.getBatchCode());
                    kcReporteddetail.setMedicineId(kcMedicine1.getMedicineId());
                    Message message = addKcReporteddetail(kcReporteddetail);
                    if (!message.getCode().equals("200")){
                        return Message.error("明细添加失败");
                    }
                    //减少库存
                    KcMedicine kcMedicine2 = new KcMedicine();
                    kcMedicine2.setId(kcMedicine1.getId());
                    kcMedicine2.setQuantity(quantity);
                    kcMedicine2.setReportedNum(quantity);
                    this.kcMedicineService.updateQuantityById(kcMedicine2);
                }
                if (kcMedicine1.getQuantity()>reportedNum){
                    int quantity = reportedNum;
                    KcReporteddetail kcReporteddetail = new KcReporteddetail();
                    kcReporteddetail.setReportedCode(code);
                    kcReporteddetail.setQuantity(quantity);
                    kcReporteddetail.setBatchCode(kcMedicine1.getBatchCode());
                    kcReporteddetail.setMedicineId(kcMedicine1.getMedicineId());
                    Message message = addKcReporteddetail(kcReporteddetail);
                    if (!message.getCode().equals("200")){
                        return Message.error("明细添加失败");
                    }
                    KcMedicine kcMedicine2 = new KcMedicine();
                    kcMedicine2.setId(kcMedicine1.getId());
                    kcMedicine2.setQuantity(kcMedicine1.getQuantity());
                    kcMedicine2.setReportedNum(quantity);
                    this.kcMedicineService.updateQuantityById(kcMedicine2);
                }
            }
            if (reportedNum!=0){
                return Message.error("库存不足");
            }
        }
        int isAdd = addKcReportedBits(kcReported);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error("添加失败");

    }

    @Override
    public int addKcReportedBits(KcReported kcReported) {
        return kcReportedMapper.insert(kcReported);
    }

    @Override
    public Message insertKcReported(KcReported kcReported) {
        int count=kcReportedMapper.insert(kcReported);
        if(count>0) {
            return Message.success();
        }else{
            return Message.error();
        }
    }


    @Override
    public boolean saveBatch(Collection<KcReported> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<KcReported> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<KcReported> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(KcReported entity) {
        return false;
    }

    @Override
    public KcReported getOne(Wrapper<KcReported> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<KcReported> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<KcReported> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public KcReportedMapper getBaseMapper() {
        return null;
    }

    @Override
    public Class<KcReported> getEntityClass() {
        return null;
    }
}
