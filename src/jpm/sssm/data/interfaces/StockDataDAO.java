package  jpm.sssm.data.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jpm.sssm.models.interfaces.StockDetails;
import jpm.sssm.models.interfaces.StockTrade;

public interface StockDataDAO {

	List<String> getMarkets();
	List<String> getStocks(String market);
	
	StockDetails getStockDetails(String Market, String Stock);
	void recordTrade(String market, String stockSymbol, LocalDateTime timeStamp,long quantity, boolean isBuy, int price);	
	
	Set<StockTrade> getTradesforStockInTimeRangeInclusive(String market,String Stock, LocalDateTime Begin, LocalDateTime end);
	
	int getLastTradePriceForStock(String market,String Stock);
	
}
