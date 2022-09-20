package com.example.demo.repositorys;

import com.example.demo.entitys.BookingEntity;
import com.example.demo.entitys.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepo extends CrudRepository<BookingEntity, UUID> {

    List<BookingEntity> findAll();

    List<BookingEntity> findAllByPerson(PersonEntity personEntity);

    List<BookingEntity> findByApproved(boolean approved);

}
