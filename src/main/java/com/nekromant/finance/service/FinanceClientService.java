package com.nekromant.finance.service;

import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.FinanceClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceClientService {
  @Autowired private FinanceClientRepository financeClientRepository;

  public void saveUser(FinanceClient financeClient) {
    financeClientRepository.save(financeClient);
  }
}
