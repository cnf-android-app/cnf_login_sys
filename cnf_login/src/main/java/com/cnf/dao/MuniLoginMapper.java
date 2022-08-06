package com.cnf.dao;

import com.cnf.domain.LoginMuniAuthPeriod;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MuniLoginMapper {

  LoginMuniAuthPeriod selectLoginMuniAuthPeriod(@Param("municipalityAuthPeriodId") int municipalityAuthPeriodId,@Param("userId") int userId, @Param("municipalityCode") int municipalityCode);

  List<LoginMuniAuthPeriod> selectLoginMuniAuthPeriodList(@Param("userId") int userId);
}

