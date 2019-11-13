package net.intelie.challenges;

import java.util.Iterator;

public class EventIteratorImpl implements EventIterator {
	
	private EventStoreImpl eventStore;
	private Event event = null;
	private boolean hasNext;
	Iterator<Event> eventAsIterator;

	public EventIteratorImpl(EventStoreImpl store) {
		this.eventStore = store;
		this.eventAsIterator = store.getListEvent().iterator();
	}

	@Override
	public void close() throws Exception {

		
	}

	@Override
	public boolean moveNext() {
		hasNext = false;
		 while (hasNext = eventAsIterator.hasNext()) {
			 this.event = eventAsIterator.next();
			 System.out.println(this.event);
			 return hasNext;
		 }
		return hasNext;
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
