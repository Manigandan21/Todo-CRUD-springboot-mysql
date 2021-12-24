package com.todotask.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.todotask.entity.Todo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class TodoDAO implements ITodoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all Todo available in database and return it as List<Todo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Todo> getTodos() {
		
		String hql = "FROM Todo as atcl ORDER BY atcl.id";
		return (List<Todo>) entityManager.createQuery(hql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Todo detail by given todo id 
	 */
	@Override
	public Todo getTodo(int todoId) {
		
		return entityManager.find(Todo.class, todoId);
	}

	/**
	 * This method is responsible to create new todo in database
	 */
	@Override
	public Todo createTodo(Todo todo) {
		entityManager.persist(todo);
		Todo t = getLastInsertedTodo();
		return t;
	}

	/**
	 * This method is responsible to update todo detail in database
	 */
	@Override
	public Todo updateTodo(int todoId, Todo todo) {
		
		//First We are taking Todo detail from database by given todo id and 
		// then updating detail with provided todo object
		Todo todoFromDB = getTodo(todoId);
		todoFromDB.setTaskName(todo.getTaskName());
		todoFromDB.setDescription(todo.getDescription());
		
		entityManager.flush();
		
		//again i am taking updated result of todo and returning the todo object
		Todo updatedTodo = getTodo(todoId);
		
		return updatedTodo;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteTodo(int todoId) {
		Todo todo = getTodo(todoId);
		entityManager.remove(todo);
		
		//we are checking here that whether entityManager contains earlier deleted todo or not
		// if contains then todo is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(todo);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Todo class
	 * @return todo
	 */
	private Todo getLastInsertedTodo(){
		String hql = "from Todo order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Todo todo = (Todo)query.getSingleResult();
		return todo;
	}

}
