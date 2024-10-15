package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Item;
import com.proj2g17.proj2g17.api.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/searchItems")
    public List<Item> getItemsByName(@RequestParam String name) {
        Optional<List<Item>> itemOptional = itemRepository.findAllByNameContaining(name);
        return itemOptional.orElseGet(Collections::emptyList);
    }



}
