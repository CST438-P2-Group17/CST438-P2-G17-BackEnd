package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.User;
import com.proj2g17.proj2g17.api.model.WishlistedItem;
import com.proj2g17.proj2g17.api.repositories.WishlistedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishlistedItemController {

    private final WishlistedItemRepository wishlistedItemRepository;

    @Autowired
    public WishlistedItemController(WishlistedItemRepository wishlistedItemRepository) {
        this.wishlistedItemRepository = wishlistedItemRepository;
    }

    @GetMapping("/userItems")
    public List<WishlistedItem> getAllItemsByUser(@RequestParam Integer user_id) {
        return wishlistedItemRepository.findAllByUser_id(user_id);
    }
}
