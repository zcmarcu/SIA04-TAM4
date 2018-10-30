package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Student {
	
	@Id @GeneratedValue
	private Integer idStudent;
	private String username;
	private String password;
	private String userType;
	
	@OneToOne(optional=false)
	private InfoStudent infoStudent;
	
	@OneToOne(optional=false)
	private StartEnd startEnd;
	
	@OneToMany(mappedBy="student",cascade = CascadeType.ALL)
	private List<Benefits> benefits = new ArrayList<Benefits>();
	
	@OneToMany(mappedBy="student",cascade = CascadeType.ALL)
	private List<Team> teams = new ArrayList<Team>();
	
	@OneToMany(mappedBy="student",cascade = CascadeType.ALL)
	private List<Task> tasks = new ArrayList<Task>();

	public Integer getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Integer idStudent) {
		this.idStudent = idStudent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public InfoStudent getInfoStudent() {
		return infoStudent;
	}

	public void setInfoStudent(InfoStudent infoStudent) {
		this.infoStudent = infoStudent;
	}

	public StartEnd getStartEnd() {
		return startEnd;
	}

	public void setStartEnd(StartEnd startEnd) {
		this.startEnd = startEnd;
	}

	public List<Benefits> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<Benefits> benefits) {
		this.benefits = benefits;
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

	public Student(Integer idStudent, String username, String password, String userType, InfoStudent infoStudent,
			StartEnd startEnd, List<Benefits> benefits, List<Team> teams, List<Task> tasks) {
		super();
		this.idStudent = idStudent;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.infoStudent = infoStudent;
		this.startEnd = startEnd;
		this.benefits = benefits;
		this.teams = teams;
		this.tasks = tasks;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
