package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Component
public interface BaseProviderMapper extends BaseMapper<BaseProvider> {

    /**
     * 分页查询供应商
     * @param name
     * @param type
     * @return
     */
    public List<BaseProvider> getBaseProviderList(@Param("name") String name,@Param("type") int type);

    /**
     * 逻辑删除供应商
     * @param id
     * @return
     */
    public int del(@Param("id") int id);

    /**
     * 通过id查供应商
     * @param id
     * @return
     */
    public BaseProvider getBaseProviderById(@Param("id") int id);

    /**
     * 重名校验
     */
    public BaseProvider checkaddBaseProvider(@Param("name") String name);

}
