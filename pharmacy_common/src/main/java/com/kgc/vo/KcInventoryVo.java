package com.kgc.vo;

import com.kgc.entity.KcInventory;
import com.kgc.entity.KcInventorydetail;
import com.kgc.entity.KcMedicine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author daidai
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class KcInventoryVo extends KcInventory {
    private String startTime;
    private String endTime;
    private String current;
    private List<KcMedicine> kcMedicineList;
    private List<KcInventorydetail> KcInventorydetailList;

    private List<PandianMedicineVo> PandianMedicineList;



}
