package store.event;

import simulator.StartEvent;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class StoreOpenEvent extends StartEvent {

	public String eventDescription = "Store opens";

	public StoreOpenEvent(StoreState state) {
		super(state);
		this.executeTime = 0.0;


	}

	@Override
	public void runEvent() {
		((StoreState) state).openStore();
		double nextExecuteTime = state.getElapsedTime() + ((StoreState) state).getTimeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent((StoreState) state, nextExecuteTime));
	}

}
