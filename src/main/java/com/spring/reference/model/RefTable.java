package com.spring.reference.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "Ref_Table")
public class RefTable implements Serializable {

  @Id private int refId;
  private String refName;
  private String createDateTimeGmt;
  private String updateDateTimeGmt;
  private String deleteDateTimeGmt;
}
