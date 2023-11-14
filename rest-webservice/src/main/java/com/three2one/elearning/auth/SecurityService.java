package com.three2one.elearning.auth;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
