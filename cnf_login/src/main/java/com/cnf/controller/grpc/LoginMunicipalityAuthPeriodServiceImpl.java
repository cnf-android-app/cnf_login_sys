package com.cnf.controller.grpc;

import com.cnf.AuthorizationServiceGrpc;
import com.cnf.IsValidMunicipalityAuthPeriodReply;
import com.cnf.IsValidMunicipalityAuthPeriodRequest;
import com.cnf.IsValidUserReply;
import com.cnf.IsValidUserRequest;
import com.cnf.exception.InvalidUserException;
import com.cnf.service.impl.MuniLoginServiceImpl;
import com.cnf.service.impl.UserLoginServiceImpl;
import io.grpc.stub.StreamObserver;
import java.time.OffsetDateTime;
import javax.annotation.Resource;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@GrpcService
@Service
public class LoginMunicipalityAuthPeriodServiceImpl extends
    AuthorizationServiceGrpc.AuthorizationServiceImplBase {

  Logger logger = LoggerFactory.getLogger(LoginMunicipalityAuthPeriodServiceImpl.class);

  @Resource
  private MuniLoginServiceImpl muniLoginService;

  @Resource
  private UserLoginServiceImpl userLoginService;

  @Override
  public void isValidMunicipalityAuthPeriod(IsValidMunicipalityAuthPeriodRequest request,
      StreamObserver<IsValidMunicipalityAuthPeriodReply> responseObserver) {

    int municipalityAuthPeriodId = request.getMunicipalityAuthPeriodId();
    int userId = request.getUserId();
    int municipalityCode = request.getMunicipalityCode();
    boolean isValidLoginMuniAuthPeriod = muniLoginService.isValidLoginMuniAuthPeriod(
        municipalityAuthPeriodId, userId, municipalityCode);

    IsValidMunicipalityAuthPeriodReply reply = IsValidMunicipalityAuthPeriodReply.newBuilder()
        .setIsValidAuthPeriod(isValidLoginMuniAuthPeriod).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  @Override
  public void isValidUser(IsValidUserRequest request,
      StreamObserver<IsValidUserReply> responseObserver) {
    String userToken = request.getUserToken();
    Integer userId = -1;
    try {
      userId = userLoginService.isValidUser(userToken);
      logger.debug("UserId: " + userId);
    } catch (InvalidUserException e) {
      logger.error(String.format("Date: %s, %s", OffsetDateTime.now(), e));
    }
    IsValidUserReply reply = IsValidUserReply.newBuilder().setUserId(userId).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
