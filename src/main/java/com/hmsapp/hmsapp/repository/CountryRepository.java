package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}