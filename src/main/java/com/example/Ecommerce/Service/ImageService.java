package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Image.Image;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.ImageRepository;
import com.example.Ecommerce.repository.ProductsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductsRepository productsRepository;
    // Add new images for a product
    public void addNewImage(Long productId, List<Image> imageList) {
        Products products = productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + productId));
        for (Image image : imageList) {
            image.setProducts(products);
            imageRepository.save(image);
        }
    }

    // Retrieve all images
    public List<Image> getImage() {
        return imageRepository.findAll();
    }

    // Retrieve an image by ID
    public Optional<Image> getImageBYId(Long id) {
        return imageRepository.findById(id);
    }


    // Delete an image
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    // Update an image
    public void updateImage(Long id, Image updatedImage) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        image.setImgUrl(updatedImage.getImgUrl()); // Update the URL or any other fields as needed
        imageRepository.save(image);
    }
}
