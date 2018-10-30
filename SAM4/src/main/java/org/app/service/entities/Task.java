package org.app.service.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Task {
	
	@Id @GeneratedValue
	private Integer idTask;
	
	@Temporal(TemporalType.DATE)
	private Date oraStartTask;
	
	@Temporal(TemporalType.DATE)
	private Date oraEndTask;
	
	private String descriereTask;

	private enum Status{Open, InProgress, Done;};
	private Status status;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Project project;

	public Integer getIdTask() {
		return idTask;
	}

	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}

	public Date getOraStartTask() {
		return oraStartTask;
	}

	public void setOraStartTask(Date oraStartTask) {
		this.oraStartTask = oraStartTask;
	}

	public Date getOraEndTask() {
		return oraEndTask;
	}

	public void setOraEndTask(Date oraEndTask) {
		this.oraEndTask = oraEndTask;
	}

	public String getDescriereTask() {
		return descriereTask;
	}

	public void setDescriereTask(String descriereTask) {
		this.descriereTask = descriereTask;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public Task(Integer idTask, Date oraStartTask, Date oraEndTask, String descriereTask, Status status,
			Student student, Project project) {
		super();
		this.idTask = idTask;
		this.oraStartTask = oraStartTask;
		this.oraEndTask = oraEndTask;
		this.descriereTask = descriereTask;
		this.status = status;
		this.student = student;
		this.project = project;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
