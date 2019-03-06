package simulator;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Rickard Bemm
 *
 */
public abstract class SimView implements Observer {

	public abstract void printConsole();

	public abstract void printFile(String filePath, boolean overwrite);

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

}