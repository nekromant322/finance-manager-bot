package com.nekromant.finance.repository;

import com.nekromant.finance.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query(
      value = "select keywords from category_keywords where category_id = :categoryId",
      nativeQuery = true)
  List<String> findKeywordsByCategoryId(@Param("categoryId") Long categoryId);

  @Modifying
  @Transactional
  @Query(
      value = "delete from clients_categories where categories_id = :categoryId",
      nativeQuery = true)
  void deleteCategoryById(@Param("categoryId") Long id);

  Category findByName(String name);
}
