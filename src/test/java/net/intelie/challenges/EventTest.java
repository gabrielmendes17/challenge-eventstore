package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventTest {
    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event("some_type", 123L);

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
        assertEquals(123L, event.timestamp());
        assertEquals("some_type", event.type());
    }
    
    @Test
    public void eventCreate() throws Exception {
        EventStoreImpl eventStore = new EventStoreImpl();
        EventIteratorImpl eventIterator;
        
    	new Thread(new TaskAddElement(eventStore, "PAYMENT")).run();
    	new Thread(new TaskAddElement(eventStore, "BILLING")).run();
    	new Thread(new TaskAddElement(eventStore, "ORDINARY EXPENSES")).run();
    	
    	eventIterator = eventStore.query("PAYMENT", 10, 30);
    	eventIterator.setStore(eventStore);

    	eventIterator.moveNext();
    	eventIterator.current();
    	eventIterator.remove();
    	
    	assertEquals(19, eventIterator.getEvents().size());

    	
    	eventIterator.moveNext();
    	eventIterator.remove();
    	
    	assertEquals(18, eventIterator.getEvents().size());
    	
    	
    	assertEquals(98, eventStore.getMapEvent().get("PAYMENT").size());
    	
    	eventStore.removeAll("PAYMENT");
    	assertEquals(0, eventStore.getMapEvent().get("PAYMENT").size());
    	
    	
    	assertEquals(100, eventStore.getMapEvent().get("BILLING").size());
    }
}