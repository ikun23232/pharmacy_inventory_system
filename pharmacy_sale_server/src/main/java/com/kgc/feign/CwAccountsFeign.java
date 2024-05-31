package com.kgc.feign;

import com.kgc.remote.CwAccountsRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pharmacyFinance",contextId = "saleCwAccountsFeign")
public interface CwAccountsFeign extends CwAccountsRemote {

}
