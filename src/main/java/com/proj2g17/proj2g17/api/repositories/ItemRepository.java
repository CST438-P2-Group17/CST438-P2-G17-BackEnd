package com.proj2g17.proj2g17.api.repositories;

import com.proj2g17.proj2g17.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
