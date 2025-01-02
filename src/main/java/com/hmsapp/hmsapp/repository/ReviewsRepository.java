package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.Property;
import com.hmsapp.hmsapp.Entity.Reviews;
import com.hmsapp.hmsapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
   List<Reviews> findByUser (User user);
   Reviews findByPropertyAndUser(Property property, User user);
}