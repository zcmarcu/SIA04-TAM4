package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Student;

@Remote
public interface StudentDataService {
	
	//CREATE or UPDATE
	Student addStudent(Student studentToAdd);
	
	//DELETE
	String removeStudent(Student studentToDelete);
	
	//READ 
	Student getStudentByIdStudent(Integer idStudent);
	Collection<Student> getStudent();
	
	//Others
	String sayRest();
}
