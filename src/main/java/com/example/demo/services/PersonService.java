package com.example.demo.services;

import com.example.demo.entitys.PersonEntity;
import com.example.demo.exeptions.AlreadyExistsException;
import com.example.demo.exeptions.NotFoundException;
import com.example.demo.repositorys.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PersonService {

    private final PersonRepo personRepo;

    PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<PersonEntity> getAll() {
        log.info("Executing find all users ...");
        return personRepo.findAll();
    }

    public Optional<PersonEntity> getPersonById(UUID id) {
        log.info("Executing find user by ID ...");
        return personRepo.findById(id);
    }

    public Optional<PersonEntity> getByName(String name) {
        return personRepo.findByName(name);
    }

    @Transactional
    public PersonEntity create(PersonEntity newPerson) {
        val personEntity = personRepo.findById(newPerson.getId());
        if (personEntity.isEmpty()) {
            log.info("Executing create new user ...");
            return personRepo.save(newPerson);
        } else {
            throw new AlreadyExistsException("Already exists");
        }
    }

    @Transactional
    public PersonEntity update(PersonEntity updatePerson) {
        val personEntity = personRepo.findById(updatePerson.getId());
        if (personEntity.isPresent()) {
            log.info("Executing update user ...");
            return personRepo.save(updatePerson);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Transactional
    public void delete(UUID deletePersonId) {
        val personEntity = personRepo.findById(deletePersonId);
        if (personEntity.isPresent()) {
            log.info("Executing delete user ...");
            personRepo.deleteById(deletePersonId);
        } else {
            throw new NotFoundException("User not found");
        }
    }
}
