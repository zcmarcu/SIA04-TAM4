package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.BenefitsDataService;
import org.app.service.ejb.BenefitsDataServiceEJB;
import org.app.service.entities.Benefits;
import org.app.service.entities.EntityBase;
import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBenefitsDataServiceEJBArq {
	
	private static Logger logger = Logger.getLogger(TestBenefitsDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB //EJB DataService Ref
	private static BenefitsDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(Benefits.class.getPackage())
	                .addClass(BenefitsDataService.class)
	                .addClass(BenefitsDataServiceEJB.class)
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
	public void test1_addBenefit() {
		logger.info("DEBUG: Junit TESTING testAddBenefit...");
		
		Integer idStudent = 7;
		Student idStudentTest = service.getStudentById(idStudent);
		
		Integer benefitToAdd = 3;
		for(int i=1; i <= benefitToAdd; i++) {
			
			service.addBenefit(new Benefits(null, "Benefits_" + i, idStudentTest));
		}
		
		Collection<Benefits> benefits = service.getBenefits();
		assertTrue("Fail to read benefits!", benefits.size() > 0);
	}
	
	@Test
	public void test2_getBenefits() {
		logger.info("DEBUG: Junit TESTING testGetBenefits...");
		
		Collection<Benefits> benefits = service.getBenefits();
		assertTrue("Fail to read benefits!", benefits.size() > 0);
	}
	
	
	/*
	@Test
	public void test3_DeleteBenefits() {
		
		logger.info("DEBUG: Junit TESTING testGetBenefits...");
		
		Collection<Benefits> benefits = service.getBenefits();
		for(Benefits b: benefits)
			service.removeBenefit(b);
		
		Collection<Benefits> benefitsAfterDelete = service.getBenefits();
		assertTrue("Fail to read Benefits!", benefitsAfterDelete.size() == 0);
	}
	*/
}
