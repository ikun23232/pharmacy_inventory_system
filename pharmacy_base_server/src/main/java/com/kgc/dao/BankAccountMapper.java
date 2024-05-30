package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BankAccount;
import com.kgc.entity.BaseProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Mapper
public interface BankAccountMapper extends BaseMapper<BankAccount> {

    /**
     * 分页查询银行卡
     * @param name
     * @return
     */
    public List<BankAccount> getBankAccountList(@Param("belongBank") String belongBank, @Param("name") String name,@Param("bandCount") String bandCount);

    public BankAccount getBankAccountBybandCount(@Param("belongBank") String belongBank, @Param("name") String name,@Param("bandCount") String bandCount);
    public BankAccount getBankAccountById(@Param("id") int id);

    public int delBankAccount(@Param("bandCount") String bandCount);

    public BankAccount checkaddBankAccount(@Param("belongBank") String belongBank, @Param("name") String name,@Param("bandCount") String bandCount);
}
