package simulator;

import store.event.StoreStopEvent;
import store.state.StoreState;

public abstract class StartEvent extends Event{
	

	
	public StartEvent(SimState state) {
		super(state);
		this.executeTime = 0;
		addEventToQueue(this);
	}


	public abstract void runEvent();
}
