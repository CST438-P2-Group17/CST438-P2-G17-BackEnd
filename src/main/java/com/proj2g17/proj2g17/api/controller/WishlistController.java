package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.User;
import com.proj2g17.proj2g17.api.model.Wishlist;
import com.proj2g17.proj2g17.api.repositories.UserRepository;
import com.proj2g17.proj2g17.api.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WishlistController {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishlistController(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/wishlists")
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @PostMapping("/addWishlist")
    public Wishlist addWishList(@RequestParam int userId, @RequestParam String name ){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setName(name);
            wishlist.setUser(user.get());
            return wishlistRepository.save(wishlist);
        }
        else{
            throw new RuntimeException("USER NOT FOUND");
        }
    }
}
