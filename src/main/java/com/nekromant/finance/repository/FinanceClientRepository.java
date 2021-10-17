package com.nekromant.finance.repository;

import com.nekromant.finance.models.FinanceClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceClientRepository extends CrudRepository<FinanceClient, Long> {

}
