package jpm.sssm.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jpm.sssm.services.interfaces.CalculationService;
import jpm.sssm.services.interfaces.ServiceLocator;

public class BasicServiceLocator implements ServiceLocator{
	
	private Map<String,CalculationService> services; 
	
	public BasicServiceLocator (CalculationService ...servicesList){
		services = new HashMap<String, CalculationService>();
		
		for (CalculationService calcService: servicesList) {
			services.put(calcService.getCalculationName(), calcService);
		}
	}

	@Override
	public List<String> getRegisteredServices() {
		return new ArrayList<String>(services.keySet());
	}

	@Override
	public CalculationService getService(String name) {
		return services.get(name);
	}

}
