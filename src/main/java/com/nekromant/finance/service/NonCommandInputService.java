package com.nekromant.finance.service;

import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.models.NonCommandInput;
import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.repository.FinanceClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NonCommandInputService {

    private final InputParser inputParser;
    private final CategorySearcher categorySearcher;
    private final FinanceClientRepository financeClientRepository;
    private final SimpleMessageSender simpleMessageSender;

    public void processNonCommandInput(String rawInput, long chatId) {
        NonCommandInput nonCommandInput = inputParser.parseNonCommandInput(rawInput);

        FinanceClient financeClient = financeClientRepository.findById(chatId)
                .orElseThrow(() -> new CommandExecuteException(Errors.USER_NOT_FOUND));


        financeClient.getCategories().stream()
                .filter(c -> c.getName().equals(categorySearcher.searchCategory(financeClient.getCategories(), nonCommandInput.getText())
                        .orElse(new Category()).getName()))
                .forEach(category -> financeClient.getTransactionsHistory().add(
                        Transaction.builder()
                                .category(category)
                                .commment(nonCommandInput.getText())
                                .sum(nonCommandInput.getMoney())
                                .build()
                ));
        simpleMessageSender.sendMessage("залупа", String.valueOf(chatId));
        //TODO отправить текст, что все заебись, т.к. не выпало исключения
    }

}
