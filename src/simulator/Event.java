package simulator;

public abstract class Event {
	
	private double executeTime;
	protected StoreState state;
	
	
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
	public void addEventToQue(Event event){
		state.eventQueue.add(event);
	}
	
	/**
	 * Abstract method to be overriten in specific event classes
	 */
	public abstract void runEvent();
	
}
