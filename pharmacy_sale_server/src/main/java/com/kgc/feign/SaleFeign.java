package com.kgc.feign;

import com.kgc.remote.SysLogRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystemServer",contextId = "user-server2")
public interface SaleFeign extends SysLogRemote {
}
