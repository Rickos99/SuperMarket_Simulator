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
		// Checks if there are available registers to pay in and if the que is
		// empty.
		double checkOutTime = ((StoreState) state).getElapsedTime()
				+ ((StoreState) state).getTimeNextCustomer();
		if (((StoreState) state).getRegistersOpen() > 0
				&& ((StoreState) state).checkOutQueueIsEmpty()) {
			// Adds a checkout event with no people in the queue and there
			// are Available registers.
			addEventToQueue(
					new CheckOutEvent((StoreState) state, checkOutTime, customer));
		} else {
			// Adds a checkout event where there are customers already in the queue.
			// and places the customer who wants to buy in the back of the FIFO queue.
			((StoreState) state).addCustomerInPayoutLine(customer);
			addEventToQueue(new CheckOutEvent((StoreState) state, checkOutTime));
		}
	}

}
