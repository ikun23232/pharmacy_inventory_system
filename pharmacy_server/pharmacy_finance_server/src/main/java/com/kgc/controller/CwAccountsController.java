package com.kgc.controller;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;
import com.kgc.service.CwAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/finance")
public class CwAccountsController {

    @Autowired
    private CwAccountsService cwAccountsService;

    @RequestMapping("/getCwAccountsList")
    public Message getCwAccountsList(@RequestBody CwAccounts cwAccounts, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        return cwAccountsService.getCwAccountsList(cwAccounts,pageNum,pageSize);
    }

    @RequestMapping("/getCwNumByYear")
    public Message getCwNumByYear(@RequestParam("year") String year) {
        return cwAccountsService.getCwNumByYear(year);
    }

    @RequestMapping("/getCwNumByMonth")
    public Message getCwNumByMonth(@RequestParam("year") String year, @RequestParam("month") String month) {
        return cwAccountsService.getCwNumByMonth(year,month);
    }

    @RequestMapping("/cwAccountsExcel")
    public void cwAccountsExcel(HttpServletResponse response) {
        cwAccountsService.cwAccountsExcel(response);
    }
}
