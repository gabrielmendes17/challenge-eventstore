package net.intelie.challenges;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventIteratorImpl implements EventIterator {
	
	private Event event = null;
	private boolean hasNext;
	Iterator<Event> eventAsIterator;
	EventStoreImpl eventStore;
	Map<String, Integer> ultimo;

	public EventIteratorImpl(EventStoreImpl eventStore) {
		this.eventStore = eventStore;
		ultimo = new HashMap<>();
	}

	@Override
	public void close() throws Exception {

		
	}

	@Override
	public boolean moveNext() {
		if (eventStore.getMapEvent().size() > 0) {
			event = eventStore.getMapEvent().get(eventStore.getCurrentEvent().type()).get(0);
			System.out.println(ultimo.get(event.type()));
			if (ultimo.containsKey(eventStore.getCurrentEvent().type())) {
				ultimo.put(eventStore.getCurrentEvent().type(), ultimo.get(eventStore.getCurrentEvent().type())+1);
				return hasNext = true;
			} else {
				ultimo.put(event.type(), 1);
				return hasNext = true;
			}
		} else {
			
			
			}
		
		return hasNext = false;
	}

	@Override
	public Event current() {
		if (event == null || !hasNext) throw new IllegalStateException();
		return event;
	}

	@Override
	public void remove() {
		eventAsIterator.remove();
	}

}
