package com.todotask.services;

import java.util.List;

import com.todotask.dao.ITodoDAO;
import com.todotask.entity.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService implements ITodoService {
	
	@Autowired
	private ITodoDAO dao;

	@Override
	public List<Todo> getTodos() {
		return dao.getTodos();
	}

	@Override
	public Todo createTodo(Todo todo) {
		return dao.createTodo(todo);
	}

	@Override
	public Todo updateTodo(int todoId, Todo todo) {
		return dao.updateTodo(todoId, todo);
	}

	@Override
	public Todo getTodo(int todoId) {
		return dao.getTodo(todoId);
	}

	@Override
	public boolean deleteTodo(int todoId) {
		return dao.deleteTodo(todoId);
	}

}
