package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.Entity.Property;
import com.hmsapp.hmsapp.Entity.PropertyImage;
import com.hmsapp.hmsapp.repository.PropertyImageRepository;
import com.hmsapp.hmsapp.repository.PropertyRepository;
import com.hmsapp.hmsapp.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")


public class PropertyController {
    private PropertyRepository propertyRepository;
    private BucketService bks;
    private PropertyImageRepository propertyImageRepository;


    public PropertyController(PropertyRepository propertyRepository, BucketService bks, PropertyImageRepository propertyImageRepository) {
        this.propertyRepository = propertyRepository;
        this.bks = bks;
        this.propertyImageRepository = propertyImageRepository;
    }

    @PostMapping("/add")
    public String addProperty() {
        return "added";
    }

    @PostMapping("/delete")
    public String deleteProperty() {
        return "deleted";
    }

    @GetMapping("/{searchParam}")
    public List<Property> searchProperty(@PathVariable String searchParam) {
        return propertyRepository.getPropertyDetails(searchParam);
    }

    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public String uploadPropertyImages(@RequestParam MultipartFile file,
                                       @PathVariable String bucketName,
                                       @PathVariable long propertyId) {
        String imgUrl = bks.uploadFile(file, bucketName);
        PropertyImage propertyImage = new PropertyImage();
        propertyImage.setUrl(imgUrl);
        Optional<Property> op = propertyRepository.findById(propertyId);
        if (op.isPresent()) {
            Property property = op.get();
            propertyImage.setProperty(property);
            propertyImageRepository.save(propertyImage);
            return "image is uploaded";
        } else {
            return "Property not found";

        }
    }

    @GetMapping("/get/property/images")
    public List<PropertyImage> getPropertyImages(@RequestParam long  propertyId) {
//        Optional<Property> op = propertyRepository.findById(propertyId);
//        if (op.isPresent()) {
//            Property property = op.get();
            return propertyImageRepository.findByPropertyId(propertyId);
//        } else {
//            return null;
//
//        }
    }
}