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
public class StopEvent extends Event {

	public String eventDescription = "Store closed";

	public StopEvent(StoreState state, double stopTime) {
		super(state);
		this.executeTime = stopTime;
	}

	/**
	 * Only purpose for this event is to stop other events from being added to
	 * the eventQue. Sets storeOpen to false so that
	 */
	@Override
	public void runEvent() {
		((StoreState)state).closeStore();
	}

}
