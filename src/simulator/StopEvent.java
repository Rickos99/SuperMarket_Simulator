package simulator;

public class StopEvent extends Event {

	public StopEvent(SimState state, double executeTime) {
		super(state);
		super.eventDescription = "Stop";
		super.eventUserDescription = "-";
		this.executeTime = executeTime;
	}

	public void runEvent() {
		state.stopSimulator();
	}


}
