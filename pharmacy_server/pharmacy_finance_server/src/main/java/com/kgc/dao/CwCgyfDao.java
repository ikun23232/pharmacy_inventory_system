package com.kgc.dao;

import com.kgc.entity.BaseProvider;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (CwCgyf)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
public interface CwCgyfDao {

    List<CwCgyf> getCwCgyfList(CwCgyf cwCgyf);

    List<BaseProvider> getProviderList();

    CgddOrder getCgddByCode(CgddOrder cgddOrder);

}

