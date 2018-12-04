package org.app.service.ejb;

import java.io.Serializable;
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
public class InfoStudentDataServiceEJB extends EntityRepositoryBase<InfoStudent> implements InfoStudentDataService, Serializable {

	private static Logger logger = Logger.getLogger(InfoStudentDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	private EntityRepository<Student> studentRepository;
	
	//Constructor
	public InfoStudentDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		studentRepository = new EntityRepositoryBase<Student>(this.em,Student.class);
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CREATE or UPDATE
	@Override
	public InfoStudent addInfoStudent(InfoStudent infoStudentToAdd) {
		em.persist(infoStudentToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(infoStudentToAdd);
		return infoStudentToAdd;
	}
	//READ
	@Override
	public InfoStudent getInfoStudentByIdInfoStudent(Integer idInfoStudent) {
		return em.find(InfoStudent.class, idInfoStudent);
	}
	
	public Collection<InfoStudent> getInfoStudent(){
		List<InfoStudent> infoStudent=em.createQuery("Select infs from InfoStudent infs", InfoStudent.class).getResultList();
		return infoStudent;
	}
	
	
	//CUSTOM READ
	public InfoStudent getInfoStudentByName(String numeStudent) {
		return em.createQuery("SELECT infs from InfoStudent infs WHERE infs.numeStudent = :numeStudent", InfoStudent.class).setParameter("numeStudent", numeStudent).getSingleResult();
	}

	//REMOVE
	public String removeInfoStudent(InfoStudent infoStudentToDelete) {
		infoStudentToDelete = em.merge(infoStudentToDelete);
		em.remove(infoStudentToDelete);
		em.flush();
		return "True";
	}
	
	public Student getStudentById(Integer idStudent) {
		return studentRepository.getById(idStudent);
	}
	
	//Others 
	public String sayRest() {
		return "InfoStudent Service is On!";
	}

}
