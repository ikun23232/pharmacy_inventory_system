package com.kgc.feign;

import com.kgc.remote.ProcurementOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "pharmacyProcurement", contextId = "CGDDFeign")
public interface CGDDFegin extends ProcurementOrderRemote {
}
