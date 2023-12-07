package com.openclassrooms.chatop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.models.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {

}
