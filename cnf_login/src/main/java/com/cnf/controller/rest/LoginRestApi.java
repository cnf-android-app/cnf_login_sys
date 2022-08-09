package com.cnf.controller.rest;

import static com.cnf.util.RedisConstants.HTTP_AUTHORIZATION_HEADER_NAME;

import com.cnf.domain.LoginMuniAuthPeriod;
import com.cnf.dto.TokenDTO;
import com.cnf.dto.UserLoginForm;
import com.cnf.exception.InvalidAuthPeriodException;
import com.cnf.exception.InvalidUserException;
import com.cnf.service.IMuniLoginService;
import com.cnf.service.IUserLoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.OffsetDateTime;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "login")
public class LoginRestApi {

  Logger logger = LoggerFactory.getLogger(LoginRestApi.class);
  @Resource
  private IUserLoginService userLogin;

  @Resource
  private IMuniLoginService muniLoginService;

  @GetMapping("/test8")
  public ResponseEntity<?> testLogin() {
    return new ResponseEntity<>("hello world", HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<?> userLogin(@RequestBody UserLoginForm userLoginForm) {
    try {
      String token = userLogin.userLogin(userLoginForm);
      return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
    } catch (InvalidUserException e) {
      logger.error(String.format("Date: %s, %s", OffsetDateTime.now(), e));
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/authPeriod")
  public ResponseEntity<?> getLoginMuniAuthPeriodList(HttpServletRequest request) {
    String loginUserToken = request.getHeader(HTTP_AUTHORIZATION_HEADER_NAME);
    try {
      List<LoginMuniAuthPeriod> list = muniLoginService.getLoginMuniAuthPeriodList(loginUserToken);
      return new ResponseEntity<>(list, HttpStatus.OK);
    } catch (InvalidUserException e) {
      logger.error(String.format("Date: %s, %s", OffsetDateTime.now(), e));
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/isValidUserToken")
  public ResponseEntity<?> isValidUserToken(HttpServletRequest request) {
    String loginUserToken = request.getHeader(HTTP_AUTHORIZATION_HEADER_NAME);
    try {
      Integer validUser = userLogin.isValidUser(loginUserToken);
      return new ResponseEntity<>(validUser, HttpStatus.OK);
    } catch (InvalidUserException e) {
      logger.error(String.format("Date: %s, %s", OffsetDateTime.now(), e));
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
