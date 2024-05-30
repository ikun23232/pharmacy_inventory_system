package com.kgc.feign;

import com.kgc.remote.BaseMedineRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:36
 */
@FeignClient(value = "BaseOYH",contextId = "PublicBaseMedicineFegin")
public interface PublicBaseMedicineFegin extends BaseMedineRemote {
}
