package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Project;
import org.app.service.entities.Team;

@Remote
public interface ProjectTeamStudentDataService extends EntityRepository<Project> {

	//create aggregate entity: project root with releases as components
	Project createNewProject(Integer id);
	
	//Query method on team components 
	Team getTeamById(Integer teamid);
	
	//Other
	String getMessage();
	
}
