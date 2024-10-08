package com.example.Ecommerce.controller;


import com.example.Ecommerce.Model.Image.Image;
import com.example.Ecommerce.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/image")
@CrossOrigin(value="*")
public class  ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/add")
    public String registerNewImage(@RequestParam (name ="productId") Long productId, @RequestBody  List<Image> imageList) {
        System.out.println("hello address conntroller");
        imageService.addNewImage(productId,imageList);
        return "Address added successfully";
    }

    @GetMapping("/getall")
    public List<Image> getImage() {
        return imageService.getImage();
    }

    @GetMapping("/getall/{id}")
    public Optional<Image> getImageBYId(@PathVariable(value = "id") Long imageId) {
        return imageService.getImageBYId(imageId);
    }



    @DeleteMapping(path = "{ImageId}")
    public void deleteImage(@PathVariable("imageId") Long imageId) {
        imageService.deleteImage(imageId);
    }

    @PutMapping(path = "{imageId}")
    public void updateAddresses(@RequestBody Image image, @PathVariable("imageId") Long imageId) {
        imageService.updateImage(imageId, image);
    }
}