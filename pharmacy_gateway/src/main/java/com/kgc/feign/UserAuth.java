package com.kgc.feign;

import com.kgc.remote.SysUserRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystem",contextId = "user-server")
public interface UserAuth extends SysUserRemote {

}
