package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InfoStudentDataService;
import org.app.service.ejb.InfoStudentDataServiceEJB;
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
public class TestInfoStudentDataServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestInfoStudentDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB //EJB DataService Ref
	private static InfoStudentDataService service;	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(InfoStudent.class.getPackage())
	                .addClass(InfoStudentDataService.class)
	                .addClass(InfoStudentDataServiceEJB.class)
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
	public void test1_addInfoStudent() {
		logger.info("DEBUG: Junit TESTING testAddInfoStudent...");
		
		Integer idStudent = 7;
		Student idStudentTest = service.getStudentById(idStudent);
		
		Integer infoStudentToAdd = 1;
		for(int i=1; i <= infoStudentToAdd; i++) {
			service.addInfoStudent(new InfoStudent(null, "Bianca" + i, "Morariu" + i,"JrDeveloper","FEAA", "3",idStudentTest));
		}
		
		Collection<InfoStudent> infoStudent = service.getInfoStudent();
		assertTrue("Fail to read infoStudent!", infoStudent.size() > 0);
	}
	
	@Test
	public void test2_getInfoStudent() {
		
		logger.info("DEBUG: Junit TESTING testGetInfoStudent...");
		
		Collection<InfoStudent> infoStudent = service.getInfoStudent();
		assertTrue("Fail to read infoStudent!", infoStudent.size() > 0);
	}
	
	@Test
	public void test3_DeleteInfoStudent() {
		
		logger.info("DEBUG: Junit TESTING testGetInfoStudent...");
		
		Collection<InfoStudent> infoStudent = service.getInfoStudent();
		for(InfoStudent infs: infoStudent)
			service.removeInfoStudent(infs);
		
		Collection<InfoStudent> infoStudentAfterDelete = service.getInfoStudent();
		assertTrue("Fail to read infoStudent!", infoStudentAfterDelete.size() == 0);
	}
	
}
