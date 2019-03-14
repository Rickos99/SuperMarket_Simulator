package main;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Random;
import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;

public class Optimize implements OptimizeTesting {

	public static void main(String[] args) {
		EventQueue eventQueue = new EventQueue();
		int maxRegisters = 5;
		StoreState state = new StoreState(TIME_SEED,MAX_CUSTOMERS,maxRegisters,TIME_STORE_CLOSE,
										  ARRIVAL_SPEED,MIN_PICKING_TIME,MAX_PICKING_TIME,MIN_CHECKOUT_TIME,
										  MAX_CHECKOUT_TIME,eventQueue,SIM_STOP_TIME);
		new Optimize().metod3(state,1234);
	}
	
	private static int example = 0;

	public StoreState metod1(StoreState state,long timeSeed, int maxRegisters) {

		EventQueue eventQueue = new EventQueue();
		state = new StoreState(timeSeed, state.getMAX_CUSTOMERS(), maxRegisters, state.getTIME_STORE_CLOSE(), state.getARRIVAL_SPEED(),
				state.getMIN_PICKING_TIME(), state.getMAX_PICKING_TIME(), state.getMIN_CHECKOUT_TIME(),state.getMAX_CHECKOUT_TIME(),
				eventQueue, state.getTIME_SIM_STOP());
		
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, state.getTIME_STORE_CLOSE()));
		eventQueue.addEvent(new StopEvent(state, state.getTIME_SIM_STOP()));
		new Simulator(state, eventQueue).run();
		return state;
	}
	
	public StoreState metod2(StoreState state,long timeSeed) {
		int missedCustomers = metod1(state, timeSeed, MAX_CUSTOMERS).getCustomersDeniedEntry();
		int max_Registers = 1;
		while (metod1(state,timeSeed, max_Registers).getCustomersDeniedEntry() > missedCustomers) {
			max_Registers ++;
		}
		return state;
	}

	public void metod3(StoreState state,long seed) {
		Random random = new Random(seed);
		int maxLeastRegisters = 0;
		int leastRegistersIteration = 0;

		for(int i = 0; i < 100; i++) {
			leastRegistersIteration = metod2(state, random.nextLong()).getCustomersDeniedEntry();
			if(leastRegistersIteration > maxLeastRegisters) {
				maxLeastRegisters = leastRegistersIteration;
			}
		}
		printResultsMetod3(state);
	}

	/// FOR RESULTS VIEW

	private String resultBody(StoreState state) {
		String result = "Exampel " + (example++) + "\n";
		result += MessageFormat.format("Max som ryms, M...........: {0} \n", state.getMAX_CUSTOMERS());
		result += MessageFormat.format("Ankomshastighet, lambda...: {0} \n", state.getARRIVAL_SPEED());
		result += MessageFormat.format("Plocktider, [P_min, P_max]: [{0}..{1}] \n", state.getMIN_PICKING_TIME(),
				state.getMAX_PICKING_TIME());
		result += MessageFormat.format("Betaltider, [K_min, K_max]: [{0}..{1}] \n", state.getMIN_CHECKOUT_TIME(),
				state.getMAX_CHECKOUT_TIME());
		result += MessageFormat.format("Frö, F....................: {0} \n", state.getTIME_SEED());
		return result;

	}

	private String resultsEnd(StoreState state) {
		String result = "";
		result += MessageFormat.format("Stängning sker tiden {0} och stophändelsen sker tiden {1} \n",
				state.getTIME_STORE_CLOSE(), state.getTIME_SIM_STOP());
		result += MessageFormat.format("Minsta antal kassor som ger minimalt antal missade ({0}): {1} \n ",
				state.getCustomersDeniedEntry(), state.getMAX_REGISTERS());
		if (state.getCustomersDeniedEntry() != 0) {
			result += MessageFormat.format("(OBS! Missar som minst {0} kunder.)", "<ANTAL MISSADE>");
		}
		return result;

	}

	private void printResultsMetod2(StoreState state) {
		String result = resultBody(state) + resultsEnd(state);
		System.out.println(result);

	}
	
	private void printResultsMetod3(StoreState state){
		String result = resultBody(state);
		result += MessageFormat.format("Lambda = {0} \n", state.getARRIVAL_SPEED());
		result += resultsEnd(state);
		System.out.println(result);
	}
	
	private String cutDecimals(double d) {
		return new DecimalFormat("#.##").format(d);
	}
}
