package simulator;

import store.state.StoreState;

public class StopEvent extends Event {

	public StopEvent(SimState state, double executeTime) {
		super(state);
		this.executeTime = executeTime;
	}

	public void runEvent() {
		state.stopSimulator();
		
	}

}
