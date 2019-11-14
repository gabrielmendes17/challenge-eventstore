package net.intelie.challenges;

import java.util.HashSet;
import java.util.Set;

public class EventMemoryList {
	
	private Set<Event> listEvent;
	
	

	public EventMemoryList() {
		this.listEvent = new HashSet<>();;
	}

	public Set<Event> getListEvent() {
		return listEvent;
	}

	public void setListEvent(Set<Event> listEvent) {
		this.listEvent = listEvent;
	}

}
