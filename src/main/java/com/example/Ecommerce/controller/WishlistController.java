package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.ProductsService;
import com.example.Ecommerce.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/wishlist")
public class  WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/getall")
    public List<Wishlist> getWishlist() {
        return wishlistService.getWishlist();
    }
    @GetMapping("/getall/{id}")
    public Optional<Wishlist> getWishlistBYId(@PathVariable(value = "id") Long wishlistId) {
        return wishlistService.getWishlistBYId(wishlistId);
    }
    @PostMapping("/add")
    public void registerNewWishlist(@RequestParam(name ="productsId") @PathVariable Long productsId,@RequestParam(name ="userId")@PathVariable Long usersId, @RequestBody Wishlist wishlist) {
        wishlistService.addNewWishlist(productsId,usersId, wishlist);
    }

    @DeleteMapping(path = "{wishlistId}")
    public void deleteWishlist(@PathVariable("wishlistId") Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

    @PutMapping(path = "{wishlistId}")
    public void updateWishlist(@RequestBody Wishlist wishlist, @PathVariable("productsId") Long wishlistId) {
        wishlistService.updateWishlist(wishlistId, wishlist);
    }
}