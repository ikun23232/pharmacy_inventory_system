package com.kgc.feign;

import com.kgc.remote.CwCgyfRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/29 14:41
 */
@FeignClient(value = "pharmacyFinance",contextId = "CwCgyfFeign")
public interface CwCgyfFeign extends CwCgyfRemote {

}
