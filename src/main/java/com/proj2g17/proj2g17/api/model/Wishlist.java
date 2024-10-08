package com.proj2g17.proj2g17.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wishlist_id")
    private Integer wishlist_id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "wishlist")
    Set<WishlistedItem> wishlistedItems;

    public Wishlist() {};

    public Integer getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Integer wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
