package simulator;

import java.util.Observable;

/**
 * 
 * @author Rickard Bemm
 * @version 1.0
 */
public abstract class SimState extends Observable {
	protected boolean simulatorIsRunning;
	
	public void stopSimulator() {
		simulatorIsRunning = false;
	}
	
	public void startSimulator() {
		simulatorIsRunning = true;
	}
	
	public boolean simulatorIsRunning() {
		return this.simulatorIsRunning;
	}
	
	abstract public void runSim();
}
