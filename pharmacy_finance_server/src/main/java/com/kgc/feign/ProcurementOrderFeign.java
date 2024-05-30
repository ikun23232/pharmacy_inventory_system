package com.kgc.feign;

import com.kgc.remote.ProcurementOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(value = "procurementProviderOYH", contextId = "ProcurementOrderFeign")
public interface ProcurementOrderFeign extends ProcurementOrderRemote {
}
