package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.repository.CategoriesRepository;
import com.example.Ecommerce.repository.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriesService {
    private final SubCategoriesRepository subCategoriesRepository;
    @Autowired
    private  CategoriesRepository categoriesRepository;
    @Autowired
    public SubCategoriesService(SubCategoriesRepository subCategoriesRepository) {
        this.subCategoriesRepository = subCategoriesRepository;
    }

    public List<SubCategories> getSubCategories() {
        return subCategoriesRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    public Optional<SubCategories> getSubCategoriesBYId(Long subCategoriesId) {

        return subCategoriesRepository.findById(subCategoriesId);
    }
    public void addNewSubCategories(Long id,SubCategories subCategories) {
        Categories categories = categoriesRepository.findById(id).get();
        if (categories.equals(null)) {
            throw new RuntimeException("user not found");
        } else {
            subCategories.setCreatedAt(LocalDateTime.now());
            subCategories.setDeletedAt(LocalDateTime.now());
            subCategories.setCategories(categories);
            subCategoriesRepository.save(subCategories);
        }
    }

    public void deleteSubCategories(Long subCategoriesId) {
        boolean exists = subCategoriesRepository.existsById(subCategoriesId);
        if (!exists) {
            throw new IllegalStateException("SubCategories with id " + subCategoriesId + " does not exist");
        }

        subCategoriesRepository.deleteById(subCategoriesId);
    }

    public void updateSubCategories(Long subCategoriesId,Long id,SubCategories updatedSubCategories) {

        SubCategories existingSubCategories = subCategoriesRepository.findById(subCategoriesId)
                .orElseThrow(() -> new IllegalStateException("Address with id " + subCategoriesId + " does not exist"));

        Categories categories1 = categoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category with the given id is not given" +id));

//        System.out.println(categories1);

            String name = updatedSubCategories.getName();
            String description = updatedSubCategories.getDescription();
            existingSubCategories.setName(name);
            existingSubCategories.setDescription(description);
            existingSubCategories.setCreatedAt(LocalDateTime.now());
            existingSubCategories.setDeletedAt(LocalDateTime.now());
            existingSubCategories.setCategories(categories1);

            subCategoriesRepository.save(existingSubCategories);
        }
    }

