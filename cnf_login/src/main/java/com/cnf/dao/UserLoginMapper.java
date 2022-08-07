package com.cnf.dao;

import com.cnf.domain.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {
   UserLogin userLogin(String username, String password);
}
