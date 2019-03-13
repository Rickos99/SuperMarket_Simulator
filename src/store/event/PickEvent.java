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
		double checkOutTime = ((StoreState) state).getElapsedTime()
				+ ((StoreState) state).getTimeNextCustomerCheckout();
		if(((StoreState) state).getRegistersOpen() > 0) {
			((StoreState)state).closeOneRegister();
			eventQueue.addEvent(new CheckOutEvent((StoreState) state, checkOutTime, customer));
		}else {
			((StoreState) state).addCustomerInPayoutLine(customer);
		}
	}

}
