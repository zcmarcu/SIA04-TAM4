package org.app.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;

@Stateless @LocalBean
public class StudentDataServiceEJB implements StudentDataService {
	
	private static Logger logger = Logger.getLogger(StudentDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public StudentDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public Student addStudent(Student studentToAdd){
		em.persist(studentToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(studentToAdd);
		return studentToAdd;
	}
	//READ
	@Override
	public Student getStudentByIdStudent(Integer idStudent) {
		return em.find(Student.class, idStudent);
	}
	
	public Collection<Student>getStudent(){
		List<Student> student=em.createQuery("Select s from Student s", Student.class).getResultList();
		return student;
	}

	//REMOVE
	public String removeStudent(Student studentToDelete) {
		studentToDelete = em.merge(studentToDelete);
		em.remove(studentToDelete);
		em.flush();
		return "True";
	}
	
	//Others 
	public String sayRest() {
		return "Student Service is On!";
	}
}
