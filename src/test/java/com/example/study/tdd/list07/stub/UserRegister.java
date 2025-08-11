package com.example.study.tdd.list07.stub;

import com.example.study.tdd.list07.domain.User;
import com.example.study.tdd.list07.exception.DupIdException;
import com.example.study.tdd.list07.exception.WeakPasswordException;
import com.example.study.tdd.list07.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegister {
    
    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;

    public void register(String id, String pw, String email) {
        if(passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if(user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User("id", "pw", "email"));
    }
}
