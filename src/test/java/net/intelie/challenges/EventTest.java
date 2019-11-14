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
        Event event1 = new Event("PAGAMENTO", 123L);
        Event event11 = new Event("PAGAMENTO", 123L);
        Event event2 = new Event("RECEBIMENTO", 123L);
        Event event22 = new Event("RECEBIMENTO", 123L);
        Event event3 = new Event("FATURAMENTO", 123L);
        Event event33 = new Event("FATURAMENTO", 123L);
        EventStoreImpl eventStore = new EventStoreImpl();
        EventIteratorImpl eventIterator = new EventIteratorImpl(eventStore);
        eventStore.insert(event1);
        eventStore.insert(event11);
        eventIterator.moveNext();
        eventIterator.moveNext();
        eventIterator.current();
        eventStore.insert(event2);
        eventStore.insert(event22);
        eventIterator.moveNext();
        eventIterator.current();
        eventStore.insert(event3);
        eventStore.insert(event33);
        eventIterator.moveNext();
        eventIterator.moveNext();
        eventIterator.current();
        
        
        
        assertEquals(123L, event1.timestamp());
        assertEquals("some_type", event1.type());
    }
}