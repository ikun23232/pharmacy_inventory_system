package com.kgc.providerController;

import com.kgc.entity.KcMedicine;
import com.kgc.remote.KcMedicineRemote;
import com.kgc.service.KcMedicineService;
import com.kgc.vo.KcMedicineBSVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KcMedicineFeignController implements KcMedicineRemote {
    @Autowired
    private KcMedicineService kcMedicineService;
    @Override
    public List<KcMedicineBSVO> getKcMedicineByReportedCodeTo(@RequestParam("reportedCode") String reportedCode) {
        return kcMedicineService.getKcMedicineVOByReportedCode(reportedCode);
    }
}
