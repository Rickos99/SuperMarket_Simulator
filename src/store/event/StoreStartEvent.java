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
public class StoreStartEvent extends StartEvent {


	public StoreStartEvent(StoreState state) {
		super(state);
		super.eventDescription = "Store opens";
		super.eventUserDescription = "-";
		this.executeTime = 0.0;

	}
	

	@Override
	public void runEvent() {
		((StoreState) state).openStore();
		double nextExecuteTime = state.getElapsedTime() + ((StoreState) state).getTimeNextCustomer();
		addEventToQueue(new CustomerArrivedEvent((StoreState) state, nextExecuteTime));
	}

}
