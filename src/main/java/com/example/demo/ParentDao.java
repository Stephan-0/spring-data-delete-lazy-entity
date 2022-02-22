package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ParentDao extends CrudRepository<ParentEntity, UUID> {

}
