package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class StartEnd implements Comparable<StartEnd>, Serializable {
	
	@Id
	private Integer idStartEnd;
	
	@Temporal(TemporalType.DATE)
	private Date dataStart;
	
	@Temporal(TemporalType.DATE)
	private Date dataEnd;
	
	@OneToOne(optional=false)
	private Student student;

	public Integer getIdStartEnd() {
		return idStartEnd;
	}

	public void setIdStartEnd(Integer idStartEnd) {
		this.idStartEnd = idStartEnd;
	}

	public Date getDataStart() {
		return dataStart;
	}

	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}

	public Date getDataEnd() {
		return dataEnd;
	}

	public void setDataEnd(Date dataEnd) {
		this.dataEnd = dataEnd;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StartEnd(Integer idStartEnd, Date dataStart, Date dataEnd, Student student) {
		super();
		this.idStartEnd = idStartEnd;
		this.dataStart = dataStart;
		this.dataEnd = dataEnd;
		this.student = student;
	}

	public StartEnd() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(StartEnd se) {
		if(this.equals(se))
			return 0;
		return this.getIdStartEnd().compareTo(se.getIdStartEnd());
	}
}
