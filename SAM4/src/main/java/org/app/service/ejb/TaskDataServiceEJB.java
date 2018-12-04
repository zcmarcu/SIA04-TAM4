package org.app.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.service.entities.Task;

@Stateless @LocalBean
public class TaskDataServiceEJB implements TaskDataService {
	
	private static Logger logger = Logger.getLogger(TaskDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public TaskDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public Task addTask(Task taskToAdd){
		em.persist(taskToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(taskToAdd);
		return taskToAdd;
	}
	//READ
	@Override
	public Task getTaskByIdTask(Integer idTask) {
		return em.find(Task.class, idTask);
	}
	
	public Collection<Task>getTask(){
		List<Task> task=em.createQuery("Select t from Task ", Task.class).getResultList();
		return task;
	}
	
	
	//CUSTOM READ
	public Task getTaskByStatus(String status) {
		return em.createQuery("SELECT t from Task t WHERE t.status = :status", Task.class).setParameter("status", status).getSingleResult();
	}

	//REMOVE
	public String removeTask(Task taskToDelete) {
		taskToDelete = em.merge(taskToDelete);
		em.remove(taskToDelete);
		em.flush();
		return "True";
	}
	
	//Others 
	public String sayRest() {
		return "Task Service is On!";
	}
}
