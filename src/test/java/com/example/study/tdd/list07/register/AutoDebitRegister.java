package com.example.study.tdd.list07.register;

import com.example.study.tdd.list07.domain.AutoDebitInfo;
import com.example.study.tdd.list07.domain.AutoDebitReq;
import com.example.study.tdd.list07.repository.AutoDebitInfoRepository;
import com.example.study.tdd.list07.validator.CardNumberValidator;
import com.example.study.tdd.list07.validator.CardValidity;

import java.time.LocalDateTime;

public class AutoDebitRegister {
    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserId());
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }
        return RegisterResult.success();
    }
}