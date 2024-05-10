package com.kgc.controller;


import com.kgc.entity.BankAccount;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import com.kgc.service.BankAccountService;
import com.kgc.service.BaseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/base/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping("/getBankAccount")
    public Message getBankAccount(@RequestParam("belongBank") String belongBank,@RequestParam("name") String name,@RequestParam("bandCount") String bandCount,@RequestParam("currentPageNo") int currentPageNo,@RequestParam("pageSize") int pageSize) {
        Message message = bankAccountService.getBankAccount(belongBank, name, bandCount, currentPageNo, pageSize);
        return message;
    }

    @RequestMapping("addBankAccount")
    public Message getBankAccountBybandCount(BankAccount bankAccount) {
        Message message = bankAccountService.addBankAccount(bankAccount);
        return message;
    }

    @RequestMapping("updateBankAccount")
    public Message updateBankAccount(BankAccount bankAccount) {
        Message message = bankAccountService.updateBankAccount(bankAccount);
        return message;
    }

    @RequestMapping("delBankAccountBybandCount")
    public Message delBankAccountBybandCount(@RequestParam("bandCount") String bandCount) {
        Message message = bankAccountService.delBankAccountBybandCount(bandCount);
        return message;
    }

    @RequestMapping("getBankAccountBybandCount")
    public Message getBankAccountBybandCount(@RequestParam("belongBank") String belongBank,@RequestParam("name") String name,@RequestParam("bandCount") String bandCount) {
        Message message = bankAccountService.getBankAccountBybandCount(belongBank,name,bandCount);
        return message;
    }

    @RequestMapping("checkaddBankAccount")
    public Message checkaddBankAccount(@RequestParam("belongBank") String belongBank,@RequestParam("name") String name,@RequestParam("bandCount") String bandCount) {
        Message message = bankAccountService.checkaddBankAccount(belongBank,name,bandCount);
        return message;
    }

    @RequestMapping("checkupdateBankAccount")
    public Message checkupdateBankAccount(@RequestParam("belongBank") String belongBank,@RequestParam("name") String name,@RequestParam("bandCount") String bandCount,@RequestParam("id") int id) {
        Message message = bankAccountService.checkupdateBankAccount(belongBank,name,bandCount,id);
        return message;
    }
}

