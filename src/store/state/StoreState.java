package store.state;

import simulator.Event;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StoreState extends simulator.SimState {

	private final int MAX_CUSTOMERS;
	private final int MAX_REGISTERS;
	private final double ARRIVAL_SPEED;
	private final double MIN_PICKING_TIME;
	private final double MAX_PICKING_TIME;
	private final double MIN_CHECKOUT_TIME;
	private final double MAX_CHECKOUT_TIME;

	private int customersPayed;
	private int customersInTotal;
	private int customersVisited;
	private int customersInQueue;
	private int customersDeniedEntry;
	
	private int registersOpen;

	private double queueTime;
	private double elapsedTime;
	private double checkoutFreeTime;
	
	private boolean storeIsClosed;

	private FIFO<Customer> checkoutQueue;
	private StoreTime storeTime;

	public SimState() {

	}

	public Customer customer(int id) {
		// TODO Implement customer(int id)
		throw new NotImplementedException();
	}
	
	public void createNewCustomer() {
		throw new NotImplementedException();
	}
	
	public void openNewRegister() {
		throw new NotImplementedException();
	}

	public void closeOneRegister() {
		throw new NotImplementedException();
	}
	
	public boolean storeIsOpen() {
		throw new NotImplementedException();
	}
	
	public void closeStore() {
		throw new NotImplementedException();
	}
	
	public void openStore() {
		throw new NotImplementedException();
	}
	
	public void increaseCustomerDeniedByOne() {
		throw new NotImplementedException();
	}
	
	public StoreTime getStoreTime() {
		throw new NotImplementedException();
	}
	
	public double getElapsedTime() {
		throw new NotImplementedException();
	}
	
	public FIFO getCheckoutQueue() {
		throw new NotImplementedException();
	}
	
	public int getMaxCustomers() {
		throw new NotImplementedException();
	}
	
	public int getCustomersInTotal() {
		throw new NotImplementedException();
	}

	@Override
	public void runSim() {
		// TODO Implement runSim()
	}

	private void executeEvent(Event e) {
		// TODO Implement executeEvent(Event e)
		throw new NotImplementedException();
	}

}
