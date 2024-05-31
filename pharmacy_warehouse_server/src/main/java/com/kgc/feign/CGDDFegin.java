package com.kgc.feign;

import com.kgc.remote.ProcurementOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "procumentProvider", contextId = "CGDDFeign")
public interface CGDDFegin extends ProcurementOrderRemote {
}
