package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.Entity.Booking;
import com.hmsapp.hmsapp.Entity.Property;
import com.hmsapp.hmsapp.Entity.RoomAvailability;
import com.hmsapp.hmsapp.repository.BookingRepository;
import com.hmsapp.hmsapp.repository.PropertyRepository;
import com.hmsapp.hmsapp.repository.RoomAvailabilityRepository;
import com.hmsapp.hmsapp.service.PDFGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {
    private RoomAvailabilityRepository roomAvailabilityRepository;
    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;
    private PDFGenerator pdfGenerator;

    public BookingsController(RoomAvailabilityRepository roomAvailabilityRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository, PDFGenerator pdfGenerator) {
        this.roomAvailabilityRepository = roomAvailabilityRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.pdfGenerator = pdfGenerator;
    }
@GetMapping("/search/rooms")
    public ResponseEntity<?> searchRoomsAndBooks(
           @RequestParam LocalDate fromDate,
           @RequestParam LocalDate toDate,
           @RequestParam String roomType,
           @RequestParam Long propertyId,
            @RequestBody Booking bookings
    ) {
    List<RoomAvailability> room = roomAvailabilityRepository.findAvailableRooms(
            fromDate,
            toDate,
            roomType
    );
       Property property = propertyRepository.findById(propertyId).get();
    for (RoomAvailability r : room) {
            if(r.getTotal_rooms()==0){
                return new ResponseEntity<>("no room available", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
   bookings.setProperty(property);
    Booking savedBookings = bookingRepository.save(bookings);
    pdfGenerator.generatePDF("D:\\bookings_docs\\bookings"+"_"+savedBookings.getId()+".pdf",savedBookings);

    return new ResponseEntity<>(room, HttpStatus.OK);
}
}
