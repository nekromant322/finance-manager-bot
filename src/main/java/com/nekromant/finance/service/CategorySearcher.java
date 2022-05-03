package com.nekromant.finance.service;

import com.nekromant.finance.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorySearcher {

  public Optional<Category> searchCategory(List<Category> categories, String comment) {
    for (Category category : categories) {
      // TODO add inaccurate search
      if (category.getKeywords().contains(comment)) {
        return Optional.of(category);
      }
    }
    return Optional.empty();
  }
}
