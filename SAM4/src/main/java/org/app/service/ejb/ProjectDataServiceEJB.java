package org.app.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.service.entities.Project;

@Stateless @LocalBean
public class ProjectDataServiceEJB implements ProjectDataService {
	
	private static Logger logger = Logger.getLogger(ProjectDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public ProjectDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public Project addProject(Project projectToAdd){
		em.persist(projectToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(projectToAdd);
		return projectToAdd;
	}
	//READ
	@Override
	public Project getProjectByIdProject(Integer idProiect) {
		return em.find(Project.class, idProiect);
	}
	
	public Collection<Project>getProject(){
		List<Project> project=em.createQuery("Select p from Project p", Project.class).getResultList();
		return project;
	}
	
	
	//CUSTOM READ
	public Project getProjectByDenumireProiect(String denumireProiect) {
		return em.createQuery("SELECT p from Project p WHERE p.denumireProiect = :denumireProiect", Project.class).setParameter("denumireProiect", denumireProiect).getSingleResult();
	}

	//REMOVE
	public String removeProject(Project projectToDelete) {
		projectToDelete = em.merge(projectToDelete);
		em.remove(projectToDelete);
		em.flush();
		return "True";
	}
	
	//Others 
	public String sayRest() {
		return "Project Service is On!";
	}
}
