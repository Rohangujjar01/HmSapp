package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}