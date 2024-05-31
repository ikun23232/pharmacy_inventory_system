package com.kgc.feign;


import com.kgc.remote.KcReportedRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pharmacyWarhouse",contextId = "saleKcReportedFeign")
public interface KcReportedFeign extends KcReportedRemote {
}
