package com.kgc.feign;

import com.kgc.remote.KcReportedRemote;
import com.kgc.remote.KcReporteddetailRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouseProvider",contextId = "saleKcReporteddetailFeign")
public interface KcReporteddetailFeign extends KcReporteddetailRemote {
}
