package com.nekromant.finance;

import com.nekromant.finance.repository.FinanceClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@PropertySource({
        "classpath:application.yml"
})
@EnableScheduling
public class FinanceManagerApplication {
    @Autowired
    FinanceClientRepository financeClientRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinanceManagerApplication.class, args);
    }
}

