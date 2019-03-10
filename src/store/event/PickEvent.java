package store.event;

import simulator.Event;

public class PickEvent extends Event{
	
	private StoreState state;
	private CreateCustomer customer;
	
	public PickEvent(StoreState state, double time, CreateCustomer customer ){
		this.executeTime = time;
		this.state = state;
		this.customer = customer;

		
		
	}
	
	
	/**
	 * TODO: Change name from fifoQue to the name variabel of 
	 * the FIFO que object in state when it is created.
	 * 
	 * Göra en add till fifoque från state?
	 * Samma sak med storetime?? dvs metoder i state som returnerar storetime värden.
	 */
	
	@Override
	public void runEvent() {
		//Checks if there are avaliable registers to pay in and if the que is empty.
		double checkOutTime = state.getElapsedTime() + state.storeTime.timeCustomerCheckOut();
		if(state.registersOpen > 0 && state.checkOutQueue.isEmpty()){
			state.registersOpen--;
			addEventToQueue(new CheckOutEvent(state, checkOutTime, customer ));
		}else{
			state.checkOutQueue.add(customer);
			addEventToQueue(new CheckOutEvent(state, checkOutTime));
		}
		
	}

}
