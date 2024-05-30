package com.kgc.providerController;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;
import com.kgc.remote.CwAccountsRemote;
import com.kgc.service.CwAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CwAccountsFeignController implements CwAccountsRemote {

    @Autowired
    private CwAccountsService cwAccountsService;

    @Override
    public Message addCwAccounts(@RequestBody CwAccounts cwAccounts)
    {
        return cwAccountsService.addCwAccounts(cwAccounts);
    }

    @Override
    public Message insertCwAccounts(CwAccounts cwAccounts) {
        Message message=cwAccountsService.insertCwAccounts(cwAccounts);
        return message;
    }
}
