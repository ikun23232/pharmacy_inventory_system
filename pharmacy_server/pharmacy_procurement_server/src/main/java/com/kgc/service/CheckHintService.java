package com.kgc.service;

import com.kgc.entity.CheckHint;
import com.kgc.entity.Message;

public interface CheckHintService {

    CheckHint getcgdd();

    CheckHint getcgrk();

    CheckHint getcgsq();

    CheckHint getkcDispatch();

    CheckHint getkcKeported();

    CheckHint getxsOrder();

    Message getCheckHintList();
}
