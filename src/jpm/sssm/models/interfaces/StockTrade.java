package jpm.sssm.models.interfaces;

import java.time.LocalDateTime;

public interface StockTrade extends Comparable<StockTrade> {
	String getTicker();
	String getMarket();
	LocalDateTime getTimeStamp();
	long getQuantity();
	boolean isBuy();
	int getPrice();
}
