package store.event;

import simulator.Event;
import store.state.CreateCustomer;
import store.state.StoreState;

public class PickEvent extends Event {


	public String eventDescription = "Costumer picking products";

	public PickEvent(StoreState state, double time, CreateCustomer customer) {
		this.executeTime = time;
		this.state = state;
		this.customer = customer;

	}

	/**
	 *
	 * 
	 * 
	 *
	 */

	@Override
	public void runEvent() {
		// Checks if there are avaliable registers to pay in and if the que is
		// empty.
		try {
		double checkOutTime = state.getElapsedTime() + state.storeTime.timeCustomerCheckOut();
		if (state.registersOpen > 0 && state.checkOutQueue.isEmpty()) {
			// Adds a checkout event with no people in the queue and there are
			// avaliable registers.
			addEventToQueue(new CheckOutEvent(state, checkOutTime, customer));
		} else {
			// Adds a checkout event where there are customers alrdy in the
			// queue.
			// and places the customer who wants to buy in the back of the FIFO
			// queue.
			state.checkOutQueue.add(customer);
			addEventToQueue(new CheckOutEvent(state, checkOutTime));
		}
		}
		catch (IOException e) {System.out.println("Ojj, nu vare något galet.");}
	}



}
