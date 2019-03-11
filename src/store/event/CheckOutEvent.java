package store.event;

import simulator.Event;

public class CheckOutEvent extends Event {


	private String eventDescription = "Customer paying for products";
	private boolean peopleInQueue;

	/**
	 * Constructor if there are avaliable registers and the que is empty.
	 * 
	 * @param state
	 *            current store state
	 * @param time
	 *            event execute time
	 * @param customer
	 *            event for this customer
	 */
	public CheckOutEvent(StoreState state, double time, CreateCustomer customer) {
		this.state = state;
		this.state.registersOpen--;
		this.executeTime = time;
		this.customer = customer;
		this.peopleInQueue = false;
		

	}

	/**
	 * 
	 * Constructor for if there are customers in the que that are waiting to
	 * pay.
	 * 
	 * 
	 * @param state
	 *            current store state
	 * @param time
	 *            event execute time
	 */
	public CheckOutEvent(StoreState state, double time) {
		this.state = state;
		//:S:S:S:SS::
		this.state.registersOpen--;
		this.executeTime = time;
		// Gets the first custommer in the queue.
		this.customer = state.checkOutQueue.getFirst();
		this.peopleInQueue = true;

	}

	@Override
	public void runEvent() {
		double newExecuteTime = state.getTimeElapsed() + state.storeTime.timeCustomerCheckOut();
		if (peopleInQueue) {
			//Removes the first person in the queue
			state.checkOutQueue.removeFirst();
			//Open upp a new register.
			state.registersOpen++;
			//Checks to see if there are anymore customers in the queue.
			if (!state.checkOutQueue.isEmpty()) {
				addEventToQueue(state, newExecuteTime);

			}


		}else{
			state.registersOpen++;
			//No new event since there are no people in the queue.
		}
	}


}
