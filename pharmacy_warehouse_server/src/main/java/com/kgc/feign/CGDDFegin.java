package com.kgc.feign;

import com.kgc.remote.ProcurementOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "procurementProviderOYH", contextId = "CGDDFeign")
public interface CGDDFegin extends ProcurementOrderRemote {
}