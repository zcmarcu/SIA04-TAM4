package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.StartEnd;

@Remote
public interface StartEndDataService {
	
	//CREATE or UPDATE
	StartEnd addStartEnd(StartEnd startEndToAdd);
	
	//DELETE
	String removeStartEnd(StartEnd startEndToDelete);
	
	//READ 
	StartEnd getStartEndByIdStartEnd(Integer idStartEnd);
	Collection<StartEnd> getStartEnd();
	
	//Others
	String sayRest();
}
