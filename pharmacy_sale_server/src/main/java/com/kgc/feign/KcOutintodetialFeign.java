package com.kgc.feign;

import com.kgc.remote.KcOutintodetialRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProviderLxh",contextId = "saleKcOutintodetialFeign")
public interface KcOutintodetialFeign extends KcOutintodetialRemote {
}
