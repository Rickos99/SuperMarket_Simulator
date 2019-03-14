package main;
import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;
import store.view.StoreView;;

public class MainDriver {

	public static void main(String[] args) {
		
		//Ex 1
		
		long TIME_SEED = 1234; 			// Seed to generate random number
		int MAX_CUSTOMERS = 5; 			// Maximum number of costumers allowed in store at once
		int MAX_REGISTERS = 2; 			// Maximum number of registers available in store
		int TIME_STORE_CLOSE = 10; 		// At what time store closes
		double ARRIVAL_SPEED = 1.0d; 	// Speed of which costumers arrive at
		double MIN_PICKING_TIME = 0.5d; 	// Minimum time a costumer can pick items in
		double MAX_PICKING_TIME = 1d; 	// Maximum time a costumer can pick items in
		double MIN_CHECKOUT_TIME = 2d; 	// Minimum time a costumer can checkout in
		double MAX_CHECKOUT_TIME = 3d; 	// Time a costumer can checkout in

		// Create instances of various objects
		StoreView view = new StoreView();
		EventQueue eventQueue = new EventQueue();
		StoreState state = new StoreState(TIME_SEED, MAX_CUSTOMERS, MAX_REGISTERS, TIME_STORE_CLOSE,
										  ARRIVAL_SPEED, MIN_PICKING_TIME, MAX_PICKING_TIME, MIN_CHECKOUT_TIME, 
										  MAX_CHECKOUT_TIME, eventQueue);
		
		// Create and add events
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, TIME_STORE_CLOSE));
		eventQueue.addEvent(new StopEvent(state, 999));
		
		state.addObserver(view);
		
		// Run simulator
		new Simulator(state, eventQueue).run();
		
		
		//Ex x
		

		long TIME_SEED_X = 13; 			
		int MAX_CUSTOMERS_X = 7; 			
		int MAX_REGISTERS_X = 2; 			
		int TIME_STORE_CLOSE_X = 8; 		
		double ARRIVAL_SPEED_X = 3.0d; 	
		double MIN_PICKING_TIME_X = 0.6d; 	
		double MAX_PICKING_TIME_X = 0.9d; 	
		double MIN_CHECKOUT_TIME_X = 0.35d; 	
		double MAX_CHECKOUT_TIME_X = 0.6d; 	
		
		
		
		
		StoreView viewX = new StoreView();
		EventQueue eventQueueX = new EventQueue();
		StoreState stateX = new StoreState(TIME_SEED_X, MAX_CUSTOMERS_X, MAX_REGISTERS_X, TIME_STORE_CLOSE_X,
				  ARRIVAL_SPEED_X, MIN_PICKING_TIME_X, MAX_PICKING_TIME_X, MIN_CHECKOUT_TIME_X, 
				  MAX_CHECKOUT_TIME_X, eventQueueX);
		
		eventQueueX.addEvent(new StoreStartEvent(stateX));
		eventQueueX.addEvent(new StoreCloseEvent(stateX, TIME_STORE_CLOSE_X));
		eventQueueX.addEvent(new StopEvent(stateX, 999));
		
		stateX.addObserver(viewX);

//		new Simulator(stateX, eventQueueX).run();
	
	}

}
