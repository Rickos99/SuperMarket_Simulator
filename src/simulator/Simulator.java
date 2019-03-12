package simulator;

/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public class Simulator {

	private SimState state;
	private EventQueue queue;

	/**
	 * Create a new instance of Simulator
	 * 
	 * @param state A simulator state
	 */
	public Simulator(SimState state, Event startEvent) {
		this.state = state;
		this.queue = state.getEventQueue();
		state.getEventQueue().addEvent(startEvent);
	}

	/**
	 * Run simulator
	 */
	public void run() {
		state.runSim();

		while (!queue.getEventQueueIsEmpty()) {
			queue.getEvent().runEvent();
		}
	}

}