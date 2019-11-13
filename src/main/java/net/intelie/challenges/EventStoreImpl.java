package net.intelie.challenges;

import java.util.HashSet;
import java.util.Set;

public class EventStoreImpl implements EventStore
{
	
	private Set<Event> listEvent;
	
	

	public EventStoreImpl() {
		listEvent = new HashSet<>();
	}

	@Override
	public void insert(Event event) {
		listEvent.add(event);
	}

	@Override
	public void removeAll(String type) {
		listEvent.remove(type);
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		return null;
	}

	public Set<Event> getListEvent() {
		return listEvent;
	}

	public void setListEvent(Set<Event> listEvent) {
		this.listEvent = listEvent;
	}
	
}
