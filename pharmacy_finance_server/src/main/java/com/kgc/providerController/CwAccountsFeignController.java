package com.kgc.providerController;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;
import com.kgc.remote.CwAccountsRemote;
import com.kgc.service.CwAccountsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CwAccountsFeignController implements CwAccountsRemote {

    @Resource
    private CwAccountsService cwAccountsService;

    @Override
    public Message addCwAccounts(@RequestBody CwAccounts cwAccounts)
    {
        return cwAccountsService.addCwAccounts(cwAccounts);
    }

}
