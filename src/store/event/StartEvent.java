package store.event;

import simulator.Event;
import store.state.StoreState;

public class StartEvent extends Event {

	private final double stopExecuteTime;
	public String eventDescription = "Store opens";

	public StartEvent(StoreState state, double stopExecuteTime) {
		this.state = state;
		this.executeTime = 0.0;
		this.stopExecuteTime = stopExecuteTime;
		addEventToQueue(this);

	}

	@Override
	public void runEvent() {
		double nextExecuteTime = state.getElapsedTime() + state.getTimeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent(state, nextExecuteTime));
		addEventToQueue(new StopEvent(state, stopExecuteTime));

	}

}
