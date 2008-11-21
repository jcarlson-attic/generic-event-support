package com.methodknowledgy.events.dispatch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.methodknowledgy.events.dispatch.impl.SimpleEventDispatcher;

public class SimpleEventDispatcherTest {

    private IEventDispatcher dispatcher;

    @Before
    public void initializeDspatcher() {
        dispatcher = new SimpleEventDispatcher();
    }

    @Test
    public void register_subscription() {
        MockSubscriber s = new MockSubscriber();
        dispatcher.register(s);
        Assert.assertTrue(dispatcher.getSubscriptions().contains(s));
    }
    
    @Test
    public void unregister_subscription() {
        MockSubscriber s = new MockSubscriber();
        dispatcher.register(s);
        dispatcher.unregister(s);
        Assert.assertFalse(dispatcher.getSubscriptions().contains(s));
    }
    
    @Test
    public void dispatches_event() {
        MockSubscriber s = new MockSubscriber();
        dispatcher.register(s);
        dispatcher.dispatch(null);
        Assert.assertTrue(s.handledEvent);
    }
    
}
