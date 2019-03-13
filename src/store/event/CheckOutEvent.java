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
	}

	@Override
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.increaseCustomerPayedByOne();
		s.decreaseCustomersInStoreByOne();
		if(s.checkOutQueueIsEmpty()) {
			s.openNewRegister();
		} else {
			double checkoutTime = s.getElapsedTime() + s.getTimeNextCustomerCheckout();
			Customer nextCustomer = s.getFirstFromCheckoutQueue();
			eventQueue.addEvent(new CheckOutEvent(s, checkoutTime, nextCustomer));
		}
	}
}
