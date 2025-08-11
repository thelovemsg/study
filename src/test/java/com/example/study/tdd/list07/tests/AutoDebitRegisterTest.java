package com.example.study.tdd.list07.tests;

import com.example.study.tdd.list07.domain.AutoDebitReq;
import com.example.study.tdd.list07.register.AutoDebitRegister;
import com.example.study.tdd.list07.register.RegisterResult;
import com.example.study.tdd.list07.repository.AutoDebitInfoRepository;
import com.example.study.tdd.list07.repository.JpaAutoDebitInfoRepository;
import com.example.study.tdd.list07.validator.CardNumberValidator;
import com.example.study.tdd.list07.validator.CardValidity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234213412341324");
        RegisterResult result = this.register.register(req);
        Assertions.assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        Assertions.assertEquals(CardValidity.THEFT, result.getValidity());
    }
}
