package net.intelie.challenges;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventStoreImpl implements EventStore {
	
	private Map<String, List<Event>> mapEvent;

	public EventStoreImpl() {
		mapEvent = new ConcurrentHashMap<>();
	}

	@Override
	public synchronized void insert(Event event) {
		if (!mapEvent.containsKey(event.type())) {
			List<Event> listEvent = new Vector<>();
			listEvent.add(event);
			mapEvent.put(event.type(), listEvent);
		} else {
			mapEvent.get(event.type()).add(event);
		}
	}

	@Override
	public synchronized void removeAll(String type) {
		mapEvent.get(type).clear();
	}

	@Override
	public EventIteratorImpl query(String type, long startTime, long endTime) {
		List<Event> result = mapEvent.get(type)
				.stream()
				.parallel()
				.filter(e -> e.timestamp() >= startTime && e.timestamp() < endTime)
				.collect(Collectors.toList());
		return new EventIteratorImpl(result);
	}

	public Map<String, List<Event>> getMapEvent() {
		return mapEvent;
	}

	public void setMapEvent(Map<String, List<Event>> mapEvent) {
		this.mapEvent = mapEvent;
	}
	
}
