package com.kgc.feign;

import com.kgc.remote.RefundInWarehouseRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProvider",contextId = "saleRefundInWarehouseFeign")
public interface RefundInWarehouseFeign extends RefundInWarehouseRemote {
}
