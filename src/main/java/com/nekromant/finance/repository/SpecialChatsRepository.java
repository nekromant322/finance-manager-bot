package com.nekromant.finance.repository;

import com.nekromant.finance.model.SpecialChats;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface SpecialChatsRepository extends CrudRepository<SpecialChats, Long> {
    List<SpecialChats> findAll();
}
