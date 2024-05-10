package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BankAccountMapper;
import com.kgc.entity.BankAccount;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import com.kgc.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements BankAccountService {

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Override
    public Message getBankAccount(String belongBank, String name, String bandCount, int currentPageNo, int pageSize) {
        PageHelper.startPage(currentPageNo, pageSize);
        List<BankAccount> bankAccountList = bankAccountMapper.getBankAccountList(belongBank, name, bandCount);
        PageInfo<BankAccount> page = new PageInfo<>(bankAccountList);
        page.setList(bankAccountList);
        return Message.success(page);
    }

    @Override
    public Message getBankAccountBybandCount(String bandCount) {
        BankAccount bankAccount = bankAccountMapper.getBankAccountBybandCount(bandCount);
        if (bankAccount != null) {
            return Message.success(bankAccount);
        } else {
            return Message.error();
        }
    }

    @Override
    public Message delBankAccountBybandCount(String bandCount) {
        int count = bankAccountMapper.delBankAccount(bandCount);
        if (count > 0) {
            return Message.success();
        } else {
            return Message.error();
        }
    }

    @Override
    public Message addBankAccount(BankAccount bankAccount) {
        bankAccount.setCreateTime(new Date());
        int insert = bankAccountMapper.insert(bankAccount);
        if(insert>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message updateBankAccount(BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountMapper.getBankAccountBybandCount(bankAccount.getBandCount());
        bankAccount.setUpdateTime(new Date());
        bankAccount.setUpdateTime(bankAccount1.getCreateTime());
        UpdateWrapper<BankAccount> wrapper = new UpdateWrapper<>();
        wrapper.eq("bandCount",bankAccount.getBandCount());
        int update = bankAccountMapper.update(bankAccount,wrapper);
        if(update>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message getAllBankCountList() {
        List<BankAccount> bankAccountList=bankAccountMapper.selectList(null);
        if(bankAccountList!=null){
            return Message.success(bankAccountList);
        }else{
            return Message.error();
        }
    }


}
