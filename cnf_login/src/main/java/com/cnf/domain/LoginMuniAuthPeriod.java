package com.cnf.domain;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class LoginMuniAuthPeriod {

  private Integer muniAuthPeriodId;
  private Integer muniCode;
  private Integer userId;
  private OffsetDateTime accessGrantedDateStart;
  private OffsetDateTime accessGrantedDateStop;
  private OffsetDateTime recordDeactivatedTS;
  private String authorizedRole;
  private OffsetDateTime createdTS;
  private Integer createdByUserId;
  private String notes;
  private Integer supportAssignedBy;
  private Integer assignmentRank;
  private OffsetDateTime oathTS;
  private Integer oathCourtEntityId;

}
