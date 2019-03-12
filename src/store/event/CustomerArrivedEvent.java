/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

package store.event;

import simulator.Event;
import store.state.StoreState;

public class CustomerArrivedEvent extends Event {

	private String eventDescription = "Customer Arrived";

	public CustomerArrivedEvent(StoreState state, double time) {
		this.state = state;
		this.executeTime = time;
		this.customer = state.createNewCustomer();

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
		 * aff�ren.
		 * 
		 * state.getStoreOpen checks if the store is opened or closed. Only need
		 * to chekc if the store is oppened here since if the store is opened
		 * other events can still occour.
		 */
		if (state.storeIsOpen()) {

			double newTimeCustomer = state.getElapsedTime() + state.getTimeNextCustomer();
			if (state.getCustomersInTotal() >= state.getMaxCustomers()) {
				state.increaseCustomerDeniedByOne();
				addEventToQueue(new CustomerArrivedEvent(state, newTimeCustomer));
			} else {
				double newPickTime = state.getElapsedTime() + state.getTimeCustomerPick();
				addEventToQueue(new CustomerArrivedEvent(state, newTimeCustomer));
				addEventToQueue(new PickEvent(state, newPickTime, customer));
			}
		}
	}

}
