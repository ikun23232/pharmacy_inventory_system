package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.vo.CwCgyfVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (CwCgyf)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
public interface CwCgyfDao extends BaseMapper<CwCgyf> {

    List<CwCgyf> getCwCgyfList(CwCgyf cwCgyf);

    CwCgyf getCwCgyfByCode(@Param("code") String code);

    List<CwCgyfVO> getCwCgyfVOList();

    CwCgyf getCwCgyfById(@Param("id") int id);

    List<BaseProvider> getProviderList();

    CgddOrder getCgddByCode(CgddOrder cgddOrder);

}

