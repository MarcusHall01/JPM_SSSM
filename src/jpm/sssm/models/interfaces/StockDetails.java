package jpm.sssm.models.interfaces;

public interface StockDetails {
	
	enum StockType{
		Common,
		Preferred
	};
	
	String 		getTicker();	
	String 		getMarket();
	int    		getLastDividend();
	int    		getParValue();
	double 		getFixedInterestPctValue();
	StockType 	getStockType();
}
