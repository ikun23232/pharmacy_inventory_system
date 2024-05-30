package com.kgc.remote;

import com.kgc.entity.KcMedicine;
import com.kgc.vo.KcMedicineBSVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface KcMedicineRemote {

    @RequestMapping("/getKcMedicineByReportedCodeTo")
    @ResponseBody
    List<KcMedicineBSVO> getKcMedicineByReportedCodeTo(@RequestParam("reportedCode") String reportedCode);
}
