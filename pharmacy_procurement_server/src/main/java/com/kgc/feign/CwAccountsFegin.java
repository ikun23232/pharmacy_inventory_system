package com.kgc.feign;

import com.kgc.remote.CwAccountsRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/29 14:03
 */
@FeignClient(value = "pharmacyFinance",contextId = "CwAccountsFegin")
public interface CwAccountsFegin extends CwAccountsRemote {

}
