package com.kgc.feign;

import com.kgc.remote.SysLogRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystem",contextId = "wareLog")
public interface WarehouseLogFeign extends SysLogRemote {
}
