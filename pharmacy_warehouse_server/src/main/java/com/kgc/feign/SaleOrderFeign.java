package com.kgc.feign;

import com.kgc.remote.SaleOrderRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pharmacySale",contextId = "warehouseSaleOrderFeign")
public interface SaleOrderFeign extends SaleOrderRemote {
}
