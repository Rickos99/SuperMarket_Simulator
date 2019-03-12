package simulator;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 *
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
