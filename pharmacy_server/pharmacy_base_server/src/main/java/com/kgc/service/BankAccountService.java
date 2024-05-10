package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BankAccount;
import com.kgc.entity.Message;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BankAccountService extends IService<BankAccount> {

    public Message getBankAccount(String belongBank,String name,String bandCount,int currentPageNo,int pageSize);

    public Message getBankAccountBybandCount(String bandCount);

    public Message delBankAccountBybandCount(String bandCount);

    public Message addBankAccount(BankAccount bankAccount);

    public Message updateBankAccount(BankAccount bankAccount);

    public Message getAllBankCountList();

}
