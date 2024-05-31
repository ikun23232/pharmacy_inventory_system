package com.kgc.feign;

import com.kgc.remote.SysUserRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystem",contextId = "user-server23")
public interface UserAuth extends SysUserRemote {
}
