package com.cnf.service.impl;


import static com.cnf.util.RedisConstants.LOGIN_USER_KEY_PREFIX;

import com.cnf.dao.MuniLoginMapper;
import com.cnf.domain.LoginMuniAuthPeriod;

import com.cnf.exception.InvalidAuthPeriodException;
import com.cnf.exception.InvalidUserException;
import com.cnf.service.IMuniLoginService;
import com.cnf.service.IUserLoginService;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MuniLoginServiceImpl implements IMuniLoginService {

  @Resource
  private MuniLoginMapper muniLoginMapper;

  @Resource
  private IUserLoginService userLoginService;

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public boolean isValidLoginMuniAuthPeriod(int municipalityAuthPeriodId, int userId,
      int municipalityCode) {
    LoginMuniAuthPeriod loginMuniAuthPeriod = muniLoginMapper.selectLoginMuniAuthPeriod(
        municipalityAuthPeriodId, userId, municipalityCode);
    return isValidLoginMuniAuthPeriod(loginMuniAuthPeriod);
  }

  @Override
  public List<LoginMuniAuthPeriod> getLoginMuniAuthPeriodList(String loginUserToken)
      throws InvalidUserException {
    Integer userId = userLoginService.isValidUser(loginUserToken);
    return muniLoginMapper.selectLoginMuniAuthPeriodList(userId);
  }

  private boolean isValidLoginMuniAuthPeriod(LoginMuniAuthPeriod loginMuniAuthPeriod) {
    if (loginMuniAuthPeriod == null
        || loginMuniAuthPeriod.getAccessGrantedDateStop() == null
        || loginMuniAuthPeriod.getAccessGrantedDateStart() == null) {
      return false;
    }
    OffsetDateTime syncNow = OffsetDateTime.now();
    if (loginMuniAuthPeriod.getRecordDeactivatedTS() != null
        || loginMuniAuthPeriod.getAccessGrantedDateStart().isAfter(syncNow)
        || loginMuniAuthPeriod.getAccessGrantedDateStop().isBefore(syncNow)) {
      return false;
    }
    return true;
  }
}