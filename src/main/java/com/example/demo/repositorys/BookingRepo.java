package com.example.demo.repositorys;

import com.example.demo.entitys.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepo extends CrudRepository<BookingEntity, UUID> {

    List<BookingEntity> findAll();

}