package store.view;

import java.io.File;
import java.io.PrintWriter;

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
	private StoreState state;

	/**
	 * Constructs a new instance of a {@code StoreView} object.
	 * 
	 * @param state
	 */
	public StoreView(StoreState state) {
		this.state = state;
	}

	/**
	 * Print simulation parameters, progress and result to a console window.
	 */
	@Override
	public void printConsole() {
		System.out.println(generateParameters());
		System.out.println(generateProgress());
		System.out.println(generateResult());
	}

	/**
	 * Print simulation parameters, progress and result to a new file. Default
	 * encoding is {@code UTF-8}.
	 * 
	 * @param filePath  Path to file
	 * @param overwrite Should a already existing file be overwritten.
	 */
	@Override
	public void printFile(String filePath, boolean overwrite) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			return;
		}

		if (!overwrite) {
			if (file.exists()) {
				return;
			}
		}

		PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		writer.println(generateParameters());
		writer.println(generateProgress());
		writer.println(generateResult());
	}
	
	/**
	 * Method to generate the paramaters for this simulation.
	 * 
	 * @return parameter statistics from the simulator
	 */
	private String generateParameters() {
		String result = generateHeader("Parametrar") + newLine;
		result += "Antal kassor, N...........:" + newLine;
		result += "Max som ryms, M...........:" + newLine;
		result += "Ankomshastighet, lambda...:" + newLine;
		result += "Plocktider, [P_min, P_max]:" + newLine;
		result += "Betaltider, [K_min, K_max]:" + newLine;
		result += "Frö, F....................:" + newLine;
		return result;
	}
	
	/**
	 * Method generates the event course of the simulation.
	 * 
	 * @return simulation event description
	 */
	
	private String generateProgress() {
		String result = generateHeader("Förlopp") + newLine;
		return result;
	}
	/**
	 * Method generates statistics regarding how well the store is run.
	 * 
	 * @return simulation results
	 */
	private String generateResult() {
		String result = generateHeader("Resultat") + newLine;
		result += "1) Av <TOT_KUNDER> kunder handlade <KUNDER_HANDLADE> medan <KUNDER_MISSADE> missades"
				+ newLine;
		result += "2) Total tid <MAX_KASSOR> kassor varit lediga: <KASSOR_LEDIG_TID> te."
				+ newLine;
		result += "   Genomsnittlig ledig kassatid: <KASSOR_LEDIG_TID_AVG> te (dvs <PROCENT> av tiden från öppning tills sista kunden betalat)."
				+ newLine;
		result += "3) Total tid 12 kunder tvingats köa: 3,77 te." + newLine;
		result += "   Genomsnittlig kötid: <KÖTID_AVG> te." + newLine;
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