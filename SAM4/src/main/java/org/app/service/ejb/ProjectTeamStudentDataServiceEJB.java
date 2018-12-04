package org.app.service.ejb;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Student;
import org.app.service.entities.Team;


@Stateless @LocalBean
public class ProjectTeamStudentDataServiceEJB extends EntityRepositoryBase<Project> implements ProjectTeamStudentDataService, Serializable {

	private static Logger logger = Logger.getLogger(ProjectTeamStudentDataServiceEJB.class.getName());
	
	@EJB
	private StudentDataService studentDataService;
	
	//Local component-entity-repository
	private EntityRepository<Team> teamRepository;
	
	@PostConstruct
	public void init() {
		//local instantiation of local component-entity-repository
		teamRepository = new EntityRepositoryBase<Team>(this.em, Team.class);
		logger.info("POSTCONSTRUCT-INIT teamRepository: "+this.teamRepository);
		logger.info("POSTCONSTRUCT-INIT studentDataService: "+this.studentDataService);
	}
	
	//Aggregate factory method
	public Project createNewProject(Integer id) {
		
		//create project aggregate
		Project project = new Project(id, "NEW Project"+"."+id, "intern","Open");
		
		Student student = studentDataService.addStudent(new Student(null,"user_team","pass_team", "internship"));
		
		List<Team> teamProject = new ArrayList<>();
		
		Integer teamCount = 2;
		for(int i=0; i<=teamCount-1; i++) {
			teamProject.add(new Team(null, "Team no"+i, student, project));
		}
		
		project.setTeams(teamProject);
		
		//save project aggregate
		this.add(project);
		
		//return project aggregate to service client
		return project;
	}
	
	public Team getTeamById(Integer teamid) {
		return teamRepository.getById(teamid);
	}
	
	public String getMessage() {
		return "ProjectSprint DataService is working!";
	}
}
