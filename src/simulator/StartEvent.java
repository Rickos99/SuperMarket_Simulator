package simulator;

import store.event.StoreCloseEvent;
import store.state.StoreState;

public abstract class StartEvent extends Event {

	public StartEvent(SimState state) {
		super(state);
		this.executeTime = 0;
		
		
	}

	public abstract void runEvent();
}
