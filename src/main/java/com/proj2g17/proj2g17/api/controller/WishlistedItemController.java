package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Item;
import com.proj2g17.proj2g17.api.model.Wishlist;
import com.proj2g17.proj2g17.api.model.WishlistedItem;
import com.proj2g17.proj2g17.api.repositories.ItemRepository;
import com.proj2g17.proj2g17.api.repositories.WishlistRepository;
import com.proj2g17.proj2g17.api.repositories.WishlistedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WishlistedItemController {

    private final WishlistedItemRepository wishlistedItemRepository;
    private final WishlistRepository wishlistRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public WishlistedItemController(WishlistedItemRepository wishlistedItemRepository, WishlistRepository wishlistRepository, ItemRepository itemRepository) {
        this.wishlistedItemRepository = wishlistedItemRepository;
        this.wishlistRepository = wishlistRepository;
        this.itemRepository =itemRepository;
    }

//    @GetMapping("/viewUserWishlist")
//    public List<WishlistedItem> getWishlistByWishlist_id(@RequestParam int wishlist_id){
//        return wishlistedItemRepository.findAllByWishlist_id(wishlist_id);
//
//    }

    @GetMapping("/viewWishlist")
    public List<WishlistedItem> getWishlistByWishlist_id(@RequestParam int wishlist_id){
        return wishlistedItemRepository.findAllByWishlistId(wishlist_id);
    }


    @GetMapping("/userItems")
    public List<WishlistedItem> getAllItemsByUser(@RequestParam Integer user_id) {
        return wishlistedItemRepository.findAllByUser_id(user_id);
    }

    @PostMapping ("/addWishlistedItem")
    public WishlistedItem addWishListedItem(@RequestParam int wishlistId, @RequestParam String url, String name, Double price, String seller, String image_url){
        Optional<Wishlist> wishlist = wishlistRepository.findById(wishlistId);
        if(wishlist.isPresent()){
            Item item = new Item();
            item.setUrl(url);
            item.setName(name);
            item.setPrice(price);
            item.setSeller(seller);
            item.setImage_url(image_url);
            itemRepository.save(item);
            WishlistedItem wishlistedItem = new WishlistedItem();
            wishlistedItem.setWishlist(wishlist.get());
            wishlistedItem.setItem(item);
            return wishlistedItemRepository.save(wishlistedItem);
        }else {
            throw new RuntimeException("WISHLIST NOT FOUND");
        }
    }

    @DeleteMapping("/deleteWishlistedItem")
    public ResponseEntity<Void> deleteWishlistedItem(@RequestParam int wishlisted_item_id) {
        Optional<WishlistedItem> optionalWishlistedItem = wishlistedItemRepository.findById(wishlisted_item_id);
        if(optionalWishlistedItem.isPresent()){
            wishlistedItemRepository.delete(optionalWishlistedItem.get());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
