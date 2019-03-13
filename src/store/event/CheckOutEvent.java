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
		this.customer = customer;
		
	}



	@Override
	public void runEvent() {
		//double newExecuteTime = state.getElapsedTime() + ((StoreState)state).getTimeNextCustomerCheckout();
		if(((StoreState)state).getCheckOutQueue().isEmpty()) {
			((StoreState)state).increaseCustomerPayedByOne();
			((StoreState)state).openNewRegister();
		}else {
			eventQueue.addEvent(new CheckOutEvent(((StoreState)state),
					((StoreState)state).getElapsedTime(),
					((StoreState)state).getFirstCustomerInCheckout()));
		}
	}
}
