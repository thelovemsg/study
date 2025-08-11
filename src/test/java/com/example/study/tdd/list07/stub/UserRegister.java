package com.example.study.tdd.list07.stub;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegister {
    
    private final WeakPasswordChecker passwordChecker;

    public void register(String id, String pw, String email) {
        if(passwordChecker.checkPassworkWeak(pw)) {
            throw new WeakPasswordException();
        }
        //구현 전
    }


}
