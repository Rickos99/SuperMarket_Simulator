import simulator.Event;
import simulator.EventQueue;
import simulator.Simulator;
import store.event.StartEvent;
import store.state.StoreState;
import store.view.StoreView;;

public class MainDriver {

	public static void main(String[] args) {
		// Create instances of various objects
		EventQueue eventQueue = new EventQueue();
		StoreState state = new StoreState(13, 7, 2, 10, 3.0, 0.6, 0.9, 0.35, 0.6, eventQueue);
		Event startEvent = new StartEvent(state, 0);
		StoreView view = new StoreView(state);
		
		state.addObserver(view);
		
		// Run simulator
		new Simulator(state, startEvent).run();
	}

}
