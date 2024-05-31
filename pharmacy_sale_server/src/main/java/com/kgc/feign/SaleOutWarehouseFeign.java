package com.kgc.feign;

import com.kgc.remote.SaleOutWarehouseRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pharmacyWarhouse",contextId = "saleSaleOutWarehouseFeign")
public interface SaleOutWarehouseFeign extends SaleOutWarehouseRemote {
}
