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
	
	private boolean storeIsOpen;

	private FIFO<Customer> checkoutQueue;
	private StoreTime storeTime;
	private CreateCustomer customerSpawn;

	public StoreState(int max_customers, int max_registers) {
		// TODO: Initialize all parameters in this state
		storeTime = new StoreTime();
		checkoutQueue = new FIFO<Customer>();
		customerSpawn = new CreateCustomer();
		this.MAX_CUSTOMERS = max_customers;
		this.MAX_REGISTERS = max_registers;
	}

	public Customer customer(int id) {
		// TODO Implement customer(int id)
		throw new NotImplementedException();
	}
	
	public Customer createNewCustomer() {
		return customerSpawn.newCustomer();
	}
	
	public void openNewRegister() {
		if(registersOpen < MAX_REGISTERS) {
			registersOpen++;
		} else {
			// TODO: throw new OpenRegisterFailedException()
		}
	}

	public void closeOneRegister() {
		if(registersOpen > 0) {
			registersOpen--;
		} else {
			// TODO: throw new CloseRegisterFailedException()
		}
	}
	
	public boolean storeIsOpen() {
		return storeIsOpen;
	}
	
	public void closeStore() {
		storeIsOpen = false;
	}
	
	public void openStore() {
		storeIsOpen = true;
	}
	
	public void increaseCustomerDeniedByOne() {
		customersDeniedEntry++;
	}
	
	public StoreTime getStoreTime() {
		//TODO
	}
	
	public double getElapsedTime() {
		return elapsedTime;
	}
	
	public FIFO<Customer> getCheckoutQueue() {
		return checkoutQueue;
	}
	
	public int getMaxCustomers() {
		return MAX_CUSTOMERS;
	}
	
	public int getCustomersInTotal() {
		return customersInTotal;
	}
	
	public Customer getFirst() {
		checkoutQueue.getFirst();
	}

	@Override
	public void runSim() {
		startSimulator();
	}

	private void executeEvent(Event e) {
		e.runEvent();
	}

}
