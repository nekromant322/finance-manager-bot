package com.nekromant.finance.mapper;

import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.models.dto.CategoryInfoDto;
import com.nekromant.finance.models.dto.TransactionDto;
import com.nekromant.finance.models.dto.UserStatDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionMapper {

  public TransactionDto toDto(Transaction transaction) {
    return TransactionDto.builder()
        .categoryName(transaction.getCategoryName())
        .clientId(transaction.getClientId())
        .comment(transaction.getComment())
        .creationDate(transaction.getCreationDate())
        .sum(transaction.getSum())
        .build();
  }

  public UserStatDto toUserStatDto(List<Transaction> records) {
    Map<String, Double> recordMap = new HashMap<>();
    records.forEach(
        n -> {
            recordMap.merge(n.getCategoryName(), n.getSum(), Double::sum);
        });
    List<CategoryInfoDto> categoryInfoList = new ArrayList<>();
    recordMap.forEach((key, value) -> categoryInfoList.add(new CategoryInfoDto(key, value)));
    UserStatDto statDto = new UserStatDto();
    statDto.setCategoryUsageList(categoryInfoList);
    Double sum =
        categoryInfoList.stream().map(CategoryInfoDto::getMoneySpent).reduce(0.0, Double::sum);
    statDto.setTotalMoneySpent(sum);
    return statDto;
  }
}
