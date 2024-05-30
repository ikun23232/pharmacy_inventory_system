package com.kgc.feign;


import com.kgc.remote.KcMedicineRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProvider",contextId = "financeKcMedicineFeign")
public interface KcMedicineFeign extends KcMedicineRemote {
}
