package com.example.study.tdd.list08;

import com.example.study.tdd.list08.auth.*;

public class LoginService {

    private String authKey = "somekey";
    private CustomerRepository customerRepo;
    private AuthService authService = new AuthService();

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);

        if(resp == -1) return LoginResult.badAuthKey();

        if(resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}
