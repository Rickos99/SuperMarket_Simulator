package store.event;

import simulator.Event;

public class StartEvent extends Event {

	private final double stopExecuteTime;
	public String eventDescription = "Store opens";

	public StartEvent(StoreState state, double stopTime) {
		this.state = state;
		this.executeTime = 0.0;
		this.customer = "-";
		this.stopExecuteTime = stopTime;

	}

	@Override
	public void runEvent() {
		double nextExecuteTime = state.getElapsedTime() + state.storeTime.timeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent(state, nextExecuteTime));
		addEventToQueue(new StopEvent(state, stopExecuteTime));

	}


}
