package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Benefits implements Comparable<Benefits>, Serializable {
	
	@Id @GeneratedValue
	private Integer idBenefit;
	private String descriereBeneficiu;
	@ManyToOne
	private Student student;
	
	public Integer getIdBenefit() {
		return idBenefit;
	}
	public void setIdBenefit(Integer idBenefit) {
		this.idBenefit = idBenefit;
	}
	public String getDescriereBeneficiu() {
		return descriereBeneficiu;
	}
	public void setDescriereBeneficiu(String descriereBeneficiu) {
		this.descriereBeneficiu = descriereBeneficiu;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Benefits(Integer idBenefit, String descriereBeneficiu, Student student) {
		super();
		this.idBenefit = idBenefit;
		this.descriereBeneficiu = descriereBeneficiu;
		this.student = student;
	}
	public Benefits() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(Benefits b) {
		if(this.equals(b))
			return 0;
		return this.getIdBenefit().compareTo(b.getIdBenefit());
	}
	
}
