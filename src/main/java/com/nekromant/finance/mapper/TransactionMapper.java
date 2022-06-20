package com.nekromant.finance.mapper;

import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.models.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionDto toDto(Transaction transaction) {
    return TransactionDto.builder()
        .categoryId(transaction.getCategoryId())
        .clientId(transaction.getClientId())
        .comment(transaction.getComment())
        .creationDate(transaction.getCreationDate())
        .sum(transaction.getSum())
        .build();
  }
}
