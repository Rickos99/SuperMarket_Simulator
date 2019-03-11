package simulator;

import java.util.ArrayList;

import store.state.StoreState;

public class EventQueue {
	private ArrayList<Event> eventQueue;
	protected StoreState state;

	public EventQueue(StoreState state) {
		this.state = state;
		this.eventQueue = new ArrayList<Event>();

	}

	/**
	 * Adds an event to the event queue
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		eventQueue.add(event);
	}

	/**
	 * 
	 * @return the event first in line.
	 */

	public Event getEvent() throws IndexOutOfBoundsException {
		if (eventQueue.size() == 0) {
			throw new IndexOutOfBoundsException("Event Queue is empty.");
		}
		// Sorts the current array via it's getExTime method.

		eventQueue = sortEvent(eventQueue);

		Event nextRunEvent = eventQueue.get(0);
		state.uppdateRegistersDownTime(state.getRegistersOpen()*(nextRunEvent.getExTime()-state.getElapsedTime()));
		// Sets time in state to be the time that the event was executed.
		state.setTimeElapsed(nextRunEvent.getExTime());
		eventQueue.remove(0);
		return nextRunEvent;
	}

	private static ArrayList<Event> sortEvent(ArrayList<Event> list) {
		if (list.size() == 1) {
			return list;
		}
		int midPoint = list.size() / 2;
		int startIndex = 0;

		ArrayList<Event> tempLeft = new ArrayList<Event>();
		ArrayList<Event> tempRight = new ArrayList<Event>();

		while (startIndex < midPoint) {
			tempLeft.add(list.get(startIndex));
			startIndex++;
		}
		while (midPoint < list.size()) {
			tempRight.add(list.get(midPoint));
			midPoint++;
		}
		/// Blir lite dumt med namnen h�r men va fan, kommer p� n� b�ttre p� ngn
		/// dag.
		tempLeft = sortEvent(tempLeft);
		tempRight = sortEvent(tempRight);

		return merge(tempLeft, tempRight);

	}

	private static ArrayList<Event> merge(ArrayList<Event> left, ArrayList<Event> right) {

		ArrayList<Event> resultingList = new ArrayList<Event>();

		int leftPointer, rightPointer;
		leftPointer = rightPointer = 0;

		while (leftPointer < left.size() || rightPointer < right.size()) {
			if (leftPointer < left.size() && rightPointer < right.size()) {
				if (left.get(leftPointer).getExTime() <= right.get(rightPointer).getExTime()) {
					resultingList.add(left.get(leftPointer));
					leftPointer++;
				} else {
					resultingList.add(right.get(rightPointer));
					rightPointer++;
				}
			}
			/// Getting events from the original array if there are any left.
			else if (leftPointer < left.size()) {
				resultingList.add(left.get(leftPointer));
				leftPointer++;
			} else if (rightPointer < right.size()) {
				resultingList.add(right.get(rightPointer));
				rightPointer++;

			}

		}

		return resultingList;

	}

}
