package com.kgc.service;

import com.kgc.remote.SysUserRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystemServer",contextId = "user-server")
public interface UserAuth extends SysUserRemote {
}
