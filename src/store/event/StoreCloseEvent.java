package store.event;

import simulator.Event;
import simulator.StopEvent;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class StoreCloseEvent extends Event {

	public String eventDescription = "Store closed";

	public StoreCloseEvent(StoreState state, double executeTime) {
		super(state);
		this.executeTime = executeTime;

	}

	/**
	 * Only purpose for this event is to stop other events from being added to the
	 * eventQue. Sets storeOpen to false so that
	 */
	@Override
	public void runEvent() {
		((StoreState) state).closeStore();
	}

}
