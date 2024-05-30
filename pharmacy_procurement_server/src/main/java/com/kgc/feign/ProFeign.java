package com.kgc.feign;

import com.kgc.remote.SysLogRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystemServer",contextId = "user-server3")
public interface ProFeign extends SysLogRemote {
}
