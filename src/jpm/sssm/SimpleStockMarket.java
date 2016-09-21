package jpm.sssm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

import jpm.sssm.data.NonPersistentMockDB;
import jpm.sssm.data.interfaces.StockDataDAO;
import jpm.sssm.services.BasicServiceLocator;
import jpm.sssm.services.*;
import jpm.sssm.services.interfaces.ServiceLocator;

public class SimpleStockMarket {

	static StockDataDAO 	StockDataDB;
	static ServiceLocator 	calculationServiceLocator;
	
	static String SESSION_SELECTED_MARKET = null;
	
	static {
		StockDataDB 				= new NonPersistentMockDB();
		calculationServiceLocator	= new BasicServiceLocator(
				new CalculationServiceDivYield(), new CalculationServicePERatio(),
				new CalculationServiceWeightedStockPriceLast15Mins(), new CalculationServiceASI() );
	}
	

	public static void main(String[] args) {
		
		selectMarket();
		
		String menuSelection;
		do{
				System.out.println("Select Option (Current Market: "+SESSION_SELECTED_MARKET+")");
				System.out.println("1:	Select Market");
				System.out.println("2:	Calculation");
				System.out.println("3:	Load Trades");
				System.out.println("4:	Exit");
			
				menuSelection = getLine();
			
				switch(menuSelection){
				case "1": selectMarket();break;
				case "2": selectCalculation(); break;
				case "3": recordTrade();break;
				case "4": break;
				default:
					System.out.println("Unknown option");
				}
		}while(!menuSelection.equals("4"));
	}

	
	private static void selectMarket(){
		
		List<String> marketList = StockDataDB.getMarkets();
		
		System.out.println("Markets:");
		for(String market : marketList){
			System.out.println(market);
		}
		
		String selectedMarket;
			do{
				System.out.println("Enter Market:");
				selectedMarket = getLine();
				if(marketList.contains(selectedMarket)){
					SESSION_SELECTED_MARKET = selectedMarket;
				}
				
			} while(SESSION_SELECTED_MARKET == null);
	}

	
	private static void selectCalculation(){
		
		List<String> marketOperations= calculationServiceLocator.getRegisteredServices();
		
		System.out.println("Operations:");
		for(String operations : marketOperations){
			System.out.println(operations);
		}
		
		String selectedOperation;

		System.out.println("Enter Operation (Exact Name)");
		selectedOperation =  getLine();
		if(marketOperations.contains(selectedOperation)){
			System.out.println("Enter Stock Symbol (if applicable - enter if not):");
			String stockSymbol = getLine();
			System.out.println("Enter Price (if applicable):");
			
			int price;
			try {
				price = Integer.parseInt(getLine());
			} catch (NumberFormatException e) {
				price=0;
			}
			
			System.out.println(selectedOperation+": "+stockSymbol+","+price+", result: "+ calculationServiceLocator.getService(selectedOperation)
				.performCalculation(StockDataDB,SESSION_SELECTED_MARKET,stockSymbol, price));
			
		} else {
			System.out.println("Unkown Operation");
		}
	}
	
	private static String getLine(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			return br.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	
	private static void recordTrade(){
		
		System.out.println("To Save time, Hardcoded trades loaded. Feel free to modify SimpleStockMarket.java : recordTrades()");
		
		//Load Data as if from GUI
		StockDataDB.recordTrade("GBCE", "TEA", LocalDateTime.now().minusMinutes(18L), 12308, true, 4000);
		StockDataDB.recordTrade("GBCE", "TEA", LocalDateTime.now().minusMinutes(5L) , 123, true, 40);
		StockDataDB.recordTrade("GBCE", "TEA", LocalDateTime.now().minusMinutes(1L), 47,  true, 41);
		StockDataDB.recordTrade("GBCE", "TEA", LocalDateTime.now() , 77,  true, 41);
		StockDataDB.recordTrade("GBCE", "POP", LocalDateTime.now().minusMinutes(7L), 12308, true, 104);
		StockDataDB.recordTrade("GBCE", "POP", LocalDateTime.now().minusMinutes(5L) , 984, true, 105);
		StockDataDB.recordTrade("GBCE", "POP", LocalDateTime.now().minusMinutes(1L), 478,  true, 103);
		StockDataDB.recordTrade("GBCE", "ALE", LocalDateTime.now(), 10000,  true, 4);
	}
	
}
