package com.example.todoapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.domain.TodoItem;
import com.example.todoapp.service.TodoService;
import com.example.todoapp.web.repository.TodomongoRepository;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
public class TodoController {
	
	/// front-end            java-server
	// HttpRequest --- >    Controller --- >  Service --- >  Repository
	//  front-end  < ---    Controller  < ---  Service < ---
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodomongoRepository todomongoRepository;
	
	// fetch all todo items (from database) 
	@GetMapping ("/api/todoItems")
	public ResponseEntity<?> fetchAllTodoItems (){
		List<TodoItem> todoItems = todoService.fetchAllTodoItems();
		todomongoRepository.findAll();
		return ResponseEntity.ok(todoItems);
	}
	
	@PostMapping("/api/todoItems")
	public ResponseEntity<?> createNewTodoItem (){
		TodoItem todoItem = todoService.createTodoItem();
		todomongoRepository.insert(todoItem);
		List<TodoItem> abc= todomongoRepository.findAll();
		return ResponseEntity.ok(abc);
	}
	
	@PutMapping("/api/todoItems/{id}")
	public ResponseEntity<?> updateTodoItem (@PathVariable Integer id, @RequestBody TodoItem todoItem){
		//call the service
		//get the data back from server
		TodoItem updatedTodoItem = todoService.updateTodoItem(id, todoItem);		
		
		//send it! (back to the front-end)
		return ResponseEntity.ok(updatedTodoItem);
	}
	
	@DeleteMapping("/api/todoItems/{id}")
	public ResponseEntity<?> deleteTodoItem (@PathVariable Integer id){
		//call the service
		//get the data back from server
		todoService.deleteTodoItem(id);		
		
		//send it! (back to the front-end)
		return ResponseEntity.ok(null);
	}
}
