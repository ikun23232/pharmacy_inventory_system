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
    public Message getBankAccountBybandCount(String belongBank,String name,String bandCount) {
        BankAccount bankAccount = bankAccountMapper.getBankAccountBybandCount(belongBank,name,bandCount);
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
    public Message getBankAccountById(int id) {
        BankAccount bankAccount = bankAccountMapper.getBankAccountById(id);
        if (bankAccount != null) {
            return Message.success(bankAccount);
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
        BankAccount bankAccount1 = bankAccountMapper.getBankAccountById(bankAccount.getId());
        bankAccount.setUpdateTime(new Date());
        bankAccount.setCreateTime(bankAccount1.getCreateTime());
        UpdateWrapper<BankAccount> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",bankAccount.getId());
        int update = bankAccountMapper.update(bankAccount,wrapper);
        if(update>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message checkaddBankAccount(String belongBank, String name, String bandCount) {
        BankAccount bankAccount = bankAccountMapper.checkaddBankAccount(belongBank, name, bandCount);
        if(bankAccount!=null){
            return Message.error();
        }else {
            return Message.success();
        }
    }

    @Override
    public Message checkupdateBankAccount(String belongBank, String name, String bandCount, int id) {
            BankAccount bankAccount1 = bankAccountMapper.getBankAccountById(id);
        BankAccount bankAccount = bankAccountMapper.checkaddBankAccount(belongBank, name, bandCount);
        if(bankAccount!=null){
            if(bankAccount1.getBandCount().equals(bankAccount.getBandCount())){
                return Message.success();
            }
            return Message.error();
        }else {
            return Message.success();
        }
    }


}
