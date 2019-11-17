package net.intelie.challenges;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		System.out.println(mapEvent);
	}

	@Override
	public EventIteratorImpl query(String type, long startTime, long endTime) {
		Instant before = Instant.now();
		List<Event> resultado = mapEvent.get(type)
				.stream()
				.parallel()
				.filter(e -> e.timestamp() >= startTime && e.timestamp() < endTime)
				.collect(Collectors.toList());
		Instant after = Instant.now();
		Duration duration = Duration.between(before, after);
		System.out.println("time = " + duration.toMillis() + " ms ");
		System.out.println(resultado);
		return new EventIteratorImpl(resultado);
	}

	public Map<String, List<Event>> getMapEvent() {
		return mapEvent;
	}

	public void setMapEvent(Map<String, List<Event>> mapEvent) {
		this.mapEvent = mapEvent;
	}
	
}
