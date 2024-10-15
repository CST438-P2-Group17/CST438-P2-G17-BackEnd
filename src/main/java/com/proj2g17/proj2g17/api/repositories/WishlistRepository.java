package com.proj2g17.proj2g17.api.repositories;

import com.proj2g17.proj2g17.api.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query("SELECT w FROM Wishlist w WHERE w.user.user_id = ?1")
    List<Wishlist> findAllByUserId(Integer userId);
}