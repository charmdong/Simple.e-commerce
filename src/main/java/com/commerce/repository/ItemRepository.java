package com.commerce.repository;

import com.commerce.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    void deleteById(Long id);
}
