package store.event;

import simulator.Event;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class StartEvent extends Event {

	private final double stopExecuteTime;
	public String eventDescription = "Store opens";

	public StartEvent(StoreState state, double stopExecuteTime) {
		super(state);
		this.executeTime = 0.0;
		this.stopExecuteTime = stopExecuteTime;
		addEventToQueue(this);
	}

	@Override
	public void runEvent() {
		double nextExecuteTime = state.getElapsedTime() + ((StoreState)state).getTimeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent((StoreState)state, nextExecuteTime));
		addEventToQueue(new StopEvent((StoreState)state, stopExecuteTime));
	}

}
