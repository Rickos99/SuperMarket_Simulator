package store.event;

import simulator.Event;

public class StopEvent extends Event{

	
	public StopEvent(StoreState state, double stopTime){
		this.state = state;
		this.executeTime = stopTime;

		
		
	}
	/**
	 * 
	 * Only purpouse for this event is to stop other events from being added to the eventQue.
	 * Sets storeOpen to false so that
	 */
	@Override
	public void runEvent() {
		state.setStoreClosed();
		
	}

}
