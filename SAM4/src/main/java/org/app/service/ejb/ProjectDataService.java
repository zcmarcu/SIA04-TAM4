package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Project;

@Remote
public interface ProjectDataService {
	//CREATE or UPDATE
	Project addProject(Project projectToAdd);
	
	//DELETE
	String removeProject(Project projectToDelete);
	
	//READ 
	Project getProjectByIdProject(Integer idProiect);
	Collection<Project> getProject();
	
	//READ CUSTOM
	Project getProjectByDenumireProiect(String denumireProiect);
	
	//Others
	String sayRest();
}
