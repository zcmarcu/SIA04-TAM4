package org.app.service.ejb;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Benefits;
import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;

@Stateless @LocalBean
public class BenefitsDataServiceEJB extends EntityRepositoryBase<Benefits> implements BenefitsDataService, Serializable {
	
	private static Logger logger = Logger.getLogger(BenefitsDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	private EntityRepository<Student> StudentRepository;
	
	//Constructor
	public BenefitsDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		StudentRepository = new EntityRepositoryBase<Student>(this.em,Student.class);
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public Benefits addBenefit(Benefits benefitToAdd){
		em.persist(benefitToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(benefitToAdd);
		return benefitToAdd;
	}
	//READ
	@Override
	public Benefits getBenefitByIdBenefit(Integer idBenefit) {
		return em.find(Benefits.class, idBenefit);
	}
	
	public Collection<Benefits> getBenefits(){
		List<Benefits> benefits=em.createQuery("Select b from Benefits b ", Benefits.class).getResultList();
		return benefits;
	}
	
	//REMOVE
	public String removeBenefit(Benefits benefitToDelete) {
		benefitToDelete = em.merge(benefitToDelete);
		em.remove(benefitToDelete);
		em.flush();
		return "True";
	}
	
	public Student getStudentById(Integer idStudent) {
		return StudentRepository.getById(idStudent);
	}
	
	//Others 
	public String sayRest() {
		return "Benefits Service is On!";
	}
	
}


