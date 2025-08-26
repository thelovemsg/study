package com.example.study.tdd.list07.email;

import lombok.Getter;

@Getter
public class SpyEmailNotifier implements EmailNotifier{
    private boolean called;
    private String email;

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
