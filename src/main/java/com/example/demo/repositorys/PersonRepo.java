package com.example.demo.repositorys;

import com.example.demo.entitys.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepo extends CrudRepository<PersonEntity, UUID> {

    Optional<PersonEntity> findByUsername(String username);

}
