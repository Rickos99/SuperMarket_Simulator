package store.event;

import simulator.Event;
import store.state.Customer;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class CheckOutEvent extends Event {
	
	private boolean isPeopleInQueue;
	private Customer customer;

	/**
	 * Constructor if there are available registers and the queue is empty.
	 * 
	 * @param state    current store state
	 * @param time     event execute time
	 * @param customer event for this customer
	 */
	public CheckOutEvent(StoreState state, double time, Customer customer) {
		super(state);
		super.eventDescription = "Checkout";
		super.eventUserDescription = customer.toString();
		super.executeTime = time;
		state.closeOneRegister();
		this.customer = customer;
		this.isPeopleInQueue = false;
	}

	/**
	 * 
	 * Constructor for if there are customers in the que that are waiting to pay.
	 * 
	 * 
	 * @param state current store state
	 * @param time  event execute time
	 */
	public CheckOutEvent(StoreState state, double time) {
		super(state);
		super.eventDescription = "Checkout";
		super.executeTime = time;
		state.closeOneRegister();
		// Gets the first customer in the queue and deletes it from the queue.
		this.customer = state.getFirstCustomerInCheckout();
		this.isPeopleInQueue = true;
	}

	@Override
	public void runEvent() {
		double newExecuteTime = state.getElapsedTime() + ((StoreState)state).getTimeNextCustomerCheckout();
		if (isPeopleInQueue) {
			// Open up a new register.
			((StoreState)state).openNewRegister();
			((StoreState)state).increaseCustomerPayedByOne();
			// Checks to see if there are anymore customers in the queue.
			if (!((StoreState)state).checkOutQueueIsEmpty()) {
				addEventToQueue(new CheckOutEvent((StoreState)state, newExecuteTime));
			}

		} else {
			
			((StoreState)state).openNewRegister();
			((StoreState)state).increaseCustomerPayedByOne();
			// No new event since there are no people in the queue.
		}

	}
}
