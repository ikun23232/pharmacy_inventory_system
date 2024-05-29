package com.kgc.feign;

import com.kgc.remote.PublicOMedicineRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 14:59
 */
@FeignClient(name = "pharmacySystemServer",contextId = "first")
public interface PublicOMedicineFegin extends PublicOMedicineRemote {
}
