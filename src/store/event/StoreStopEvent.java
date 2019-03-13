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
public class StoreStopEvent extends StopEvent {

	public String eventDescription = "Store closed";

	public StoreStopEvent(StoreState state, double stopTime) {
		super(state, stopTime);
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
