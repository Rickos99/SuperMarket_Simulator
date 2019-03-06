package simulator;

import java.util.ArrayList;

public class EventQueue {
	private ArrayList<Event> eventQue;
	private StoreState state;

	public EventQueue(StoreState state){
		this.state = state;
		this.eventQue = new ArrayList<Event>();		
		
	}
	/**
	 * Adds an event to the event queue.
	 * 
	 * @param event
	 */
	public void addEvent(Event event){
		this.eventQue.add(event);
	}
	
	/**
	 * 
	 * @return the event first in line.
	 */
	
	public Event getEvent() throws IndexOutOfBoundsException{
		if(this.eventQue.size() == 0){
			throw new IndexOutOfBoundsException("Event Queue is empty.");
		}
		//Sorts the current array via it's getExTime method.
		
		this.eventQue = mergeSort(this.eventQue);
		
		Event tempEvent = this.eventQue.get(0);
		//Sets time in state to be the time that the event was executed.
		this.state.elapsedTime = tempEvent.getExTime();
		this.eventQue.remove(0);
		return tempEvent;
	}
	
	
	private static ArrayList<Event> mergeSort(ArrayList<Event> list){
		if(list.size() == 1){
			return list;
		}
		int midPoint = list.size()/2;
		int startIndex = 0;
		
		ArrayList<Event> tempLeft = new ArrayList<Event>();
		ArrayList<Event> tempRight = new ArrayList<Event>();
		

		while(startIndex<midPoint){
			tempLeft.add(list.get(startIndex));
			startIndex++;
		}
		while(midPoint<list.size()){
			tempRight.add(list.get(midPoint));
			midPoint++;
		}
		///Blir lite dumt med namnen här men va fan, kommer på nå bättre på ngn dag.
		tempLeft = mergeSort(tempLeft);
		tempRight = mergeSort(tempRight);
		
		return merge(tempLeft,tempRight);
	
	}
	
	private static ArrayList<Event> merge(ArrayList<Event> left, ArrayList<Event> right){
		
		ArrayList<Event> resultingList = new ArrayList<Event>();
		
		int leftPointer, rightPointer;
		leftPointer = rightPointer = 0;
	
		while(leftPointer < left.size() || rightPointer < right.size()){
			if(leftPointer < left.size() && rightPointer < right.size()){
				if(left.get(leftPointer).getExTime() <= right.get(rightPointer).getExTime()){
					resultingList.add(left.get(leftPointer));
					leftPointer++;
				}else{
					resultingList.add(right.get(rightPointer));
					rightPointer++;
				}	
			}
			///Getting events from the original array if there are any left.
			else if(leftPointer < left.size()){
				resultingList.add(left.get(leftPointer));
				leftPointer++;
			}
			else if(rightPointer < right.size()){
				resultingList.add(right.get(rightPointer));
				rightPointer++;
				
			}
			
			
			
		}
		
		
		return resultingList;
		
	}












}
