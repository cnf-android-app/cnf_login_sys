package com.cnf.domain;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Municipality {

  private Integer municode;
  private String muniname;
  private String address_street;
  private String address_city;
  private String address_state;
  private String address_zip;
  private String phone;
  private String fax;
  private String email;
  private Integer population;
  private Boolean activeinprogram;
  private Integer defaultcodeset;
  private Integer occpermitissuingsource_sourceid;
  private Integer novprintstyle_styleid;
  private Integer profile_profileid;
  private Boolean enablecodeenforcement;
  private Boolean enableoccupancy;
  private Boolean enablepublicceactionreqsub;
  private Boolean enablepublicceactionreqinfo;
  private Boolean enablepublicoccpermitapp;
  private Boolean enablepublicoccinspectodo;
  private Integer munimanager_userid;
  private Integer office_propertyid;
  private String notes;
  private OffsetDateTime lastupdatedts;
  private Integer lastupdated_userid;
  private Integer primarystaffcontact_userid;
  private Integer defaultoccperiod;
  private Integer officeparcel_parcelid;

}
