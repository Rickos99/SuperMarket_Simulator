package simulator;


import store.state.StoreState;

public abstract class StartEvent extends Event {
	
	/**
	 * 
	 * @param state
	 */
	public StartEvent(SimState state) {
		super(state);
		super.eventDescription = "Simulator starting";
		super.eventUserDescription = "-";
		this.executeTime = 0;
		
		
	}

	public abstract void runEvent();
}
