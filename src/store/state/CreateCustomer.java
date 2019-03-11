package store.state;

public class CreateCustomer {

	private int id;
	
	public CreateCustomer() {
		id = 0;
	}
	
	public Customer newCustomer() {
		return new Customer(id++);
	}
	
}
