package com.demo.roombooking.domain.repository;

import com.demo.roombooking.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
