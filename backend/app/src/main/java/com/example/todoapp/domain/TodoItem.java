package com.example.todoapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskmanage")
public class TodoItem {
	
	@Id
	private Integer id;
	private String task;
	private Boolean isDone;
	
	
	public TodoItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TodoItem(String task, Boolean isDone) {
		super();
		this.task = task;
		this.isDone = isDone;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	} 

}
