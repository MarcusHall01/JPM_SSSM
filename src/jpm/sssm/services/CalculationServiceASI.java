package jpm.sssm.services;

import java.util.List;

import jpm.sssm.data.interfaces.StockDataDAO;
import jpm.sssm.services.interfaces.CalculationService;

public class CalculationServiceASI implements CalculationService{

	private static final String SERVICENAME="Calculate ASI";

	@Override
	public String getCalculationName() {
		return SERVICENAME;
	}

	@Override
	public double performCalculation(StockDataDAO dataDB, String market, String stockSymbol, int price) {
	
		List<String> shares =  dataDB.getStocks(market);
		Long priceProduct =1L;
		
		int lastPrice;
		int noTradeStocks=0;
		
		for(String availableStockSymbol: shares)
		{
			lastPrice=dataDB.getLastTradePriceForStock(market, availableStockSymbol);
			
			if(lastPrice>-1){
				priceProduct = priceProduct*((long) lastPrice);
			}
			else{
				noTradeStocks++;
			}
		}

		return nRoot(shares.size()-noTradeStocks, priceProduct);
	}	
	
	private double nRoot(int size, Long priceProduct) {
		return Math.pow(priceProduct, ((double)1/size));
	}

}
