package com.openclassrooms.chatop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
