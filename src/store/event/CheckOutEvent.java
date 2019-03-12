/**
 * @author Nour Aldein Bahtite
 */
package store.event;

import simulator.Event;
import store.state.CreateCustomer;
import store.state.Customer;
import store.state.StoreState;

public class CheckOutEvent extends Event {

	private String eventDescription = "Customer paying for products";
	private boolean isPeopleInQueue;

	/**
	 * Constructor if there are avaliable registers and the que is empty.
	 * 
	 * @param state
	 *            current store state
	 * @param time
	 *            event execute time
	 * @param customer
	 *            event for this customer
	 */
	public CheckOutEvent(StoreState state, double time, Customer customer) {
		this.state = state;
		this.state.closeOneRegister();
		this.executeTime = time;
		this.customer = customer;
		this.isPeopleInQueue = false;

	}

	/**
	 * 
	 * Constructor for if there are customers in the que that are waiting to
	 * pay.
	 * 
	 * 
	 * @param state
	 *            current store state
	 * @param time
	 *            event execute time
	 */
	public CheckOutEvent(StoreState state, double time) {
		this.state = state;
		this.state.closeOneRegister();
		this.executeTime = time;
		// Gets the first custommer in the queue and deletes it from the queue.
		this.customer = state.getFirst();
		this.isPeopleInQueue = true;

	}

	@Override
	public void runEvent() {
		double newExecuteTime = state.getElapsedTime() + state.getTimeNextCustomerCheckout();
		if (isPeopleInQueue) {
			// Open upp a new register.
			state.openNewRegister();
			// Checks to see if there are anymore customers in the queue.
			if (!state.getCheckOutQueueIsEmpty()) {
				addEventToQueue(new CheckOutEvent(state, newExecuteTime));

			}

		} else {
			state.openNewRegister();
			// No new event since there are no people in the queue.
		}

	}
}
