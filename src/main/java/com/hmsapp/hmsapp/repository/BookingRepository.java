package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}