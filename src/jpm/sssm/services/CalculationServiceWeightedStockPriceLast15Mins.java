package jpm.sssm.services;

import java.time.LocalDateTime;
import java.util.Set;

import jpm.sssm.data.interfaces.StockDataDAO;
import jpm.sssm.models.interfaces.StockTrade;
import jpm.sssm.services.interfaces.CalculationService;

public class CalculationServiceWeightedStockPriceLast15Mins implements CalculationService{

	private static final String SERVICENAME="Calculate WSP";

	@Override
	public String getCalculationName() {
		return SERVICENAME;
	}

	@Override
	public double performCalculation(StockDataDAO dataDB, String market, String stockSymbol, int price) {
		
		LocalDateTime dateTimeNow			= LocalDateTime.now();
		LocalDateTime dateTime15minutesAgo 	= LocalDateTime.now().minusMinutes(15L);
		
		Set<StockTrade> trades = dataDB.getTradesforStockInTimeRangeInclusive(market, stockSymbol, dateTime15minutesAgo, dateTimeNow);
		
		long tradedPriceQuantityProduct = 0;
		long tradedQuantity             = 0;
		
		for(StockTrade trade: trades){
			tradedPriceQuantityProduct = tradedPriceQuantityProduct + (trade.getPrice() * trade.getQuantity());
			tradedQuantity             = tradedQuantity + trade.getQuantity();
		}
		
		return ((double)tradedPriceQuantityProduct)/((double)tradedQuantity);
		
	}
}