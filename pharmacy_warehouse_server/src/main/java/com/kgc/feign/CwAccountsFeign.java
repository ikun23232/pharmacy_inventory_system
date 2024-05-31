package com.kgc.feign;

import com.kgc.remote.CwAccountsRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "financeProvider", contextId = "CwAccountsFeign")
public interface CwAccountsFeign extends CwAccountsRemote {
}
