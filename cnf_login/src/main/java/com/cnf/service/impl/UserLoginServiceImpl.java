package com.cnf.service.impl;

import static com.cnf.util.RedisConstants.LOGIN_USER_KEY_PREFIX;
import static com.cnf.util.RedisConstants.LOGIN_USER_TTL;

import com.cnf.dao.UserLoginMapper;
import com.cnf.domain.UserLogin;
import com.cnf.dto.UserLoginForm;
import com.cnf.exception.InvalidUserException;
import com.cnf.service.IUserLoginService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements IUserLoginService {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Resource
  private UserLoginMapper userLoginMapper;

  @Override
  public String userLogin(UserLoginForm userLoginForm) throws InvalidUserException {
    UserLogin login = userLoginMapper.userLogin(userLoginForm.getUsername(),
        userLoginForm.getPassword());
    if (login == null) {
      throw new InvalidUserException("Invalid User");
    }
    String token = UUID.randomUUID().toString();
    String tokenKey = LOGIN_USER_KEY_PREFIX + token;
    stringRedisTemplate.opsForValue()
        .set(tokenKey, String.valueOf(login.getUserId()), LOGIN_USER_TTL, TimeUnit.SECONDS);
    return token;
  }

  @Override
  public Integer isValidUser(String userLoginToken) throws InvalidUserException {
    String userId = stringRedisTemplate.opsForValue().get(LOGIN_USER_KEY_PREFIX + userLoginToken);
    if (userId == null) {
      throw new InvalidUserException("Invalid User");
    }
    return Integer.valueOf(userId);
  }
}
