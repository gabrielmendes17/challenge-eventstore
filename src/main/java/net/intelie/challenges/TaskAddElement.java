package net.intelie.challenges;

public class TaskAddElement implements Runnable {

	EventStoreImpl store;
	String tipo;
	
	public TaskAddElement(EventStoreImpl store, String tipo) {
		this.store = store;
		this.tipo = tipo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event(tipo, i); 
			store.insert(event);
		}
	}

}
