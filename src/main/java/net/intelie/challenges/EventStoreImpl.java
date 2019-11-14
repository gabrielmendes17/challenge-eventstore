package net.intelie.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventStoreImpl implements EventStore
{
	
	private List<Event> listEvent;
	private Set<Event> newList;
	private Map<String, List<Event>> mapEvent;
	Event currentEvent;
	
	

	public EventStoreImpl() {
		mapEvent = new HashMap<>();
	}

	@Override
	public void insert(Event event) {
		currentEvent = event;
		if (!mapEvent.containsKey(event.type())) {
			List<Event> listEvent = new ArrayList<>();
			listEvent.add(event);
			mapEvent.put(event.type(), listEvent);
		} else {
			mapEvent.get(event.type()).add(event);
		}
	}

	@Override
	public void removeAll(String type) {
		listEvent.remove(type);
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		return null;
	}

	public List<Event> getListEvent() {
		return listEvent;
	}

	public void setListEvent(List<Event> listEvent) {
		this.listEvent = listEvent;
	}

	public Map<String, List<Event>> getMapEvent() {
		return mapEvent;
	}

	public void setMapEvent(Map<String, List<Event>> mapEvent) {
		this.mapEvent = mapEvent;
	}

	public Event getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;
	}
	
}
