package com.fajarachmad.tutorial.service.impl;

public class ClientService {
	
	private InpatientService inpatientService;
	private OutpatientService outpatientService;
	
	public void mainProcess(String service){
		
		if (service.equals("INPATIENT")) {
			inpatientService.regisrationProcess();
		} else {
			outpatientService.regisrationProcess();
		}
		
	}
	
}
