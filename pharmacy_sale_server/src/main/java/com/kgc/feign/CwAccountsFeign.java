package com.kgc.feign;

import com.kgc.remote.CwAccountsRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "financeProviderLxh",contextId = "saleCwAccountsFeign")
public interface CwAccountsFeign extends CwAccountsRemote {

}
