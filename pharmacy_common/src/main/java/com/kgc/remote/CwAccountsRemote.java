package com.kgc.remote;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CwAccountsRemote {

    @RequestMapping("addCwAccountsFeign")
    @ResponseBody
    public Message addCwAccounts(CwAccounts cwAccounts);
}
