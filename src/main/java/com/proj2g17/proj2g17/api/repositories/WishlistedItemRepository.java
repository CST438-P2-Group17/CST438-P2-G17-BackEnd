package com.proj2g17.proj2g17.api.repositories;

import com.proj2g17.proj2g17.api.model.WishlistedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistedItemRepository extends JpaRepository<WishlistedItem, Integer> {

    // Some documentation for this: https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT wi FROM WishlistedItem wi JOIN wi.wishlist w WHERE w.user.user_id = ?1")
    List<WishlistedItem> findAllByUser_id(Integer user_id);
}
