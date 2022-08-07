package com.cnf.service;

import com.cnf.dto.UserLoginForm;
import com.cnf.exception.InvalidUserException;

public interface IUserLoginService {
  String userLogin(UserLoginForm userLoginForm) throws InvalidUserException;

  Integer isValidUser(String loginUserToken) throws InvalidUserException;
}
