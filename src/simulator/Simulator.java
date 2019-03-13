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
	public Simulator(SimState state, Event startEvent, Event stopEvent, Event storeCloseEvent) {
		this.state = state;
		this.queue = state.getEventQueue();
		queue.addEvent(startEvent);
		queue.addEvent(stopEvent);
		queue.addEvent(storeCloseEvent);
	}

	/**
	 * Run simulator
	 */
	public void run(){
		state.runSim();

		while (state.simulatorIsRunning) {
			System.out.println(queue.getQueueSize());
			queue.getEvent().runEvent();
			state.updateTimeElapsed(queue.getEvent());
			queue.removeEvent();
		}
	}

}
