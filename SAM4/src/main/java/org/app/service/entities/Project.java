package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Project {
	
	@Id @GeneratedValue
	private Integer idProiect;
	
	private String denumireProiect;
	private String tipProiect;

	private enum StatusProiect{Open, InProgress, Done;};
	private StatusProiect statusProiect;
	
	@OneToMany(mappedBy="project", cascade = CascadeType.ALL)
	private List<Team>teams = new ArrayList<Team>();
	
	@OneToMany(mappedBy="project", cascade = CascadeType.ALL)
	private List<Task>tasks = new ArrayList<Task>();

	public Integer getIdProiect() {
		return idProiect;
	}

	public void setIdProiect(Integer idProiect) {
		this.idProiect = idProiect;
	}

	public String getDenumireProiect() {
		return denumireProiect;
	}

	public void setDenumireProiect(String denumireProiect) {
		this.denumireProiect = denumireProiect;
	}

	public String getTipProiect() {
		return tipProiect;
	}

	public void setTipProiect(String tipProiect) {
		this.tipProiect = tipProiect;
	}

	public StatusProiect getStatusProiect() {
		return statusProiect;
	}

	public void setStatusProiect(StatusProiect statusProiect) {
		this.statusProiect = statusProiect;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Project(Integer idProiect, String denumireProiect, String tipProiect, StatusProiect statusProiect,
			List<Team> teams, List<Task> tasks) {
		super();
		this.idProiect = idProiect;
		this.denumireProiect = denumireProiect;
		this.tipProiect = tipProiect;
		this.statusProiect = statusProiect;
		this.teams = teams;
		this.tasks = tasks;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
