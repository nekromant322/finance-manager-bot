package com.nekromant.finance.config.properties;

import com.nekromant.finance.models.Category;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@ConstructorBinding
@ConfigurationProperties(prefix = "default-categories")
public class DefaultCategoriesProperties {
    private List<Category> categories;
}
