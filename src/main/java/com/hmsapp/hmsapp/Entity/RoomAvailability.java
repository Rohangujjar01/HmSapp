package com.hmsapp.hmsapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "room_availability")
public class RoomAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type", nullable = false)
    private String room_type;

    @Column(name = "total_rooms", nullable = false)
    private Long total_rooms;

    @Column(name = "nightly_price", nullable = false)
    private Long nightly_price;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Long getTotal_rooms() {
        return total_rooms;
    }

    public void setTotal_rooms(Long total_rooms) {
        this.total_rooms = total_rooms;
    }

    public Long getNightly_price() {
        return nightly_price;
    }

    public void setNightly_price(Long nightly_price) {
        this.nightly_price = nightly_price;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}