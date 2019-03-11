package store.state;

import simulator.Event;
import store.time.StoreTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StoreState extends simulator.SimState {

	private final int TIME_SEED;
	private final int MAX_CUSTOMERS;
	private final int MAX_REGISTERS;
	private final double ARRIVAL_SPEED;
	private final double MIN_PICKING_TIME;
	private final double MAX_PICKING_TIME;
	private final double MIN_CHECKOUT_TIME;
	private final double MAX_CHECKOUT_TIME;
	private final double TIME_LAMBDA;

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

	private FIFO<Customer> checkOutQueue;
	private StoreTime storeTime;
	private CreateCustomer customerSpawn;

	public StoreState(int max_customers, int max_registers) {
		// TODO: Initialize all parameters in this state
		storeTime = new StoreTime();
		checkOutQueue = new FIFO<Customer>();
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
	
	public void setTimeElapsed(double time) {
		elapsedTime = time;
	}
	
	public double getTimeElapsed() {
		return elapsedTime;
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
		return checkOutQueue;
	}

	public int getMaxCustomers() {
		return MAX_CUSTOMERS;
	}

	public int getCustomersInTotal() {
		return customersInTotal;
	}

	public int getRegistersOpen() {
		return registersOpen;
	}

	public boolean getCheckOutQueueIsEmpty() {
		return checkOutQueue.isEmpty();
	}
	
	public Customer getFirst() {
		return checkOutQueue.getFirst();
	}

	public int getTimeNextCustomer() {
		return (int) storeTime.timeNextCustomer();
	}
	public int getTimeNextCustomerCheckout() {
		return (int) storeTime.timeCustomerCheckOut();
	}
	public int getTimeCustomerPick() {
		return (int) storeTime.timeCustomerPick();
	}

	public void addCustomerInPayoutLine(Customer customer) {
		checkOutQueue.add(customer);
	}
	
	public int getTIME_SEED() {
		return TIME_SEED;
	}
	
	public int getMAX_CUSTOMERS() {
		return MAX_CUSTOMERS;
	}

	public int getMAX_REGISTERS() {
		return MAX_REGISTERS;
	}

	public double getARRIVAL_SPEED() {
		return ARRIVAL_SPEED;
	}

	public double getMIN_PICKING_TIME() {
		return MIN_PICKING_TIME;
	}

	public double getMAX_PICKING_TIME() {
		return MAX_PICKING_TIME;
	}

	public double getMIN_CHECKOUT_TIME() {
		return MIN_CHECKOUT_TIME;
	}

	public double getMAX_CHECKOUT_TIME() {
		return MAX_CHECKOUT_TIME;
	}

	public int getCustomersPayed() {
		return customersPayed;
	}

	public int getCustomersVisited() {
		return customersVisited;
	}

	public int getCustomersInQueue() {
		return customersInQueue;
	}

	public int getCustomersDeniedEntry() {
		return customersDeniedEntry;
	}

	public double getQueueTime() {
		return queueTime;
	}

	public double getCheckoutFreeTime() {
		return checkoutFreeTime;
	}
	
	public double getTIME_LAMBDA() {
		return TIME_LAMBDA;
	}
	public void uppdateRegistersDownTime(double deadRegisterTime){
		checkoutFreeTime+= deadRegisterTime;
	}
	
	
	@Override
	public void runSim() {
		startSimulator();
	}

	private void executeEvent(Event e) {
		e.runEvent();
	}

}
