package simulator;

import store.event.StoreStopEvent;
import store.state.StoreState;

public class StartEvent extends Event{
	
	protected double stopEventExecuteTime;

	
	public StartEvent(SimState state, double stopEventExecuteTime) {
		super(state);
		this.stopEventExecuteTime = stopEventExecuteTime;
		addEventToQueue(this);
	}


	public void runEvent() {
		addEventToQueue(new StopEvent(state, stopEventExecuteTime));
		
	}

}
