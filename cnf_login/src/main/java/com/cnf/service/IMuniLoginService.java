package com.cnf.service;

import com.cnf.domain.LoginMuniAuthPeriod;
import com.cnf.exception.InvalidAuthPeriodException;
import com.cnf.exception.InvalidUserException;
import java.util.List;

public interface IMuniLoginService {

  boolean isValidLoginMuniAuthPeriod(int municipalityAuthPeriodId, int userId, int municipalityCode);

  List<LoginMuniAuthPeriod> getLoginMuniAuthPeriodList(String userLoginToken) throws InvalidUserException;

}

