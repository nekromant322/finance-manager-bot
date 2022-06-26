package com.nekromant.finance.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatDto {
  List<CategoryInfoDto> categoryUsageList;
  Double totalMoneySpent;
}
