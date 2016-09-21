package jpm.sssm.models;

import jpm.sssm.models.interfaces.StockDetails;

public class SpecificStockInformation implements StockDetails{

	private String ticker;
	private String market;
	private int lastDividend;
	private int parValue;
	private double fixedInterestPct;
	private StockType StockTypeIndicator;
	
	
	public SpecificStockInformation(String ticker, String market,StockType StockTypeIndicator,int lastDividend,double fixedInterestPct,int parValue)
	{
		this.ticker 			= ticker;
		this.market 			= market;
		this.lastDividend 		= lastDividend;
		this.parValue			= parValue;
		this.fixedInterestPct	= fixedInterestPct;
		this.StockTypeIndicator = StockTypeIndicator;
	}
	
	
	public String 		getTicker(){return ticker;}		
	public String	 	getMarket(){return market;}
	public int    		getLastDividend(){return lastDividend;}
	public int    		getParValue(){return parValue;}
	public double 		getFixedInterestPctValue(){return fixedInterestPct;}
	public StockType 	getStockType() {return StockTypeIndicator;}
	
}
