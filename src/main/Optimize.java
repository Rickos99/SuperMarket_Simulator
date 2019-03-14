package main;

import java.util.Random;
import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;

public class Optimize {
	long TIME_SEED = 1234; // Seed to generate random number
	int MAX_CUSTOMERS = 5; // Maximum number of costumers allowed in store at once
	int MAX_REGISTERS = 2; // Maximum number of registers available in store
	int TIME_STORE_CLOSE = 10; // At what time store closes
	double ARRIVAL_SPEED = 1.0d; // Speed of which costumers arrive at
	double MIN_PICKING_TIME = 0.5d; // Minimum time a costumer can pick items in
	double MAX_PICKING_TIME = 1d; // Maximum time a costumer can pick items in
	double MIN_CHECKOUT_TIME = 2d; // Minimum time a costumer can checkout in
	double MAX_CHECKOUT_TIME = 3d; // Time a costumer can checkout in

	EventQueue eventQueue = new EventQueue();
	StoreState state = new StoreState(TIME_SEED, MAX_CUSTOMERS, MAX_REGISTERS,
			TIME_STORE_CLOSE, ARRIVAL_SPEED, MIN_PICKING_TIME, MAX_PICKING_TIME,
			MIN_CHECKOUT_TIME, MAX_CHECKOUT_TIME, eventQueue);
	
	private void printResults(StoreState state) {
		String newLine = "\r\n";
		String result = "Results"+newLine+"=====\n";
		
		result += MessageFormat.format("1) Av {0} kunder handlade {1} medan {2} missades \n",
				state.getCustomersVisited(), state.getCustomersPayed(), state.getCustomersDeniedEntry());
		
		result += MessageFormat.format("2) Total tid {0} kassor varit lediga: {1} te. \n", state.getMAX_REGISTERS(),
				cutDecimals(state.getCheckOutFreeTime()));
				
				
		result += MessageFormat.format(
				"	 Genomsnittlig ledig kassatid: {0} te (dvs {1}% av tiden från öppning tills sista kunden betalat). \n",
				cutDecimals(state.getCheckOutFreeTime()/state.getMAX_REGISTERS()), cutDecimals(state.getCheckOutFreeTime()/state.getMAX_REGISTERS()/state.getSpecElapsedTime()*100));

		result += MessageFormat.format("3) Total tid {0} kunder tvingats köa: {1} te. \n", state.getCustomersInQueueTotal(),
				cutDecimals(state.getQueueTime()));
		
		result += MessageFormat.format("	Genomsnittlig kötid: {0} te. \n", cutDecimals(state.getQueueTime()/state.getCustomersInQueueTotal()));
		System.out.println(result);
	}
	
	private String cutDecimals(double d) {
		return new DecimalFormat("#.##").format(d);
	}
	
	
	
	
	private StoreState metod1(StoreState state) {
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, TIME_STORE_CLOSE));
		eventQueue.addEvent(new StopEvent(state, 999));
		new Simulator(state, eventQueue).run();
		return state;
	}

	private StoreState metod2(StoreState state) {
		while (state.getCustomersDeniedEntry() > 0) {
			MAX_REGISTERS++;
			metod1(state);
		}
		return state;
	}

	public void metod3(long seed) {
		Random random = new Random(seed);
		double dN;

		do {
			
		} while (dN > 1);
	}

}
