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
import org.app.service.entities.Task;


@Stateless @LocalBean
public class ProjectTaskStudentDataServiceEJB extends EntityRepositoryBase<Project> implements ProjectTaskStudentDataService, Serializable {

	private static Logger logger = Logger.getLogger(ProjectTaskStudentDataServiceEJB.class.getName());
	
	@EJB
	private StudentDataService studentDataService;
	
	//Local component-entity-repository
	private EntityRepository<Task> taskRepository;
	
	@PostConstruct
	public void init() {
		//local instantiation of local component-entity-repository
		taskRepository = new EntityRepositoryBase<Task>(this.em, Task.class);
		logger.info("POSTCONSTRUCT-INIT taskRepository: "+this.taskRepository);
		logger.info("POSTCONSTRUCT-INIT studentDataService: "+this.studentDataService);
	}
	
	//Aggregate factory method
	public Project createNewProject(Integer id) {
		
		//create project aggregate
		Project project = new Project(id, "NEW Project"+"."+id, "intern","In Progress");
		
		Student student = studentDataService.addStudent(new Student(null,"user_task","pass_task", "internship"));
		
		List<Task> taskProject = new ArrayList<>();
		Date oraStartTask = new Date();
		Date oraEndTask = new Date();
		
		Integer taskCount = 2;
		for(int i=0; i<=taskCount-1; i++) {
			taskProject.add(new Task(null, oraStartTask, oraEndTask, "De realizat teste Arq", "Open", student, project));
		}
		
		project.setTasks(taskProject);
		
		
		//save project aggregate
		this.add(project);
		
		//return project aggregate to service client
		return project;
	}
	
	public Task getTaskById(Integer taskid) {
		return taskRepository.getById(taskid);
	}
	
	public String getMessage() {
		return "ProjectSprint DataService is working!";
	}
}
