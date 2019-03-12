import simulator.Simulator;
import store.state.StoreState;
import store.view.StoreView;;

public class MainDriver {

	public static void main(String[] args) {
		StoreState state = new StoreState(13, 7, 2, 10, 3.0, 0.6, 0.9, 0.35, 0.6, 3.0);
		StoreView view = new StoreView(state);
		
		state.addObserver(view);
		
		new Simulator(state, eventQueue);
	}

}
