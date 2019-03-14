package main;

import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;

public class Optimize {
	long TIME_SEED = 1234; // Seed to generate random number
	int MAX_CUSTOMERS = 5; // Maximum number of costumers allowed in store at once
	int MAX_REGISTERS = 2; // Maximum number of registers available in store
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
	
public StoreState metod1() {
	eventQueue.addEvent(new StoreStartEvent(state));
	eventQueue.addEvent(new StoreCloseEvent(state, TIME_STORE_CLOSE));
	eventQueue.addEvent(new StopEvent(state, 999));
	new Simulator(state, eventQueue).run();
	return state;
}

}