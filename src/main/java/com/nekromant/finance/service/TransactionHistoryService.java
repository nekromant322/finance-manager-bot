package com.nekromant.finance.service;

import com.nekromant.finance.mapper.TransactionMapper;
import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.models.dto.TransactionDto;
import com.nekromant.finance.models.dto.UserStatDto;
import com.nekromant.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHistoryService {
  @Autowired private TransactionRepository repository;
  @Autowired private TransactionMapper mapper;

  public List<TransactionDto> getHistory(Long clientId) {
    return repository.findAllByClientId(clientId).stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  public UserStatDto getUserStat(Long clientId) {
    List<Transaction> allUserRecords = repository.findAllByClientId(clientId);
    return mapper.toUserStatDto(allUserRecords);
  }
}
