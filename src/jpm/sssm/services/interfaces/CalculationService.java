package jpm.sssm.services.interfaces;

import jpm.sssm.data.interfaces.StockDataDAO;

public interface CalculationService {
	String getCalculationName();
	double performCalculation(StockDataDAO dataDB,String market,String stockSymbol, int price);
}
