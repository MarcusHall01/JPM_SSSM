package jpm.sssm.models;

import java.time.LocalDateTime;

import jpm.sssm.models.interfaces.StockTrade;

public class SpecificTradeInfomation implements StockTrade{

	private String 			symbol;
	private String 			market;
	private LocalDateTime 	timestamp;
	private long 			quantity;
	private boolean 		isBuy;
	private int 			unitPrice;
	
	public SpecificTradeInfomation(LocalDateTime timestamp){
		this(null,null,timestamp,0L,false,0);
	}
	
	public SpecificTradeInfomation(String symbol, String market, LocalDateTime timestamp, long quantity, boolean isBuy, int unitPrice){
		this.symbol 	= symbol;
		this.market 	= market;
		this.timestamp 	= timestamp;
		this.quantity	= quantity;
		this.isBuy		= isBuy;
		this.unitPrice	= unitPrice;
	}
	
	
	@Override
	public String getTicker() {
		return symbol;
	}

	@Override
	public String getMarket() {
		return market;
	}

	@Override
	public LocalDateTime getTimeStamp() {
		// Immutable
		return timestamp;
	}

	@Override
	public long getQuantity() {
		return quantity;
	}

	@Override
	public boolean isBuy() {
		return isBuy;
	}

	@Override
	public int getPrice() {
		return unitPrice;
	}

	@Override
	public int compareTo(StockTrade tradeToCompare) {
		//a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
		if(this.timestamp.isBefore(tradeToCompare.getTimeStamp())) return -1;
		if(this.timestamp.isEqual(tradeToCompare.getTimeStamp())) return 0;
		
		return 1;
	}
}
