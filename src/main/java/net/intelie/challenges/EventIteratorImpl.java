package net.intelie.challenges;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventIteratorImpl implements EventIterator {
	
	private Event currentEvent = null;
	private List<Event> events;
	private AtomicInteger position;
	boolean hasNext = false;
	private EventStoreImpl store;
	
	public EventIteratorImpl(List<Event> events) {
		position = new AtomicInteger();
		this.events = events;
	}

	@Override
	public void close() throws Exception {

		
	}

	@Override
	public synchronized boolean moveNext() {
		if (position.get() >= events.size() || events.get(position.get()) == null) {
            return hasNext = false;
        } else {
        	currentEvent = events.get(position.get());
			position.incrementAndGet();
            return hasNext = true;
        }
	}

	@Override
	public Event current() {
		if (hasNext) {
			return currentEvent;
		} 
		throw new IllegalStateException();
	}

	@Override
	public synchronized void remove() {
		if (hasNext) {
			events.remove(currentEvent);
			store.getMapEvent().get(currentEvent.type()).remove(currentEvent);
			System.out.println(store);
		} else {
			throw new IllegalStateException();
		}
	}

	public EventStoreImpl getStore() {
		return store;
	}

	public void setStore(EventStoreImpl store) {
		this.store = store;
	}

	public List<Event> getEvents() {
		return events;
	}
	
}
