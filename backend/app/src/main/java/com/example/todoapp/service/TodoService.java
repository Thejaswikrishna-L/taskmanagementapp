package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.domain.TodoItem;
import com.example.todoapp.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	public List<TodoItem> fetchAllTodoItems (){
		return todoRepo.fetchAllTodoItems();
	}
	
	public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
		Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
			.stream()
			.filter(item -> item.getId().equals(id))
			.findAny();
		if (todoOpt.isPresent()) {
			TodoItem item = todoOpt.get();
			item.setIsDone(todoItem.getIsDone());
			item.setTask(todoItem.getTask());
			item.setTaskstatus(todoItem.getTaskstatus());
			return item;
		}
		return null;
	}

	public TodoItem createTodoItem() {
		TodoItem todoItem = new TodoItem();
		todoItem.setIsDone(false);
		todoItem = todoRepo.save(todoItem);
		todoItem.setTask("Task " + todoItem.getId());
		todoItem.setTaskstatus("CREATED");
		return todoItem;
	}
	
	public void deleteTodoItem(Integer id) {
		todoRepo.delete(id);
		// TODO Auto-generated method stub
		
	}
}
