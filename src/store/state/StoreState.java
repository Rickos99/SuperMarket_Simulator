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

	public StoreState() {
		// TODO: Initialize all parameters in this state
		storeTime = new StoreTime();
		checkoutQueue = new FIFO<Customer>();
		customerSpawn = new CreateCustomer();
	}

	public Customer customer(int id) {
		// TODO Implement customer(int id)
		throw new NotImplementedException();
	}

	public void createNewCustomer() {
		throw new NotImplementedException();
	}

	/**
	 * Open a new register. If maximum number of registers is achieved already,
	 * a OpenRegisterFailedToOpenException is thrown.
	 */
	public void openNewRegister() {
		if (registersOpen < MAX_REGISTERS) {
			registersOpen++;
		} else {
			// TODO: throw new OpenRegisterFailedToOpenException()
		}
	}

	/**
	 * Close a register. If all registers already is closed, a
	 * CloseRegisterFailedToCloseException is thrown.
	 */
	public void closeOneRegister() {
		if (registersOpen > 0) {
			registersOpen--;
		} else {
			// TODO: throw new CloseRegisterFailedToCloseException()
		}
	}

	/**
	 * Determine if store is open.
	 * 
	 * @return True if store is open, otherwise false
	 */
	public boolean storeIsOpen() {
		return storeIsOpen;
	}

	/**
	 * Close store
	 */
	public void closeStore() {
		storeIsOpen = false;
	}

	/**
	 * Open store
	 */
	public void openStore() {
		storeIsOpen = true;
	}
	
	public void increaseCustomerDeniedByOne() {
		customersDeniedEntry++;
	}

	public StoreTime getStoreTime() {
		return storeTime;
	}

	/**
	 * Get a time representation of how long simulator has been running for.
	 * 
	 * @return How long simulator has been running for
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}

	public FIFO<Customer> getCheckoutQueue() {
		return checkoutQueue;
	}

	/**
	 * Get number of maximum customers allowed in store at once.
	 * 
	 * @return Number of maximum customers allowed in store at once
	 */
	public int getMaxCustomers() {
		return MAX_CUSTOMERS;
	}

	/**
	 * Get total number of customers that has visited the store.
	 * 
	 * @return Number of customers that has visited the store
	 */
	public int getCustomersInTotal() {
		return customersInTotal;
	}

	@Override
	public void runSim() {
		startSimulator();
		// TODO Implement runSim()
		throw new NotImplementedException();
	}

	private void executeEvent(Event e) {
		e.runEvent();
	}

}
