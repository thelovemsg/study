package com.example.study.tdd.list07.stub;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPassworkWeak(String pw) {
        return weak;
    }
}
