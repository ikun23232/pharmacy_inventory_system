package com.kgc.feign;

import com.kgc.remote.CwXsysRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "financeProviderLxh",contextId = "saleCwXsysFeign")
public interface CwXsysFeign extends CwXsysRemote {

}
