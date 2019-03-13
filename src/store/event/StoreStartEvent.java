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
		super.eventDescription = "Open";
		super.eventUserDescription = "-";
		super.executeTime = 0.0;
	}
	
	@Override
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.updateState(this);
		s.openStore();
		
		double nextExecuteTime = state.getElapsedTime() + s.getTimeNextCustomer();
		eventQueue.addEvent(new CustomerArrivedEvent(s, nextExecuteTime));
	}

}
