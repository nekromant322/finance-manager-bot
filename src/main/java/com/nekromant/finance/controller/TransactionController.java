package com.nekromant.finance.controller;

import com.nekromant.finance.models.dto.TransactionDto;
import com.nekromant.finance.service.TransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("spending-history")
@Tag(name = "TransactionController", description = "API для получения истории трат")
public class TransactionController {
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Operation(summary = "Получение истории трат пользователя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнен успешно", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = TransactionDto.class)))})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<TransactionDto>> getHistory(@PathVariable Long clientId) {
        return ResponseEntity.ok(transactionHistoryService.getHistory(clientId));
    }
}
