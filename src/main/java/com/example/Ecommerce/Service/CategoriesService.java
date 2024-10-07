package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.CategoriesRepository;
import com.example.Ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public List<Categories> getCategories() {
        return categoriesRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    public Optional<Categories> getCategoriesBYId(Long categoriesId) {

        return categoriesRepository.findById(categoriesId);
    }
    public void addNewCategories(List<Categories> categoriess) {
        LocalDateTime now = LocalDateTime.now();

        for (Categories categories : categoriess) {
            categories.setCreatedAt(now);
            categories.setDeletedAt(now);
            categoriesRepository.save(categories);
        }
    }
    public void deleteCategories(Long categoriesId) {
        boolean exists = categoriesRepository.existsById(categoriesId);
        if (!exists) {
            throw new IllegalStateException("User with id " + categoriesId + " does not exist");
        }

        categoriesRepository.deleteById(categoriesId);
    }

    public void updateCategories(Long categoriesId, Categories updatedCategories) {
        Categories existingCategories = categoriesRepository.findById(categoriesId)
                .orElseThrow(() -> new IllegalStateException("User with id " + categoriesId + " does not exist"));


        String name = updatedCategories.getName();
        String description = updatedCategories.getDescription();







        existingCategories.setName(name);
        existingCategories.setDescription(description);

        existingCategories.setCreatedAt(LocalDateTime.now());
        existingCategories.setDeletedAt(LocalDateTime.now());

        categoriesRepository.save(existingCategories);

    }
}



