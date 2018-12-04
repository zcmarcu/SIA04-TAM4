package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Team;

@Remote
public interface TeamDataService {
	
	//CREATE or UPDATE
	Team addTeam(Team teamToAdd);
	
	//DELETE
	String removeTeam(Team teamToDelete);
	
	//READ 
	Team getTeamByIdTeam(Integer idTeam);
	Collection<Team> getTeam();
	
	//READ CUSTOM
	Team getTeamByDenumireEchipa(String denumireEchipa);
	
	//Others
	String sayRest();
}
