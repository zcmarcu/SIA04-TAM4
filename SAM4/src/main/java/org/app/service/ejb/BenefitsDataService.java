package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Benefits;
import org.app.service.entities.InfoStudent;
import org.app.service.entities.Student;

@Remote
public interface BenefitsDataService {
	
	//CREATE or UPDATE
	Benefits addBenefit(Benefits benefitToAdd);
	Student getStudentById(Integer idStudent);
	
	//DELETE
	String removeBenefit(Benefits benefitToDelete);
	
	//READ 
	Benefits getBenefitByIdBenefit(Integer idBenefit);
	Collection<Benefits> getBenefits();
	
	//Others
	String sayRest();
}
