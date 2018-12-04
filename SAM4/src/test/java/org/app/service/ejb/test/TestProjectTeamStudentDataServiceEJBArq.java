package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ProjectTeamStudentDataService;
import org.app.service.ejb.ProjectTeamStudentDataServiceEJB;
import org.app.service.ejb.StudentDataService;
import org.app.service.ejb.StudentDataServiceEJB;
import org.app.service.entities.Project;
import org.app.service.entities.Team;
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

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectTeamStudentDataServiceEJBArq {
	
	private static Logger logger = Logger.getLogger(TestProjectTeamStudentDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "MSD-test.war")
                .addPackage(EntityRepository.class.getPackage())
                .addPackage(Project.class.getPackage())
                .addClass(StudentDataService.class).addClass(StudentDataServiceEJB.class)
                .addClass(ProjectTeamStudentDataService.class)
                .addClass(ProjectTeamStudentDataServiceEJB.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }
	
	@EJB //EJB DataService Ref
	private static ProjectTeamStudentDataService service;	
	
	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test1_CreateNewProject() {
		Project project = service.createNewProject(1001);
		assertNotNull("Fail to create new project in repository!", project);
		
		//update project
		project.setDenumireProiect(project.getDenumireProiect() + " - changed by test client");
		
		List<Team> teams = project.getTeams();
		for(Team t: teams) 
			t.setDenumireEchipa(t.getDenumireEchipa() + " - changed by test client");
		project = service.add(project);
		assertNotNull("Fail to save new project in repository!", project);
		logger.info("DEBUG createNewProject: project changed:" + project);
		
		
		//check read
		project = service.getById(1001);
		assertNotNull("Fail to find changed project in repository!", project);
		logger.info("DEBUG createNewProject: queried project:" + project);
	}

	
	@Test
	public void test2_GetProject() {
		logger.info("DEBUG: Junit TESTING: testGetProject 1001 ...");
		Project project = service.getById(1001);
		assertNotNull("Fail to Get Project 1001!", project);
	}
	/*
	@Test
	public void test3_DeleteProject() {
		logger.info("DEBUG: Junit TESTING: testDeleteProject 1001...");
		Project project = service.getById(1001);
		if(project != null)
			service.remove(project);
		project = service.getById(1001);
		assertNull("Fail to delete Project 1001!", project);
	}
	*/
}
