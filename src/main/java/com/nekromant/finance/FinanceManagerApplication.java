package com.nekromant.finance;

import com.nekromant.finance.config.properties.DefaultCategoriesProperties;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.models.Transaction;
import com.nekromant.finance.models.Type;
import com.nekromant.finance.repository.FinanceClientRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@PropertySource({
        "classpath:application.yml"
})
@EnableConfigurationProperties({
        DefaultCategoriesProperties.class
})
@EnableScheduling
public class FinanceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceManagerApplication.class, args);
    }


}

