package com.kgc.feign;

import com.kgc.remote.CwBsysRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "pharmacyFinance", contextId = "cwBsysFeign")
public interface CwBsysFeign extends CwBsysRemote {
}
