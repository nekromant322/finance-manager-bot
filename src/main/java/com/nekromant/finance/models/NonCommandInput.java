package com.nekromant.finance.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NonCommandInput {
    private double money;
    private String text;
}
