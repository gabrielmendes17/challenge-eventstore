package net.intelie.challenges;

public class TaskAddElement implements Runnable {

	EventStoreImpl store;
	String type;
	
	public TaskAddElement(EventStoreImpl store, String type) {
		this.store = store;
		this.type = type;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event(type, i); 
			store.insert(event);
		}
	}

}
