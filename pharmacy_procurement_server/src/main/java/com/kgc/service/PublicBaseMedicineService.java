package com.kgc.service;

import com.kgc.remote.BaseMedineRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:36
 */
@FeignClient(value = "baseProvider",contextId = "second")
public interface PublicBaseMedicineService extends BaseMedineRemote {
}
