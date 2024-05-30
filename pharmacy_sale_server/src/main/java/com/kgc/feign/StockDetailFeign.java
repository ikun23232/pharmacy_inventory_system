package com.kgc.feign;

import com.kgc.remote.StockDetailRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProvider",contextId = "saleStockDetailFeign")
public interface StockDetailFeign extends StockDetailRemote {

}
