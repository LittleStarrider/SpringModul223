package com.example.demo.controllers;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.PersonEntity;
import com.example.demo.services.BookingService;
import com.example.demo.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin", produces = "application/json")
@Tag(name = "Admin", description = "Admin management endpoints")
public class AdminController {

    private final PersonService personService;
    private final BookingService bookingService;

    AdminController(PersonService personService, BookingService bookingService) {
        this.personService = personService;
        this.bookingService = bookingService;
    }

    @Operation(
            summary = "Get all users",
            description = "Get all users"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/users")
    List<PersonEntity> getAllUsers(@RequestParam(required = false) String name) {
        if (name != null) {
            return List.of(personService.getByName(name).get());
        }
        return personService.getAll();
    }

    @Operation(
            summary = "edit a user",
            description = "edit a user that exists"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("users/edit/{id}")
    PersonEntity updatePerson(@RequestBody PersonEntity personEntity, @PathVariable UUID id) {
        if (personEntity.getId() == id) {
            return personService.update(personEntity);
        } else return null;
    }

    @Operation(
            summary = "delete user",
            description = "delete a user that exists"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @DeleteMapping("users/delete/{id}")
    void deletePerson(@PathVariable UUID id) {
        personService.delete(id);
    }

    @Operation(
            summary = "get all bookings",
            description = "with param 'approved' = 'yes' --> only approved requests. 'approved' = 'no' --> only not approved requests"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/booking")
    List<BookingEntity> getAllBookings(@RequestParam(required = false) String approved) {
        if (Objects.equals(approved, "yes")) {
            return bookingService.getAllByApproved(true);
        } else if (Objects.equals(approved, "no")) {
            return bookingService.getAllByApproved(false);
        }
        return bookingService.getAll();
    }

    @Operation(
            summary = "edit booking",
            description = "edit an existing booking"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("booking/edit/{id}")
    BookingEntity updateBooking(@RequestBody BookingEntity booking, @PathVariable UUID id) {
        if (booking.getId() == id) {
            return bookingService.update(booking);
        } else return null;
    }

    @Operation(
            summary = "delete booking",
            description = "delete booking that exists"
            //security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @DeleteMapping("booking/delete/{id}")
    void deleteBooking(@PathVariable UUID id) {
        bookingService.delete(id);
    }

}
