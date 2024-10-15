package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Item;
import com.proj2g17.proj2g17.api.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/editItem")
    public ResponseEntity<Item> editItem(
        @RequestParam Integer item_id,
        @RequestParam Optional<String> url,
        @RequestParam Optional<String> name,
        @RequestParam Optional<Double> price,
        @RequestParam Optional<String> seller,
        @RequestParam Optional<String> image_url
    ) {
        Optional<Item> itemOptional = itemRepository.findById(item_id);

        if (itemOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Item item = itemOptional.get();
        item.setUrl(url.orElseGet(item::getUrl));
        item.setName(name.orElseGet(item::getName));
        item.setPrice(price.orElseGet(item::getPrice));
        item.setSeller(seller.orElseGet(item::getSeller));
        item.setImage_url(image_url.orElseGet(item::getImage_url));
        itemRepository.save(item);

        return ResponseEntity.ok(item);
    }

}
