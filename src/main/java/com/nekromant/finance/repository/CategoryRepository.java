package com.nekromant.finance.repository;

import com.nekromant.finance.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select keywords from category_keywords where category_id = :categoryId", nativeQuery = true)
    List<String> findKeywordsByCategoryId(@Param("categoryId") Long categoryId);
}
