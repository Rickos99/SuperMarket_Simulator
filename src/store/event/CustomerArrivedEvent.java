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
		//System.out.println("CUSTOMER RUN"+this.customer);
		double newTimeCustomer = ((StoreState)state).getElapsedTime() + ((StoreState)state).getTimeNextCustomer();
		if (((StoreState)state).storeIsOpen()) {

			//double newTimeCustomer = ((StoreState)state).getElapsedTime() + ((StoreState)state).getTimeNextCustomer();
			if (((StoreState)state).getCustomersInStore() < ((StoreState)state).getMAX_CUSTOMERS()) {
				((StoreState)state).increaseCustomerVisitedByOne();
				double newPickTime = state.getElapsedTime() + ((StoreState)state).getTimeCustomerPick();
				addEventToQueue(new PickEvent((StoreState)state, newPickTime, customer));
			} else {
				((StoreState)state).increaseCustomerDeniedByOne();
			}
			addEventToQueue(new CustomerArrivedEvent((StoreState)state, newTimeCustomer));
		}
	}
}
