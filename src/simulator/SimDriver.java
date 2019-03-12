package simulator;

import store.state.StoreState;

public class SimDriver {
	
	private StoreState state;
	private EventQueue queue;
	
	public SimDriver(long TIME_SEED, int MAX_CUSTOMERS, int MAX_REGISTERS, int TIME_STORE_CLOSE, 
					 double ARRIVAL_SPEED, double MIN_PICKING_TIME, double MAX_PICKING_TIME, 
					 double MIN_CHECKOUT_TIME,double MAX_CHECKOUT_TIME, double TIME_LAMBDA) {

		this.state = new StoreState(TIME_SEED, MAX_CUSTOMERS, MAX_REGISTERS, 
									TIME_STORE_CLOSE, ARRIVAL_SPEED, MIN_PICKING_TIME, MAX_PICKING_TIME
									,MIN_CHECKOUT_TIME, MAX_CHECKOUT_TIME, TIME_LAMBDA);
		this.queue = new EventQueue(state);
		
		
		
		
	}
	
	public void run() {
		state.runSim();
		while(simulatorIsRunning()) {
			queue.getEvent().runEvent();
			
			
		}
		
		
		
	}
	
	
}
