/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

package store.view;

import java.io.File;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Observable;
import simulator.SimState;
import simulator.SimView;
import store.state.StoreState;

/**
 * @author Rickard Bemm
 * @author Philip Eriksson
 * @author Nour Aldein Bahtite
 * @author Andr� Christofferson
 *
 */
public class StoreView extends SimView {

	private String newLine = "\r\n";
	private String result;
	
	@Override
	public void update(Observable o, Object arg) {
		result += generateProgress((StoreState)o);
		if(!((SimState)o).simulatorIsRunning()) {
			result += generateResult((StoreState) o);
		}
	}
	
	public StoreView(StoreState state) {
		super(state);
		this.result = generateParameters(state);
	}

	/**
	 * Print simulation parameters, progress and result to a console window.
	 */
	@Override
	public void printConsole(SimState state) {
		System.out.println(result);
	}

	/**
	 * Print simulation parameters, progress and result to a new file. Default
	 * encoding is {@code UTF-8}.
	 * 
	 * @param filePath  Path to file
	 * @param overwrite Should a already existing file be overwritten.
	 */
	@Override
	public void printFile(SimState state, String filePath, boolean overwrite) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			return;
		}

		if (!overwrite) {
			if (file.exists()) {
				return;
			}
		}

		try {
			PrintWriter writer = new PrintWriter(file);
			writer.println(result);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Method to generate the paramaters for this simulation.
	 * 
	 * @return parameter statistics from the simulator
	 */
	private String generateParameters(StoreState state) {
		String result = generateHeader("Parametrar") + newLine;
		result += MessageFormat.format("Antal kassor, N...........: {0} \n", state.getMAX_REGISTERS());
		result += MessageFormat.format("Max som ryms, M...........: {0} \n", state.getMAX_CUSTOMERS());
		result += MessageFormat.format("Ankomshastighet, lambda...: {0} \n", state.getTIME_LAMBDA());
		result += MessageFormat.format("Plocktider, [P_min, P_max]: [{0}..{1}] \n", state.getMIN_PICKING_TIME(),
				state.getMAX_PICKING_TIME());
		result += MessageFormat.format("Betaltider, [K_min, K_max]: [{0}..{1}] \n", state.getMIN_CHECKOUT_TIME(),
				state.getMAX_CHECKOUT_TIME());
		result += MessageFormat.format("Frö, F....................: {0} \n", state.getTIME_SEED());
		return result;
	}

	/**
	 * Method generates the event course of the simulation.
	 * 
	 * @return simulation event description
	 */
	
	private String generateProgress(StoreState state) {
		String result = generateHeader("Förlopp") + newLine;
		return result;
	}

	/**
	 * Method generates statistics regarding how well the store is run.
	 * 
	 * @return simulation results
	 */
	private String generateResult(StoreState state) {
		String result = generateHeader("Resultat") + newLine;
		result += MessageFormat.format("1) Av {0} kunder handlade {1} medan {2} missades \n",
				state.getCustomersInTotal(), state.getCustomersPayed(), state.getCustomersDeniedEntry());
		result += MessageFormat.format("2) Total tid {0} kassor varit lediga: {1} te. \n", state.getMAX_REGISTERS(),
				state.getCheckoutFreeTime());
		result += MessageFormat.format(
				"\t Genomsnittlig ledig kassatid: {0} te (dvs {1} av tiden från öppning tills sista kunden betalat). \n",
				"<KASSOR_LEDIG_TID_AVG>", "<PROCENT>");
		result += MessageFormat.format("3) Total tid {0} kunder tvingats köa: {1} te. \n", state.getCustomersPayed(),
				"<TOTAL_KÖTID_FÖR_KUNDER");
		result += MessageFormat.format("\tGenomsnittlig kötid: {0} te. \n", "<KÖTID_AVG>");
		return result;
	}

	/**
	 * Method generates description of the current event.
	 * 
	 * @param headerText describes the current event executed.
	 * @return header description of the event
	 */

	private String generateHeader(String headerText) {
		String headerString = headerText + "\r\n";
		for (int i = 0; i < headerText.length(); i++) {
			headerString += "=";
		}
		return headerString;
	}
}