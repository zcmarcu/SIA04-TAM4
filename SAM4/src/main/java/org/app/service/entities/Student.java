package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Student implements Comparable<Student>, Serializable {
	
	@Id @GeneratedValue
	private Integer idStudent;
	private String username;
	private String password;
	private String userType;
	
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

	public Student(Integer idStudent, String username, String password, String userType, List<Benefits> benefits, List<Team> teams, List<Task> tasks) {
		super();
		this.idStudent = idStudent;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.benefits = benefits;
		this.teams = teams;
		this.tasks = tasks;
	}
	
	public Student(Integer idStudent, String username, String password, String userType) {
		super();
		this.idStudent = idStudent;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(Student s) {
		if(this.equals(s))
			return 0;
		return this.getIdStudent().compareTo(s.getIdStudent());
	}
	
}
