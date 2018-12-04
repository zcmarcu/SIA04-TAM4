package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Team implements Comparable<Team>, Serializable {
	
	@Id @GeneratedValue
	private Integer idTeam;
	
	private String denumireEchipa;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Project project;

	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	public String getDenumireEchipa() {
		return denumireEchipa;
	}

	public void setDenumireEchipa(String denumireEchipa) {
		this.denumireEchipa = denumireEchipa;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Team(Integer idTeam, String denumireEchipa, Student student, Project project) {
		super();
		this.idTeam = idTeam;
		this.denumireEchipa = denumireEchipa;
		this.student = student;
		this.project = project;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(Team t) {
		if(this.equals(t))
			return 0;
		return this.getIdTeam().compareTo(t.getIdTeam());
	}
	
}
