package simulator;

public abstract class Event {
	
	protected double executeTime;
	private StoreState state;
	private EventQueue eventQueue;
	
	
	/**
	 * 
	 * 
	 * @return time when event is executed.
	 */

	
	
	public double getExTime(){
		return executeTime;
	}
	
	/**
	 * Adds an event to the event queue for the specific state.
	 * 
	 * @param event adds this to the eventqueue
	 */
	public void addEventToQueue(Event event){
		this.state.eventQueue.addEvent(event);
	}
	
	/**
	 * Abstract method to be overriten in specific event classes
	 */
	public abstract void runEvent();
	
}
