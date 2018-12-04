package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;

@Remote
public interface InfoStudentDataService {

	//CREATE or UPDATE
	InfoStudent addInfoStudent(InfoStudent infoStudentToAdd);
	
	Student getStudentById(Integer idStudent);
	
	//DELETE
	String removeInfoStudent(InfoStudent infoStudentToDelete);
	
	//READ 
	InfoStudent getInfoStudentByIdInfoStudent(Integer idInfoStudent);
	Collection<InfoStudent> getInfoStudent();
	
	//READ CUSTOM
	InfoStudent getInfoStudentByName(String numeStudent);
	
	//Others
	String sayRest();
}
