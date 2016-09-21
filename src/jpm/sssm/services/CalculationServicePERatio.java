package jpm.sssm.services;


import jpm.sssm.data.interfaces.StockDataDAO;
import jpm.sssm.services.interfaces.CalculationService;

public class CalculationServicePERatio implements CalculationService{

	private static final String SERVICENAME="Calculate PE Ratio";

	@Override
	public String getCalculationName() {
		return SERVICENAME;
	}

	@Override
	public double performCalculation(StockDataDAO dataDB,String market,String stockSymbol, int price) {
		return ((double) price)/((double) dataDB.getStockDetails(market, stockSymbol).getLastDividend());
	}
	
}
