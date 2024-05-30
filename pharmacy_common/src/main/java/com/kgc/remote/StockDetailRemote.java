package com.kgc.remote;

import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface StockDetailRemote {
    @RequestMapping("/reduceStockDetailByMedicineId")
    @ResponseBody
    public Message reduceStockDetailByMedicineId(@RequestParam("medicineId") int medicineId,@RequestParam("quantity")int quantity, @RequestParam("batchCode") String batchCode);

    @RequestMapping("/addStockDetailByMedicineId")
    @ResponseBody
    public Message addStockDetailByMedicineId(@RequestParam("medicineId") int medicineId,@RequestParam("quantity")int quantity, @RequestParam("batchCode") String batchCode);


}
