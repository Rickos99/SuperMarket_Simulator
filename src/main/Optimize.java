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

public class Optimize {

	public StoreState metod1(long timeSeed, int maxRegisters) {
		long TIME_SEED = timeSeed; // Seed to generate random number
		int MAX_CUSTOMERS = 5; // Maximum number of costumers allowed in store at once
		int MAX_REGISTERS = maxRegisters; // Maximum number of registers available in store
		int TIME_STORE_CLOSE = 10; // At what time store closes
		double ARRIVAL_SPEED = 1.0d; // Speed of which costumers arrive at
		double MIN_PICKING_TIME = 0.5d; // Minimum time a costumer can pick items in
		double MAX_PICKING_TIME = 1d; // Maximum time a costumer can pick items in
		double MIN_CHECKOUT_TIME = 2d; // Minimum time a costumer can checkout in
		double MAX_CHECKOUT_TIME = 3d; // Time a costumer can checkout in
		
		EventQueue eventQueue = new EventQueue();
		StoreState state = new StoreState(TIME_SEED, MAX_CUSTOMERS, MAX_REGISTERS,
				TIME_STORE_CLOSE, ARRIVAL_SPEED, MIN_PICKING_TIME, MAX_PICKING_TIME,
				MIN_CHECKOUT_TIME, MAX_CHECKOUT_TIME, eventQueue);
		
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, TIME_STORE_CLOSE));
		eventQueue.addEvent(new StopEvent(state, 999));
		new Simulator(state, eventQueue).run();
		return state;
	}

	public int metod2(long seed) {
		while (state.getCustomersDeniedEntry() > 0) {
			MAX_REGISTERS++;
			metod1(state);
		}
		return MAX_REGISTERS;
	}

	public void metod3(long seed) {
		Random random = new Random(seed);
		int maxLeastRegisters = 0;
		int leastRegistersIteration;
		int i;

		while (maxLeastRegisters - leastRegistersIteration > 1 && i <= 100) {
			leastRegistersIteration = metod2();
			if(maxLeastRegisters == 0 || leastRegistersIteration < maxLeastRegisters) {
				maxLeastRegisters = leastRegistersIteration;
			}
			if(maxLeastRegisters == leastRegistersIteration) {
				i++;
			} else {
				i = 0;
			}
		}
	}

}
