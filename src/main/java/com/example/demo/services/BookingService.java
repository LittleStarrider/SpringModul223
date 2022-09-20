package com.example.demo.services;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.PersonEntity;
import com.example.demo.exeptions.AlreadyExistsException;
import com.example.demo.exeptions.NotFoundException;
import com.example.demo.repositorys.BookingRepo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookingService {

    private final BookingRepo bookingRepo;

    BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public List<BookingEntity> getAll() {
        return bookingRepo.findAll();
    }

    public List<BookingEntity> getAllByUserId(PersonEntity personEntity) {
        return bookingRepo.findAllByPerson(personEntity);
    }

    public List<BookingEntity> getAllByApproved(boolean approved) {
        return bookingRepo.findByApproved(approved);
    }

    @Transactional
    public BookingEntity create(BookingEntity newBookingEntity) {
        val booingEntity = bookingRepo.findById(newBookingEntity.getId());
        if (booingEntity.isEmpty()) {
            log.info("Executing create booking ...");
            return bookingRepo.save(newBookingEntity);
        } else {
            throw new AlreadyExistsException("Booking already exists");
        }
    }

    @Transactional
    public BookingEntity update(BookingEntity updateBooking) {
        val bookingEntity = bookingRepo.findById(updateBooking.getId());
        if (bookingEntity.isPresent()) {
            log.info("Executing update booking ...");
            return bookingRepo.save(updateBooking);
        } else {
            throw new NotFoundException("Booking not found");
        }
    }

    @Transactional
    public void delete(UUID deleteBookingId) {
        val bookingEntity = bookingRepo.findById(deleteBookingId);
        if (bookingEntity.isPresent()) {
            log.info("Executing delete booking ...");
            bookingRepo.deleteById(deleteBookingId);
        } else {
            throw new NotFoundException("Booking not found");
        }
    }
}
