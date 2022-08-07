package com.cnf.domain;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class UserLogin {

  private Integer userId;
  private String username;
  private String password;
  private String notes;
  private Integer personLink;
  private OffsetDateTime passwordLastUpdated;
  private OffsetDateTime forcePasswordReset;
  private Integer createdBy;
  private OffsetDateTime createdTS;
  private boolean noLoginVirtualOnly;
  private OffsetDateTime deActivatedTS;
  private Integer deActivatedByUserId;
  private OffsetDateTime lastUpdatedTS;
  private String userRole;
  private Integer homeMuni;

}
