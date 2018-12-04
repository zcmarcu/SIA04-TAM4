package org.app.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.*;

import org.app.service.entities.Team;

@Stateless @LocalBean
public class TeamDataServiceEJB implements TeamDataService {

	private static Logger logger = Logger.getLogger(TeamDataServiceEJB.class.getName());
	
	/*DataService initialization*/
	//Inject resource
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public TeamDataServiceEJB() {
		
	}
	
	//Init after constructor
	@PostConstruct
	public void init() {
		logger.info("POSTRCONSTRUCT - INIT : " + this.em);
	}
	//CRUD operation implementation
	//CRUD or UPDATE
	@Override
	public Team addTeam(Team teamToAdd){
		em.persist(teamToAdd);
		em.flush();
		//transactions are manager by default by container
		em.refresh(teamToAdd);
		return teamToAdd;
	}
	//READ
	@Override
	public Team getTeamByIdTeam(Integer idTeam) {
		return em.find(Team.class, idTeam);
	}
	
	public Collection<Team>getTeam(){
		List<Team> team=em.createQuery("Select t from Team ", Team.class).getResultList();
		return team;
	}
	
	
	//CUSTOM READ
	public Team getTeamByDenumireEchipa(String denumireEchipa) {
		return em.createQuery("SELECT t from Team t WHERE t.denumireEchipa = :denumireEchipa", Team.class).setParameter("denumireEchipa", denumireEchipa).getSingleResult();
	}

	//REMOVE
	public String removeTeam(Team teamToDelete) {
		teamToDelete = em.merge(teamToDelete);
		em.remove(teamToDelete);
		em.flush();
		return "True";
	}
	
	//Others 
	public String sayRest() {
		return "Team Service is On!";
	}
}
