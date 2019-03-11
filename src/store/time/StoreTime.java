package store.time;


public class StoreTime {
	private ExponentialRandomStream randomExcept;
	private UniformRandomStream randomUni;
	

	public StoreTime(double lambda, long seed) {
		this.randomExcept = new ExponentialRandomStream(lambda, seed);
		this.randomUni = new UniformRandomStream(lambda, seed);

	}

	public double timeNextCustomer() {
		return randomExcept.next();

	}

	public double timeCustomerPick() {
		return randomUni.next();

	}

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
