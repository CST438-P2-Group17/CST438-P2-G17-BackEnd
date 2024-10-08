package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Wishlist;
import com.proj2g17.proj2g17.api.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishlistController {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistController(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @GetMapping("/wishlists")
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }
}
