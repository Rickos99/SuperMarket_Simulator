package simulator;

import store.state.Customer;
import store.state.StoreState;

/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public abstract class Event {

	protected double executeTime;
	protected SimState state;
	protected String eventDescription;
	protected EventQueue eventQueue;
	protected Customer customer;

	public Event(SimState state) {
		this.state = state;
		this.eventQueue = state.getEventQueue();
	}

	/**
	 * Get time for event to execute.
	 * 
	 * @return time for event to execute
	 */
	public double getExTime() {
		return executeTime;
	}

	/**
	 * Get costumer id.
	 * 
	 * @return customer id
	 */
	public Customer getCustomerID() {
		return customer;
	}

	/**
	 * Get event description.
	 * 
	 * @return event description
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * Adds an event to the event queue for the specific state.
	 * 
	 * @param event adds this to the event queue
	 */
	public void addEventToQueue(Event event) {
		this.eventQueue.addEvent(event);
	}

	/**
	 * Abstract method which is designed for each event.
	 */
	public abstract void runEvent();

}
