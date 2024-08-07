package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.SubCategoriesRepository;
import com.example.Ecommerce.repository.UsersRepository;
import com.example.Ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Wishlist> getWishlist() {
        return wishlistRepository.findAll();
    }
    public Optional<Wishlist> getWishlistBYId(Long wishlistId) {

        return wishlistRepository.findById(wishlistId);
    }

    public void addNewWishlist(Long productsId,Long userId, Wishlist wishlist) {
        Products products = productsRepository.findById(productsId).get();
        Users users = usersRepository.findById(userId).get();
        System.out.println("Wishlist service");

            wishlist.setCreatedAt(LocalDateTime.now());
            wishlist.setDeletedAt(LocalDateTime.now());
            wishlist.setProducts(products);
            wishlist.setUsers(users);
            wishlistRepository.save(wishlist);

    }

    public void deleteWishlist(Long wishlistId) {
        boolean exists = wishlistRepository.existsById(wishlistId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + wishlistId + " does not exist");
        }
        wishlistRepository.deleteById(wishlistId);
    }

    public void updateWishlist(Long wishlistId, Wishlist updatedWishlist) {
        Wishlist existingWishlist= wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + wishlistId + " does not exist"));


        existingWishlist.setCreatedAt(LocalDateTime.now());
        existingWishlist.setDeletedAt(LocalDateTime.now());

        wishlistRepository.save(existingWishlist);
    }
}


