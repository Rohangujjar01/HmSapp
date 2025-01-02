package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    //jpql query to search properties based on city and country
    @Query("Select p from Property p join p.city c join p.country co where c.name = :searchParam or co.name = :searchParam ")
    List<Property> getPropertyDetails(@Param("searchParam") String searchParam);




}