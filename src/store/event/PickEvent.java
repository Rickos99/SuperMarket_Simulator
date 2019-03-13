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
public class PickEvent extends Event {

	private Customer customer;

	public PickEvent(StoreState state, double time, Customer customer) {
		super(state);
		super.eventDescription = "Pick";
		super.eventUserDescription = customer.toString();
		super.executeTime = time;
		this.customer = customer;
	}

	@Override
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.updateState(this);
		double checkOutTime = (s.getElapsedTime() + s.getTimeNextCustomerCheckout());
		if (s.getCustomersInQueue() == 0) {
			s.closeOneRegister();
			eventQueue.addEvent(new CheckOutEvent(s, checkOutTime, customer));
		} else {
			s.getFirstFromCheckoutQueue().runEvent();
			s.addToCheckoutQueue(new CheckOutEvent(s, checkOutTime, customer));
		}
	}
}
