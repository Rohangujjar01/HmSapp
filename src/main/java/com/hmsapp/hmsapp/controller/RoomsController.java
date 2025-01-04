package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.Entity.RoomAvailability;
import com.hmsapp.hmsapp.repository.RoomAvailabilityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public RoomsController(RoomAvailabilityRepository roomAvailabilityRepository) {
        this.roomAvailabilityRepository = roomAvailabilityRepository;
    }
@GetMapping("/search/rooms")
    public ResponseEntity<?> searchRooms(
           @RequestParam LocalDate fromDate,
           @RequestParam LocalDate toDate,
           @RequestParam String roomType,
           @RequestParam Long propertyId

    ) {
    List<RoomAvailability> room = roomAvailabilityRepository.findAvailableRooms(
            fromDate,
            toDate,
            roomType
    );
    for (RoomAvailability r : room) {
            if(r.getTotal_rooms()==0){
                return new ResponseEntity<>("no room available", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    return new ResponseEntity<>(room, HttpStatus.OK);
}
}
