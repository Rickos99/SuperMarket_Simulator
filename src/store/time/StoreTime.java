package store.time;


public class StoreTime {
	private ExponentialRandomStream randomExcept;
	private UniformRandomStream randomUni;
	
	/**
	 *  Construct a new Time Object
	 * @param lambda
	 * @param seed
	 */

	public StoreTime(double lambda, long seed) {
		this.randomExcept = new ExponentialRandomStream(lambda, seed);
		this.randomUni = new UniformRandomStream(lambda, seed);

	}
	/**
	 * Returns time for next customer to arrive
	 * @Return time for next customer
	 */
	public double timeNextCustomer() {
		return randomExcept.next();

	}
	/**
	 * Returns 
	 * @return duration for pick event
	 */
	public double timeCustomerPick() {
		return randomUni.next();

	}
	/**
	 * 
	 * @return time duration for checkout event
	 */
	public double timeCustomerCheckOut() {
		return randomUni.next();
	}

//	public static void main(String[] args) {
//		StoreTime obj = new StoreTime(1, 100);
//		System.out.println(obj.timeCustomerCheckOut());
//		System.out.println(obj.timeNextCustomer());
//		System.out.println(obj.timeCustomerPick());
//
//	}

}
