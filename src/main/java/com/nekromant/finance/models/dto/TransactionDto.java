package com.nekromant.finance.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
  private Double sum;
  String categoryName;
  Long clientId;
  ZonedDateTime creationDate;
  private String comment;
}
