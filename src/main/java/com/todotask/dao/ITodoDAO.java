package com.todotask.dao;

import java.util.List;

import com.todotask.entity.Todo;

public interface ITodoDAO {
	
	List<Todo> getTodos();
	Todo createTodo(Todo todo);
	Todo updateTodo(int todoId, Todo todo);
	Todo getTodo(int todoId);
	boolean deleteTodo(int todoId);

}
