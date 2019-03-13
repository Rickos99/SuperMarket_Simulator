package store.event;

import simulator.Event;
import store.state.StoreState;

public class StoreCloseEvent extends Event {
	
public StoreCloseEvent(StoreState state, double time) {
	super(state);
	this.executeTime = time;
	}
public void runEvent() {
	((StoreState) state).closeStore();
	}
}
