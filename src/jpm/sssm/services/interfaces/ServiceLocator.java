package jpm.sssm.services.interfaces;

import java.util.List;

public interface ServiceLocator {
	List<String> getRegisteredServices();
	CalculationService getService(String name);
}
