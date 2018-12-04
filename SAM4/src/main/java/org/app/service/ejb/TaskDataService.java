package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Task;

@Remote
public interface TaskDataService {

	//CREATE or UPDATE
	Task addTask(Task taskToAdd);
	
	//DELETE
	String removeTask(Task taskToDelete);
	
	//READ 
	Task getTaskByIdTask(Integer idTask);
	Collection<Task> getTask();
	
	//READ CUSTOM
	Task getTaskByStatus(String status);
	
	//Others
	String sayRest();
}
