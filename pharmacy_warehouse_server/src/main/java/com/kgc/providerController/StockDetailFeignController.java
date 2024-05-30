package com.kgc.providerController;

import com.kgc.entity.Message;
import com.kgc.remote.StockDetailRemote;
import com.kgc.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StockDetailFeignController implements StockDetailRemote {

    @Autowired
    private StockDetailService stockDetailService;

    @Override
    public Message reduceStockDetailByMedicineId(int medicineId, int quantity, String batchCode) {
        Message message=stockDetailService.reduceStockDetailByMedicineId(medicineId,quantity,batchCode);
        return message;
    }

    @Override
    public Message addStockDetailByMedicineId(int medicineId, int quantity, String batchCode) {
        Message message=stockDetailService.addStockDetailByMedicineId(medicineId,quantity,batchCode);
        return message;
    }
}
