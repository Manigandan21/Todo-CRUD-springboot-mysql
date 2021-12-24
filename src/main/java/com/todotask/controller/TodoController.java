package com.todotask.controller;

import java.util.List;

import com.todotask.entity.Todo;
import com.todotask.services.ITodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("todoservice")
public class TodoController {
	
	@Autowired
	private ITodoService service;
	
	/**
	 * Get list of all todos available in DB
	 * @return 
	 */
	@GetMapping("todos")
	public ResponseEntity<List<Todo>> getTodos(){
		
		List<Todo> todos = service.getTodos();
		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
		
	}
	
	/**
	 * Get specific Todo with its ID
	 * @param id
	 * @return
	 */
	@GetMapping("todos/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable("id") Integer id){
		Todo todo = service.getTodo(id);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	/**
	 * Create new todo record
	 * @param todo
	 * @return
	 */
	@PostMapping("todos")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
		Todo t = service.createTodo(todo);
		return new ResponseEntity<Todo>(t, HttpStatus.OK);
		
	}
	
	/**
	 * Update todo based on ID
	 * @param id
	 * @param todo
	 * @return
	 */
	@PutMapping("todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") int id, @RequestBody Todo todo){
		
		Todo t = service.updateTodo(id, todo);
		return new ResponseEntity<Todo>(t, HttpStatus.OK);
	}
	
	/**
	 * Delete single todo record based on ID
	 * @param id
	 * @return
	 */
	@DeleteMapping("todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") int id){
		boolean isDeleted = service.deleteTodo(id);
		if(isDeleted){
			String responseContent = "Todo with id: "+id+" has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting todo from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
