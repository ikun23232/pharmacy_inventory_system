package com.kgc.service;

import com.kgc.remote.SysUserRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystem",contextId = "user-server25")
public interface UserAuth extends SysUserRemote {
}
