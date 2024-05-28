package com.kgc.service.impl;

import com.kgc.dao.CheckHintMapper;
import com.kgc.entity.CheckHint;
import com.kgc.entity.Message;
import com.kgc.service.CheckHintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckHintServiceImpl implements CheckHintService {

    @Autowired
    private CheckHintMapper checkHintMapper;

    @Override
    public CheckHint getcgdd() {
        CheckHint checkHint = checkHintMapper.getcgdd();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public CheckHint getcgrk() {
        CheckHint checkHint = checkHintMapper.getcgrk();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public CheckHint getcgsq() {
        CheckHint checkHint = checkHintMapper.getcgsq();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public CheckHint getkcDispatch() {
        CheckHint checkHint = checkHintMapper.getkcDispatch();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public CheckHint getkcKeported() {
        CheckHint checkHint = checkHintMapper.getkcKeported();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public CheckHint getxsOrder() {
        CheckHint checkHint = checkHintMapper.getxsOrder();
        if (checkHint == null){
            return new CheckHint();
        }
        return checkHint;
    }

    @Override
    public Message getCheckHintList() {
        List<CheckHint> list = new ArrayList<>();
        CheckHint checkHint = checkHintMapper.getcgdd();
        if (checkHint != null){
            list.add(checkHint);
        }
        CheckHint checkHint1 = checkHintMapper.getcgrk();
        if (checkHint1 != null){
            list.add(checkHint1);
        }
        CheckHint checkHint2 = checkHintMapper.getcgsq();
        if (checkHint2 != null){
            list.add(checkHint2);
        }
        CheckHint checkHint3 = checkHintMapper.getkcDispatch();
        if (checkHint3 != null){
            list.add(checkHint3);
        }
        CheckHint checkHint4 = checkHintMapper.getkcKeported();
        if (checkHint4 != null){
            list.add(checkHint4);
        }
        CheckHint checkHint5 = checkHintMapper.getxsOrder();
        if (checkHint5 != null){
            list.add(checkHint5);
        }
        return Message.success(list);
    }
}
