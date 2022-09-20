package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.PersonEntity;
import com.example.demo.services.BookingService;
import com.example.demo.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/member", produces = "application/json")
@Tag(name = "Member", description = "Member management endpoints")
public class MemberController {

    private final PersonService personService;
    private final BookingService bookingService;

    MemberController(PersonService personService, BookingService bookingService) {
        this.personService = personService;
        this.bookingService = bookingService;
    }

    @Operation(
            summary = "Get all bookings from the user",
            description = "Loads all bookings from database."
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    List<BookingEntity> loadAllBookings(@PathVariable UUID id) {
        return bookingService.getAllByUserId(id);
    }

    @Operation(
            summary = "Create booking",
            description = "Create new booking for that user."
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PostMapping("/create")
    BookingEntity create(BookingEntity newBookingEntity) {
        return bookingService.create(newBookingEntity);
    }

    @Operation(
            summary = "Edit booking",
            description = "Edit a booking request for a user"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("/edit")
    BookingEntity update(@RequestBody BookingEntity updateBookingEntity) {
        val personEntity = bookingService.getById(updateBookingEntity.getId()).get().getPerson();
        if (personEntity.equals(updateBookingEntity.getPerson())) {
            return bookingService.update(updateBookingEntity);
        } else {
            return null;
        }
    }

    @Operation(
            summary = "Delete booking",
            description = "Delete a booking request for a user"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("/edit/{id}")
    void delete(@PathVariable UUID id) {
        val bookigEntity = bookingService.getById(id);
        bookingService.delete(id);
    }


}
