package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        Event event1 = new Event("some_type1", 123L);
        Event event2 = new Event("some_type2", 123L);
        Event event3 = new Event("some_type3", 123L);
        EventStoreImpl eventStore = new EventStoreImpl();
        EventIteratorImpl eventIterator = new EventIteratorImpl(eventStore);
        eventStore.insert(event1);
        eventStore.insert(event2);
        eventStore.insert(event3);
        
        
        eventIterator.moveNext();
        eventIterator.moveNext();
        
        assertEquals(123L, event1.timestamp());
        assertEquals("some_type", event1.type());
    }
}