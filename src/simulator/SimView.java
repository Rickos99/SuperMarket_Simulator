package simulator;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract class used as template for viewing the results of the simulator
 * with the Observer and Observable design pattern.
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public abstract class SimView implements Observer {
	
	public SimView(SimState state) {
		state.addObserver(this);
	}
	
	/**
	 * Method used to save simulation results in console.
	 */
	public abstract void printConsole(SimState state);
	
	/**
	 * Method used to save simulation results in a file.
	 * 
	 * @param filePath designated file path
	 * @param overwrite overwrite current file path
	 */
	public abstract void printFile(SimState state, String filePath, boolean overwrite);

	@Override
	public abstract void update(Observable o, Object arg);

}