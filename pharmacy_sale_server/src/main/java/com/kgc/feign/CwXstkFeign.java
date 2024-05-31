package com.kgc.feign;

import com.kgc.remote.CwXstkRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "financeProvider",contextId = "saleCwXstkFeign")
public interface CwXstkFeign extends CwXstkRemote {
}
