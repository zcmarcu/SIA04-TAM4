package org.app.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.service.entities.StartEnd;

@Stateless @LocalBean
public class StartEndDataServiceEJB implements StartEndDataService {

	private static Logger logger = Logger.getLogger(StartEndDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public StartEndDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public StartEnd addStartEnd(StartEnd startEndToAdd){
		em.persist(startEndToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(startEndToAdd);
		return startEndToAdd;
	}
	//READ
	@Override
	public StartEnd getStartEndByIdStartEnd(Integer idStartEnd) {
		return em.find(StartEnd.class, idStartEnd);
	}
	
	public Collection<StartEnd>getStartEnd(){
		List<StartEnd> startEnd=em.createQuery("Select se from StartEnd ", StartEnd.class).getResultList();
		return startEnd;
	}

	//REMOVE
	public String removeStartEnd(StartEnd startEndToDelete) {
		startEndToDelete = em.merge(startEndToDelete);
		em.remove(startEndToDelete);
		em.flush();
		return "True";
	}
	
	//Others 
	public String sayRest() {
		return "StartEnd Service is On!";
	}
}
