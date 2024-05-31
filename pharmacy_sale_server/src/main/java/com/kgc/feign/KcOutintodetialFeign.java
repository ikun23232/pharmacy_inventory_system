package com.kgc.feign;

import com.kgc.remote.KcOutintodetialRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProvider",contextId = "saleKcOutintodetialFeign")
public interface KcOutintodetialFeign extends KcOutintodetialRemote {
}
