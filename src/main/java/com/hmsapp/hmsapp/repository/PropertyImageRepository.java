package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.Property;
import com.hmsapp.hmsapp.Entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    List<PropertyImage>findByPropertyId(
           Long id
    );
}