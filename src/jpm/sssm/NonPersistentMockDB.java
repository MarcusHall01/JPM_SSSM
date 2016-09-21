package jpm.sssm.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jpm.sssm.models.interfaces.StockTrade;
import jpm.sssm.models.SpecificStockInformation;
import jpm.sssm.models.SpecificTradeInfomation;
import jpm.sssm.models.interfaces.StockDetails;
import jpm.sssm.models.interfaces.StockDetails.StockType;
import jpm.sssm.data.interfaces.StockDataDAO;

public class NonPersistentMockDB implements StockDataDAO{
		
	private static Map<String, StockDetails> StockInformation;
	private static Map<String, Map<String, StockDetails>> StockInfomationPerMarket;
	private static List<String> Markets;
	
	static {
		StockInformation = new HashMap<String,StockDetails>();
		StockInformation.put("TEA",new SpecificStockInformation("TEA","GBCE",StockType.Common,0,0,100));
		StockInformation.put("POP",new SpecificStockInformation("POP","GBCE",StockType.Common,8,0,100));
		StockInformation.put("ALE",new SpecificStockInformation("ALE","GBCE",StockType.Common,23,0,60));
	    StockInformation.put("GIN",new SpecificStockInformation("GIN","GBCE",StockType.Preferred,8,2,100));
		StockInformation.put("JOE",new SpecificStockInformation("JOE","GBCE",StockType.Common,13,0,250));
	
		StockInfomationPerMarket = new HashMap<String, Map<String, StockDetails>>();
		StockInfomationPerMarket.put("GBCE", StockInformation);
		
		Markets = new ArrayList<String>();
		Markets.add("GBCE");
	}
	
	private Map<String, TreeSet<StockTrade>> RecordedTrades
	= new HashMap<String, TreeSet<StockTrade>>();
	
	
	@Override
	public List<String> getMarkets() {
		return new ArrayList<String>(Markets);
	}
	
	@Override
	public List<String> getStocks(String market) {
		return new ArrayList<String>(StockInfomationPerMarket.get(market).keySet());
	}
	
	@Override
	public StockDetails getStockDetails(String market, String stockSymbol) {
		return StockInfomationPerMarket.get(market).get(stockSymbol);
	}

	@Override
	public void recordTrade(String market, String stockSymbol, LocalDateTime timeStamp,long quantity, boolean isBuy, int price) {
		SpecificTradeInfomation trade = new SpecificTradeInfomation(stockSymbol, market, timeStamp, quantity, isBuy, price);
		recordTrade(trade.getMarket()+"#"+trade.getTicker(), trade);
	}

	@Override
	public Set<StockTrade> getTradesforStockInTimeRangeInclusive(String market, String symbol, LocalDateTime begin, LocalDateTime end) {
		
		TreeSet<StockTrade> trades = RecordedTrades.get(market+"#"+symbol);
		return trades.subSet(new SpecificTradeInfomation(begin), new SpecificTradeInfomation(end));
	}
	
	@Override
	public int getLastTradePriceForStock(String market, String stock) {
		TreeSet<StockTrade> tradeSet= RecordedTrades.get(market+"#"+stock);
		
		// If no trades, return -1;
		if(tradeSet == null){ return -1;}
		return tradeSet.last().getPrice();
	}
	
	private void recordTrade(String key, StockTrade trade)
	{
		Set<StockTrade> trades = RecordedTrades.get(key);
				
		if(trades == null)
		{
			synchronized(RecordedTrades){
				trades = RecordedTrades.get(key);
				if(trades ==null)
				{
					RecordedTrades.put(key, new TreeSet<StockTrade>());
					trades = RecordedTrades.get(key);
				}
			}
		}
		trades.add(trade);
	}
}
