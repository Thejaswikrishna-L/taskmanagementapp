package com.example.todoapp.web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.todoapp.domain.TodoItem;

@Repository
public interface TodomongoRepository extends MongoRepository<TodoItem, Integer>{}
