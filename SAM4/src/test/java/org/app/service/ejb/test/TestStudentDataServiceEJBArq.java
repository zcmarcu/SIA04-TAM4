package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InfoStudentDataService;
import org.app.service.ejb.StudentDataService;
import org.app.service.ejb.StudentDataServiceEJB;
import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestStudentDataServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestStudentDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB //EJB DataService Ref
	private static StudentDataService service;	
	//private static InfoStudentDataService service2;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-test.war")
	                .addPackage(Student.class.getPackage())
	                .addClass(StudentDataService.class)
	                .addClass(StudentDataServiceEJB.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	
	@Test
	public void test() {
		
		logger.info("DEBUG: Junit TESTING ...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test1_addStudent() {
		
		logger.info("DEBUG: Junit TESTING testAddStudent...");

		Integer studentToAdd = 4;
		for(int i=1; i <= studentToAdd; i++) {
			service.addStudent(new Student(null, "popescuion"+i, "password","intern"+i));
		}
		
		Collection<Student> student = service.getStudent();
		assertTrue("Fail to read student!", student.size() > 0);
		
	}
	
	@Test
	public void test2_getStudent() {
		
		logger.info("DEBUG: Junit TESTING testGetStudent...");
		
		Collection<Student> student = service.getStudent();
		assertTrue("Fail to read student!", student.size() > 0);
	}

	/*
	@Test
	public void test3_DeleteStudent() {
		
		logger.info("DEBUG: Junit TESTING testGetStudent...");
		
		Collection<Student> student = service.getStudent();
		for(Student s: student)
			service.removeStudent(s);
		
		Collection<Student> studentAfterDelete = service.getStudent();
		assertTrue("Fail to read student!", studentAfterDelete.size() == 0);
	}
	*/
}
