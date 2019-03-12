/**
 * @author Nour Aldein Bahtite
 * 
 */

/**
 * In this class, the customer id will be returned every time the code is run. 
 */
package store.state;

public class Customer {

	private int id;
	
	public Customer(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Cstomer id
	 */
	public int getId() {
		return this.id;
	}

}
