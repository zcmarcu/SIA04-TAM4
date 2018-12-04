package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class InfoStudent implements Comparable<InfoStudent>, Serializable {

	@Id @GeneratedValue
	private Integer idInfoStudent;
	private String numeStudent;
	private String prenumeStudent;
	private String pozitie;
	private String facultate;
	private String anFacultate;

	@OneToOne(optional=false)
	private Student student;
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getIdInfoStudent() {
		return idInfoStudent;
	}

	public void setIdInfoStudent(Integer idInfoStudent) {
		this.idInfoStudent = idInfoStudent;
	}

	public String getNumeStudent() {
		return numeStudent;
	}

	public void setNumeStudent(String numeStudent) {
		this.numeStudent = numeStudent;
	}

	public String getPrenumeStudent() {
		return prenumeStudent;
	}

	public void setPrenumeStudent(String prenumeStudent) {
		this.prenumeStudent = prenumeStudent;
	}

	public String getPozitie() {
		return pozitie;
	}

	public void setPozitie(String pozitie) {
		this.pozitie = pozitie;
	}

	public String getFacultate() {
		return facultate;
	}

	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}

	public String getAnFacultate() {
		return anFacultate;
	}

	public void setAnFacultate(String anFacultate) {
		this.anFacultate = anFacultate;
	}

	public InfoStudent(Integer idInfoStudent, String numeStudent, String prenumeStudent, String pozitie,
			String facultate, String anFacultate, Student student) {
		super();
		this.idInfoStudent = idInfoStudent;
		this.numeStudent = numeStudent;
		this.prenumeStudent = prenumeStudent;
		this.pozitie = pozitie;
		this.facultate = facultate;
		this.anFacultate = anFacultate;
		this.student = student;
	}

	public InfoStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(InfoStudent i) {
		if(this.equals(i))
			return 0;
		return this.getIdInfoStudent().compareTo(i.getIdInfoStudent());
	}
	
}
