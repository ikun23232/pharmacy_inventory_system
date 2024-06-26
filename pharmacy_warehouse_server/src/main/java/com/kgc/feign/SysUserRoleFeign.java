package com.kgc.feign;

import com.kgc.remote.SaleOrderRemote;
import com.kgc.remote.SysUserRoleRemote;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author daidai
 */
@FeignClient(name = "pharmacySystem",contextId = "sysUserRoleFeign")
public interface SysUserRoleFeign extends SysUserRoleRemote {
}