package com.kgc.feign;

import com.kgc.remote.BaseMedineRemote;
import com.kgc.remote.SaleOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "baseApplication",contextId = "financeBaseMedineRemotes")
public interface BaseMedineFeign extends BaseMedineRemote {
}
