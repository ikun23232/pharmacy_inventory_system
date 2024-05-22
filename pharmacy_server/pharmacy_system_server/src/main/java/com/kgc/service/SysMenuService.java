package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface SysMenuService extends IService<SysMenu> {
   Message getMenuListByToken(Integer userId);
   Message getAllMenuList();
   Message getAuth();

}
