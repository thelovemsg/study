package com.example.study.tdd.list07.tests;

import com.example.study.tdd.list07.domain.User;
import com.example.study.tdd.list07.email.SpyEmailNotifier;
import com.example.study.tdd.list07.repository.MemoryUserRepository;
import com.example.study.tdd.list07.stub.StubWeakPasswordChecker;
import com.example.study.tdd.list07.stub.UserRegister;
import com.example.study.tdd.list07.exception.WeakPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {

    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubPasswordChecker.setWeak(true); // 암호가 약하다고 응답하도록 설정

        Assertions.assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        Assertions.assertEquals("id", savedUser.getId());
        Assertions.assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        Assertions.assertTrue(spyEmailNotifier.isCalled());
        Assertions.assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }

}
