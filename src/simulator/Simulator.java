package simulator;

public class Simulator {

	private SimState state;
	private EventQueue queue;

	/**
	 * 
	 * @param TIME_SEED
	 * @param MAX_CUSTOMERS
	 * @param MAX_REGISTERS
	 * @param TIME_STORE_CLOSE
	 * @param ARRIVAL_SPEED
	 * @param MIN_PICKING_TIME
	 * @param MAX_PICKING_TIME
	 * @param MIN_CHECKOUT_TIME
	 * @param MAX_CHECKOUT_TIME
	 * @param TIME_LAMBDA
	 */
	public Simulator(SimState state, EventQueue eventQueue) {
		this.state = state;
		this.queue = eventQueue;
	}

	public void run() {
		state.runSim();

		while (!queue.getEventQueueIsEmpty()) {
			queue.getEvent().runEvent();
		}
	}

}
