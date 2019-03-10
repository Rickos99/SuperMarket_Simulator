package store.event;

import simulator.Event;

public class StartEvent extends Event {
	
	private final double stopExecuteTime;

	
	public StartEvent(StoreState state, double stopTime){
		this.state = state;
		this.executeTime = 0.0;
		//Stoptime for the simulator.
		this.stopExecuteTime = stopTime;

		
		
	}

	@Override
	public void runEvent() {
		double nextExecuteTime = state.getElapsedTime() + state.storeTime.timeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent(state, nextExecuteTime));
		addEventToQueue(new StopEvent(state,stopExecuteTime));
		
	}
	
	

	
	
	
}
