package com.nekromant.finance.service;

import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.models.NonCommandInput;
import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NonCommandInputService {

  private final InputParser inputParser;

  private final TransactionRepository transactionRepository;
  private final FinanceClientRepository financeClientRepository;

  private final CategoryRepository categoryRepository;
  private final MessageSender messageSender;

  public void processNonCommandInput(String rawInput, long chatId) {
    NonCommandInput nonCommandInput = inputParser.parseNonCommandInput(rawInput);
    FinanceClient financeClient = null;
    Optional<FinanceClient> optionalFinanceClient = financeClientRepository.findById(chatId);
    List<Category> categories = new ArrayList<>();
    if (optionalFinanceClient.isPresent()) {
      financeClient = optionalFinanceClient.get();
      categories = financeClient.getCategories();
    }

    Long userCategoryId = null;
    for (Category category : categories) {
      List<String> keywords = categoryRepository.findKeywordsByCategoryId(category.getId());
      if (keywords.contains(nonCommandInput.getText())) {
        userCategoryId = category.getId();
        break;
      }
    }
    System.out.println(userCategoryId);

    transactionRepository.save(
        Transaction.builder()
            .categoryName(categoryRepository.findById(userCategoryId).get().getName())
            .sum(nonCommandInput.getMoney())
            .comment(nonCommandInput.getComment())
            .creationDate(ZonedDateTime.now())
            .clientId(financeClient.getChatId())
            .build());

    messageSender.sendMessage("Транзакция добавлена", String.valueOf(chatId));
  }
}
