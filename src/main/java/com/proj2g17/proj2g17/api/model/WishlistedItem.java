package com.proj2g17.proj2g17.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="wishlisted_item")
public class WishlistedItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="wishlisted_item_id")
    private Integer wishlisted_item_id;

    @ManyToOne(optional = false)
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

    @ManyToOne(optional = false)
    @JoinColumn(name="item_id")
    private Item item;

    public WishlistedItem() {};

    public Integer getWishlisted_item_id() {
        return wishlisted_item_id;
    }

    public void setWishlisted_item_id(Integer wishlisted_item_id) {
        this.wishlisted_item_id = wishlisted_item_id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
