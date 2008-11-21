package com.methodknowledgy.events.dispatch;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.methodknowledgy.events.Event;

public class EventDispatcherTest {

    @Before
    public void removeSubscriptions() {
        EventDispatcher.reset();
    }
    
    @Test
    public void setImplementation() {
        Subscriber s1 = new MockSubscriber();
        Subscriber s2 = new MockSubscriber();

        EventDispatcher.getInstance().register(s1);
        EventDispatcher.getInstance().register(s2);

        Collection<Subscriber> subscribers = EventDispatcher.getInstance()
                .getSubscriptions();
        Assert.assertTrue(subscribers.contains(s1));
        Assert.assertTrue(subscribers.contains(s2));
        Assert.assertEquals(2, subscribers.size());

        EventDispatcher.getInstance().setImplementation(
                new TestEventDispatcher());
        subscribers = EventDispatcher.getInstance().getSubscriptions();
        Assert.assertTrue(subscribers.contains(s1));
        Assert.assertTrue(subscribers.contains(s2));
        Assert.assertEquals(2, subscribers.size());
    }
    
    @Test
    public void dispatch() {
        MockSubscriber s = new MockSubscriber();
        EventDispatcher.getInstance().register(s);
        EventDispatcher.getInstance().dispatch(null);
        
        Assert.assertTrue(s.handledEvent);
    }

    private class TestEventDispatcher implements IEventDispatcher {

        private Set<Subscriber> subscriptions = new HashSet<Subscriber>();

        public <E extends Event<?, ?>> void dispatch(E event) {
        }

        public Collection<Subscriber> getSubscriptions() {
            return subscriptions;
        }

        public void register(Subscriber subscription) {
            subscriptions.add(subscription);
        }

        public void unregister(Subscriber subscription) {
            subscriptions.remove(subscription);
        }
        
        public void unregisterAllSubscribers() {
            subscriptions.clear();
        }

    }

}
