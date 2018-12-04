package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ProjectDataService;
import org.app.service.ejb.ProjectDataServiceEJB;
import org.app.service.entities.Project;
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
public class TestProjectDataServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestProjectDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB //EJB DataService Ref
	private static ProjectDataService service;	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-test.war")
	                .addPackage(Project.class.getPackage())
	                .addClass(ProjectDataService.class)
	                .addClass(ProjectDataServiceEJB.class)
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
	public void test1_addProject() {
		logger.info("DEBUG: Junit TESTING testAddProject...");
		
		
		Integer projectToAdd = 1;
		for(int i=1; i <= projectToAdd; i++) {
			//service.addBenefits(new Benefits(100 + i, "Benefits_" + (100 + i)));
			service.addProject(new Project(null,"Proiect_no"+i, "intern","Open"));
		}
		
		Collection<Project> project = service.getProject();
		assertTrue("Fail to read project!", project.size() > 0);
	}
	
	@Test
	public void test2_getProject() {
		
		logger.info("DEBUG: Junit TESTING testGetProject...");
		
		Collection<Project> project = service.getProject();
		assertTrue("Fail to read project!", project.size() > 0);
	}
	
	
	@Test
	public void test3_DeleteProject() {
		
		logger.info("DEBUG: Junit TESTING testGetProject...");
		
		Collection<Project> project = service.getProject();
		for(Project infs: project)
			service.removeProject(infs);
		
		Collection<Project> projectAfterDelete = service.getProject();
		assertTrue("Fail to read project!", projectAfterDelete.size() == 0);
	}
	
	
}
