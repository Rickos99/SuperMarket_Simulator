package simulator;

/**
 * 
 * @author Rickard Bemm
 * @version 1.0
 */
public abstract class SimState {
	protected boolean simulatorIsRunning;
	
	public void stopSimulator() {
		simulatorIsRunning = false;
	}
	
	public void startSimulator() {
		simulatorIsRunning = true;
	}
	
	abstract public void runSim();
}
