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
public class CustomerArrivedEvent extends Event {
	
	private Customer customer;
	
	public CustomerArrivedEvent(StoreState state, double time) {
		super(state);
		super.eventDescription = "Arrived";
		this.customer = state.createNewCustomer();
		super.eventUserDescription = customer.toString();
		super.executeTime = time;

	}

	/**
	 * state.storeTime.timeNextCustomer() blir storetime objektet som vi
	 * skapar i storestate. state.customersInTotal blir antalet kunder i
	 * affären.
	 * 
	 * state.getStoreOpen checks if the store is opened or closed. Only need
	 * to chekc if the store is oppened here since if the store is opened
	 * other events can still occour.
	 * 
	 * TODO: add customer id to every single event.
	 */
	@Override
	public void runEvent() {
		StoreState s = ((StoreState)state);
		
		if (s.storeIsOpen()) {
			s.increaseCustomerVisitedByOne();
			if(s.getCustomersInStore() >= s.getMAX_CUSTOMERS()) {
				s.increaseCustomerDeniedByOne();
			} else {
				s.increaseCustomersInStoreByOne();
				double pickTime = state.getElapsedTime() + s.getTimeCustomerPick();
				eventQueue.addEvent(new PickEvent(s, pickTime, customer));
			}
			s.updateState(this);
			double newTimeCustomer = s.getElapsedTime() + s.getTimeNextCustomer();
			eventQueue.addEvent(new CustomerArrivedEvent(s, newTimeCustomer));
		}
		
			
	}
}
