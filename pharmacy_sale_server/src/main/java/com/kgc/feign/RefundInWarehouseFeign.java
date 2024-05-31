package com.kgc.feign;

import com.kgc.remote.RefundInWarehouseRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pharmacyWarhouse",contextId = "saleRefundInWarehouseFeign")
public interface RefundInWarehouseFeign extends RefundInWarehouseRemote {
}
