package jpm.sssm.services;
import jpm.sssm.data.interfaces.StockDataDAO;
import jpm.sssm.models.interfaces.StockDetails;
import jpm.sssm.models.interfaces.StockDetails.StockType;
import jpm.sssm.services.interfaces.CalculationService;

public class CalculationServiceDivYield implements CalculationService{

	private static final String SERVICENAME="Calculate Dividend Yield";
	
	@Override
	public String getCalculationName() {
		return SERVICENAME;
	}

	@Override
	public double performCalculation(StockDataDAO dataDB,String market,String stockSymbol, int price) {
		
		StockDetails stock = dataDB.getStockDetails(market, stockSymbol);
		
		if(stock.getStockType() == StockType.Common) {return calculateCommonDivYield(stock,price);}
		return calculatePreferredDivYield(stock,price);
	}
	
	private double calculateCommonDivYield(StockDetails stock, int price){
		return ((double)stock.getLastDividend() / (double) price);
	}
	
	private double calculatePreferredDivYield(StockDetails stock, int price){
		return ((((double)stock.getFixedInterestPctValue())*0.01) * ((double) stock.getParValue()))/(double) price;
	}


}
