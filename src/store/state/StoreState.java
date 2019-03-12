/**
 *
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
package store.state;

import simulator.Event;
import store.event.StartEvent;
import store.time.StoreTime;


public class StoreState extends simulator.SimState {

	/**
	 * Final variables cannot be changed while the program is running.
	 */

	private final long TIME_SEED;
	private final int MAX_CUSTOMERS;
	private final int MAX_REGISTERS;
	private final int TIME_STORE_CLOSE;
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

	/**
	 *
	 * @param TIME_SEED
	 * @param MAX_CUSTOMERS
	 * @param MAX_REGISTERS
	 * @param TIME_STORE_CLOSE
	 * @param ARRIVAL_SPEED
	 * @param MIN_PICKING_TIME
	 * @param MAX_PICKING_TIME
	 * @param MIN_CHECKOUT_TIME
	 * @param MAX_CHECKOUT_TIME
	 * @param TIME_LAMBDA
	 */

	public StoreState(long TIME_SEED, int MAX_CUSTOMERS, int MAX_REGISTERS, int TIME_STORE_CLOSE, double ARRIVAL_SPEED,
			double MIN_PICKING_TIME, double MAX_PICKING_TIME, double MIN_CHECKOUT_TIME, double MAX_CHECKOUT_TIME,
			double TIME_LAMBDA) {
		storeTime = new StoreTime(TIME_LAMBDA, TIME_SEED);
		checkOutQueue = new FIFO<Customer>();
		customerSpawn = new CreateCustomer();

		this.TIME_SEED = TIME_SEED;
		this.MAX_CUSTOMERS = MAX_CUSTOMERS;
		this.MAX_REGISTERS = MAX_REGISTERS;
		this.TIME_STORE_CLOSE = TIME_STORE_CLOSE;
		this.ARRIVAL_SPEED = ARRIVAL_SPEED;
		this.MIN_PICKING_TIME = MIN_PICKING_TIME;
		this.MAX_PICKING_TIME = MAX_PICKING_TIME;
		this.MIN_CHECKOUT_TIME = MIN_CHECKOUT_TIME;
		this.MAX_CHECKOUT_TIME = MAX_CHECKOUT_TIME;
		this.TIME_LAMBDA = TIME_LAMBDA;

		new StartEvent(this, TIME_STORE_CLOSE);
	}



	/**
	 *
	 * Create a new customer
	 *
	 * @return newCustomer()
	 */
	public Customer createNewCustomer() {
		return customerSpawn.newCustomer();
	}

	/**
	 * A new customer enters the store. The store can accept a new customer only if
	 * the store doesn't have the maximal number of customer in it
	 *
	 * @throws OpenRegisterFailedException else
	 */
	public void openNewRegister() {
		if (registersOpen < MAX_REGISTERS) {
			setChanged();
			notifyObservers();
			registersOpen++;
		} else {
			// TODO: throw new OpenRegisterFailedException()
		}
	}

	/**
	 * Close the store and allow the customers (if they found ) to pay for their
	 * things.
	 *
	 * @throws CloseRegisterFailedException else.
	 */
	public void closeOneRegister() {
		if (registersOpen > 0) {
			setChanged();
			notifyObservers();
			registersOpen--;
		} else {
			// TODO: throw new CloseRegisterFailedException()
		}
	}

	/**
	 * The store is open and can accept customers.
	 *
	 * @return storeIsOpen
	 */
	public boolean storeIsOpen() {
		return storeIsOpen;
	}

	/**
	 * The store is closed and doesn't accept new customers. Change storeIsOpen to
	 * false.
	 */
	public void closeStore() {
		if (storeIsOpen) {
			setChanged();
			notifyObservers();
			storeIsOpen = false;
		}
	}

	public void uppdateTimeElapsed(double time) {
		setChanged();
		notifyObservers();
		elapsedTime = time;
	}

	/**
	 * Get the elapsed time
	 *
	 * @return elapsedTime.
	 */

	public double getTimeElapsed() {
		return elapsedTime;
	}

	/**
	 * To open the store.
	 *
	 * StoreIsOpen change to true.
	 */
	public void openStore() {
		if (!storeIsOpen) {
			setChanged();
			notifyObservers();
			storeIsOpen = true;
		}
	}

	/**
	 * Get the number of the customers who couldn't enter the store.
	 */
	public void increaseCustomerDeniedByOne() {
		setChanged();
		notifyObservers();
		customersDeniedEntry++;
	}

	public StoreTime getStoreTime() {
		return storeTime;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Get the number of customers who wait on the queue to pay their things.
	 *
	 * @return checkOutQueue
	 */
	public FIFO<Customer> getCheckoutQueue() {
		return checkOutQueue;
	}

	/**
	 * Get the max number of customers in the store.
	 *
	 * @return MAX_CUSTOMERS
	 */
	public int getMaxCustomers() {
		return MAX_CUSTOMERS;
	}

	/**
	 * Get the number of all the customers who could and couldn't enter the store.
	 *
	 * @return customersInTotal
	 */
	public int getCustomersInTotal() {
		return customersInTotal;
	}

	/**
	 * Get the number of the customers which can be accepted in the store in the
	 * moment
	 *
	 * @return registersOpen
	 */
	public int getRegistersOpen() {
		return registersOpen;
	}

	/**
	 * If the check out queue is empty.
	 *
	 * @return isEmpty()
	 */
	public boolean getCheckOutQueueIsEmpty() {
		return checkOutQueue.isEmpty();
	}

	/**
	 * Get the first customer who waits in the check out queue for paying his things
	 *
	 * @return getFirst()
	 */
	public Customer getFirst() {
		return checkOutQueue.getFirst();
	}

	/**
	 * Get queue time between two customers.
	 *
	 * @return timeNextCustomer()
	 */
	public double getTimeNextCustomer() {
		return storeTime.timeNextCustomer();
	}

	/**
	 * Get check out time between two customers.
	 *
	 * @return timeCustomerCheckOut()
	 */
	public double getTimeNextCustomerCheckout() {
		return storeTime.timeCustomerCheckOut();
	}

	/**
	 * Get pick time for customers.
	 *
	 * @return timeCustomerPick()
	 */
	public double getTimeCustomerPick() {
		return storeTime.timeCustomerPick();
	}

	/**
	 * Add new customer in pay queue
	 *
	 * @param customer
	 */
	public void addCustomerInPayoutLine(Customer customer) {
		setChanged();
		notifyObservers();
		checkOutQueue.add(customer);
	}

	/**
	 * Get seed time.
	 *
	 * @return TIME_SEED
	 */
	public long getTIME_SEED() {
		return TIME_SEED;
	}

	/**
	 * Get the max number of customers.
	 *
	 * @return MAX_CUSTOMERS
	 */
	public int getMAX_CUSTOMERS() {
		return MAX_CUSTOMERS;
	}

	/**
	 * Get max number of registers.
	 *
	 * @return MAX_REGISTERS
	 */
	public int getMAX_REGISTERS() {
		return MAX_REGISTERS;
	}

	/**
	 * Get the speed of arrival.
	 *
	 * @return ARRIVAL_SPEED
	 */
	public double getARRIVAL_SPEED() {
		return ARRIVAL_SPEED;
	}

	/**
	 * Get the minimum time of picking.
	 *
	 * @return MIN_PICKING_TIME
	 */
	public double getMIN_PICKING_TIME() {
		return MIN_PICKING_TIME;
	}

	/**
	 * Get the maximum time of picking.
	 *
	 * @return MAX_PICKING_TIME
	 */
	public double getMAX_PICKING_TIME() {
		return MAX_PICKING_TIME;
	}

	/**
	 * Get the minimum time of check out.
	 *
	 * @return MIN_CHECKOUT_TIME
	 */
	public double getMIN_CHECKOUT_TIME() {
		return MIN_CHECKOUT_TIME;
	}

	/**
	 *
	 * Get the maximum time of check out.
	 *
	 * @return MAX_CHECKOUT_TIME
	 */
	public double getMAX_CHECKOUT_TIME() {
		return MAX_CHECKOUT_TIME;
	}

	/**
	 * Get the number of customers who paid for their things
	 *
	 * @return customersPayed
	 */
	public int getCustomersPayed() {
		return customersPayed;
	}

	/**
	 * Get the number of customers who visited the store
	 *
	 * @return customersVisited
	 */
	public int getCustomersVisited() {
		return customersVisited;
	}

	/**
	 * Get the number of the customers who wait in queue.
	 *
	 * @return customersInQueue
	 */
	public int getCustomersInQueue() {
		return customersInQueue;
	}

	/**
	 * Get the number of customers who couldn't go in the store.
	 *
	 * @return customersDeniedEntry
	 */
	public int getCustomersDeniedEntry() {
		return customersDeniedEntry;
	}

	/**
	 * Get the time spent in the queue
	 *
	 * @return queueTime
	 */
	public double getQueueTime() {
		return queueTime;
	}

	/**
	 * Get the time while the checkout was empty
	 *
	 * @return checkoutFreeTime
	 */

	public double getCheckoutFreeTime() {
		return checkoutFreeTime;
	}

	/**
	 *
	 * @return TIME_LAMBDA
	 */
	public double getTIME_LAMBDA() {
		return TIME_LAMBDA;
	}

	/**
	 * Uppdate the time when the checout is down.
	 *
	 * @param deadRegisterTime
	 */
	public void uppdateRegistersDownTime(double deadRegisterTime) {
		setChanged();
		notifyObservers();
		checkoutFreeTime += deadRegisterTime;
	}

	/**
	 * Uppdate the time that spant in queue
	 *
	 * @param peopleInQueueTime
	 */
	public void uppdateCustomersInQueueTime(double peopleInQueueTime) {
		setChanged();
		notifyObservers();
		queueTime += peopleInQueueTime;
	}

	@Override
	public void runSim() {
		setChanged();
		notifyObservers();
		startSimulator();
	}

	private void executeEvent(Event e) {
		e.runEvent();
	}

}
