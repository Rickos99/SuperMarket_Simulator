/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
package simulator;

import store.state.CreateCustomer;
import store.state.Customer;
import store.state.StoreState;

public abstract class Event {

	protected double executeTime;
	protected StoreState state;
	protected String eventDescription;
	protected EventQueue eventQueue;
	protected Customer customer;

	/**
	 * 
	 * 
	 * @return time when event is executed.
	 */

	public double getExTime() {
		return executeTime;
	}

	/**
	 * 
	 * @return customer id
	 */
	public Customer getCustomerID() {
		return customer;
	}

	/**
	 * 
	 * @return eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * Adds an event to the event queue for the specific state.
	 * 
	 * @param event adds this to the eventqueue
	 */
	public void addEventToQueue(Event event) {
		this.eventQueue.addEvent(event);
	}

	/**
	 * Abstract method which is designed for each event.
	 */
	public abstract void runEvent();

}
