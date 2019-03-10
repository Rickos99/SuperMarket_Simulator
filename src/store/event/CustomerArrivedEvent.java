package store.event;

import simulator.Event;

public class CustomerArrivedEvent extends Event {

	private StoreState state;
	private CreateCustomer newCustomer;

	public CustomerArrivedEvent(StoreState state, double time) {
		this.state = state;
		this.executeTime = time;
		this.newCustomer = state.createNewCustomer();


	}
	
	/**
	 * TODO: add customer id to every single event.
	 * 
	 * 
	 */
	@Override
	public void runEvent() {
		/**
		 * 
		 * state.storeTime.timeNextCustomer() blir storetime objektet som vi
		 * skapar i storestate. state.customersInTotal blir antalet kunder i
		 * affären.
		 * 
		 * state.getStoreOpen checks if the store is opened or closed. Only need to chekc if the store is oppened here
		 * since if the store is opened other events can still occour.
		 */
		if (state.getStoreOpen()) {

			double newTimeCustomer = state.getElapsedTime() + state.storeTime.timeNextCustomer();
			if (state.getCustomersInTotal() >= state.getMaxCustomers()) {
				state.customersDeniedEntry++;
				addEventToQueue(new CustomerArrivedEvent(state, newTimeCustomer));
			} else {
				double newPickTime = state.getElapsedTime() + state.storeTime.timeCustomerPick();
				addEventToQueue(new CustomerArrivedEvent(state, newTimeCustomer));
				addEventToQueue(new PickEvent(state, newPickTime, newCustomer));
			}
		}
	}

}
